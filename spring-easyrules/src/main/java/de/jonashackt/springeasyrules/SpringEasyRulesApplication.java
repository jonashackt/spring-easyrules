package de.jonashackt.springeasyrules;

import static org.easyrules.core.JmxRulesEngineBuilder.aNewJmxRulesEngine;

import org.easyrules.api.JmxRulesEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import de.jonashackt.springeasyrules.internalmodel.Address;
import de.jonashackt.springeasyrules.internalmodel.Order;
import de.jonashackt.springeasyrules.rules.AddressRule;
import de.jonashackt.springeasyrules.rules.OrderRule;

@SpringBootApplication
@EnableConfigurationProperties // needed, to automatically load rules.yml defined properties to Rule-Pojos fields, see http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html
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

        
        // Given
        Order order = new Order();
		order.setState2ship2("GERMANY");
		order.setAmount(40);
 		OrderRule orderRule = new OrderRule();
 		orderRule.setAddress(address);
 		orderRule.setOrder(order);
 		
 		// When
 		rulesEngine.registerJmxRule(orderRule);
 		rulesEngine.fireRules();
    }
}
