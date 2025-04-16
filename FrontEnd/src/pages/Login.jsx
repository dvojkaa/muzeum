import React, { useState, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import '../CSS/Login.css';
import { AuthContext } from '../components/AuthContext';

const Login = () => {
    const navigate = useNavigate();
    const { login } = useContext(AuthContext);

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

        try {
            const response = await fetch('http://localhost:8080/user/login', {
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
                login(data.token); // použijeme funkci z contextu
                navigate('/');
            } else {
                console.error('Login failed:', response.statusText);
            }
        } catch (error) {
            console.error('Error:', error);
        }
    };

    return (
        <div className="login">
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <input
                        onChange={handleChange}
                        value={formData.email || ''}
                        type="email"
                        id="email"
                        name="email"
                        placeholder="Email:"
                    />
                </div>
                <div className="form-group">
                    <input
                        onChange={handleChange}
                        value={formData.password || ''}
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
