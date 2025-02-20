package cz.cvut.fel.MuzeumSys.mapper;

import cz.cvut.fel.MuzeumSys.bo.QRCodeBO;
import cz.cvut.fel.MuzeumSys.dto.QRCodeDTO;
import cz.cvut.fel.MuzeumSys.model.QRCode;
import org.mapstruct.Mapper;

/**
 * Mapper pro převod mezi entitami {@link QRCode}, {@link QRCodeBO} a {@link QRCodeDTO}.
 */
@Mapper(componentModel = "spring")
public interface QRCodeMapper {

    QRCodeBO qrCodeToQrCodeBO(QRCode qrCode);

    QRCodeDTO qrCodeBoToQrCodeDto(QRCodeBO qrCodeBO);

    QRCodeBO qrCodeDtoToQrCodeBo(QRCodeDTO qrCodeDTO);

    QRCode qrCodeBoToQrCode(QRCodeBO qrCodeBO);
}
