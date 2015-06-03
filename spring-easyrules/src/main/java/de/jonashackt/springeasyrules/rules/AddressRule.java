package de.jonashackt.springeasyrules.rules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import de.jonashackt.springeasyrules.errorhandling.PlausibilityStatus;
import de.jonashackt.springeasyrules.internalmodel.Address;

/**
 * When Address` postcode should be checked for NotNull {@value #addressPostCodeNotNull} or it doesn´t match {@value #postcodeReqex},
 * then {@value #ERRORTEXT}
 */
@Component
@ConfigurationProperties(locations="rules.yml", prefix="address", ignoreUnknownFields=false) // this should load configuration via spring autoconfiguration to the rules fields
@Rule(name = "Address rule", description = "Verifying the ingredients of an address")
public class AddressRule extends AbstractRule {

	// TODO: Change duplicate description!
	public static final String ERRORTEXT = "The Address´ postcode isn´t valid!";
	private Address address;
	private String postcodeReqex = "([0-9]{5})";
	private boolean addressPostCodeNotNull;
	
	@Condition
    public boolean when() {
		return addressPostCodeNotNull && address.getPostcode() == null && !address.getPostcode().matches(postcodeReqex);
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
	
	public String getPostcodeReqex() {
		return postcodeReqex;
	}

	public void setPostcodeReqex(String postcodeReqex) {
		this.postcodeReqex = postcodeReqex;
	}
	
	public boolean isAddressPostCodeNull() {
		return addressPostCodeNotNull;
	}

	public void setAddressPostCodeNull(boolean addressPostCodeNull) {
		this.addressPostCodeNotNull = addressPostCodeNull;
	}
}
