//package cz.cvut.fel.muzeumSys.service;
//
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.WriterException;
//import com.google.zxing.client.j2se.MatrixToImageConfig;
//import com.google.zxing.client.j2se.MatrixToImageWriter;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.qrcode.QRCodeWriter;
//import cz.cvut.fel.muzeumSys.dto.Record.QRCodeDto;
//import cz.cvut.fel.muzeumSys.mapper.QRCodeMapper;
//import cz.cvut.fel.muzeumSys.model.Art;
//import cz.cvut.fel.muzeumSys.model.QRCode;
//import cz.cvut.fel.muzeumSys.repository.ArtRepository;
//import cz.cvut.fel.muzeumSys.repository.QRCodeRepository;
//import org.springframework.stereotype.Service;
//
//
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.Base64;
//import java.util.Optional;
//
//@Service
//public class QRCodeService {
//
//    private final QRCodeRepository qrCodeRepository;
//    private final QRCodeMapper qrCodeMapper;
//    private final ArtRepository artRepository;
//
//    public QRCodeService(QRCodeRepository qrCodeRepository, QRCodeMapper qrCodeMapper, ArtRepository artRepository) {
//        this.qrCodeRepository = qrCodeRepository;
//        this.qrCodeMapper = qrCodeMapper;
//        this.artRepository = artRepository;
//    }
//
//
//    public QRCode createQRCode(Long artId) throws WriterException, IOException {
//        // Najdeme art podle ID
//        Optional<Art> optionalArt = artRepository.findById(artId);
//        if (optionalArt.isEmpty()) {
//            throw new IllegalArgumentException("Artwork not found for ID: " + artId);
//        }
//
//        Art art = optionalArt.get();
//        String qrContent = ("http://localhost:5173/art/" + artId); // co bude vevnitř QR kódu
//        String color = art.getColor();
//
//        int width = 300;
//        int height = 300;
//
//        int qrColor = Color.decode(ColorDecode(color)).getRGB();
//        int backgroundColor = Color.WHITE.getRGB();
//
//        // QR generování
//        QRCodeWriter qrCodeWriter = new QRCodeWriter();
//        BitMatrix bitMatrix = qrCodeWriter.encode(qrContent, BarcodeFormat.QR_CODE, width, height);
//
//        MatrixToImageConfig config = new MatrixToImageConfig(qrColor, backgroundColor);
//        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix, config);
//
//        // Složka
//        File directory = new File("files/qrcodes/");
//        if (!directory.exists()) {
//            directory.mkdirs();
//        }
//
//        // Název souboru
//        String fileName = "QR_" + art.getName()+ art.getId() + ".png";
//        Path filePath = Paths.get(directory.getPath(), fileName);
//
//        // Uložení souboru
//        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", filePath, config);
//
//        // QRCode entita
//        QRCode qrCode = new QRCode();
//        qrCode.setQrCode(qrContent);
//        qrCode.setColor(cz.cvut.fel.muzeumSys.model.enums.Color.valueOf(color));
//        qrCode.setArtId(art.getId());
//        qrCode.setImagePath(filePath.toString());
//
//        art.setQrCodePath(filePath.toString());
//
//        return qrCodeRepository.save(qrCode);
//    }
//
//
//    private String ColorDecode(String color) {
//        return switch (color) {
//            case "RED" -> "#FF0000"; // Hex pro červenou
//
//            case "GREEN" -> "#00FF00"; // Hex pro zelenou
//
//            case "YELLOW" -> "#FFFF00"; // Hex pro žlutou
//
//            default -> "Unknown color"; // Pro neznámou barvu
//
//        };
//    }
//
//    public QRCodeDto getQRCodeByArtId(Long artId) throws IOException {
//        Optional<Art> optionalArt = artRepository.findById(artId);
//        if (optionalArt.isEmpty()) {
//            throw new IllegalArgumentException("Artwork not found for ID: " + artId);
//        }
//
//        Art art = optionalArt.get();
//
//        // Načtení QR obrázku podle cesty v Art
//        File imgFile = new File(art.getQrCodePath());
//        byte[] fileContent = Files.readAllBytes(imgFile.toPath());
//        String base64Image = Base64.getEncoder().encodeToString(fileContent);
//
//        return new QRCodeDto(
//                null,
//                ("http://localhost:5173/art/" + artId),         // obsah QR kódu
//                art.getColor(),          // barva
//                art.getQrCodePath(),        // cesta k obrázku
//                base64Image              // base64 obrázek
//        );
//    }
//}
package cz.cvut.fel.muzeumSys.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import cz.cvut.fel.muzeumSys.dto.Record.QRCodeDto;
import cz.cvut.fel.muzeumSys.mapper.QRCodeMapper;
import cz.cvut.fel.muzeumSys.model.Art;
import cz.cvut.fel.muzeumSys.model.QRCode;
import cz.cvut.fel.muzeumSys.repository.ArtRepository;
import cz.cvut.fel.muzeumSys.repository.QRCodeRepository;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Base64;
import java.util.Optional;

@Service
public class QRCodeService {

    private final QRCodeRepository qrCodeRepository;
    private final QRCodeMapper qrCodeMapper;
    private final ArtRepository artRepository;

    public QRCodeService(QRCodeRepository qrCodeRepository, QRCodeMapper qrCodeMapper, ArtRepository artRepository) {
        this.qrCodeRepository = qrCodeRepository;
        this.qrCodeMapper = qrCodeMapper;
        this.artRepository = artRepository;
    }

    public QRCode createQRCode(Long artId) throws WriterException, IOException {
        Optional<Art> optionalArt = artRepository.findById(artId);
        if (optionalArt.isEmpty()) {
            throw new IllegalArgumentException("Artwork not found for ID: " + artId);
        }

        Art art = optionalArt.get();
//        String qrContent = "http://localhost:5173/art/" + artId;
        String qrContent = "http://muzeum.vercel.app/art/" + artId;

        String color = art.getColor();

        int width = 300;
        int height = 300;
        int qrColor = Color.decode(ColorDecode(color)).getRGB();
        int backgroundColor = Color.WHITE.getRGB();

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(qrContent, BarcodeFormat.QR_CODE, width, height);
        MatrixToImageConfig config = new MatrixToImageConfig(qrColor, backgroundColor);

        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix, config);

        Path directory = Paths.get("files/qrcodes");
        Files.createDirectories(directory);

        String fileName = "QR_" + art.getName().replace(" ", "_") + art.getId() + ".png";
        Path filePath = directory.resolve(fileName);

        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", filePath, config);

        QRCode qrCode = new QRCode();
        qrCode.setQrCode(qrContent);
        qrCode.setColor(cz.cvut.fel.muzeumSys.model.enums.Color.valueOf(color));
        qrCode.setArtId(art.getId());
        qrCode.setImagePath(fileName); // ⬅ důležité: pouze název!

        art.setQrCodePath(fileName);

        return qrCodeRepository.save(qrCode);
    }

    private String ColorDecode(String color) {
        return switch (color) {
            case "RED" -> "#FF0000";
            case "GREEN" -> "#00FF00";
            case "YELLOW" -> "#FDB702";
            default -> "#000000";
        };
    }

    public QRCodeDto getQRCodeByArtId(Long artId) throws IOException {
        Optional<Art> optionalArt = artRepository.findById(artId);
        if (optionalArt.isEmpty()) {
            throw new IllegalArgumentException("Artwork not found for ID: " + artId);
        }

        Art art = optionalArt.get();
        String fileName = art.getQrCodePath();

        if (fileName == null) {
            throw new IllegalStateException("QR code not generated for this artwork.");
        }

        Path filePath = Paths.get("files/qrcodes", fileName);
        byte[] fileContent = Files.readAllBytes(filePath);
        String base64Image = Base64.getEncoder().encodeToString(fileContent);

        return new QRCodeDto(
                null,
                art.getId(),
//                "http://localhost:5173/art/" + art.getId(),
                "https://muzeum.vercel.app/art/" + art.getId(),
                art.getColor(),
                fileName,
                base64Image
        );
    }
}
