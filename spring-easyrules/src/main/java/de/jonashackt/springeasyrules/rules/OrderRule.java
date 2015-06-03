package de.jonashackt.springeasyrules.rules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import de.jonashackt.springeasyrules.errorhandling.PlausibilityStatus;
import de.jonashackt.springeasyrules.internalmodel.Address;
import de.jonashackt.springeasyrules.internalmodel.Order;

/**
 * When Address` state is not equal the Orders` state2ship or the Orders´ amount is smaller than the minimum amount,
 * then {@value #ERRORTEXT}
 */
@Component
@ConfigurationProperties(locations="rules.yml", prefix="order.myfirstcategory", ignoreUnknownFields=false) // this should load configuration via spring autoconfiguration to the rules fields
@Rule(name = "Order rule", description = " Is the state in Address the same as it is in the Order?")
public class OrderRule extends AbstractRule {

	public static final String ERRORTEXT = "The States aren´t equal or the amount is smaller then the minimum, so we couldn´t ship the product!";
		
	private Address address;
	private Order order;
	
	private int minimumamount;
	private String test;
	
	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	@Condition
	public boolean when() {
		return address.getState().equals(order.getState2ship2()) && order.getAmount() > minimumamount && minimumamount == 150;
	}
	
	@Action
	public void then() {
		System.out.println(ERRORTEXT);
		getResult().setStatus(PlausibilityStatus.ERROR);
		getResult().addMessage(ERRORTEXT);
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	public void setMinimumamount(int minimumamount) {
		this.minimumamount = minimumamount;
	}
}
