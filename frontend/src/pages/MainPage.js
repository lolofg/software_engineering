import React from 'react';
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import HeaderStartPage from '../components/HeaderStartPage';
import Header from '../components/Header';



export default function MainPage() {
            <Header/>
    return (
        <>
            <HeaderStartPage />
            <div className="main-page-container">
                <div className="content">
                    <h1 className="display-4 text-white">Welcome to Your Smart Home</h1>
                    <p className="lead text-light">Control, automate, and secure your home effortlessly.</p>
                    <div className="button-group">
                        <Link to="/features" className="btn btn-outline-light btn-lg mx-2">Explore Features</Link>
                    </div>
                </div>
            </div>
        </>
    );
}
