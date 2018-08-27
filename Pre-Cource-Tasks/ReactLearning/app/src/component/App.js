import React from 'react'
import {Component} from 'react'
import articles from '../data'
import ArticlesList from "./ArticlesList";
import Time from './Time'

class App extends Component {

    render() {

        return (
            <div className="container ">
                <div className="jumbotron" id="time-element">
                    <Time />
                </div>

                <ArticlesList articles={articles}/>

            </div>
        );
    }


}


export default App;