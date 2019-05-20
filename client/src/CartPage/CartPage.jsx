import React from "react";
import ReactTable from "react-table";

import { authenticationService } from "../_services";
import { checkout, removeItemFromCart } from "../_helpers";

import "react-table/react-table.css";

class CartPage extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            currentUser: authenticationService.currentUserValue,
            data: null
        };
    }

    componentDidMount() {
        let newState = Object.assign({}, this.state);
        newState.data = [
            {
                id_: 1,
                name: "alma",
                price: 100,
                description:
                    "This is an apple"
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
        const { data, currentUser } = this.state;
        console.log(data);
        return (
            <div>
                {data && (
                    <ReactTable
                        data={data}
                        filterable
                        defaultFilterMethod={(filter, row) =>
                            String(row[filter.id]).includes(filter.value)
                        }
                        columns={[
                            {
                                Header: "Shop items",
                                columns: [
                                    {
                                        Header: "#",
                                        accessor: "id_",
                                        width: 50,
                                        filterable: false
                                    },
                                    {
                                        Header: "Name",
                                        accessor: "name",
                                        maxWidth: 100
                                    },
                                    {
                                        Header: "Short description",
                                        accessor: "description",
                                        width: 300,
                                        maxWidth: 500
                                    },
                                    {
                                        Header: "Price",
                                        accessor: "price",
                                        width: 100
                                    },
                                    {
                                        Header: "",
                                        accessor: "remove",
                                        filterable: false,
                                        Cell: ({ row }) => (
                                            <button
                                                onClick={() =>
                                                    removeItemFromCart(row.id_, currentUser)
                                                }
                                            >
                                                Remove from cart
                                            </button>
                                        )
                                    }
                                ]
                            }
                        ]}
                        defaultPageSize={10}
                        className="-striped -highlight"
                    />
                )}
                <button
                onClick={() =>
                    checkout(currentUser)
                }
                >
                    Checkout
                </button>
            </div>
        );
    }
}

export { CartPage };
