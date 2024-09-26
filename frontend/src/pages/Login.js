import React from 'react';
import {Link} from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';

export default function Login() {
    return (
        <div className="container h-100 text-center">
            <h1 className="h3 mb-3 font-weight-normal">Login</h1>
            <div>
                <Link to="/user/1" className={"px-1 text-white"}>
                    <button className="btn btn-primary btn-lg">login as user</button>
                </Link>
                <Link to="/admin" className={"px-1 text-white"}>
                    <button className="btn btn-primary btn-lg">login as Admin</button>
                </Link>
            </div>
        </div>
    )
}