// function getItems() {
//     const requestOptions = {
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/json'
//         },
//         body: JSON.stringify({
//             'name': username,
//             'password': password
//         })
//     };
//     return fetch(authApiRegisterUrl, requestOptions)
//         .then(handleResponse)
//         .then(response => {
//             console.log(`Added for ${username} resulted in: ${response}`);
//             return response;
//         });
// };

// export { getItems };