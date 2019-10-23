import React, { Component } from 'react'
import ToDoListService from '../api/ToDoListService.js'
import AuthenticationService from '../authentication/AuthenticationService.js'

class ListTodosComponent extends Component {
    constructor(props) {
        console.log('constructor')
        super(props)
        this.state = {
            todos: [],
            message: null
        }
        this.deleteTodoClicked = this.deleteTodoClicked.bind(this)
        this.updateTodoClicked = this.updateTodoClicked.bind(this)
        this.addTodoClicked = this.addTodoClicked.bind(this)
        this.refreshTodos = this.refreshTodos.bind(this)
    }

    shouldComponentUpdate(nextProps, nextState) {
        console.log('shouldComponentUpdate')
        console.log(nextProps)
        console.log(nextState)
        return true
    }

    componentDidMount() {
        console.log('componentDidMount')
        this.refreshTodos();
        console.log(this.state)
    }

    refreshTodos() {
        let username = AuthenticationService.getLoggedInUserName()
        ToDoListService.getAllToDoLists(username)
            .then(
                response => {
                    //console.log(response);
                    this.setState({ todos: response.data })
                }
            )
    }

    deleteTodoClicked(id) {
        let username = AuthenticationService.getLoggedInUserName()
        //console.log(id + " " + username);
        ToDoListService.deleteTodoList(username, id)
            .then(
                response => {
                    this.setState({ message: `Delete of todo ${id} Successful` })
                    this.refreshTodos()
                }
            )
    }

    addTodoClicked() {
        let username = AuthenticationService.getLoggedInUserName()
        this.props.history.push(`${username}/todos/-1`)
    }

    updateTodoClicked(id) {
        let username = AuthenticationService.getLoggedInUserName()
        this.props.history.push(`${username}/todos/${id}`)
    }
    showAllTodoItemsOfList(id){
        let username = AuthenticationService.getLoggedInUserName()
        this.props.history.push(`${username}/${id}/todos`)
    }

    render() {
        console.log('render')
        this.refreshTodos();
        return (
            <div>
                <h1>List Todos</h1>
                {this.state.message && <div class="alert alert-success">{this.state.message}</div>}
                <div className="container">
                    <div className="row">
                        <button className="btn btn-success" onClick={this.addTodoClicked}>Add</button>
                    </div>
                    <table className="table">
                        <thead>
                        <tr>
                            <th>Description</th>
                            <th>List Items</th>
                            <th>Update</th>
                            <th>Delete</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.todos.map(
                                todo =>
                                    <tr key={todo.id}>
                                        <td>{todo.todoListName}</td>
                                        <td><button className="btn btn-success" onClick={() => this.showAllTodoItemsOfList(todo.id)}>List Items</button></td>
                                        <td><button className="btn btn-warning" onClick={() => this.updateTodoClicked(todo.id)}>Update</button></td>
                                        <td><button className="btn btn-danger" onClick={() => this.deleteTodoClicked(todo.id)}>Delete</button></td>
                                    </tr>
                            )
                        }
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}

export default ListTodosComponent