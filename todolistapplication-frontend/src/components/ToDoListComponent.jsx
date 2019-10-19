import React, {Component} from 'react'
import {ErrorMessage, Field, Form, Formik} from 'formik';
import TodoDataService from '../api/ToDoListService.js'
import AuthenticationService from '../authentication/AuthenticationService.js'

class ToDoListComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            todoListName: '',
        }

        this.onSubmit = this.onSubmit.bind(this)
        this.validate = this.validate.bind(this)

    }

    componentDidMount() {

        if (this.state.id === -1) {
            return
        }

        let username = AuthenticationService.getLoggedInUserName()

        TodoDataService.getTodoList(username, this.state.id)
            .then(response => this.setState({
                todoListName: response.data.todoListName
            }))
    }

    validate(values) {
        let errors = {}
        if (!values.todoListName) {
            errors.todoListName = 'Enter a Description'
        } else if (values.todoListName.length < 5) {
            errors.todoListName = 'Enter atleast 5 Characters in Description'
        }

        return errors

    }

    onSubmit(values) {
        let username = AuthenticationService.getLoggedInUserName()

        let todo = {
            id: this.state.id,
            todoListName: values.todoListName
        }

        if (this.state.id === -1) {
            TodoDataService.createTodoList(username, todo)
                .then(() => this.props.history.push('/todolists'))
        } else {
            TodoDataService.updateTodoList(username, this.state.id, todo)
                .then(() => this.props.history.push('/todolists'))
        }

        console.log(values);
    }

    render() {

        let {todoListName} = this.state
        //let targetDate = this.state.targetDate

        return (
            <div>
                <h1>Todo</h1>
                <div className="container">
                    <Formik
                        initialValues={{ todoListName }}
                        onSubmit={this.onSubmit}
                        validateOnChange={false}
                        validateOnBlur={false}
                        validate={this.validate}
                        enableReinitialize={true}
                    >
                        {
                            (props) => (
                                <Form>
                                    <ErrorMessage name="todoListName" component="div"
                                                  className="alert alert-warning"/>
                                    <fieldset className="form-group">
                                        <label>todoListName</label>
                                    </fieldset>
                                    <button className="btn btn-success" type="submit">Save</button>
                                </Form>
                            )
                        }
                    </Formik>

                </div>
            </div>
        )
    }
}

export default ToDoListComponent