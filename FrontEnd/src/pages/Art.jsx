import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import '../CSS/Art.css';

const Art = () => {
    const { id } = useParams(); // <--- z URL získáš ID
    const [art, setArt] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchArt = async () => {
            try {
                const response = await fetch(`https://muzeum-production.up.railway.app//art/${id}`);
                if (!response.ok) {
                    throw new Error('Artwork not found');
                }
                const data = await response.json();
                setArt(data);
            } catch (err) {
                setError(err.message);
            } finally {
                setLoading(false);
            }
        };

        fetchArt();
    }, [id]);

    if (loading) return <div>Loading...</div>;
    if (error) return <div>Error: {error}</div>;
    if (!art) return <div>No artwork found</div>;

    return (
        <div className="art-container">
            <header className="art-header">
                <a href="/login" className="login-link">Login</a>
                <h1 className="museum-name">MuzeumName</h1>
            </header>

            <div className="art-image">
                {/* Nahraď správnou cestou k obrázku */}
                <img src={`http://localhost:8080/${art.imgPath}`} alt={art.name}/>
            </div>

            <div className="art-details">
            <h2>{art.name}</h2>
                <p>{art.description}</p>
                <p className="art-material">
                    {art.parameters}
                </p>
                <p><strong>Author:</strong> {art.author}</p>
                <p><strong>Era:</strong> {art.era}</p>
                <p><strong>Type:</strong> {art.type}</p>
                <p><strong>Color:</strong> {art.color}</p>
            </div>
        </div>
    );
};

export default Art;
