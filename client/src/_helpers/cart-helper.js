import { handleResponse } from '../_helpers';
import { resourceApiCart } from '../config/urlconfig';

function addToCart(itemId, user) {
    console.log(resourceApiCart);
    var username = user.username;
    const requestOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${user.access_token}`
        },
        body: JSON.stringify({
            'id': itemId
        })
    };
    return fetch(resourceApiCart, requestOptions)
        .then(handleResponse)
        .then(response => {
            console.log(`Add to cart itemid ${itemId} for ${username} resulted in: ${response.status}`);
            return response;
        });
};

function removeItemFromCart(itemId, user) {
    var username = user.username;
    const requestOptions = {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${user.access_token}`
        },
        body: JSON.stringify({
            'id': itemId
        })
    };
    return fetch(resourceApiCart, requestOptions)
        .then(handleResponse)
        .then(response => {
            console.log(`Delete itemid ${itemId} from cart for ${username} resulted in: ${response.status}`);
            return response;
        });
};

function checkout(user) {
    var username = user.username;
    const requestOptions = {
        method: 'PUT',
        headers: {
            'Authorization': `Bearer ${user.access_token}`
        },
        body: JSON.stringify({
        })
    };
    return fetch(resourceApiCart, requestOptions)
        .then(handleResponse)
        .then(response => {
            console.log(`Checkout cart for ${username} resulted in: ${response.status}`);
            return response;
        });
};

function getCart(user) {
    var username = user.username;
    const requestOptions = {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${user.access_token}`
        }
    };
    return fetch(resourceApiCart, requestOptions)
        .then(handleResponse)
        .then(response => {
            console.log(`Get cart for ${username} resulted in: ${response.status}`);
            return response;
        });
};

export { addToCart, checkout, removeItemFromCart, getCart };
