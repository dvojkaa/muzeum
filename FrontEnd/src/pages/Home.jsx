import React from "react";
import "../CSS/Home.css"; // Připojíme CSS soubor

const Home = () => {
    return (
        <div className="home-container">
            {/* Horní navigace */}
            <header className="home-header">
                <div className="logo">🌀 EvidenceSys</div>
                <nav>
                    <a href="#">Home</a>
                    <a href="#">Save Items</a>
                    <a href="#">View Database</a>
                    <a href="#">Add Personal</a>
                    <a href="#">Logout</a>
                    <button className="btn">Sign up</button>
                    <button className="btn">Log In</button>
                </nav>
            </header>

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

            {/* Spodní sekce */}
            <footer className="home-footer">
                <div className="footer-title">🌀 DataStorage</div>
                <div className="footer-links">
                    <a href="#">Product</a>
                    <a href="#">Company</a>
                    <a href="#">Support</a>
                    <a href="#">Legal</a>
                </div>
            </footer>
        </div>
    );
};

export default Home;
