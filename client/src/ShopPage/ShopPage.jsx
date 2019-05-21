import React from "react";
import ReactTable from "react-table";

import { authenticationService } from "../_services";
import { addToCart, getItems } from "../_helpers";

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
        getItems(this.state.currentUser).then(
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
                {!data && <p>The shop is empty :(</p>}
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
                                        accessor: "add",
                                        filterable: false,
                                        Cell: ({ row }) => (
                                            <button
                                                onClick={() =>
                                                    addToCart(row.id, currentUser)
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
