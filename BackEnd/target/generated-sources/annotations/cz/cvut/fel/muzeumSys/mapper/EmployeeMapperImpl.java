package cz.cvut.fel.muzeumSys.mapper;

import cz.cvut.fel.muzeumSys.dto.Record.EmployeeDto;
import cz.cvut.fel.muzeumSys.model.Employee;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-04T16:18:57+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeDto toDto(Employee employeeEntity) {
        if ( employeeEntity == null ) {
            return null;
        }

        Long id = null;
        String email = null;
        String role = null;
        String password = null;
        String firstName = null;
        String lastName = null;
        String phoneNumber = null;
        Boolean suspended = null;

        EmployeeDto employeeDto = new EmployeeDto( id, email, role, password, firstName, lastName, phoneNumber, suspended );

        return employeeDto;
    }

    @Override
    public Employee toEntity(EmployeeDto employeeDto) {
        if ( employeeDto == null ) {
            return null;
        }

        Employee employee = new Employee();

        return employee;
    }
}
