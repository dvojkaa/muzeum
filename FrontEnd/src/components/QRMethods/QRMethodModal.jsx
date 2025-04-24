import React, { useState } from 'react';
import CameraScanner from './CameraScanner';
import FileUploader from './FileUploader';

const QRMethodModal = ({ onClose, onScanComplete, isMultiScan, actionType }) => {
    const [method, setMethod] = useState(null);
    const [scannedArts, setScannedArts] = useState([]);

    const handleScannedArt = async (qrText) => {
        if (actionType === 'edit' && scannedArts.length >= 1) {
            console.warn("V režimu editace lze naskenovat pouze jedno dílo.");
            handleFinalDone()
            return; // Zabráníme naskenování více než jednoho díla
        }

        try {
            const url = new URL(qrText);
            const id = url.pathname.split('/').pop();
            const response = await fetch(`https://muzeum-production.up.railway.app/art/${id}`);
            const data = await response.json();

            if (!scannedArts.some(a => a.id === data.id)) {
                setScannedArts(prev => [...prev, data]);
            }
        } catch (err) {
            console.error("Chyba při načítání díla:", err);
        }
    };

    const handleRemoveArt = (idToRemove) => {
        setScannedArts(prev => prev.filter(a => a.id !== idToRemove));
    };

    const handleFinalDone = () => {
        onScanComplete(scannedArts); // vždy posíláme pole ArtDto
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
