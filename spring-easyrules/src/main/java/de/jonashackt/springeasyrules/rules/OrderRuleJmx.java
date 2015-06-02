package de.jonashackt.springeasyrules.rules;

import javax.management.MXBean;

@MXBean
public interface OrderRuleJmx {

	public String getAddressState();
	public void setAddressState(String state);
	public String getOrderState2Ship2();
	public void setOrderState2Ship2(String state);
	public int getMinimumamount();
}
