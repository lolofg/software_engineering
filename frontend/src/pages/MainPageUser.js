import React, {useEffect, useState} from 'react';
import {Link, useParams} from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import Header from "../components/Header";
import Footer from '../components/Footer';

export default function MainPageUser() {
    const [selectedDevice, setSelectedDevice] = useState(null);
    const [User, setUser] = useState({});
    const { UserID } = useParams();
    const [devices, setDevices] = useState([]);
    const [availableDevices, setAvailableDevices] = useState([]);
    const [showAddDevicePopup, setShowAddDevicePopup] = useState(false);

    useEffect(() => {
        if (UserID) {
            fetch('http://localhost:8080/user/' + UserID)
                .then(response => response.json())
                .then(data => setUser(data))
                .catch(error => console.error(error));

            fetch('http://localhost:8080/GetOneUserDevices/' + UserID)
                .then(response => response.json())
                .then(data => setDevices(data))
                .catch(error => console.error(error));
        }
        else {
            setUser(null)
        }
    }, [UserID]);

    const handleDeviceClick = (device) => {
        setSelectedDevice(device);
    };

    const closeDevicePopup = () => {
        setSelectedDevice(null);
    };

    const fetchAvailableDevices = () => {
        fetch('http://localhost:8080/allProducts')
            .then(response => response.json())
            .then(data => {
                setAvailableDevices(data);
                setShowAddDevicePopup(true);
            })
            .catch(error => console.error(error));
    };

    const [selectedDeviceForName, setSelectedDeviceForName] = useState(null);
    const [customDeviceName, setCustomDeviceName] = useState("");

    const handleSelectDeviceForName = (device) => {
        setSelectedDeviceForName(device);
    };

    const handleAddDeviceWithCustomName = () => {
        if (!customDeviceName.trim()) {
            alert("Please enter a name for the device.");
            return;
        }

        fetch(`http://localhost:8080/AddDeviceToUser/` + UserID, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                usersID: UserID,
                productID: selectedDeviceForName.productID,
                name: customDeviceName,
            }),
        })
            .then(response => {
                if (response.ok) {
                    alert("Device added successfully!");
                    setShowAddDevicePopup(false);
                    setSelectedDeviceForName(null);
                    setCustomDeviceName("");
                    fetch('http://localhost:8080/GetOneUserDevices/' + UserID)
                        .then(response => response.json())
                        .then(data => setDevices(data))
                        .catch(error => console.error(error));
                } else {
                    alert("Failed to add device.");
                }
            })
            .catch(error => console.error(error));
    };

    return (
        <>
            <Header user={User}/>
            <div className="mainpage_wrapper">
                <div className="device_grid">
                    <div className="search_item">
                        <h2>Devices</h2>
                    </div>
                    {devices.map((device, index) => (
                        <div key={index} className="device_item" onClick={() => handleDeviceClick(device)}>
                            <i className="bi bi-cloud-download-fill" id='icon_avatar'></i>
                            <p>{device.name}</p>
                        </div>
                    ))}
                </div>

                <div className="action_buttons">
                    <button className="button_add" onClick={fetchAvailableDevices}>Add Device</button>
                    <button className="button_remove">Remove Device</button>
                </div>

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

                {showAddDevicePopup && (
                    <div className="add_device_popup">
                        <div className="add_popup_content">
                            <button className="add_close_button" onClick={() => setShowAddDevicePopup(false)}>×</button>
                            {selectedDeviceForName ? (
                                <div>
                                    <h3>Name Your Device</h3>
                                    <input
                                        type="text"
                                        className="device_name_input"
                                        placeholder="Enter device name"
                                        value={customDeviceName}
                                        onChange={(e) => setCustomDeviceName(e.target.value)}
                                    />
                                    <button className="confirm_button" onClick={handleAddDeviceWithCustomName}>Add Device</button>
                                    <button className="back_button" onClick={() => setSelectedDeviceForName(null)}>Back</button>
                                </div>
                            ) : (
                                <div>
                                    <h3>Available Devices</h3>
                                    <ul>
                                        {availableDevices.map((device, index) => (
                                            <li key={index} className="add_device_item">
                                                <span>{device.productName}</span>
                                                <button onClick={() => handleSelectDeviceForName(device)}>Select</button>
                                            </li>
                                        ))}
                                    </ul>
                                </div>
                            )}
                        </div>
                    </div>
                )}
            </div>
            <Footer />
        </>
    );
}
