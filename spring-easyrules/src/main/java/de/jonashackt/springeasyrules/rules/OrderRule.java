package de.jonashackt.springeasyrules.rules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import de.jonashackt.springeasyrules.PlausibilityStatus;
import de.jonashackt.springeasyrules.internalmodel.Address;
import de.jonashackt.springeasyrules.internalmodel.Order;

/**
 * When Address` state is not equal the Orders` state2ship or the Orders´ amount is smaller than the minimum amount,
 * then {@value #ERRORTEXT}
 */
@Component
@ConfigurationProperties(locations="rules.yml", prefix="order.myfirstcategory", ignoreUnknownFields=false) // this should load configuration via spring autoconfiguration to the rules fields
@Rule
public class OrderRule extends AbstractRule {

	public static final String ERRORTEXT = "The States aren´t equal or the amount is smaller then the minimum, so we couldn´t ship the product!";
		
	private Address address;
	private Order order;
	
	private int minimumamount;

	@Condition
	public boolean when() {
		return !address.getState().equals(order.getState2ship2()) || order.getAmount() < minimumamount;
	}
	
	@Action
	public void then() {
		System.out.println(ERRORTEXT);
		getResult().setStatus(PlausibilityStatus.ERROR);
		getResult().setMessage(ERRORTEXT);
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
