package cz.cvut.fel.muzeumSys.mapper;

import cz.cvut.fel.muzeumSys.dto.Record.EmployeeDto;
import cz.cvut.fel.muzeumSys.dto.Record.UserDto;
import cz.cvut.fel.muzeumSys.model.Employee;
import cz.cvut.fel.muzeumSys.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto toDto(Employee employeeEntity);

    Employee toEntity(EmployeeDto employeeDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEmployeeFromDto(UserDto userDto, @MappingTarget User existingUser);
}
