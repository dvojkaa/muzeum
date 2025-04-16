import React, { useEffect, useState } from 'react';

const DarkModeToggle = () => {
    const [darkMode, setDarkMode] = useState(() => {
        const saved = localStorage.getItem('dark-mode');
        return saved === 'true' || false;
    });

    useEffect(() => {
        document.body.classList.toggle('dark-mode', darkMode);
        localStorage.setItem('dark-mode', darkMode);
    }, [darkMode]);

    const toggle = () => setDarkMode(prev => !prev);

    return (
        <button
            onClick={toggle}
            className="darkmode-toggle"
            title={darkMode ? 'Switch to light mode' : 'Switch to dark mode'}
        >
            {darkMode ? '🌙' : '🌞'}
        </button>
    );
};

export default DarkModeToggle;
