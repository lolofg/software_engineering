import React from 'react';
import {Link} from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import "bootstrap-icons/font/bootstrap-icons.css";
import {Button} from "react-bootstrap";

function Header({user}) {

    return (
        <header className="p-3 bg-dark text-white">
            <div className="container">
                <div className="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">

                    <ul className="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                        {
                            user
                                ? (
                                    <Link to={"http://localhost:3000/user/" + user.touristID} style={{ textDecoration: 'none' }}>
                                        <li className="nav-link px-2 text-white"> Home </li>
                                    </Link>
                                )
                                : (
                                    <Link to={"http://localhost:3000"} style={{ textDecoration: 'none' }}>
                                        <li><a href="#" className="nav-link px-2 text-white">Home</a></li>
                                    </Link>
                                )
                        }
                        <li><a href="#" className="nav-link px-2 text-white">Settings</a></li>
                        <li><a href="#footer" className="nav-link px-2 text-white">Contact</a></li>
                    </ul>

                    <form className="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
                        <input type="search" className="form-control form-control-dark" placeholder="Search..." aria-label="Search" />
                    </form>

                    <div className="text-end">
                        {user ? (
                            // Display the user's name if they are logged in
                            <div className="d-flex align-items-center">
                                <span className="text-white me-3">Welcome, {user.firstName}</span>
                                <Link to={"http://localhost:3000/"}>
                                    <Button className="btn btn-secondary" >
                                        Log out
                                    </Button>
                                </Link>
                            </div>
                        ) : (
                            <>
                                <Link to="/login" className="btn btn-primary me-2">Log In</Link>
                                <Link to="/SignUpPage" className="btn btn-secondary">Sign Up</Link>
                            </>
                        )}
                    </div>
                </div>
            </div>
        </header>
    );
}

export default Header;
