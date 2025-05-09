import React, {useState} from 'react';
import CameraScanner from './CameraScanner';
import FileUploader from './FileUploader';

const QRMethodModal = ({onClose, onScanComplete, isMultiScan, actionType}) => {
    const [method, setMethod] = useState(null);
    const [scannedArts, setScannedArts] = useState([]);
    const [error, setError] = useState('');

    const handleScannedArt = async (qrText) => {
        if (actionType === 'edit' && scannedArts.length >= 1) {
            setError("V režimu editace lze naskenovat pouze jedno dílo.");
            return;
        }

        try {
            const url = new URL(qrText);
            const id = url.pathname.split('/').pop();
            const response = await fetch(`${import.meta.env.VITE_API_URL}/art/${id}`);

            if (!response.ok) {
                throw new Error('Dílo nenalezeno.');
            }

            const data = await response.json();

            if (!scannedArts.some(a => a.id === data.id)) {
                setScannedArts(prev => [...prev, data]);
            }
        } catch (err) {
            console.error("Chyba při načítání díla:", err);
            setError('Nepodařilo se načíst dílo.');
        }
    };

    const handleRemoveArt = (idToRemove) => {
        setScannedArts(prev => prev.filter(a => a.id !== idToRemove));
    };

    const handleFinalDone = () => {
        onScanComplete(scannedArts);
    };

    const renderScanner = () => (
        <>
            {method === 'camera' && (
                <CameraScanner
                    onDetected={handleScannedArt}
                    scannedArts={scannedArts}
                    onDone={handleFinalDone}
                />
            )}
            {method === 'file' && (
                <FileUploader
                    onFileSelected={handleScannedArt}
                    onCancel={onClose}
                    scannedArts={scannedArts}
                    onDone={handleFinalDone}
                    onRemove={handleRemoveArt}
                />
            )}

            {error && <p className="error-message">{error}</p>}

            {scannedArts.length > 0 && (
                <div style={{marginTop: '1rem'}}>
                    <h4>Naskenovaná díla:</h4>
                    <ul>
                        {scannedArts.map((art) => (
                            <li key={art.id} style={{display: 'flex', justifyContent: 'space-between'}}>
                                {art.name}
                                <button onClick={() => handleRemoveArt(art.id)} style={{
                                    background: 'transparent',
                                    border: 'none',
                                    color: 'red',
                                    cursor: 'pointer'
                                }}>❌
                                </button>
                            </li>
                        ))}
                    </ul>
                    <button className="btn-primary" onClick={handleFinalDone}>Done</button>
                </div>
            )}
            <button className="btn-secondary" onClick={onClose}>Zrušit</button>
        </>
    );

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                {!method ? (
                    <>
                        <h2>Vyber způsob načtení QR</h2>
                        <div className="modal-btn-group">
                            <button className="btn-primary" onClick={() => setMethod('camera')}>📷 Naskenovat kamerou
                            </button>
                            <button className="btn-primary" onClick={() => setMethod('file')}>🖼️ Nahrát obrázek</button>
                        </div>
                        {error && <p className="error-message">{error}</p>}
                        <button className="btn-secondary" onClick={onClose}>Zrušit</button>
                    </>
                ) : (
                    renderScanner()
                )}
            </div>
        </div>
    );
};

export default QRMethodModal;
