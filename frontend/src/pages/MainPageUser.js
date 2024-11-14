import React from 'react';
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import Header from "../components/Header";

export default function MainPageUser() {
    return (
        <>
        <Header />
        <div className="mainpage_wrapper">
            <div className="device_grid">
                <div className="device_item">
                    <div className="device_avatar"></div>
                    <p>TV</p>
                </div>
                <div className="device_item">
                    <div className="device_avatar"></div>
                    <p>GARDINER</p>
                </div>
                <div className="device_item">
                    <div className="device_avatar"></div>
                    <p>HØYTALER</p>
                </div>
                <div className="device_item">
                    <div className="device_avatar"></div>
                    <p>MIKROBØLGEOVN</p>
                </div>
                <div className="device_item">
                    <div className="device_avatar"></div>
                    <p>RINGEKLOKKE</p>
                </div>
                <div className="device_item">
                    <div className="device_avatar"></div>
                    <p>HUS</p>
                </div>
            </div>
            <div className="action_buttons">
                <button className="button_add">Legg til enhet</button>
                <button className="button_remove">Fjern enhet</button>
            </div>
        </div>
    </>
    );
}
