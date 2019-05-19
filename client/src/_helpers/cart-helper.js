function addToCart(row, user) {
    console.log(row);
    console.log(user);
};

function checkout(user) {
    console.log('CHECKOUT');
};

function removeItemFromCart(row, user) {
    console.log('REMOVE ITEM FROM CART');
};

export { addToCart, checkout, removeItemFromCart };
