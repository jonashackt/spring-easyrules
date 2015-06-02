package de.jonashackt.springeasyrules;

import org.easyrules.api.JmxRulesEngine;
import org.easyrules.api.RulesEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.jonashackt.springeasyrules.internalmodel.Address;
import de.jonashackt.springeasyrules.internalmodel.Order;
import de.jonashackt.springeasyrules.rules.AddressRule;
import de.jonashackt.springeasyrules.rules.OrderRule;
import static org.easyrules.core.JmxRulesEngineBuilder.aNewJmxRulesEngine;
import static org.easyrules.core.RulesEngineBuilder.aNewRulesEngine;

@SpringBootApplication
public class SpringEasyRulesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringEasyRulesApplication.class, args);
        
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
        rulesEngine.fireRules();
        
        // Given
        Order order = new Order();
		order.setState2ship2("GERMANY");
 		OrderRule orderRule = new OrderRule();
 		orderRule.setAddress(address);
 		orderRule.setOrder(order);
 		
 		// When
 		rulesEngine.registerRule(orderRule);
 		rulesEngine.fireRules();
    }
}
