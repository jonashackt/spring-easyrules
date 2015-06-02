package de.jonashackt.springeasyrules.rules;

import org.easyrules.core.BasicRule;

import de.jonashackt.springeasyrules.internalmodel.Address;

public class AddressRule extends BasicRule implements AddressJmxRule {

	private Address address;
	private String postcodeReqex = "([0-9]{5})";

	public AddressRule(String name, String description) {
		super(name, description);
	}
	
	@Override
    public boolean evaluate() {
		return address.getPostcode() != null && address.getPostcode().matches(postcodeReqex);
    }

    @Override
    public void execute() throws Exception {
    	System.out.println("Rule successfully checked: Postcode is fine.");
    }
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String getPostcodeReqex() {
		return postcodeReqex;
	}

	public void setPostcodeReqex(String postcodeReqex) {
		this.postcodeReqex = postcodeReqex;
	}
}
