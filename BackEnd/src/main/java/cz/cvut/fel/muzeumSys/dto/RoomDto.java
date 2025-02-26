package cz.cvut.fel.muzeumSys.dto;

/**
 * Data Transfer Object for Room entity.
 */

public record RoomDto (
    Long id,
    String name,
    Integer floorNumber
        ){
}
