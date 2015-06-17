package de.jonashackt.springeasyrules.rules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.jonashackt.springeasyrules.AbstractRule;

@Component
@Rule(name="FooRule")
public class FooRule extends AbstractRule {
	
	@Autowired
	public FooRuleProperties fooRuleProperties;
	
	@Condition
	public boolean when() {
		System.out.println("blubb: ");
		return "BlaTestBla".equals(fooRuleProperties.getBar());
	}
	
	@Action
	public void then() {
		System.out.println("Rule Fired");
		System.out.println(fooRuleProperties.toString());
	}
	
}
