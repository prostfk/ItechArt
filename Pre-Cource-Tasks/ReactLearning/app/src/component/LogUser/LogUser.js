import {Component} from "react";
import React from 'react';
import LogUserStyle from './LogUserStyle.css'

class LogUser extends Component {

    constructor() {
        super();
        this.state = {
            status: true
        }
    }

    render() {
        const body = (
            this.state.status ?
                <div>

                    <form>
                        <div className="form-group">
                            <label htmlFor="username">Username</label>
                            <input placeholder="username" id="username" className="form-control"/>
                        </div>
                        <div className="form-group">
                            <label htmlFor="password">Password</label>
                            <input placeholder="password" id="password" type="password" className="form-control"/>
                        </div>
                        <input type="button" className="btn btn-primary" onClick={this.onClicker} value="Submit"/>
                    </form>
                </div> : <h3>Thanks</h3>
        );
        return (
            <div>
                <div id="snackbar">Some text some message..</div>
                {body}
            </div>
        )
    }


    onClicker = () => {
        // this.setState({
        //     status: false
        // });
        LogUser.startPush();
    };

    static startPush() {
        let password = document.getElementById('password').value;
        let username = document.getElementById('username').value;
        let x = document.getElementById("snackbar");
        let result = function () {
            if (password.length < 4 || username.length < 5) {
                return 0;
            } else if (password.length > 3 && password.length < 6 && username.length > 5 && username.length < 15) {
                return 1;
            } else {
                return 2;
            }
        };
        switch (result()) {
            case 0:
                x.className = "show error";
                x.innerHTML = "Not safety data";
                break;
            case 1:
                x.className = "show warning";
                x.innerHTML = "Ok but not so safety data";
                break;
            case 2:
                x.className = "show success";
                x.innerHTML = "Safety data";
                break;
        }
        setTimeout(function () {
            x.className = x.className.replace("show", "");
        }, 3000);
    }

}

export default LogUser;