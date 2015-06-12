package de.jonashackt.springeasyrules.rules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="foorule", ignoreUnknownFields=false, locations="rules.yml")
@Rule(name="FooRule")
public class FooRule {

	private String bar;

	public String getBar() {
		return bar;
	}

	public void setBar(String bar) {
		this.bar = bar;
	}
	
	@Condition
	public boolean when() {
		System.out.println("blubb: ");
		return "BlaTestBla".equals(bar);
	}
	
	@Action
	public void then() {
		System.out.println("Rule Fired");
	}
}
