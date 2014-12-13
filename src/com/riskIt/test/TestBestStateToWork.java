package com.riskIt.test;

import com.riskIt.controller.*;

import junit.framework.Assert;
import junit.framework.TestCase;
import java.util.ArrayList;

/**
 * This test class conducts tests for the following requirement ( Menu Option ):
 * 21. Given a user record, recommend best state to work
 * 
 * @author Selma Tiganj
 *
 */

public class TestBestStateToWork extends TestCase {
	
	private Requirements19_24 requirements;
	
	public TestBestStateToWork ()
	{
		super();
		requirements = new Requirements19_24();
	}
		
	public void testBestStateToWork1()
	{
		ArrayList<String> result = new ArrayList<String>();

		result.add("State: VA                   Average weekly wage for the user's occupation: 1011.0");
		result.add("State: CT                   Average weekly wage for the user's occupation: 383.33");
		result.add("State: NV                   Average weekly wage for the user's occupation: 337.33");
	
		Assert.assertEquals(result, requirements.RecommendBestStateToWork(102001827));
    }

	public void testBestStateToWork2()
	{
		ArrayList<String> result = new ArrayList<String>();

		result.add("State: DE                   Average weekly wage for the user's occupation: 250.0");
		result.add("State: OH                   Average weekly wage for the user's occupation: 200.0");
		result.add("State: TN                   Average weekly wage for the user's occupation: 200.0");
	
		Assert.assertEquals(result, requirements.RecommendBestStateToWork(102001641));
    }
	
	public void testBestStateToWork3()
	{
		ArrayList<String> result = new ArrayList<String>();

		result.add("State: VA                   Average weekly wage for the user's occupation: 1540.0");
		result.add("State: NE                   Average weekly wage for the user's occupation: 558.33");
		result.add("State: MT                   Average weekly wage for the user's occupation: 353.46");
	
		Assert.assertEquals(result, requirements.RecommendBestStateToWork(102001518));
    }
	
	public void testBestStateToWork4()
	{
		ArrayList<String> result = new ArrayList<String>();

		result.add("State: TX                   Average weekly wage for the user's occupation: 218.08");
		result.add("State: KS                   Average weekly wage for the user's occupation: 207.14");
		result.add("State: WY                   Average weekly wage for the user's occupation: 204.55");
	
		Assert.assertEquals(result, requirements.RecommendBestStateToWork(102001506));
    }
	
	public void testBestStateToWork5()
	{
		ArrayList<String> result = new ArrayList<String>();

		result.add("State: SD                   Average weekly wage for the user's occupation: 800.0");
		result.add("State: MS                   Average weekly wage for the user's occupation: 500.0");
		result.add("State: NE                   Average weekly wage for the user's occupation: 465.0");
	
		Assert.assertEquals(result, requirements.RecommendBestStateToWork(102001314));
    }
}
