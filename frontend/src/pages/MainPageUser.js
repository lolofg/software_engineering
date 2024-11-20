import React, {useEffect, useState} from 'react';
import {Link, useParams} from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import Header from "../components/Header";

export default function MainPageUser() {
    const [selectedDevice, setSelectedDevice] = useState(null);

    const devices = [
        { name: 'TV' },
        { name: 'CURTAINS' },
        { name: 'SPEAKERS' },
        { name: 'MICROWAVE' },
        { name: 'DOORBELL' },
        { name: 'HOUSE' }
    ];

    const handleDeviceClick = (device) => {
        setSelectedDevice(device);
    };

    const closeDevicePopup = () => {
        setSelectedDevice(null);
    };

    const [User, setUser] = useState({});
    const { UserID } = useParams();

    useEffect(() => {
        if (UserID) {
            fetch('http://localhost:8080/user/' + UserID)
                .then(response => response.json())
                .then(data => setUser(data))
                .catch(error => console.error(error));
        }
        else {
            setUser(null)
        }
    }, [UserID]);

    return (
        <>
            <Header user={User}/>
            <div className="mainpage_wrapper">
                {/* Grid med enheter */}
                <div className="device_grid">
                    {devices.map((device, index) => (
                        <div key={index} className="device_item" onClick={() => handleDeviceClick(device)}>
                            <div className="device_avatar"></div>
                            <p>{device.name}</p>
                        </div>
                    ))}
                </div>

                {/* Knappene rett under grid */}
                <div className="action_buttons">
                    <button className="button_add">Legg til enhet</button>
                    <button className="button_remove">Fjern enhet</button>
                </div>

                {/* Popup som vises når en enhet er valgt */}
                {selectedDevice && (
                    <div className="device_popup">
                        <div className="popup_content">
                            <button className="close_button" onClick={closeDevicePopup}>×</button>
                            <div className="device_avatar large"></div>
                            <p>{selectedDevice.name}</p>
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec congue mollis urna, et imperdiet sem vulputate vitae. Integer sit amet ultricies metus.</p>
                        </div>
                    </div>
                )}
            </div>
        </>
    );
}
