package cz.cvut.fel.muzeumSys.rest.controller;

import com.google.zxing.WriterException;
import cz.cvut.fel.muzeumSys.dto.Record.QRCodeDto;
import cz.cvut.fel.muzeumSys.model.QRCode;
import cz.cvut.fel.muzeumSys.service.QRCodeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/qrcode")
public class QRCodeController {

    private final QRCodeService qrCodeService;

    public QRCodeController(QRCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QRCode> createQRCode(@RequestBody Long artId) {
        try {
            QRCode qrCode = qrCodeService.createQRCode(artId);
            return ResponseEntity.ok(qrCode);
        } catch (WriterException | IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(value = "/get", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QRCodeDto> getQRCode(@RequestBody Long artId) {
        try {
            QRCodeDto dto = qrCodeService.getQRCodeByArtId(artId);
            return ResponseEntity.ok(dto);
        } catch (IOException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
