import React from 'react';
import { Link } from 'react-router-dom';
import '../CSS/Footer.css';

const Footer = () => {
    return (
        <footer className="footer">
            <div className="footer-title">🌀 DataStorage</div>
            <div className="footer-links">
                <Link >Product</Link>
                <Link >Company</Link>
                <Link >Support</Link>
                <Link >Legal</Link>
            </div>
        </footer>
    );
};

export default Footer;
