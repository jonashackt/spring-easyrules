package de.jonashackt.springeasyrules.rules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;




import de.jonashackt.springeasyrules.AbstractRule;

@Component
@ConfigurationProperties(prefix="foorule", ignoreUnknownFields=false, locations="rules.yml")
@Rule(name="FooRule")
public class FooRule extends AbstractRule {

	@NotNull // checking, if the values are set in our rules.yml
	private String bar;
	@NotNull
	private String fooBarFieldValue;
	@NotNull
	private String blaBlubbField;
	
	@Condition
	public boolean when() {
		System.out.println("blubb: ");
		return "BlaTestBla".equals(bar);
	}
	
	@Action
	public void then() {
		System.out.println("Rule Fired");
	}
	
	
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
	
	
}
