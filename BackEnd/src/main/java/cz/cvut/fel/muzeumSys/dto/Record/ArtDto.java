package cz.cvut.fel.muzeumSys.dto.Record;

/**
 * Data Transfer Object for Art entity.
 */

public record ArtDto(
        Long id,
        String name,
        String era,
        String type,
        String description,
        String parameters,
        String author,
        String qrCode,
        Integer group,
        Integer room
) {
}
