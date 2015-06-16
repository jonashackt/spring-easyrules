package de.jonashackt.springeasyrules.rules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import de.jonashackt.springeasyrules.AbstractRule;
import de.jonashackt.springeasyrules.PlausibilityStatus;
import de.jonashackt.springeasyrules.internalmodel.Address;

/**
 * When Address` postcode mandatory {@value #postcodeMandatory} but is Null or it doesn´t match {@value #postcodeReqex},
 * then {@value #ERRORTEXT}
 */
@Component
@ConfigurationProperties(locations="rules.yml", prefix="address", ignoreUnknownFields=false) // this should load configuration via spring autoconfiguration to the rules fields
@Rule(name="AddressRule")
public class AddressRule extends AbstractRule {

	public static final String ERRORTEXT = "The Address´ postcode isn´t valid!";
	private Address address;
	private String postcodeReqex = "([0-9]{5})";
	private boolean postcodeMandatory;	

	@Condition
    public boolean when() {
		if(postcodeMandatory && address.getPostcode() == null)
			return true; // -> postcode is null
		return !address.getPostcode().matches(postcodeReqex);
    }

	@Action
    public void then() {
    	System.out.println(ERRORTEXT);
    	setStatus(PlausibilityStatus.ERROR);
    	setMessage(ERRORTEXT);
    }
	
    
	public void setAddress(Address address) {
		this.address = address;
	}

	public void setPostcodeReqex(String postcodeReqex) {
		this.postcodeReqex = postcodeReqex;
	}

	public void setPostcodeMandatory(boolean postcodeMandatory) {
		this.postcodeMandatory = postcodeMandatory;
	}
}
