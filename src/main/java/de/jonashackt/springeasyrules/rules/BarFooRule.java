package de.jonashackt.springeasyrules.rules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="barfoorule.rule.them.all", ignoreUnknownFields=false, locations="rules.yml")
@Rule(name="BarFooRule")
public class BarFooRule extends AbstractRule {

	private String bar;

	@Condition
	public boolean when() {
		System.out.println("barfoorule?");
		return "BlaTestBla".equals(bar);
	}
	
	@Action
	public void then() {
		System.out.println("barfoorule Fired");
	}
	
	public void setBar(String bar) {
		this.bar = bar;
	}
	
	
}
