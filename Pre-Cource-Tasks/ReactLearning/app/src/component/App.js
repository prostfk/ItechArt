import React from 'react'
import {Component} from 'react'
import articles from '../data'
import ArticlesList from "./ArticlesList";
import Time from './Time'
import LogUser from "./LogUser/LogUser";

class App extends Component {

    render() {

        return (
            <div className="container ">
                <div className="jumbotron" id="time-element">
                    <Time />
                </div>

                <ArticlesList articles={articles}/>
                <LogUser/>
            </div>
        );
    }


}


export default App;