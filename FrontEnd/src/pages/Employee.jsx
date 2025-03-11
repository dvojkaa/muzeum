import '../CSS/Employee.css'

const Employee = () => {
    return (
        <div className="art-container">
            <header className="art-header">
                <a href="/login" className="login-link">Login</a>
                <h1 className="museum-name">MuzeumName</h1>
            </header>

            <div className="art-image">
                <img src="/your-image-path.jpg" alt="Ancient Artifact Display Stand" />
            </div>

            <div className="art-details">
                <h2>Ancient Artifact Display Stand</h2>
                <p>
                    Beautifully crafted stand for showcasing ancient artifacts with elegance.
                </p>
                <p className="art-material">
                    65% Hand-Carved Hardwood / 35% Antique Bronze
                </p>
            </div>
        </div>
    );
};

export default Employee;
