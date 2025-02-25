package cz.cvut.fel.muzeumSys.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Room entity.
 */
@Data
@NoArgsConstructor
public class RoomDto {

    private Long id;
    private String name;
    private Integer floorNumber;
}
