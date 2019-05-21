import React from "react";
import { Router, Route, Link } from "react-router-dom";

import { history } from "../_helpers/history";
import { authenticationService } from "../_services/authentication.service";
import { PrivateRoute, AdminRoute } from "../_components";
import { HomePage } from "../HomePage";
import { LoginPage } from "../LoginPage";
import { RegisterPage } from "../RegisterPage";
import { ProfilePage } from "../ProfilePage";
import { ShopPage } from "../ShopPage";
import { CartPage } from "../CartPage";
import { AddItemPage } from "../AddItemPage";
import { AdminUsersPage } from "../AdminPages";

import "./app.css";

class App extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            currentUser: null
        };
    }

    componentDidMount() {
        authenticationService.currentUser.subscribe(x =>
            this.setState({ currentUser: x })
        );
    }

    logout() {
        authenticationService.logout();
        history.push("/login");
    }

    render() {
        const { currentUser } = this.state;
        return (
            <Router history={history}>
                <div>
                    <nav className="navbar navbar-expand navbar-dark bg-dark">
                        <div className="navbar-nav">
                            <Link to="/" className="nav-item nav-link">
                                Home
                            </Link>
                            {!currentUser && (
                                <Link to="/login" className="nav-item nav-link">
                                    Login
                                </Link>
                            )}
                            {!currentUser && (
                                <Link
                                    to="/register"
                                    className="nav-item nav-link"
                                >
                                    Register
                                </Link>
                            )}
                            {currentUser && (
                                <Link
                                    to="/shop"
                                    className="nav-item nav-link"
                                >
                                    Shop
                                </Link>
                            )}
                            {currentUser && (
                                <Link
                                    to="/profile"
                                    className="nav-item nav-link"
                                >
                                    Profile
                                </Link>
                            )}
                            {currentUser && (
                                <Link
                                    to="/cart"
                                    className="nav-item nav-link"
                                >
                                    Cart
                                </Link>
                            )}
                            {currentUser && (
                                <Link
                                    to="/additem"
                                    className="nav-item nav-link"
                                >
                                    Post item
                                </Link>
                            )}
                            {currentUser && (
                                <Link
                                    to="/users"
                                    className="nav-item nav-link"
                                >
                                    Users
                                </Link>
                            )}
                            {currentUser && (
                                <a
                                    onClick={this.logout}
                                    className="nav-item nav-link"
                                >
                                    Logout
                                </a>
                            )}
                        </div>
                    </nav>

                    <div className="jumbotron">
                        <div className="container">
                            <div className="row">
                                <div className="col-md-12">
                                    <Route
                                        exact
                                        path="/"
                                        component={HomePage}
                                    />
                                    <PrivateRoute
                                        exact
                                        path="/profile"
                                        component={ProfilePage}
                                    />
                                    <PrivateRoute
                                        exact
                                        path="/shop"
                                        component={ShopPage}
                                    />
                                    <PrivateRoute
                                        exact
                                        path="/additem"
                                        component={AddItemPage}
                                    />
                                    <PrivateRoute
                                        exact
                                        path="/cart"
                                        component={CartPage}
                                    />
                                    <AdminRoute
                                        exact
                                        path="/users"
                                        component={AdminUsersPage}
                                    />
                                    <Route
                                        path="/login"
                                        component={LoginPage}
                                    />
                                    <Route
                                        path="/register"
                                        component={RegisterPage}
                                    />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </Router>
        );
    }
}

export { App };
