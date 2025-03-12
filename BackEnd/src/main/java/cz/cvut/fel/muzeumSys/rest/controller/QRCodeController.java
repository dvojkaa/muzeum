package cz.cvut.fel.muzeumSys.rest.controller;

import com.google.zxing.WriterException;
import cz.cvut.fel.muzeumSys.dto.Record.QRCodeDto;
import cz.cvut.fel.muzeumSys.model.QRCode;
import cz.cvut.fel.muzeumSys.service.QRCodeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/qrcode")
public class QRCodeController {

    private final QRCodeService qrCodeService;

    public QRCodeController(QRCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @PostMapping(value="/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QRCode> createQRCode(@RequestBody QRCodeDto qrCodeDto){
        try {
            QRCode qrCode = qrCodeService.createQRCode(qrCodeDto);
            return ResponseEntity.ok(qrCode);
        } catch (WriterException | IOException e) {
            return ResponseEntity.internalServerError().build();
        }    }
}
