
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../CSS/Login.css';

const Login = () => {
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        email: "",
        password: ""
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
        const customer = new FormData();
        customer.append("email", formData.email);
        customer.append("password", formData.password);

        try {
            const response = await fetch('http://localhost:8080/user/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Connection': 'keep-alive',
                },
                credentials: "include",
                body: JSON.stringify(formData) // Odeslání celého formData jako JSON
            });

            if (response.ok) {
                const data = await response.json(); // Přiřazení výsledků do data
                console.log('Login successful:', data);

                // Uložení do sessionStorage a přechod na profil
                sessionStorage.setItem("accessToken", data.token);
                navigate('/');
            } else {
                console.error('Login failed:', response.statusText);
            }
        } catch (error) {
            console.error('Error:', error);
        }
    }


    return (
        <div className="login">
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="email"></label>
                    <input
                        onChange={handleChange}
                        value={formData.email || ''}  // Ujisti se, že hodnota není undefined
                        type="email"
                        id="email"
                        name="email"  // Používáme email místo username
                        placeholder="Email:"
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="password"></label>
                    <input
                        onChange={handleChange}
                        value={formData.password || ''}  // Ujisti se, že hodnota není undefined
                        type="password"
                        id="password"
                        name="password"
                        placeholder="Heslo:"
                    />
                </div>
                <button id="login" type="submit">Přihlásit se</button>
            </form>
        </div>
    );
};

export default Login;

