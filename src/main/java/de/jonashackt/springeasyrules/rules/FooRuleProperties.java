package de.jonashackt.springeasyrules.rules;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="foorule", ignoreUnknownFields=false, locations="rules.yml")
public class FooRuleProperties {

	@NotNull // checking, if the values are set in our rules.yml
	private String bar;
	@NotNull
	private String fooBarFieldValue;
	@NotNull
	private String blaBlubbField;
	
	public void setFooBarFieldValue(String fooBarFieldValue) {
		this.fooBarFieldValue = fooBarFieldValue;
	}	

	public String getFooBarFieldValue() {
		return fooBarFieldValue;
	}

	public String getBar() {
		return bar;
	}

	public void setBar(String bar) {
		this.bar = bar;
	}

	public String getBlaBlubbField() {
		return blaBlubbField;
	}

	public void setBlaBlubbField(String blaBlubbField) {
		this.blaBlubbField = blaBlubbField;
	}

	@Override
	public String toString() {
		return "FooRuleProperties [bar=" + bar + ", fooBarFieldValue="
				+ fooBarFieldValue + ", blaBlubbField=" + blaBlubbField + "]";
	}
}
