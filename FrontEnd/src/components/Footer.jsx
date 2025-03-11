import React from 'react';
import { Link } from 'react-router-dom';
import '../CSS/Footer.css';

const Footer = () => {
    return (
        <footer className="footer">
            <div className="footer-title">🌀 DataStorage</div>
            <div className="footer-links">
                <Link to="/product">Product</Link>
                <Link to="/company">Company</Link>
                <Link to="/support">Support</Link>
                <Link to="/legal">Legal</Link>
            </div>
        </footer>
    );
};

export default Footer;
