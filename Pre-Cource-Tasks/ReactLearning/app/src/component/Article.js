import React from 'react'
import {Component} from 'react'


class Article extends Component {
    state = {
        isOpen:true
    };

    render() {
        const {article} = this.props;
        console.log(this.props);
        let body = <div></div>;
        if (this.state.isOpen){
            body = <div><h1>{article.title}</h1><h5>{article.content}</h5></div>
        }
        return (
            <div>
                <button onClick={this.buttonListener}>{this.state.isOpen ? 'close' : 'open'}</button>
                {body}
                <small>{article.id}</small>

            </div>
        );
    }

    buttonListener = () =>{
        this.setState({
            isOpen: !this.state.isOpen
        })
    }

}

export default Article;