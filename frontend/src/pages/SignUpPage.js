import React from 'react';
import { Link } from "react-router-dom";
import '../index.css';

export default function Signup() {
    return (
    <div className="login_page_wrapper">
        <div className="signUp_Side">
            <h1 className="logIn_tittel">Log in</h1>
            <div className="user_avatar"></div>

            <input type="Name" placeholder="Name" className="input_field" />
            <input type="email" placeholder="Email" className="input_field" />
            <input type="password" placeholder="Password" className="input_field" />

            <button className="button_signUp">Sign up</button>
            <div className="links_SignUpPage">
                <p className='SignUpPage_AlreadyHaveAccount'>Already have an account?<Link to="/login" className="link_item"> Log in</Link></p>
            </div>
        </div>
    </div>
    );
}
