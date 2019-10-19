import axios from 'axios'
import {API_URL} from '../Constants'

class ToDoListItemService {

    getAllToDoItems(username, todoId) {
        //console.log('executed service')
        return axios.get(`${API_URL}/${username}/${todoId}/todos`);
    }

    getFilteredTodoItems(username, todoId, filter) {
        //console.log('executed service')
        return axios.get(`${API_URL}/${username}/${todoId}/filter`, filter);
    }

    deleteTodoItem(username, todoId, id) {
        //console.log('executed service')
        return axios.delete(`${API_URL}/${username}/${todoId}/delete/${id}`);
    }

    createTodoItem(username, todoId, todoItem) {
        //console.log('executed service')
        return axios.post(`${API_URL}/${username}/${todoId}/save`, todoItem);
    }

    updateTodoItem(username, todoId, todoItem, id) {
        //console.log('executed service')
        return axios.put(`${API_URL}/${username}/${todoId}/update/${id}`, todoItem);
    }

}

export default new ToDoListItemService()