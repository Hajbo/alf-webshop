import React from "react";
import ReactTable from "react-table";

import { authenticationService } from "../_services";
import { checkout, removeItemFromCart, getCart} from "../_helpers";


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
        getCart(this.state.currentUser).then(
            data => {
                newState.data = data;
                this.setState(newState);
            }
        );   
    }

    render() {
        const { data, currentUser } = this.state;
        console.log(data);
        return (
            <div>
                {!data && <p>It seems like your cart is empty! :O</p>}
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
                                        accessor: "id",
                                        width: 50,
                                        filterable: false
                                    },
                                    {
                                        Header: "Category",
                                        accessor: "category",
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
                                                    removeItemFromCart(row.id, currentUser)
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
