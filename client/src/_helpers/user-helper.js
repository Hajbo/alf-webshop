import { resourceApiUsers } from '../config/urlconfig';
import { handleResponse } from './handle-response';

function resourceApiRegister(username, email, access_token) {
    const requestOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${access_token}`
        },
        body: JSON.stringify({
            'name': username,
            'email': email
        })
    };
    return fetch(resourceApiUsers, requestOptions)
        .then(handleResponse)
        .then(response => {
            console.log(`Resource API register for ${username} resulted in: ${response}`);
            return response;
        });
};

function deleteUser(id, currentUser) {
    const requestOptions = {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${currentUser.tokendata.access_token}`
        },
        body: JSON.stringify({
            'id': id
        })
    };
    return fetch(resourceApiUsers, requestOptions)
        .then(handleResponse)
        .then(response => {
            console.log(`DELETE API register for ID ${id} resulted in: ${response}`);
            return response;
        });
};

function getUser(user) {
    var username = user.username;
    const requestOptions = {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${user.tokendata.access_token}`
        }
    };
    return fetch(resourceApiUsers, requestOptions)
        .then(handleResponse)
        .then(response => {
            console.log(`Resource API GET for ${username} resulted in: ${response}`);
            return response;
        });
}

export { resourceApiRegister, getUser, deleteUser };
