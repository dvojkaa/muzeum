package cz.cvut.fel.MuzeumSys.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Room entity.
 */
@Data
@NoArgsConstructor
public class RoomDTO {

    private Integer id;
    private String name;
    private Integer floorNumber;
}
