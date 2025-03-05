package cz.cvut.fel.muzeumSys.mapper;

import cz.cvut.fel.muzeumSys.dto.Record.EmployeeDto;
import cz.cvut.fel.muzeumSys.model.Employee;
import cz.cvut.fel.muzeumSys.model.enums.Role;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-05T15:39:15+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Amazon.com Inc.)"
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

        id = employeeEntity.getId();
        email = employeeEntity.getEmail();
        if ( employeeEntity.getRole() != null ) {
            role = employeeEntity.getRole().name();
        }
        password = employeeEntity.getPassword();
        firstName = employeeEntity.getFirstName();
        lastName = employeeEntity.getLastName();
        phoneNumber = employeeEntity.getPhoneNumber();
        suspended = employeeEntity.getSuspended();

        EmployeeDto employeeDto = new EmployeeDto( id, email, role, password, firstName, lastName, phoneNumber, suspended );

        return employeeDto;
    }

    @Override
    public Employee toEntity(EmployeeDto employeeDto) {
        if ( employeeDto == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setId( employeeDto.id() );
        employee.setEmail( employeeDto.email() );
        employee.setPassword( employeeDto.password() );
        if ( employeeDto.role() != null ) {
            employee.setRole( Enum.valueOf( Role.class, employeeDto.role() ) );
        }
        employee.setFirstName( employeeDto.firstName() );
        employee.setLastName( employeeDto.lastName() );
        employee.setPhoneNumber( employeeDto.phoneNumber() );
        employee.setSuspended( employeeDto.suspended() );

        return employee;
    }
}
