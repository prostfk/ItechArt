import React from 'react'
import {Component} from 'react'
import articles from '../data'
import Article from './Article'

class App extends Component {


    render() {
        return (
            <div>
                <h1>App from class</h1>
                <Article article={articles}/>

            </div>
        );
    }


}


export default App;