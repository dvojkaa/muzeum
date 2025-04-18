package cz.cvut.fel.muzeumSys.dto.Record;

/**
 * Data Transfer Object for Art entity.
 */

public record ArtDto(
        Long id,
        String name,
        String era,
        String type,
        String color,
        String description,
        String parameters,
        String author,
        String qrCode,
        String imgPath,
        Long group_id,
        Long room_id
) {
}
