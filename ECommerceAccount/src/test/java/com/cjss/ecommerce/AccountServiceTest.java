package com.cjss.ecommerce;

import com.cjss.ecommerce.entity.AddressEntity;
import com.cjss.ecommerce.entity.RegisterCustomerEntity;
import com.cjss.ecommerce.model.Address;
import com.cjss.ecommerce.model.RegisterCustomer;
import com.cjss.ecommerce.model.Login;
import com.cjss.ecommerce.model.Token;
import com.cjss.ecommerce.repository.RegisterCustomerRepository;
import com.cjss.ecommerce.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest     {

    @InjectMocks
    private AccountService accountService;
    @InjectMocks
    private ProductService productService;
    @Mock
    private RegisterCustomerRepository registerCustomerRepository;
    @Mock
    private Token token;

    @Test
    public void testAccountService() {
        RegisterCustomerEntity customerEntity = new RegisterCustomerEntity();
        RegisterCustomer customer = new RegisterCustomer();
        Address address = new Address();
        AddressEntity addressEntity = new AddressEntity();
        List<AddressEntity> addresses = new ArrayList<>();
        Login login = new Login();

        String email = "anusha.banthu@gmail.com";
        String password = "abcdef";
        login.setEmail(email);
        login.setPassword(password);

        customerEntity.setFirstName("anusha");
        customerEntity.setLastName("banthu");
        customerEntity.setEmail(email);
        customerEntity.setPassword(password);
        customerEntity.setMobileNumber(123243l);
//        customerEntity.setAddresses();

        address.setLine1("1");
        address.setLine2("2");
        address.setEmail(email);
        address.setCity("city1");
        address.setState("state1");
        address.setPostalCode(1111);
        address.setBillingAddress(true);
        address.setShippingAddress(true);

        addresses.add(addressToAddressEntity(address));
        customerEntity.setAddressEntityList(addresses);
        customer = customerEntityToCustomer(customerEntity);
        Optional<RegisterCustomerEntity> customerEntityOptional = Optional.of(customerEntity);
        List<RegisterCustomerEntity> customerEntityList = new ArrayList<>();
        customerEntityList.add(customerEntity);

        // Given
        when(registerCustomerRepository.existsById(email)).thenReturn(true);
        when(registerCustomerRepository.findById(email)).thenReturn(customerEntityOptional);
        when(registerCustomerRepository.findAll()).thenReturn(customerEntityList);
        when(token.getEmail()).thenReturn(email);
        when(token.getTokenExpiryDateTime()).thenReturn(LocalDateTime.now().plusSeconds(1));
//        accountService.setEncryptorConfig();
//        when(accountService.encryptor.decrypt(password)).thenReturn(password);
//        when(accountServiceMock.loginUser(login)).thenReturn(ResponseEntity.status(HttpStatus.ACCEPTED).body("User logged in successfully (CODE 202)\n-------"));
//        accountService.token.setEmail(email);
//        Token never expires
//        accountService.token.setTokenExpiryDateTime(LocalDateTime.now().plusSeconds(1));
        accountService.registerCustomer(customer);
        System.out.println(accountService.getCustomerDetailsById(email).getBody());
        assertNotNull(accountService.getCustomerDetailsById(email));

        // Checking for unauthoried status code when we try to add address without logging in
//        assertEquals(accountService.addAddressDetails(address),ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login expired. Please login again to continue (CODE 401)"));

//        System.out.println("Login:"+accountService.loginUser(login));
//        accountService.addAddressDetails(address);
//        assertEquals(accountService.addAddressDetails(address).getStatusCode(),202);
    }
    public RegisterCustomer customerEntityToCustomer(RegisterCustomerEntity entity){
        RegisterCustomer customer = new RegisterCustomer();
        customer.setFirstName(entity.getFirstName());
        customer.setLastName(entity.getLastName());
        customer.setEmail(entity.getEmail());
        customer.setPassword(entity.getPassword());
        customer.setMobileNumber(entity.getMobileNumber());
        return customer;
    }
    public AddressEntity addressToAddressEntity(Address address){
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setShippingAddress(address.getShippingAddress());
        addressEntity.setBillingAddress(address.getBillingAddress());
        addressEntity.setEmail(address.getEmail());
        addressEntity.setCity(address.getCity());
        addressEntity.setState(address.getState());
        addressEntity.setPostalCode(address.getPostalCode());
        addressEntity.setLine1(address.getLine1());
        addressEntity.setLine2(address.getLine2());
        return addressEntity;
    }
}
