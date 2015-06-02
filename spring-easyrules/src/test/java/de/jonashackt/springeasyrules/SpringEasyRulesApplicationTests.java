package de.jonashackt.springeasyrules;

import static org.easyrules.core.JmxRulesEngineBuilder.aNewJmxRulesEngine;

import org.easyrules.api.JmxRulesEngine;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.jonashackt.springeasyrules.internalmodel.Address;
import de.jonashackt.springeasyrules.internalmodel.Order;
import de.jonashackt.springeasyrules.rules.AddressRule;
import de.jonashackt.springeasyrules.rules.OrderRule;


public class SpringEasyRulesApplicationTests {

	private static final Logger LOG = LoggerFactory.getLogger(SpringEasyRulesApplicationTests.class);
	
	@Test
	public void address() {
		
		// Given
	    Address address = new Address();
	    address.setPostcode("99425");
	    address.setStreet("Haalstreet");
	    address.setState("ALBANIA");
        
        AddressRule addressRule = new AddressRule("Address rule", "Verifying the ingredients of an address");
        addressRule.setAddress(address);
        
        // When
        JmxRulesEngine rulesEngine = aNewJmxRulesEngine().build();
        rulesEngine.registerJmxRule(addressRule);
        rulesEngine.fireRules();
		
		// Then

	}
	
	@Test
	public void orderAndAdress() {
		// Given
		Address address = new Address();
		address.setState("GERMANY");
		Order order = new Order();
		order.setState2ship2("GERMANY");
		
		OrderRule orderRule = new OrderRule();
		orderRule.setAddress(address);
		orderRule.setOrder(order);
		
		// When
		JmxRulesEngine rulesEngine = aNewJmxRulesEngine().build();
		rulesEngine.registerJmxRule(orderRule);
		rulesEngine.fireRules();
		
		// Then
		
	}

}
