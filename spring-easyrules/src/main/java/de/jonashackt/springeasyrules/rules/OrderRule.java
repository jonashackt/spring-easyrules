package de.jonashackt.springeasyrules.rules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;

import de.jonashackt.springeasyrules.internalmodel.Address;
import de.jonashackt.springeasyrules.internalmodel.Order;

@Rule(name = "Order rule", description = " Is the state in Address the same as it is in the Order?")
public class OrderRule {

	private Address address;
	private Order order;
	
	@Condition
	public boolean when() {
		return address.getState().equals(order.getState2ship2());
	}
	
	@Action
	public void then() {
		System.out.println("The States are equal, so let's ship the product!");
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
