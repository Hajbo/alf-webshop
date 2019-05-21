import React from 'react';
import { Route, Redirect } from 'react-router-dom';

import { authenticationService } from '../_services';

export const AdminRoute = ({ component: Component, ...rest }) => (
    <Route {...rest} render={props => {
        const currentUser = authenticationService.currentUserValue;
        if (currentUser !== 'undefined' && currentUser !== null && currentUser.role === 'ROLE_ADMIN') {
            if ( authenticationService.checkIfTokenExpired(currentUser) ) {
                try {
                    authenticationService.refreshToken();
                } catch(err) {
                    console.log(err);
                    // Error while refreshing the token, need to log in again
                    return <Redirect to={{ pathname: '/login', state: { from: props.location } }} />
                }
            }
        } else if (!currentUser) {
            // not logged in so redirect to login page with the return url
            return <Redirect to={{ pathname: '/login', state: { from: props.location } }} />
        }

        // authorised so return component
        return <Component {...props} />
    }} />
)