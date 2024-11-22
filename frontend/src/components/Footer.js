import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

export default function Footer() {
    return (
        <footer id="footer" className="footer bg-dark text-light py-4">
            <div className="container text-center">
                <h5>Contact Us</h5>
                <p>
                    <strong>Email:</strong> <a href="mailto:support@smarthome.com" className="text-light">support@smarthome.com</a>
                </p>
                <p>
                    <strong>Phone:</strong> <a href="tel:+1234567890" className="text-light">+1 (234) 567-890</a>
                </p>
                <p className="mt-3">
                    &copy; {new Date().getFullYear()} Smart Home, Inc. All rights reserved.
                </p>
            </div>
        </footer>
    );
}