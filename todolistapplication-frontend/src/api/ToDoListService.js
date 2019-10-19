import axios from 'axios'
import {API_URL} from '../Constants'

class ToDoListService {

    getAllToDoLists(username) {
        return axios.get(`${API_URL}/${username}/todos`);
    }

    deleteTodoList(username, todoId) {
        //console.log('executed service')
        return axios.delete(`${API_URL}/${username}/delete/{todoId}`);
    }

    updateTodoList(username, todoId, todoList) {
        //console.log('executed service')
        return axios.put(`${API_URL}/${username}/update/${todoId}`, todoList);
    }

    createTodoList(username, todoList) {
        //console.log('executed service')
        return axios.post(`${API_URL}/${username}/save`, todoList);
    }

    getTodoList(username, id) {
        return axios.get(`${API_URL}/${username}/todos/${id}`);
    }
}

export default new ToDoListService()