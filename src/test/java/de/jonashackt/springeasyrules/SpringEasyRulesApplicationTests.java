package de.jonashackt.springeasyrules;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.jonashackt.springeasyrules.internalmodel.Address;
import de.jonashackt.springeasyrules.internalmodel.Order;
import de.jonashackt.springeasyrules.rules.AddressRule;
import de.jonashackt.springeasyrules.rules.BarFooRule;
import de.jonashackt.springeasyrules.rules.FooRule;
import de.jonashackt.springeasyrules.rules.OrderRule;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=SpringEasyRulesApplication.class)
public class SpringEasyRulesApplicationTests {

	@Autowired
	private OrderRule orderRule;
	@Autowired
	private AddressRule addressRule;
	@Autowired
	private FooRule fooRule;
	@Autowired
	private BarFooRule barFooRule;
	
	
	@Test
	public void address() {
		// Given
		PlausibilityChecker checker = PlausibilityChecker.aNewPlausiPruefer();
		
	    Address address = new Address();
	    address.setPostcode("994259");
	    address.setStreet("Haalstreet");
	    address.setState("GERMANY");
        addressRule.setAddress(address);
        checker.addRule(addressRule);
        
        // When
        PlausibilityStatus status = checker.fireRules();
        
        // Then
        Assert.assertEquals(PlausibilityStatus.ERROR, status);
        Assert.assertEquals(AddressRule.ERRORTEXT, addressRule.getMessage());
	}
	
	@Test 
	public void addressPostCodeMandatoryButIsNull() {
		// Given
		PlausibilityChecker checker = PlausibilityChecker.aNewPlausiPruefer();
	    
		Address address = new Address(); // -> postcode == null
        addressRule.setAddress(address);
        checker.addRule(addressRule);
        
        // When
        PlausibilityStatus status = checker.fireRules();
        
        // Then
        Assert.assertEquals(PlausibilityStatus.ERROR, status);
        Assert.assertEquals(AddressRule.ERRORTEXT, addressRule.getMessage());
	}
    
	@Test
    public void order() {    
        // Given
		PlausibilityChecker checker = PlausibilityChecker.aNewPlausiPruefer();
		
		Address address = new Address();
	    address.setPostcode("99425");
	    address.setStreet("Haalstreet");
	    address.setState("GERMANY");
		orderRule.setAddress(address);
	    
		Order order = new Order();
		order.setState2ship2("GERMANY");
		order.setAmount(140);
 		orderRule.setOrder(order);
 		
 		checker.addRule(orderRule);
 		
 		// When
 		PlausibilityStatus status = checker.fireRules();
		
		// Then
 		Assert.assertEquals(PlausibilityStatus.ERROR, status);
		Assert.assertEquals(OrderRule.ERRORTEXT, orderRule.getMessage());
	}
    
    @Test
    public void foo() {
    	// Given
    	PlausibilityChecker checker = PlausibilityChecker.aNewPlausiPruefer();
    	checker.addRule(fooRule);
    	
    	// When
    	checker.fireRules();
    	
    	// Then
    	System.out.println("foo bar");
    }
    
    @Test
    public void bar() {
    	PlausibilityChecker checker = PlausibilityChecker.aNewPlausiPruefer();
    	checker.addRule(barFooRule);
    	
    	checker.fireRules();
    }
	
	@Test
	public void testIfPropertyRulesAreLoadedCorrectlyFromYml() {
		Assert.assertEquals("Properties from rules.yml not correctly loaded. Check http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-external-config-typesafe-configuration-properties for details ",
				"BlaTestBla", fooRule.getBar());
	}

}
