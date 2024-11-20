import React from 'react';
import { Link } from "react-router-dom";
import '../index.css';

export default function Login() {
    return (
    <div className="login_page_wrapper">
        <div className="signUp_Side">
            <h1 className="logIn_tittel">Log in</h1>
            <div className="user_avatar"></div>

            <input type="email" placeholder="Email" className="input_field" />
            <input type="password" placeholder="Password" className="input_field" />

            <button className="button_signUp">Log in</button>
            <div className='button_container'>
                <Link to="/user/1" className={"user"}>
                    <button className="button_signUp1">log in as user</button>
                </Link>
                <Link to="/admin" className={"admin"}>
                    <button className="button_signUp1">log in as Admin</button>
                </Link>
            </div>

            <div className="links">
                <Link to="#" className="link_item">Forgot Password?</Link>
                <Link to="/SignUpPage" className="link_item">Sign up?</Link>
            </div>
        </div>
    </div>
    );
}
