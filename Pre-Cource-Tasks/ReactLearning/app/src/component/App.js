import React from 'react'
import {Component} from 'react'
import articles from '../data'
import ArticlesList from "./ArticlesList";

class App extends Component {


    render() {
        return (
            <div className="container ">
                <div className="jumbotron">

                    <h1 className="display-3" style={{width:'100%'}}>App from class</h1>
                </div>


                <ArticlesList articles={articles}/>

            </div>
        );
    }


}


export default App;