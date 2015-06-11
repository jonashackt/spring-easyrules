package de.jonashackt.springeasyrules;

import static org.easyrules.core.RulesEngineBuilder.aNewRulesEngine;

import org.easyrules.api.RulesEngine;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.jonashackt.springeasyrules.errorhandling.PlausibilityResult;
import de.jonashackt.springeasyrules.errorhandling.PlausibilityStatus;
import de.jonashackt.springeasyrules.internalmodel.Address;
import de.jonashackt.springeasyrules.internalmodel.Order;
import de.jonashackt.springeasyrules.rules.AddressRule;
import de.jonashackt.springeasyrules.rules.FooRule;
import de.jonashackt.springeasyrules.rules.OrderRule;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=SpringEasyRulesApplication.class)
public class SpringEasyRulesApplicationTests {

	private RulesEngine rulesEngine = aNewRulesEngine().build();  
	
	@Autowired
	private OrderRule orderRule;
	
	@Autowired
	private AddressRule addressRule;
	
	@Autowired
	private FooRule fooRule;
	
	@Test
	public void address() {
		// Given
	    Address address = new Address();
	    address.setPostcode("994259");
	    address.setStreet("Haalstreet");
	    address.setState("GERMANY");
        addressRule.setAddress(address);
        
        // When
        rulesEngine.registerRule(addressRule);
        rulesEngine.fireRules();
        
        // Then
        PlausibilityResult result = addressRule.getResult();
        Assert.assertEquals(AddressRule.ERRORTEXT,result.getMessages().get(0));
        Assert.assertEquals(PlausibilityStatus.ERROR, addressRule.getResult().getStatus());
	}
	
	@Test 
	public void addressPostCodeMandatoryButIsNull() {
		// Given
	    Address address = new Address(); // -> postcode == null
        addressRule.setAddress(address);
        
        // When
        rulesEngine.registerRule(addressRule);
        rulesEngine.fireRules();
        
        // Then
        PlausibilityResult result = addressRule.getResult();
        Assert.assertEquals(AddressRule.ERRORTEXT,result.getMessages().get(0));
        Assert.assertEquals(PlausibilityStatus.ERROR, addressRule.getResult().getStatus());
	}
    
	@Test
    public void order() {    
        // Given
		Address address = new Address();
	    address.setPostcode("99425");
	    address.setStreet("Haalstreet");
	    address.setState("GERMANY");
		orderRule.setAddress(address);
	    
		Order order = new Order();
		order.setState2ship2("GERMANY");
		order.setAmount(140);
 		orderRule.setOrder(order);
 		
 		// When
 		rulesEngine.registerRule(orderRule);
 		rulesEngine.fireRules();
		
		// Then
		Assert.assertEquals(OrderRule.ERRORTEXT, orderRule.getResult().getMessages().get(0));
		Assert.assertEquals(PlausibilityStatus.ERROR, orderRule.getResult().getStatus());
	}
    
    @Test
    public void foo() {
    	// Given
    	rulesEngine.registerRule(fooRule);
    	
    	// When
    	rulesEngine.fireRules();
    	
    	// Then
    	//...
    }
	
	@Test
	public void testIfPropertyRulesAreLoadedCorrectlyFromYml() {
		Assert.assertEquals("Properties from rules.yml not correctly loaded. Check http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-external-config-typesafe-configuration-properties for details ",
				"BlaTestBla", fooRule.getBar());
	}

}
