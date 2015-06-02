package de.jonashackt.springeasyrules.rules;

import org.easyrules.api.JmxRule;

import javax.management.MXBean;

@MXBean
public interface AddressJmxRule extends JmxRule {

	public String getPostcodeReqex();

	public void setPostcodeReqex(String postcodeReqex);
}
