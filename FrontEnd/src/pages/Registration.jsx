import React, { useState, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import '../CSS/Registration.css';
import { AuthContext } from '../components/AuthContext';

const Registration = () => {
    const navigate = useNavigate();
    const { login } = useContext(AuthContext);

    const [formData, setFormData] = useState({
        firstName: "",
        lastName: "",
        email: "",
        password: "",
        role: "ROLE_ADMIN",
        phoneNumber: ""
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch('http://localhost:8080/user/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Connection': 'keep-alive',
                },
                credentials: "include",
                body: JSON.stringify(formData)
            });

            if (response.ok) {
                const data = await response.json();
                console.log('Registration successful:', data);

                login(data.token); // 👈 Spustíme login context funkci
                navigate('/');
            } else {
                console.error('Registration failed:', response.statusText);
            }
        } catch (error) {
            console.error('Error:', error);
        }
    };

    return (
        <div className="login">
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <input onChange={handleChange} value={formData.firstName} type="text" id="firstName" name="firstName" placeholder="Jméno:" required />
                </div>
                <div className="form-group">
                    <input onChange={handleChange} value={formData.lastName} type="text" id="lastName" name="lastName" placeholder="Příjmení:" required />
                </div>
                <div className="form-group">
                    <input onChange={handleChange} value={formData.email} type="email" id="email" name="email" placeholder="E-mail:" required />
                </div>
                <div className="form-group">
                    <input onChange={handleChange} value={formData.password} type="password" id="password" name="password" placeholder="Heslo:" required />
                </div>
                <div className="form-group">
                    <input onChange={handleChange} value={formData.phoneNumber} type="tel" id="phoneNumber" name="phoneNumber" placeholder="Telefon:" />
                </div>
                <button id="register" type="submit">Registrovat se</button>
            </form>
        </div>
    );
};

export default Registration;
