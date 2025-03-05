package cz.cvut.fel.muzeumSys.mapper;

import cz.cvut.fel.muzeumSys.dto.Record.QRCodeDto;
import cz.cvut.fel.muzeumSys.model.QRCode;
import cz.cvut.fel.muzeumSys.model.enums.Color;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-05T15:39:15+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Amazon.com Inc.)"
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

        id = qrCodeEntity.getId();
        qrCode = qrCodeEntity.getQrCode();
        if ( qrCodeEntity.getColor() != null ) {
            color = qrCodeEntity.getColor().name();
        }
        imagePath = qrCodeEntity.getImagePath();

        QRCodeDto qRCodeDto = new QRCodeDto( id, qrCode, color, imagePath );

        return qRCodeDto;
    }

    @Override
    public QRCode toEntity(QRCodeDto qrCodeDto) {
        if ( qrCodeDto == null ) {
            return null;
        }

        QRCode qRCode = new QRCode();

        qRCode.setId( qrCodeDto.id() );
        qRCode.setQrCode( qrCodeDto.qrCode() );
        if ( qrCodeDto.color() != null ) {
            qRCode.setColor( Enum.valueOf( Color.class, qrCodeDto.color() ) );
        }
        qRCode.setImagePath( qrCodeDto.imagePath() );

        return qRCode;
    }
}
