// // import { useParams } from 'react-router-dom';
// // import { useEffect, useState } from 'react';
// // import '../CSS/Art.css';
// //
// // const Art = () => {
// //     const { id } = useParams();
// //     const [art, setArt] = useState(null);
// //     const [loading, setLoading] = useState(true);
// //     const [error, setError] = useState(null);
// //     const apiUrl = import.meta.env.VITE_API_URL;
// //
// //
// //     useEffect(() => {
// //         const fetchArt = async () => {
// //             try {
// //                 const response = await fetch(`${import.meta.env.VITE_API_URL}/art/${id}`);
// //                 if (!response.ok) {
// //                     throw new Error('Artwork not found');
// //                 }
// //                 const data = await response.json();
// //                 setArt(data);
// //             } catch (err) {
// //                 setError(err.message);
// //             } finally {
// //                 setLoading(false);
// //             }
// //         };
// //
// //         fetchArt();
// //     }, [id]);
// //
// //     if (loading) return <div>Loading...</div>;
// //     if (error) return <div>Error: {error}</div>;
// //     if (!art) return <div>No artwork found</div>;
// //
// //     return (
// //         <div className="art-container">
// //             <header className="art-header">
// //                 <a href="/login" className="login-link">Login</a>
// //                 <h1 className="museum-name">MuzeumName</h1>
// //             </header>
// //
// //             <div className="art-image">
// //                 <img
// //                     src={`${import.meta.env.VITE_API_URL}${art.imgPath}`}
// //                     alt={art.name}
// //                 />
// //             </div>
// //
// //             <div className="art-details">
// //                 <h2>{art.name}</h2>
// //                 <p>{art.description}</p>
// //                 <p className="art-material">
// //                 {art.parameters}
// //                 </p>
// //                 <p><strong>Author:</strong> {art.author}</p>
// //                 <p><strong>Era:</strong> {art.era}</p>
// //                 <p><strong>Type:</strong> {art.type}</p>
// //                 <p><strong>Color:</strong> {art.color}</p>
// //             </div>
// //         </div>
// //     );
// // };
// //
// // export default Art;
// import { useParams } from 'react-router-dom';
// import { useEffect, useState } from 'react';
// import '../CSS/Art.css';
//
// const Art = () => {
//     const { id } = useParams();
//     const [art, setArt] = useState(null);
//     const [loading, setLoading] = useState(true);
//     const [error, setError] = useState(null);
//     const [imageUrl, setImageUrl] = useState(null);
//     const apiUrl = import.meta.env.VITE_API_URL;
//
//     useEffect(() => {
//         const fetchArt = async () => {
//             try {
//                 const response = await fetch(`${apiUrl}/art/${id}`);
//                 if (!response.ok) {
//                     throw new Error('Artwork not found');
//                 }
//                 const data = await response.json();
//                 setArt(data);
//
//                 // Fetch the image
//                 const imgResponse = await fetch(`${apiUrl}/art/photo`, {
//                     method: "POST",
//                     headers: {
//                         "Content-Type": "application/json"
//                     },
//                     body: JSON.stringify(data.id)
//                 });
//
//                 if (imgResponse.ok) {
//                     setImageUrl(imgResponse.url);
//                 } else {
//                     throw new Error('Image not found');
//                 }
//
//             } catch (err) {
//                 setError(err.message);
//             } finally {
//                 setLoading(false);
//             }
//         };
//
//         fetchArt();
//     }, [id, apiUrl]);
//
//     if (loading) return <div>Loading...</div>;
//     // if (error) return <div>Error: {error}</div>;
//     if (!art) return <div>No artwork found</div>;
//
//     return (
//         <div className="art-container">
//             <header className="art-header">
//                 <a href="/login" className="login-link">Login</a>
//                 <h1 className="museum-name">MuzeumName</h1>
//             </header>
//
//             <div className="art-image">
//                 {imageUrl ? (
//                     <img src={imageUrl} alt={art.name} />
//                 ) : (
//                     <p>Image not available</p>
//                 )}
//             </div>
//
//             <div className="art-details">
//                 <h2>{art.name}</h2>
//                 <p>{art.description}</p>
//                 <p><strong>Author:</strong> {art.author}</p>
//                 <p><strong>Era:</strong> {art.era}</p>
//                 <p><strong>Type:</strong> {art.type}</p>
//                 <p><strong>Color:</strong> {art.color}</p>
//             </div>
//         </div>
//     );
// };
//
// export default Art;



import { useParams } from 'react-router-dom';
import React, { useEffect, useState } from 'react';
import '../CSS/Art.css';

const Art = () => {
    const { id } = useParams();
    const [art, setArt] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [image, setImage] = useState(null);
    const apiUrl = import.meta.env.VITE_API_URL;

    useEffect(() => {
        const fetchArt = async () => {
            try {
                const response = await fetch(`${apiUrl}/art/${id}`);
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
    }, [id, apiUrl]);



    if (loading) return <div>Loading...</div>;
    if (error) return <div>Error: {error}</div>;
    if (!art) return <div>No artwork found</div>;

    return (
        <div className="art-container">
            <header className="art-header">
                <a href="/login" className="login-link">Login</a>
                <h1 className="museum-name">MuzeumName</h1>
            </header>

            <div id="art-section">
                <img
                    src={`${import.meta.env.VITE_API_URL}/arts/${encodeURIComponent(art.imgPath)}`}
                    alt="Obrázek díla"
                    className="image"
                />
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