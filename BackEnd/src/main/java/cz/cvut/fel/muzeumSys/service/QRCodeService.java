package cz.cvut.fel.muzeumSys.service;

import cz.cvut.fel.muzeumSys.dto.Record.QRCodeDto;
import cz.cvut.fel.muzeumSys.mapper.QRCodeMapper;
import cz.cvut.fel.muzeumSys.model.QRCode;
import cz.cvut.fel.muzeumSys.repository.QRCodeRepository;
import org.springframework.stereotype.Service;

@Service
public class QRCodeService {

    private final QRCodeRepository qrCodeRepository;
    private final QRCodeMapper qrCodeMapper;

    public QRCodeService(QRCodeRepository qrCodeRepository, QRCodeMapper qrCodeMapper) {
        this.qrCodeRepository = qrCodeRepository;
        this.qrCodeMapper = qrCodeMapper;
    }

    public QRCode createQRCode(QRCodeDto qrCodeDto) {
        QRCode qrCode = qrCodeMapper.toEntity(qrCodeDto);

        qrCodeRepository.save(qrCode);
        return qrCode;
    }
}
