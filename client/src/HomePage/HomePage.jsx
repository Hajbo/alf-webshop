import React from 'react';

import { authenticationService } from '../_services';

class HomePage extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            currentUser: authenticationService.currentUserValue,
            users: null
        };
    }

    componentDidMount() {
        //userService.getAll().then(users => this.setState({ users }));
    }

    render() {
        return (
            <div>
                <h1>Hello there!</h1>
                <h3>Feel free register and take a look at the shop!</h3>

            </div>
        );
    }
}

export { HomePage };