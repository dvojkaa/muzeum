package cz.cvut.fel.muzeumSys.mapper;

import cz.cvut.fel.muzeumSys.dto.QRCodeDto;
import cz.cvut.fel.muzeumSys.model.QRCode;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface QRCodeMapper {

    QRCodeDto toDto(QRCode qrCodeEntity);

    QRCode toEntity(QRCodeDto qrCodeDto);

}
