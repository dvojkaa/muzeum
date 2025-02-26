package cz.cvut.fel.muzeumSys.dto;

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
        Integer groupId,
        Integer roomId
) {
}
