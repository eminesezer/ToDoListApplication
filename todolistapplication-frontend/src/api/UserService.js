import axios from 'axios'
import {API_URL} from '../Constants'

class UserService {

    registerationNewUser(userInfo) {
        //console.log('executed service')
        return axios.get(`${API_URL}/registeration`, userInfo);
    }
}

export default new UserService()