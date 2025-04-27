// import React, { useEffect, useState } from 'react';
// import '../CSS/Home.css';
//
// const Home = () => {
//     const [isLoggedIn, setIsLoggedIn] = useState(false);
//
//     useEffect(() => {
//         const token = sessionStorage.getItem('accessToken');
//         setIsLoggedIn(!!token);
//     }, []);
//
//     return (
//         <div className="home-container">
//             <section className="home-main">
//                 <div className="home-text">
//                     <h5>#1 in effortless storage</h5>
//                     <h1>Effortlessly Store <br /> and Display Data</h1>
//                     <p>Store and access your data seamlessly with MuzeumSys.</p>
//
//                     {isLoggedIn ? (
//                         <p className="status-msg">Vítej zpět v systému. Můžeš pokračovat na správu.</p>
//                     ) : (
//                         <p className="status-msg">Přihlas se nebo se zaregistruj pro přístup k databázi.</p>
//                     )}
//                 </div>
//
//                 <div className="home-image">
//                     <img src="../assets/database-image.png" alt="Data Storage" />
//                 </div>
//             </section>
//         </div>
//     );
// };
//
// export default Home;
import React, { useEffect, useState } from 'react';
import '../CSS/Home.css';
import { useNavigate } from 'react-router-dom';

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
                    <p>Elegantní, přehledný a snadno použitelný systém pro evidenci, správu a QR kódování uměleckých děl.</p>

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
                    <img src="/assets/logo.png" alt="logo" />
                </div>
            </section>
        </div>
    );
};

export default Home;
