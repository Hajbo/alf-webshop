import { BehaviorSubject } from 'rxjs';

import { authApiTokenUrl, authApiRefreshUrl } from '../config/urlconfig';
import { apiUsername, apiPassword } from '../config/authcredentials';
import { handleResponse } from '../_helpers';

var jwt = require('jsonwebtoken');
const currentUserSubject = new BehaviorSubject(JSON.parse(localStorage.getItem('currentUser')));

export const authenticationService = {
    login,
    logout,
    checkIfTokenExpired,
    refreshToken,
    currentUser: currentUserSubject.asObservable(),
    get currentUserValue () { return currentUserSubject.value }
};

function login(username, password) {
    var encodedAuth = new Buffer(`${apiUsername}:${apiPassword}`).toString('base64');
    const requestOptions = {
        method: 'POST',
        headers: { 
            'Authorization': `Basic ${encodedAuth}`
         }
    };

    return fetch(`${authApiTokenUrl}&username=${username}&password=${password}`, requestOptions)
        .then(handleResponse)
        .then(user => {
            // store user details and jwt token in local storage to keep user logged in between page refreshes
            var currentUser = {
                'username': username,
                'tokendata': user
            }
            localStorage.setItem('currentUser', JSON.stringify(currentUser));
            currentUserSubject.next(user);

            return user;
        });
};

function checkIfTokenExpired() {
    var user = localStorage.getItem('currentUser');
    if(user !== 'undefined') {
        var token = user.tokendata;
        var decodedToken = jwt.decode(token, {complete: true});
        var dateNow = new Date();
        return (decodedToken.exp < dateNow.getTime());
    } else {
        return false;
    }
};

function refreshToken() {
    var encodedAuth = new Buffer(`${apiUsername}:${apiPassword}`).toString('base64');
    const requestOptions = {
        method: 'POST',
        headers: { 
            'Authorization': `Basic ${encodedAuth}`
         }
    };
    var refreshToken = localStorage.getItem('currentUser').tokendata.refresh_token;

    fetch(`${authApiRefreshUrl}&refresh_token=${refreshToken}`, requestOptions)
        .then(handleResponse)
        .then(user => {
            var currentUser = localStorage.getItem('currentUser');
            var updatedUser = {
                'username': currentUser.userName,
                'tokendata': user
            }
            localStorage.setItem('currentUser', JSON.stringify(updatedUser));
            currentUserSubject.next(user);
        });
};

function logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    currentUserSubject.next(null);
}