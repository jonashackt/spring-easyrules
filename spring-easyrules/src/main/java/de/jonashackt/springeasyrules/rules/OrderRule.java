package de.jonashackt.springeasyrules.rules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import de.jonashackt.springeasyrules.internalmodel.Address;
import de.jonashackt.springeasyrules.internalmodel.Order;

@Component
@ConfigurationProperties(prefix="myrule", ignoreUnknownFields=false, locations="rules.yml") // this should load configuration via spring autoconfiguration to the rules fields
@Rule(name = "Order rule", description = " Is the state in Address the same as it is in the Order?")
public class OrderRule implements OrderRuleJmx {

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
		System.out.println("The States are equal & amount is bigger than the minimum, so let's ship the product!");
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

	@Override
	public String getAddressState() {
		return address.getState();
	}

	@Override
	public void setAddressState(String state) {
		address.setState(state);
	}

	@Override
	public String getOrderState2Ship2() {
		return order.getState2ship2();
	}

	@Override
	public void setOrderState2Ship2(String state) {
		order.setState2ship2(state);
	}

	@Override
	public int getMinimumamount() {
		return minimumamount;
	}
}
