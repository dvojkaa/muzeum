import React, { useState, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import '../CSS/Login.css';
import { AuthContext } from '../components/AuthContext';

const Login = () => {

    const [loginError, setLoginError] = useState('');
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
            const response = await fetch(`https://muzeum-production.up.railway.app/user/login`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            });

            if (response.ok) {
                const data = await response.json();
                await login(data.token,data.role);



                if (data.role === 'ROLE_EMPLOYEE') {
                    navigate('/employee');
                } else if (data.role === 'ROLE_ADMIN') {
                    navigate('/');
                } else {
                    navigate('/login');
                }
            } else {
                console.error('Login failed:', response.statusText);
                setLoginError('Špatné přihlášení, zkuste to znovu.');
            }
        } catch (error) {
            console.error('Chyba při loginu:', error);
            setLoginError('Nepodařilo se připojit k serveru.');
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
                <button className="btn-primary" type="submit">Přihlásit se</button>
                {loginError && <p style={{ color: 'red', marginTop: '1rem' }}>{loginError}</p>}

            </form>
        </div>
    );
};

export default Login;
