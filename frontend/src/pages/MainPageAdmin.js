import React, {useEffect, useState} from 'react';
import {Link, useParams} from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import HeaderAdmin from "../components/HeaderAdmin";

export default function MainPage() {
    const [Users, setUser] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/allUsers')
            .then(response => response.json())
            .then(data => setUser(data))
            .catch(error => console.error(error));
    }, []);

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
                {Users.map((User, index) => (
                    <div key={index} className="user_item">
                        <i className="bi bi-person" id='icon_avatar'></i>
                        <p>{User.firstName + " " + User.lastName}</p>
                    </div>
                ))}
            </div>
            </div>
        </div>
    </>
    );
}
