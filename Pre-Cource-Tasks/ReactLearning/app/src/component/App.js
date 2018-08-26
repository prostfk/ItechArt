import React from 'react'
import {Component} from 'react'
import articles from '../data'
import ArticlesList from "./ArticlesList";

class App extends Component {


    render() {
        return (
            <div>
                <h1>App from class</h1>
                <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
                      integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
                      crossOrigin="anonymous"/>

                <ArticlesList articles={articles}/>

            </div>
        );
    }


}


export default App;