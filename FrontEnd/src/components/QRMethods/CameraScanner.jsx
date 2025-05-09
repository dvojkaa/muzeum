import React, { useEffect, useRef, useState } from 'react';
import { Html5Qrcode } from 'html5-qrcode';

const CameraScanner = ({ onDetected, scannedArts = [], onDone }) => {
    const [cameras, setCameras] = useState([]);
    const [selectedCameraId, setSelectedCameraId] = useState(null);
    const scannerRef = useRef(null);
    const isRunningRef = useRef(false);

    useEffect(() => {
        Html5Qrcode.getCameras()
            .then((devices) => {
                setCameras(devices);
                if (devices.length > 0) {
                    // Pokud je druhá kamera, vezmi ji, jinak použij první
                    const cameraId = devices[1]?.id || devices[0].id;
                    setSelectedCameraId(cameraId);
                } else {
                    console.warn("Žádné kamery nebyly nalezeny.");
                }
            })
            .catch((err) => {
                console.error("Chyba při získávání kamer:", err);
            });
    }, []);

    useEffect(() => {
        if (!selectedCameraId) return;

        const qrRegionId = "reader";
        const html5QrCode = new Html5Qrcode(qrRegionId);
        scannerRef.current = html5QrCode;

        const readerElement = document.getElementById(qrRegionId);
        if (readerElement) readerElement.innerHTML = "";

        html5QrCode
            .start(
                selectedCameraId,
                { fps: 10, qrbox: 250 },
                (decodedText) => {
                    if (isRunningRef.current) {
                        isRunningRef.current = false;
                        html5QrCode.stop().then(() => onDetected(decodedText));
                    }
                },
                (error) => {}
            )
            .then(() => {
                isRunningRef.current = true;
            })
            .catch((err) => {
                console.error("Chyba při spuštění skeneru:", err);
            });

        return () => {
            if (scannerRef.current && isRunningRef.current) {
                scannerRef.current
                    .stop()
                    .then(() => {
                        isRunningRef.current = false;
                    })
                    .catch((err) => console.warn("Stop error:", err));
            }
        };
    }, [selectedCameraId, onDetected]);

    return (
        <div>
            <h3>Skenuj QR kód</h3>

            {cameras.length > 1 && (
                <select
                    onChange={(e) => setSelectedCameraId(e.target.value)}
                    value={selectedCameraId}
                    style={{ marginBottom: '1rem' }}
                >
                    {cameras.map((cam) => (
                        <option key={cam.id} value={cam.id}>
                            {cam.label || `Kamera ${cam.id}`}
                        </option>
                    ))}
                </select>
            )}

            <div id="reader" style={{ width: "100%" }} />
        </div>
    );
};

export default CameraScanner;
