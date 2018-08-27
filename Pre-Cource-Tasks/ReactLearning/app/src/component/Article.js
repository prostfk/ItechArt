import React from 'react'
import {Component} from 'react'


class Article extends Component {
    constructor(props){
        super(props);
        this.state = {
            isOpen: props.defaultOpen
        }
    }

    render() {
        const {article} = this.props;
        console.log(this.props);
        let body = <div className="card-header">
            <h3>{article.title}</h3>
            <button className={this.state.isOpen ? 'btn btn-warning' : 'btn btn-success'} onClick={this.buttonListener}>{this.state.isOpen ? 'close' : 'open'}</button>
        </div>;
        if (this.state.isOpen) {
            body = <div className="card">
                <div className="card-header">
                    <h3>{article.title}</h3>
                    <button className={this.state.isOpen ? 'btn btn-warning' : 'btn btn-success'} onClick={this.buttonListener}>{this.state.isOpen ? 'close' : 'open'}</button>
                </div>
                <div className="card-subtitle text-muted"><br/><i>id: {article.id}</i></div>
                <div className="card-text"><p>{article.content}</p></div>
            </div>
        }
        return (
            <div>
                <div>
                    <ul>
                        {body}
                    </ul>
                </div>

            </div>
        );
    }

    buttonListener = () => {
        this.setState({
            isOpen: !this.state.isOpen
        })
    }

}

export default Article;