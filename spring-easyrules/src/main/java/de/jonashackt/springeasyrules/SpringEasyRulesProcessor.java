package de.jonashackt.springeasyrules;

import static org.easyrules.core.JmxRulesEngineBuilder.aNewJmxRulesEngine;

import org.easyrules.api.JmxRulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.jonashackt.springeasyrules.internalmodel.Address;
import de.jonashackt.springeasyrules.internalmodel.Order;
import de.jonashackt.springeasyrules.rules.AddressRule;
import de.jonashackt.springeasyrules.rules.FooRule;
import de.jonashackt.springeasyrules.rules.OrderRule;

@Component
public class SpringEasyRulesProcessor {

	@Autowired
	private FooRule fooRule;
	
	@Autowired
	private OrderRule orderRule;
	
	public void processRules() {
		 // Given
	    Address address = new Address();
	    address.setPostcode("99425");
	    address.setStreet("Haalstreet");
	    address.setState("GERMANY");
        
        AddressRule addressRule = new AddressRule("Address rule", "Verifying the ingredients of an address");
        addressRule.setAddress(address);
        
        // When
        JmxRulesEngine rulesEngine = aNewJmxRulesEngine().build();
        rulesEngine.registerJmxRule(addressRule);

        
        // Given
        Order order = new Order();
		order.setState2ship2("GERMANY");
		order.setAmount(160);
 		
 		orderRule.setAddress(address);
 		orderRule.setOrder(order);
 		
 		// When
 		rulesEngine.registerJmxRule(orderRule);
 		
 		
 		// Given
 		rulesEngine.registerRule(fooRule);		
 		
 		rulesEngine.fireRules();
	}
}
