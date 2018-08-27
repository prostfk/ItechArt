import React, {Component} from 'react'

class Time extends Component {

    constructor() {
        super();
        this.state = {
            showDate: true
        }
    }

    render() {

        let body;
        if (this.state.showDate) {
            body = (
                <div>
                    <h3>{new Date().toLocaleTimeString()}</h3>
                    <button className="btn btn-primary" onClick={this.closeOrOpenClock}>
                        Close
                    </button>
                </div>

            )
        } else {
            body = <button className="btn btn-primary" onClick={this.closeOrOpenClock}>
                Open
            </button>

        }

        return (
            body
        )
    }

    closeOrOpenClock = () => {
        this.setState({
            showDate: !this.state.showDate
        })
    }


}

export default Time;