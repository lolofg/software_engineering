import React from 'react';
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import HeaderAdmin from "../components/HeaderAdmin";

export default function MainPage() {
    return (
        <>
        <HeaderAdmin />
        <div className="mainpage_wrapper">
            <div className="device_grid">
            <div className="search_item">
            <form className="search_form">
            <h2>Users</h2>
            <input
            type="search"
            className="form-control form-control-dark"
            placeholder="Search..."
            aria-label="Search"
             />
            </form>
            </div>
                <div className="device_item">
                    <div className="device_avatar"></div>
                    <p>USER 1</p>
                </div>
                <div className="device_item">
                    <div className="device_avatar"></div>
                    <p>USER  2</p>
                </div>
                <div className="device_item">
                    <div className="device_avatar"></div>
                    <p>USER  3</p>
                </div>
                <div className="device_item">
                    <div className="device_avatar"></div>
                    <p>USER 4</p>
                </div>
                <div className="device_item">
                    <div className="device_avatar"></div>
                    <p>USER  5</p>
                </div>
                <div className="device_item">
                    <div className="device_avatar"></div>
                    <p>USER  6</p>
                </div>
            </div>
        </div>
    </>
    );
}
