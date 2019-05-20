import { handleResponse } from '../_helpers';
import { resourceApiItems } from '../config/urlconfig';

function getItems(user) {
    const requestOptions = {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${user.tokendata.access_token}`
        },
    };
    return fetch(resourceApiItems, requestOptions)
        .then(handleResponse)
        .then(response => {
            console.log(`GET shop items resulted in: ${response}`);
            return response;
        });
};

function addItem(price, category, description, user) {
    const requestOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${user.tokendata.access_token}`
        },
        body: {
            'price': price,
            'category': category,
            'description': description
        }
    };
    return fetch(resourceApiItems, requestOptions)
        .then(handleResponse)
        .then(response => {
            console.log(`POST item resulted in: ${response}`);
            return response;
        });
};

export { getItems, addItem };