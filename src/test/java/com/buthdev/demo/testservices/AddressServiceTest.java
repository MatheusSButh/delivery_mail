package com.buthdev.demo.testservices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.buthdev.demo.model.Address;
import com.buthdev.demo.repositories.AddressRepository;
import com.buthdev.demo.services.AddressService;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

	@Mock
	private AddressRepository addressRepository;
	
	@InjectMocks
	private AddressService addressService;
	
	@Test
	void testFindOrCreateAddressWhenAddressExist() {
		
		//Arrange
		Address address = new Address();
		address.setId(1L);
		address.setCep("12345678");
		address.setLogradouro("Rua teste");
		address.setBairro("Bairro teste");
		address.setLocalidade("Local teste");
		address.setUf("UF teste");
		
		when(addressService.findAddressByCep(address.getCep())).thenReturn(address);
		
		//Act
		Address resultAddress = addressService.findOrCreateAddress(address.getCep());
		
		//Assets
		assertNotNull(resultAddress);
		assertEquals("12345678", resultAddress.getCep());
		assertEquals("Rua teste", resultAddress.getLogradouro());
		assertEquals("Bairro teste", resultAddress.getBairro());
		assertEquals("Local teste", resultAddress.getLocalidade());
		assertEquals("UF teste", resultAddress.getUf());
	}
}
