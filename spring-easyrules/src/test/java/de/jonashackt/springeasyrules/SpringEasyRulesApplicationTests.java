package de.jonashackt.springeasyrules;

import static org.easyrules.core.JmxRulesEngineBuilder.aNewJmxRulesEngine;
import static org.easyrules.core.RulesEngineBuilder.aNewRulesEngine;

import org.easyrules.api.JmxRulesEngine;
import org.easyrules.api.RulesEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.jonashackt.springeasyrules.internalmodel.Address;
import de.jonashackt.springeasyrules.internalmodel.Order;
import de.jonashackt.springeasyrules.rules.AddressRule;
import de.jonashackt.springeasyrules.rules.OrderRule;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringEasyRulesApplication.class)
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
		RulesEngine rulesEngine = aNewRulesEngine().build();
		rulesEngine.registerRule(orderRule);
		rulesEngine.fireRules();
		
		// Then
		
	}

}
