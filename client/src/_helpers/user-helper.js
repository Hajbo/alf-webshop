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

export { resourceApiRegister };