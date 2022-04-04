import React, {Component} from 'react';
import axios from "axios";
import './App.css';

const api = axios.create({
    baseURL: `http://localhost:8080/plantaria/cliente/fafba15e-2284-471c-958b-39f4b65a2913`
})
class App extends Component {
    constructor() {
        super();
        api.get('/').then(res => {
            console.log(res.data)
        })
    }
}

export default App;
