package cz.cvut.fel.MuzeumSys.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Art entity.
 */
@Data
@NoArgsConstructor
public class ArtDTO {

    private Integer id;
    private String name;
    private String era;
    private String type;
    private String qrCode;
    private String description;
    private String parameters;
    private String author;
    private Integer groupId;
    private Integer roomId;
}
