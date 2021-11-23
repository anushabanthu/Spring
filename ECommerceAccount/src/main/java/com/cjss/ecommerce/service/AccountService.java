package com.cjss.ecommerce.service;

import com.cjss.ecommerce.entity.*;
import com.cjss.ecommerce.model.*;
import com.cjss.ecommerce.repository.AddressRepository;
import com.cjss.ecommerce.repository.LoginRepository;
import com.cjss.ecommerce.repository.RegisterCustomerRepository;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountService {
	@Autowired
	private RegisterCustomerRepository customerRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private LoginRepository loginRepository;

	PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
	boolean encryptorCfgNotSet = true;

	public ResponseEntity getCustomerDetailsById(String id){
		RegisterCustomer customer = new RegisterCustomer();
		if(loginRepository.existsById(id) && loginRepository.getById(id).getTokenExpiryDateTime().isAfter(LocalDateTime.now())) {
			if(customerRepository.existsById(id)) {
				Optional<RegisterCustomerEntity> customerEntity = customerRepository.findById(id);
				List<Address> addresses = new ArrayList<>();
				customerEntity.get().getAddressEntityList().forEach(dbAddress -> {
					Address address = new Address();
					address.setId(dbAddress.getId());
					address.setLine1(dbAddress.getLine1());
					address.setLine2(dbAddress.getLine2());
					address.setCity(dbAddress.getCity());
					address.setState(dbAddress.getState());
					address.setPostalCode(dbAddress.getPostalCode());
					address.setShippingAddress(dbAddress.getShippingAddress());
					address.setBillingAddress(dbAddress.getBillingAddress());
					addresses.add(address);
				});
				customer.setFirstName(customerEntity.get().getFirstName());
				customer.setLastName(customerEntity.get().getLastName());
				customer.setEmail(customerEntity.get().getEmail());
				customer.setMobileNumber(customerEntity.get().getMobileNumber());
				customer.setPassword(customerEntity.get().getPassword());

				customer.setAddresses(addresses);
				return ResponseEntity.status(HttpStatus.OK).body(customer);
			}
			else	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with given id doesnt exists (CODE 400)\n");
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login expired. Please login again to continue (CODE 401)\n");
	}

	public ResponseEntity getAddressDetailsById(String id){
		Address address = new Address();
		if(loginRepository.existsById(id) && loginRepository.getById(id).getTokenExpiryDateTime().isAfter(LocalDateTime.now())) {
			if(addressRepository.existsById(id)) {
				Optional<AddressEntity> addressEntity = addressRepository.findById(id);
				address.setBillingAddress(addressEntity.get().getBillingAddress());
				address.setShippingAddress(addressEntity.get().getShippingAddress());
				address.setId(addressEntity.get().getId());
				address.setLine1(addressEntity.get().getLine1());
				address.setLine2(addressEntity.get().getLine2());
				address.setState(addressEntity.get().getState());
				address.setPostalCode(addressEntity.get().getPostalCode());
				address.setCity(addressEntity.get().getCity());
				return ResponseEntity.status(HttpStatus.OK).body(address);
			}
			else	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Address with given id doesnt exists (CODE 400)\n");
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login expired. Please login again to continue (CODE 401)\n");
	}

	public ResponseEntity registerCustomer(RegisterCustomer customer){

		if(!customerRepository.existsById(customer.getEmail())) {

			List<AddressEntity> addresses = new ArrayList<>();
			RegisterCustomerEntity customerEntity = new RegisterCustomerEntity();

			if (encryptorCfgNotSet) {
				setEncryptorConfig();
				encryptorCfgNotSet = false;
			}
			String encryptedPassword = encryptor.encrypt(customer.getPassword());
			customerEntity.setEmail(customer.getEmail());
			customerEntity.setFirstName(customer.getFirstName());
			customerEntity.setLastName(customer.getLastName());
			customerEntity.setMobileNumber(customer.getMobileNumber());
			customerEntity.setPassword(encryptedPassword);

			customer.getAddresses().forEach(address -> {
				AddressEntity addressEntity = new AddressEntity();
				addressEntity.setId(customer.getEmail() + "_" + UUID.randomUUID().toString());
				addressEntity.setLine1(address.getLine1());
				addressEntity.setLine2(address.getLine2());
				addressEntity.setPostalCode(address.getPostalCode());
				addressEntity.setState(address.getState());
				addressEntity.setCity(address.getCity());
				addressEntity.setShippingAddress(address.getShippingAddress());
				addressEntity.setBillingAddress(address.getBillingAddress());
				addressEntity.setRegisterCustomerEntity(customerEntity);
				addresses.add(addressEntity);
			});

			customerEntity.setAddressEntityList(addresses);
			RegisterCustomerEntity out = customerRepository.save(customerEntity);
			return ResponseEntity.status(HttpStatus.OK).body(out);
		}
		else	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer with email:"+customer.getEmail() +" already exists (CODE 400)");
	}

	public ResponseEntity addAddressDetails(Address address){
		if(loginRepository.existsById(address.getId()) && loginRepository.getById(address.getId()).getTokenExpiryDateTime().isAfter(LocalDateTime.now())) {
			if (customerRepository.existsById(address.getId())) {
				RegisterCustomerEntity customerEntity = customerRepository.getById(address.getId());
				List<AddressEntity> addresses = customerEntity.getAddressEntityList();
				AddressEntity addressEntity = new AddressEntity();
				addressEntity.setId(address.getId() + "_" + UUID.randomUUID().toString());
				addressEntity.setLine1(address.getLine1());
				addressEntity.setLine2(address.getLine2());
				addressEntity.setCity(address.getCity());
				addressEntity.setState(address.getState());
				addressEntity.setBillingAddress(address.getBillingAddress());
				addressEntity.setShippingAddress(address.getShippingAddress());
				addressEntity.setPostalCode(address.getPostalCode());
				addressEntity.setRegisterCustomerEntity(customerEntity);

				addresses.add(addressEntity);
				customerEntity.setAddressEntityList(addresses);
				RegisterCustomerEntity out = customerRepository.save(customerEntity);
				return ResponseEntity.status(HttpStatus.OK).body(out);
			}
			else	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer with email:"+address.getId()+" doesnt exist (CODE 400)");
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login expired. Please login again to continue (CODE 401)");
	}

	public ResponseEntity loginUser(Login login) {
		try {
//			LoginCustomer only if user is not already logged in (user is never logged in/token expired)
			if (!loginRepository.existsById(login.getEmail()) || (loginRepository.existsById(login.getEmail()) && loginRepository.getById(login.getEmail()).getTokenExpiryDateTime().isBefore(LocalDateTime.now()))) {
				if (customerRepository.existsById(login.getEmail())) {
					List<RegisterCustomerEntity> entities = customerRepository.findAll().stream().filter(customer -> customer.getEmail().equals(login.getEmail())).collect(Collectors.toList());
					if (entities.size() > 0) {
						if (encryptor.decrypt(entities.get(0).getPassword()).equals(login.getPassword())) {
							System.out.println("login");
							Token token = new Token();
							token.setEmail(login.getEmail());
							LocalDateTime tokenExpiryDateTime = LocalDateTime.now().plusSeconds(20);    // Current time + 20secs
							tokenExpiryDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
							token.setTokenExpiryDateTime(tokenExpiryDateTime);
							LoginEntity loginEntity = new LoginEntity();
							loginEntity.setEmail(login.getEmail());
							loginEntity.setPassword(login.getPassword());
							loginEntity.setTokenExpiryDateTime(tokenExpiryDateTime);
							loginRepository.save(loginEntity);
							return ResponseEntity.status(HttpStatus.OK).body("User logged in successfully (CODE 200)\n");
						} else {
							return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized (CODE 401)\n");
						}
					}
				} else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is not registered (CODE 400)\n");
				}
			} else {
				return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("User already logged in (CODE 208)\n");
			}
		}
		catch(Exception e){
			login.setToken(null);
		}
		return null;
	}

	public void setEncryptorConfig() {
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();

		config.setPassword("cjss_encryption"); // encryptor's private key
		config.setAlgorithm("PBEWithMD5AndDES");
		config.setKeyObtentionIterations("1000");
		config.setPoolSize("1");
		config.setProviderName("SunJCE");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		config.setStringOutputType("base64");
		encryptor.setConfig(config);
	}

	public ResponseEntity getTokenExpiryDateTime(String email){
		if(loginRepository.existsById(email))	return ResponseEntity.status(HttpStatus.OK).body(loginRepository.getById(email).getTokenExpiryDateTime());
		else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with email:"+email+" is not logged in");
	}
}
