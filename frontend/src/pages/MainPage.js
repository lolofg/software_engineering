import React from 'react';
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import HeaderStartPage from '../components/HeaderStartPage';
import Footer from '../components/Footer';

export default function MainPage() {
    return (
        <>
            <HeaderStartPage />
            <div className="main-page-container">
                <div className="content">
                    <h1 className="display-4 text-white">Welcome to Your Smart Home</h1>
                    <p className="lead text-light">Control, automate, and secure your home effortlessly.</p>
                </div>
            </div>
            <Footer />
        </>
    );
}
