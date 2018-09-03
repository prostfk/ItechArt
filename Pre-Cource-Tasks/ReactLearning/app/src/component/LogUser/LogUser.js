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
                        <input type="submit" className="btn btn-primary" onClick={this.onClicker} value="Submit"/>
                    </form>
                </div> : <h3>Thanks</h3>
        );
        return (
            <div>
                {body}
                <div id="snackbar">Some text some message..</div>
            </div>
        )
    }




    onClicker = () => {
        if ()
        this.setState({
            status: false
        });
        LogUser.startPush();
    };

    static startPush() {
        let password = document.getElementById('password').value;
        let username = document.getElementById('username').value;
        let x = document.getElementById("snackbar");
        let result = function(){
          if (password.length < 4 || username.length < 5){
              return 0;
          }else if (password.length > 3 && password.length < 6){
              return 1;
          }else if (password.length > 5 && password.length < 15 && username.length > 5){
              return 2;
          }
        };
        switch (result()) {

        }
        x.className = "show";
        setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
    }

}

export default LogUser;