package de.jonashackt.springeasyrules;

import static org.easyrules.core.RulesEngineBuilder.aNewRulesEngine;

import java.util.ArrayList;
import java.util.List;

import org.easyrules.api.RulesEngine;

import de.jonashackt.springeasyrules.rules.AbstractRule;

/**
 * Wrapper for EasyRules RulesEngine - registers and fires Rules
 */
public class PlausibilityChecker {

private List<AbstractRule> rules = new ArrayList<AbstractRule>();
	
	private List<String> messages = new ArrayList<String>();
	private PlausibilityStatus status = PlausibilityStatus.SUCCESS;  // default
	
	public static PlausibilityChecker aNewPlausiPruefer() {
		return new PlausibilityChecker();
	}
	
	private PlausibilityChecker() {
		super();
	}
	
	/**
	 * Checking all the rules, that were added
	 */
	public PlausibilityStatus fireRules() {
		RulesEngine rulesEngine = aNewRulesEngine().build(); 
		
		for(AbstractRule rule : rules) {
			rulesEngine.registerRule(rule);
		}
		
		rulesEngine.fireRules();
		
		for(AbstractRule rule : rules) {
			if(PlausibilityStatus.ERROR.equals(rule.getStatus())) {
				status = PlausibilityStatus.ERROR;
				messages.add(rule.getMessage());
			}			
		}
		return status;
		
	}
	
	public void addRule(AbstractRule rule) {
		rules.add(rule);
	}
	
	public List<String> getMessages() {
		return messages;
	}
}
