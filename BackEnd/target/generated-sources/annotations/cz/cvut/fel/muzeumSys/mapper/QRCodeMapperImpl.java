package cz.cvut.fel.muzeumSys.mapper;

import cz.cvut.fel.muzeumSys.dto.Record.QRCodeDto;
import cz.cvut.fel.muzeumSys.model.QRCode;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-27T16:10:01+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class QRCodeMapperImpl implements QRCodeMapper {

    @Override
    public QRCodeDto toDto(QRCode qrCodeEntity) {
        if ( qrCodeEntity == null ) {
            return null;
        }

        Long id = null;
        String qrCode = null;
        String color = null;
        String imagePath = null;

        QRCodeDto qRCodeDto = new QRCodeDto( id, qrCode, color, imagePath );

        return qRCodeDto;
    }

    @Override
    public QRCode toEntity(QRCodeDto qrCodeDto) {
        if ( qrCodeDto == null ) {
            return null;
        }

        QRCode qRCode = new QRCode();

        return qRCode;
    }
}
