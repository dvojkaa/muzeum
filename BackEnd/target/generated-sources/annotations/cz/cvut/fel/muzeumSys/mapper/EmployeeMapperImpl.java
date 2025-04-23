package cz.cvut.fel.muzeumSys.mapper;

import cz.cvut.fel.muzeumSys.dto.Record.EmployeeDto;
import cz.cvut.fel.muzeumSys.model.Employee;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-23T17:21:40+0200",
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
        String password = null;
        String firstName = null;
        String lastName = null;
        String phoneNumber = null;
        Boolean suspended = null;

        id = employeeEntity.getId();
        email = employeeEntity.getEmail();
        password = employeeEntity.getPassword();
        firstName = employeeEntity.getFirstName();
        lastName = employeeEntity.getLastName();
        phoneNumber = employeeEntity.getPhoneNumber();
        suspended = employeeEntity.getSuspended();

        EmployeeDto employeeDto = new EmployeeDto( id, email, password, firstName, lastName, phoneNumber, suspended );

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
        employee.setFirstName( employeeDto.firstName() );
        employee.setLastName( employeeDto.lastName() );
        employee.setPhoneNumber( employeeDto.phoneNumber() );
        employee.setSuspended( employeeDto.suspended() );

        return employee;
    }
}
