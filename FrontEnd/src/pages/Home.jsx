import React, {useEffect, useState} from 'react';
import '../CSS/Home.css';
import {useNavigate} from 'react-router-dom';

const Home = () => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const navigate = useNavigate();

    useEffect(() => {
        const token = sessionStorage.getItem('accessToken');
        setIsLoggedIn(!!token);
    }, []);

    return (
        <div className="home-container">
            <section className="hero">
                <div className="hero-text">
                    <h1>MuzeumSys</h1>
                    <h2>Moderní správa muzejních sbírek</h2>
                    <p>Elegantní, přehledný a snadno použitelný systém pro evidenci, správu a QR kódování uměleckých
                        děl.</p>

                    {isLoggedIn ? (
                        <button className="btn-primary" onClick={() => navigate('/admin/database')}>
                            Přejít do databáze
                        </button>
                    ) : (
                        <div className="btn-group">
                            <button className="btn-primary" onClick={() => navigate('/login')}>
                                Přihlásit se
                            </button>
                        </div>
                    )}
                </div>
                <div className="hero-image">
                    <img src="/assets/logo.png" alt="logo"/>
                </div>
            </section>
        </div>
    );
};

export default Home;
