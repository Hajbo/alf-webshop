import React from "react";
import { Formik, Field, Form, ErrorMessage } from "formik";
import * as Yup from "yup";

import { authenticationService } from "../_services";
import { addItem } from "../_helpers";

class AddItemPage extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            currentUser: authenticationService.currentUserValue
        };
    }

    render() {
        const { currentUser } = this.state;
        return (
            <div>
                <h2>Sell something</h2>
                <Formik
                    initialValues={{
                        price: "",
                        category: "",
                        description: ""
                    }}
                    validationSchema={Yup.object().shape({
                        price: Yup.string().required("Price is required"),
                        category: Yup.string().required("Category is required"),
                        description: Yup.string().required("Description is required")
                    })}
                    onSubmit={(
                        { price, category, description },
                        { setStatus, setSubmitting, resetForm }
                    ) => {
                        setStatus();
                        addItem(price, category, description, currentUser).then(
                            resp => {
                                this.props.history.push('/additem');
                                setSubmitting(false);
                                resetForm();
                            },
                            error => {
                                setSubmitting(false);
                                setStatus(error.toString());
                            }
                        );
                    }}
                    render={({ errors, status, touched, isSubmitting }) => (
                        <Form>
                            <div className="form-group">
                                <label htmlFor="price">Price</label>
                                <Field
                                    name="price"
                                    type="text"
                                    className={
                                        "form-control" +
                                        (errors.price && touched.price
                                            ? " is-invalid"
                                            : "")
                                    }
                                />
                                <ErrorMessage
                                    name="price"
                                    component="div"
                                    className="invalid-feedback"
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="category">Category</label>
                                <Field
                                    name="category"
                                    type="text"
                                    className={
                                        "form-control" +
                                        (errors.category && touched.category
                                            ? " is-invalid"
                                            : "")
                                    }
                                />
                                <ErrorMessage
                                    name="category"
                                    component="div"
                                    className="invalid-feedback"
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="description">Description</label>
                                <Field
                                    name="description"
                                    type="text"
                                    className={
                                        "form-control" +
                                        (errors.description && touched.description
                                            ? " is-invalid"
                                            : "")
                                    }
                                />
                                <ErrorMessage
                                    name="description"
                                    component="div"
                                    className="invalid-feedback"
                                />
                            </div>
                            <div className="form-group">
                                <button
                                    type="submit"
                                    className="btn btn-primary"
                                    disabled={isSubmitting}
                                >
                                    Post item
                                </button>
                                {isSubmitting && (
                                    <img alt="" src="data:image/gif;base64,R0lGODlhEAAQAPIAAP///wAAAMLCwkJCQgAAAGJiYoKCgpKSkiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAEAAQAAADMwi63P4wyklrE2MIOggZnAdOmGYJRbExwroUmcG2LmDEwnHQLVsYOd2mBzkYDAdKa+dIAAAh+QQJCgAAACwAAAAAEAAQAAADNAi63P5OjCEgG4QMu7DmikRxQlFUYDEZIGBMRVsaqHwctXXf7WEYB4Ag1xjihkMZsiUkKhIAIfkECQoAAAAsAAAAABAAEAAAAzYIujIjK8pByJDMlFYvBoVjHA70GU7xSUJhmKtwHPAKzLO9HMaoKwJZ7Rf8AYPDDzKpZBqfvwQAIfkECQoAAAAsAAAAABAAEAAAAzMIumIlK8oyhpHsnFZfhYumCYUhDAQxRIdhHBGqRoKw0R8DYlJd8z0fMDgsGo/IpHI5TAAAIfkECQoAAAAsAAAAABAAEAAAAzIIunInK0rnZBTwGPNMgQwmdsNgXGJUlIWEuR5oWUIpz8pAEAMe6TwfwyYsGo/IpFKSAAAh+QQJCgAAACwAAAAAEAAQAAADMwi6IMKQORfjdOe82p4wGccc4CEuQradylesojEMBgsUc2G7sDX3lQGBMLAJibufbSlKAAAh+QQJCgAAACwAAAAAEAAQAAADMgi63P7wCRHZnFVdmgHu2nFwlWCI3WGc3TSWhUFGxTAUkGCbtgENBMJAEJsxgMLWzpEAACH5BAkKAAAALAAAAAAQABAAAAMyCLrc/jDKSatlQtScKdceCAjDII7HcQ4EMTCpyrCuUBjCYRgHVtqlAiB1YhiCnlsRkAAAOwAAAAAAAAAAAA==" />
                                )}
                            </div>
                            {status && (
                                <div className={"alert alert-danger"}>
                                    {status}
                                </div>
                            )}
                        </Form>
                    )}
                />
            </div>
        );
    }
}

export { AddItemPage };
