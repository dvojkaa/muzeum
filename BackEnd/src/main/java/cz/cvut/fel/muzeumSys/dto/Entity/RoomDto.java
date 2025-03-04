package cz.cvut.fel.muzeumSys.dto.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object for Room entity.
 */
@Data
@NoArgsConstructor
@Getter
@Setter
public class RoomDto {

    private Integer id;
    private String name;
    private Integer floorNumber;
}
