package cz.cvut.fel.muzeumSys.dto;



/**
 * Data Transfer Object for QRCode entity.
 */

public record QRCodeDto(
    Long id,
    String qrCode,
    String color,
    String imagePath
){

}
