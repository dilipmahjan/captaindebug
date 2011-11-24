package com.captaindebug.siteproperties;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.captaindebug.address.Address;
import com.captaindebug.address.StubAddressDao;

public class AddressServiceUnitTest {

	private StubAddressDao addressDao;

	private StubPropertiesManager stubProperties;

	private AddressService instance;

	@Before
	public void setUp() {
		instance = new AddressService();

		stubProperties = new StubPropertiesManager();
		instance.setPropertiesManager(stubProperties);
	}

	@Test
	public void testAddressSiteProperties_AddressServiceDisabled() {

		/* Set up the AddressDAO Stubb for this test */
		Address address = new Address(1, "15 My Street", "My Town", "POSTCODE", "My Country");
		addressDao = new StubAddressDao(address);
		instance.setAddressDao(addressDao);

		stubProperties.setProperty("address.enabled", "false");

		Address expected = Address.INVALID_ADDRESS;
		Address result = instance.findAddress(1);

		assertEquals(expected, result);
	}
}
