package cz.cvut.fel.muzeumSys.dto.Record;


/**
 * Data Transfer Object for QRCode entity.
 */

public record QRCodeDto(
        Long id,
        Long artId,
        String qrCode,
        String color,
        String imagePath,
        String base64Image
) {

}
