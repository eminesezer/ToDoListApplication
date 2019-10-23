import React, {Component} from 'react'
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import AuthenticatedRoute from '../authentication/AuthenticatedRoute.jsx'
import LoginComponent from './LoginComponent.jsx'
// import ListTodosComponent from './ListTodosComponent.jsx'
import ErrorComponent from './ErrorComponent.jsx'
import HeaderComponent from './HeaderComponent.jsx'
import FooterComponent from './FooterComponent.jsx'
import LogoutComponent from './LogoutComponent.jsx'
import ListTodosComponent from "./ListTodosComponent";
import ToDoListComponent from "./ToDoListComponent";

// import TodoComponent from './TodoComponent.jsx'

class TodoApp extends Component {
    render() {
        return (
            <div className="TodoApp">
                <Router>
                    <>
                        <HeaderComponent/>
                        <Switch>
                            <Route path="/" exact component={LoginComponent}/>
                            <Route path="/login" component={LoginComponent}/>
                            <AuthenticatedRoute path="/todolists" component={ListTodosComponent}/>
                            <AuthenticatedRoute path="/todolists:id" component={ToDoListComponent}/>
                            {/*<AuthenticatedRoute path="/todos/:id" component={TodoComponent}/>
                            <AuthenticatedRoute path="/todos" component={ListTodosComponent}/>*/}
                            <AuthenticatedRoute path="/logout" component={LogoutComponent}/>

                            {<Route component={ErrorComponent}/>}
                        </Switch>
                        <FooterComponent/>
                    </>
                </Router>
                {/*<LoginComponent/>
                <WelcomeComponent/>*/}
            </div>
        )
    }
}

export default TodoApp