import { BehaviorSubject } from 'rxjs';

import { authApiTokenUrl } from '../config/urlconfig';
import { apiUsername, apiPassword } from '../config/authcredentials';
import { handleResponse } from '../helpers/handle-response';

const currentUserSubject = new BehaviorSubject(JSON.parse(localStorage.getItem('currentUser')));

export const authenticationService = {
    login,
    logout,
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
            localStorage.setItem('currentUser', JSON.stringify(user));
            currentUserSubject.next(user);

            return user;
        });
}

function logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    currentUserSubject.next(null);
}