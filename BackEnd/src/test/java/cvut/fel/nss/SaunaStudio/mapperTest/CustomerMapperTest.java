package cvut.fel.nss.SaunaStudio.mapperTest;

import cz.cvut.fel.nss.SaunaStudio.bo.CustomerBO;
import cz.cvut.fel.nss.SaunaStudio.configuration.TestConfig;
import cz.cvut.fel.nss.SaunaStudio.mapper.CustomerMapper;
import cz.cvut.fel.nss.SaunaStudio.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
public class CustomerMapperTest {

    @Autowired
    private CustomerMapper customerMapper;

    @Test
    public void testCustomerToCustomerBo() {
        Customer customer = new Customer();
        customer.setEmail("test@test.test");
        customer.setSuspended(false);
        customer.setFirstName("firstname");
        customer.setLastName("lastname");
        customer.setPhoneNumber("123123123");
        customer.setId(1);
        customer.setPassword("password");
        customer.setUsername("username");

        CustomerBO customerBO = customerMapper.customerToCustomerBO(customer);

        assertEquals(customer.getPhoneNumber(), customerBO.getPhoneNumber());
        assertEquals(customer.getEmail(), customerBO.getEmail());
        assertEquals(customer.getFirstName(), customerBO.getFirstName());
        assertEquals(customer.getLastName(), customerBO.getLastName());
    }
}