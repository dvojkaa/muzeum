package cz.cvut.fel.muzeumSys.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Art entity.
 */
@Data
@NoArgsConstructor
public class ArtDto {

    private Long id;
    private String name;
    private String era;
    private String type;
    private String description;
    private String parameters;
    private String author;
    private String qrCode;
    private Integer groupId;
    private Integer roomId;
}
