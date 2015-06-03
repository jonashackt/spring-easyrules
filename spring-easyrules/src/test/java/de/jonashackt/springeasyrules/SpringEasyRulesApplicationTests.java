package de.jonashackt.springeasyrules;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=SpringEasyRulesApplication.class)
public class SpringEasyRulesApplicationTests {

	private static final Logger LOG = LoggerFactory.getLogger(SpringEasyRulesApplicationTests.class);
	
	@Autowired
	private SpringEasyRulesProcessor springEasyRulesProcessor;
	
	@Test
	public void address() {
		
		// Given
	            
        // When
		springEasyRulesProcessor.processRules();
		
		// Then

	}

}
