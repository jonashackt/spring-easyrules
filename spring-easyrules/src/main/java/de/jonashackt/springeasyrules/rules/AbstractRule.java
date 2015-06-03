package de.jonashackt.springeasyrules.rules;

import de.jonashackt.springeasyrules.errorhandling.PlausibilityResult;

public class AbstractRule {

	private PlausibilityResult plausibilityResult = new PlausibilityResult();

	public PlausibilityResult getResult() {
		return plausibilityResult;
	}

	public void setResult(PlausibilityResult plausibilityResult) {
		this.plausibilityResult = plausibilityResult;
	}
}
