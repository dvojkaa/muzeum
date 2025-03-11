// pages/Home.js
import React from 'react';
import Header from '../components/Header';
import Footer from '../components/Footer';
import '../CSS/Home.css';

const Home = () => {
    return (
        <div className="home-container">

            {/* Hlavní obsah */}
            <section className="home-main">
                <div className="home-text">
                    <h5>#1 in effortless storage</h5>
                    <h1>Effortlessly Store <br /> and Display Data</h1>
                    <p>Store and access your data seamlessly with DataStorage's intuitive database solutions.</p>
                </div>
                <div className="home-image">
                    <img src="../assets/database-image.png" alt="Data Storage" />
                </div>
            </section>
        </div>
    );
};

export default Home;
