import React from "react";
import ReactTable from "react-table";

import { authenticationService } from "../_services";
import { addToCart } from "../_helpers";
import "react-table/react-table.css";

class ShopPage extends React.Component {
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
                category: "alma",
                price: 100,
                description:
                    "This is an appleasddasdssadappleasddasdssadappleasddasdssadappleasddasdssadappleasddasdssadappleasddasdssadappleasddasdssadappleasddasdssadappleasddasdssad"
            },
            {
                id_: 2,
                category: "banana",
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
                                        accessor: "add",
                                        filterable: false,
                                        Cell: ({ row }) => (
                                            <button
                                                onClick={() =>
                                                    addToCart(row.id_, currentUser)
                                                }
                                            >
                                                Add to cart
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
            </div>
        );
    }
}

export { ShopPage };
