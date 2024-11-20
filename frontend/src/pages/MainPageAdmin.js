import React from 'react';
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import Header from "../components/Header";

export default function MainPage() {
    return (
        <>
        <Header />
        <div className="mainpage_wrapper">
            <div className="device_grid">
            <div className="search_item">
    <form className="search_form">
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
                    <p>BRUKER 1</p>
                </div>
                <div className="device_item">
                    <div className="device_avatar"></div>
                    <p>BRUKER 2</p>
                </div>
                <div className="device_item">
                    <div className="device_avatar"></div>
                    <p>BRUKER 3</p>
                </div>
                <div className="device_item">
                    <div className="device_avatar"></div>
                    <p>BRUKER 4</p>
                </div>
                <div className="device_item">
                    <div className="device_avatar"></div>
                    <p>BRUKER 5</p>
                </div>
                <div className="device_item">
                    <div className="device_avatar"></div>
                    <p>BRUKER 6</p>
                </div>
            </div>
        </div>
    </>
    );
}
