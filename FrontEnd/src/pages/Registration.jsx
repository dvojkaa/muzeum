import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../CSS/Registration.css';

const Registration = () => {
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        firstName: "",
        lastName: "",
        email: "",
        password: "",
        role: "ROLE_ADMIN", // Výchozí role
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

                sessionStorage.setItem("accessToken", data.token);
                navigate('/');

                // console.log('Registration successful');
                // console.log(response.body);
                // sessionStorage.setItem("accessToken", response.access);
                // navigate('/');
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
                    <label htmlFor="firstName"></label>
                    <input onChange={handleChange} value={formData.firstName} type="text" id="firstName" name="firstName" placeholder="Jméno:" required />
                </div>
                <div className="form-group">
                    <label htmlFor="lastName"></label>
                    <input onChange={handleChange} value={formData.lastName} type="text" id="lastName" name="lastName" placeholder="Příjmení:" required />
                </div>
                <div className="form-group">
                    <label htmlFor="email"></label>
                    <input onChange={handleChange} value={formData.email} type="email" id="email" name="email" placeholder="E-mail:" required />
                </div>
                <div className="form-group">
                    <label htmlFor="password"></label>
                    <input onChange={handleChange} value={formData.password} type="password" id="password" name="password" placeholder="Heslo:" required />
                </div>
                <div className="form-group">
                    <label htmlFor="phoneNumber"></label>
                    <input onChange={handleChange} value={formData.phoneNumber} type="tel" id="phoneNumber" name="phoneNumber" placeholder="Telefon:" />
                </div>
                <button id="register" type="submit">Registrovat se</button>
            </form>
        </div>
    );
};

export default Registration;
