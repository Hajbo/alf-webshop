import React from "react";

import { authenticationService } from "../_services";

class ProfilePage extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            currentUser: authenticationService.currentUserValue,
            items: null
        };
    }

    componentDidMount() {
        let newState = Object.assign({}, this.state);
        newState.items = [
            {
                id_: 1,
                name: "alma",
                price: 100,
                description: "This is an apple"
            },
            {
                id_: 2,
                name: "banana",
                price: 200,
                description: "This is a banana"
            }
        ];
        this.setState(newState);
        //userService.getAll().then(users => this.setState({ users }));
    }

    render() {
        const { currentUser, items } = this.state;
        return (
            <div>
                <h1>Hi {currentUser.username}!</h1>
                <br />
                <h3>Items for sale:</h3>
                {items && (
                    <ul>
                        {items.map(item => (
                            <li key={item.id_}>
                                {item.name} costs {item.price}.{" "}
                                {item.description}
                            </li>
                        ))}
                    </ul>
                )}
            </div>
        );
    }
}

export { ProfilePage };
