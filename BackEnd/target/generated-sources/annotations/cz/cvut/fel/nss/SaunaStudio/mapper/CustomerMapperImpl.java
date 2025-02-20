package cz.cvut.fel.nss.SaunaStudio.mapper;

import cz.cvut.fel.nss.SaunaStudio.bo.CustomerBO;
import cz.cvut.fel.nss.SaunaStudio.dto.CustomerDTO;
import cz.cvut.fel.nss.SaunaStudio.dto.CustomerRegistrationDTO;
import cz.cvut.fel.nss.SaunaStudio.model.Employee;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-20T15:46:18+0100",
    comments = "version: 1.6.0, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerBO customerToCustomerBO(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        CustomerBO customerBO = new CustomerBO();

        customerBO.setUsername( employee.getUsername() );
        customerBO.setPassword( employee.getPassword() );
        customerBO.setFirstName( employee.getFirstName() );
        customerBO.setLastName( employee.getLastName() );
        customerBO.setEmail( employee.getEmail() );
        customerBO.setPhoneNumber( employee.getPhoneNumber() );

        return customerBO;
    }

    @Override
    public CustomerDTO customerBoToCustomerDto(CustomerBO customerBO) {
        if ( customerBO == null ) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setFirstName( customerBO.getFirstName() );
        customerDTO.setLastName( customerBO.getLastName() );
        customerDTO.setEmail( customerBO.getEmail() );
        customerDTO.setPhoneNumber( customerBO.getPhoneNumber() );

        return customerDTO;
    }

    @Override
    public CustomerBO customerDtoToCustomerBo(CustomerDTO customerDTO) {
        if ( customerDTO == null ) {
            return null;
        }

        CustomerBO customerBO = new CustomerBO();

        customerBO.setFirstName( customerDTO.getFirstName() );
        customerBO.setLastName( customerDTO.getLastName() );
        customerBO.setEmail( customerDTO.getEmail() );
        customerBO.setPhoneNumber( customerDTO.getPhoneNumber() );

        return customerBO;
    }

    @Override
    public Employee customerBoToCustomer(CustomerBO customerBO) {
        if ( customerBO == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setUsername( customerBO.getUsername() );
        employee.setPassword( customerBO.getPassword() );
        employee.setFirstName( customerBO.getFirstName() );
        employee.setLastName( customerBO.getLastName() );
        employee.setEmail( customerBO.getEmail() );
        employee.setPhoneNumber( customerBO.getPhoneNumber() );

        return employee;
    }

    @Override
    public CustomerBO customerRegistrationDtoToCustomerBO(CustomerRegistrationDTO customerRegistrationDTO) {
        if ( customerRegistrationDTO == null ) {
            return null;
        }

        CustomerBO customerBO = new CustomerBO();

        customerBO.setUsername( customerRegistrationDTO.getUsername() );
        customerBO.setPassword( customerRegistrationDTO.getPassword() );
        customerBO.setFirstName( customerRegistrationDTO.getFirstName() );
        customerBO.setLastName( customerRegistrationDTO.getLastName() );
        customerBO.setEmail( customerRegistrationDTO.getEmail() );
        customerBO.setPhoneNumber( customerRegistrationDTO.getPhoneNumber() );

        return customerBO;
    }
}
