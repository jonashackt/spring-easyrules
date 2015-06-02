package de.jonashackt.springeasyrules;

import org.easyrules.api.JmxRulesEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.jonashackt.springeasyrules.internalmodel.Address;
import de.jonashackt.springeasyrules.rules.AddressRule;
import static org.easyrules.core.JmxRulesEngineBuilder.aNewJmxRulesEngine;

@SpringBootApplication
public class SpringEasyRulesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringEasyRulesApplication.class, args);
        
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
    }
}
