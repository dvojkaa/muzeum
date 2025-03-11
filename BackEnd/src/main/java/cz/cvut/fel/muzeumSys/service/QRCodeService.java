package cz.cvut.fel.muzeumSys.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import cz.cvut.fel.muzeumSys.dto.Record.QRCodeDto;
import cz.cvut.fel.muzeumSys.mapper.QRCodeMapper;
import cz.cvut.fel.muzeumSys.model.QRCode;
import cz.cvut.fel.muzeumSys.repository.QRCodeRepository;
import org.springframework.stereotype.Service;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class QRCodeService {

    private final QRCodeRepository qrCodeRepository;
    private final QRCodeMapper qrCodeMapper;

    public QRCodeService(QRCodeRepository qrCodeRepository, QRCodeMapper qrCodeMapper) {
        this.qrCodeRepository = qrCodeRepository;
        this.qrCodeMapper = qrCodeMapper;
    }


    public QRCode createQRCode(QRCodeDto qrCodeDto) throws WriterException, IOException {
        int width = 300;
        int height = 300;

        // Konverze HEX barvy na RGB
        int qrColor = Color.decode(ColorDecode(qrCodeDto.color())).getRGB();
        int backgroundColor = Color.WHITE.getRGB(); // Bílé pozadí

        // Vygenerování QR kódu
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeDto.qrCode(), BarcodeFormat.QR_CODE, width, height);

        // Nastavení barvy QR kódu
        MatrixToImageConfig config = new MatrixToImageConfig(qrColor, backgroundColor);
        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix, config);

        // Cesta k uložení souboru
        String fileName = "QR_" + qrCodeDto.qrCode() + ".png";
        Path filePath = Paths.get("src/main/resources/QRCodes/" + fileName);

        // Vytvoření složky, pokud neexistuje
        File directory = new File("src/main/resources/QRCodes");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Uložení QR obrázku
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", filePath, config);

        // Převedení DTO na entitu
        QRCode qrCode = qrCodeMapper.toEntity(qrCodeDto);
        qrCode.setImagePath(filePath.toString());

        // Uložení do databáze
        return qrCodeRepository.save(qrCode);
    }

    private String ColorDecode(String color) {
        return switch (color) {
            case "RED" -> "#FF0000"; // Hex pro červenou

            case "GREEN" -> "#00FF00"; // Hex pro zelenou

            case "YELLOW" -> "#FFFF00"; // Hex pro žlutou

            default -> "Unknown color"; // Pro neznámou barvu

        };
    }
}
