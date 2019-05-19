function addToCart(rowindex, user) {
    const requestOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            'name': username,
            'password': password
        })
    };
    return fetch(authApiRegisterUrl, requestOptions)
        .then(handleResponse)
        .then(response => {
            console.log(`Added for ${username} resulted in: ${response}`);
            return response;
        });
};

function checkout(rowindex, user) {
    const requestOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            'name': username,
            'password': password
        })
    };
    return fetch(authApiRegisterUrl, requestOptions)
        .then(handleResponse)
        .then(response => {
            console.log(`Registration for ${username} resulted in: ${response}`);
            return response;
        });
};

function removeItemFromCart(rowindex, user) {
    const requestOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            'name': username,
            'password': password
        })
    };
    return fetch(authApiRegisterUrl, requestOptions)
        .then(handleResponse)
        .then(response => {
            console.log(`Registration for ${username} resulted in: ${response}`);
            return response;
        });
};

function getCart(user) {
    const requestOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            'name': username,
            'password': password
        })
    };
    return fetch(authApiRegisterUrl, requestOptions)
        .then(handleResponse)
        .then(response => {
            console.log(`Added for ${username} resulted in: ${response}`);
            return response;
        });
};

export { addToCart, checkout, removeItemFromCart };
