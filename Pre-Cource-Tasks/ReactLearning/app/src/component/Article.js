import React from 'react'
import {Component} from 'react'


class Article extends Component {
    state = {
        isOpen: false
    };

    render() {
        const {article} = this.props;
        console.log(this.props);
        let body = <div></div>;
        if (this.state.isOpen) {
            body = <div>
                <li>{article.id}</li>
                <li>{article.title}</li>
                <li>{article.content}</li>
            </div>
        }
        return (
            <div>
                <div>
                    <button className={this.state.isOpen ? 'btn btn-warning' : 'btn btn-success'} onClick={this.buttonListener}>{this.state.isOpen ? 'close' : 'open'}</button>
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