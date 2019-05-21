import React from "react";
import ReactTable from "react-table";

import { authenticationService } from "../_services";
import { getUser, deleteUser } from "../_helpers/user-helper";

import "react-table/react-table.css";

class AdminUsersPage extends React.Component {
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
                newState.data = data;
                this.setState(newState);
            }
        );   
    }

    render() {
        const { data, currentUser } = this.state;
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
                                Header: "Users",
                                columns: [
                                    {
                                        Header: "#",
                                        accessor: "id",
                                        width: 50,
                                        filterable: false
                                    },
                                    {
                                        Header: "Name",
                                        accessor: "name",
                                        maxWidth: 100
                                    },
                                    {
                                        Header: "",
                                        accessor: "delete",
                                        filterable: false,
                                        Cell: ({ row }) => (
                                            <button
                                                onClick={() =>
                                                    deleteUser(row.id, currentUser).then(
                                                        window.location.reload()
                                                    )
                                                }
                                            >
                                                Delete
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

export { AdminUsersPage };
