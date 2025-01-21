package cz.cvut.fel.nss.SaunaStudio.mapper;

import cz.cvut.fel.nss.SaunaStudio.bo.CustomerBO;
import cz.cvut.fel.nss.SaunaStudio.dto.CustomerDTO;
import cz.cvut.fel.nss.SaunaStudio.dto.CustomerRegistrationDTO;
import cz.cvut.fel.nss.SaunaStudio.model.Customer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-08T00:12:04+0200",
    comments = "version: 1.6.0, compiler: javac, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerBO customerToCustomerBO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerBO customerBO = new CustomerBO();

        customerBO.setUsername( customer.getUsername() );
        customerBO.setPassword( customer.getPassword() );
        customerBO.setFirstName( customer.getFirstName() );
        customerBO.setLastName( customer.getLastName() );
        customerBO.setEmail( customer.getEmail() );
        customerBO.setPhoneNumber( customer.getPhoneNumber() );

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
    public Customer customerBoToCustomer(CustomerBO customerBO) {
        if ( customerBO == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setUsername( customerBO.getUsername() );
        customer.setPassword( customerBO.getPassword() );
        customer.setFirstName( customerBO.getFirstName() );
        customer.setLastName( customerBO.getLastName() );
        customer.setEmail( customerBO.getEmail() );
        customer.setPhoneNumber( customerBO.getPhoneNumber() );

        return customer;
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
