package com.riskIt.test;

import com.riskIt.controller.Requirements19_24;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * This test class conducts tests for the following requirement( Menu Option ):
 * 24. Calculate the 'likeliness to move factor' for a person.
 * 
 * @author Selma Tiganj
 *
 */
public class TestCalculateLikelinessToMoveFactor extends TestCase {
	
	private Requirements19_24 requirements;

	public TestCalculateLikelinessToMoveFactor() {
		super();
		requirements = new Requirements19_24();
	}
	
	public void testLiklinessToMoveFactor1()
	{
		String result = "low";
	
		Assert.assertEquals(result, requirements.CalculateLikelinessToMoveFactor(101408061));
	}
	
	public void testLiklinessToMoveFactor2()
	{
		String result = "high";
	
		Assert.assertEquals(result, requirements.CalculateLikelinessToMoveFactor(101407896));
	}
	
	public void testLiklinessToMoveFactor3()
	{
		String result = "high";
	
		Assert.assertEquals(result, requirements.CalculateLikelinessToMoveFactor(101407854));
	}
}
