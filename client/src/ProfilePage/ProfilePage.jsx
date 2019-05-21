import React from "react";

import { authenticationService } from "../_services";
import { getUser } from "../_helpers";

class ProfilePage extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            currentUser: authenticationService.currentUserValue,
            data: null
        };
    }

    componentDidMount() {
        let newState = Object.assign({}, this.state);
        getUser(this.state.currentUser).then(
            data => {
                if(this.state.currentUser.role === "ROLE_ADMIN") {
                    for (let user of data) {
                        if(user.name === this.state.currentUser.username){
                            newState.data = user;
                            break;
                        }
                    }
                } else {
                    newState.data = data;
                }
                this.setState(newState);
            }
        );  
    }

    render() {
        const { currentUser, data } = this.state;
        return (
            <div>
                <h1>Hi {currentUser.username}!</h1>
                <br />
                <h3>Info:</h3>
                {data && (
                    <ul>
                        <li>Your role: {currentUser.role}</li>
                    </ul>
                )}
            </div>
        );
    }
}

export { ProfilePage };
