package com.riskIt.test;

import com.riskIt.controller.*;

import junit.framework.Assert;
import junit.framework.TestCase;
import java.util.ArrayList;

/**
 * This test class conducts tests for the following requirements
 * 19. For given an occupation category or occupation code, list the top 5 state that have the most workers
 * 20. For given an industry category or industry code, list the top 5 state that have the most workers
 * 
 * @author Selma Tiganj
 *
 */

public class TestFind5StatesWithMostWorkers extends TestCase {
	
	private Requirements19_24 requirements;
	
	public TestFind5StatesWithMostWorkers ()
	{
		super();
		requirements = new Requirements19_24();
	}
	
	
	//********************* Test By Occupation Category *********************//
		
	public void testByOccupationCategory1()
	{
		ArrayList<String> result = new ArrayList<String>();
		result.add("State: CA                   Number of workers: 156.0");
		result.add("State: FL                   Number of workers: 115.0");
		result.add("State: UT                   Number of workers: 105.0");
		result.add("State: NC                   Number of workers: 76.0");
		result.add("State: MN                   Number of workers: 68.0");

		Assert.assertEquals(result, requirements.FindTopStatesByCategory("occupation", "' Sales'"));
	}
	public void testByOccupationCategory2()
	{
		 ArrayList<String> result = new ArrayList<String>();
		 result.add("State: CA                   Number of workers: 137.0");
		 result.add("State: UT                   Number of workers: 83.0");
		 result.add("State: NC                   Number of workers: 76.0");
		 result.add("State: FL                   Number of workers: 69.0");
		 result.add("State: NM                   Number of workers: 58.0");
		 		
		 Assert.assertEquals(result, requirements.FindTopStatesByCategory("occupation", "' Executive admin and managerial'"));
	}
	
	public void testByOccupationCategory3()
	{
		 ArrayList<String> result = new ArrayList<String>();
		 result.add("State: UT                   Number of workers: 2.0");
		 result.add("State: AZ                   Number of workers: 2.0");
		 result.add("State: FL                   Number of workers: 1.0");
		 result.add("State: TX                   Number of workers: 1.0");
		 result.add("State: OK                   Number of workers: 1.0");
		 		
		 Assert.assertEquals(result, requirements.FindTopStatesByCategory("occupation", "' Armed Forces'"));
	}
	
	public void testByOccupationCategory4()
	{
		 ArrayList<String> result = new ArrayList<String>();
		 result.add("State: CA                   Number of workers: 48.0");
		 result.add("State: FL                   Number of workers: 33.0");
		 result.add("State: UT                   Number of workers: 23.0");
		 result.add("State: ND                   Number of workers: 16.0");
		 result.add("State: AR                   Number of workers: 12.0");
		 		
		 Assert.assertEquals(result, requirements.FindTopStatesByCategory("occupation", "' Farming forestry and fishing'"));
	}
	
	public void testByOccupationCategory5()
	{
		 ArrayList<String> result = new ArrayList<String>();
		 result.add("State: CA                   Number of workers: 22.0");
		 result.add("State: FL                   Number of workers: 18.0");
		 result.add("State: UT                   Number of workers: 17.0");
		 result.add("State: NC                   Number of workers: 12.0");
		 result.add("State: MI                   Number of workers: 12.0");
		 		
		 Assert.assertEquals(result, requirements.FindTopStatesByCategory("occupation", "' Protective services'"));
	}
	
	/**************************** Test By Occupation Code *********************************/
	
	public void testByOccupationCode1()
	{
		ArrayList<String> result = new ArrayList<String>();
                ArrayList<Integer> code = new ArrayList<Integer>();
                code.add(45);

		result.add("State: AK                   Number of workers: 4.0");
		result.add("State: CA                   Number of workers: 4.0");
		result.add("State: ND                   Number of workers: 3.0");
		result.add("State: VA                   Number of workers: 3.0");
		result.add("State: AR                   Number of workers: 2.0");	 
	
		Assert.assertEquals(result, requirements.FindTopStatesByCode("occupation", code));
	}
	public void testByOccupationCode2()
	{
		 ArrayList<String> result = new ArrayList<String>();
                 ArrayList<Integer> code = new ArrayList<Integer>();
                 code.add(1);
		 result.add("State: NC                   Number of workers: 4.0");
		 result.add("State: CA                   Number of workers: 3.0");
		 result.add("State: KY                   Number of workers: 3.0");
		 result.add("State: CO                   Number of workers: 3.0");
		 result.add("State: GA                   Number of workers: 2.0");
		 		
		 Assert.assertEquals(result, requirements.FindTopStatesByCode("occupation", code));
	}
	
	public void testByOccupationCode3()
	{
		 ArrayList<String> result = new ArrayList<String>();
                 ArrayList<Integer> code = new ArrayList<Integer>();
                 code.add(46);

		 result.add("State: UT                   Number of workers: 2.0");
		 result.add("State: AZ                   Number of workers: 2.0");
		 result.add("State: FL                   Number of workers: 1.0");
		 result.add("State: TX                   Number of workers: 1.0");
		 result.add("State: OK                   Number of workers: 1.0");
		 		
		 Assert.assertEquals(result, requirements.FindTopStatesByCode("occupation", code));
	}
	
	public void testByOccupationCode4()
	{
		 ArrayList<String> result = new ArrayList<String>();
                 ArrayList<Integer> code = new ArrayList<Integer>();
                 code.add(43);
		 result.add("State: NE                   Number of workers: 6.0");
		 result.add("State: KY                   Number of workers: 6.0");
		 result.add("State: MT                   Number of workers: 5.0");
		 result.add("State: AR                   Number of workers: 4.0");
		 result.add("State: ND                   Number of workers: 3.0");
		 		
		 Assert.assertEquals(result, requirements.FindTopStatesByCode("occupation", code));
	}
	
	public void testByOccupationCode5()
	{
		 ArrayList<String> result = new ArrayList<String>();
                 ArrayList<Integer> code = new ArrayList<Integer>();
                 code.add(28);

		 result.add("State: CA                   Number of workers: 22.0");
		 result.add("State: FL                   Number of workers: 18.0");
		 result.add("State: UT                   Number of workers: 17.0");
		 result.add("State: NC                   Number of workers: 12.0");
		 result.add("State: MI                   Number of workers: 12.0");
		 		
		 Assert.assertEquals(result, requirements.FindTopStatesByCode("occupation", code));
	}
	
	//********************* Test By Industry Category *********************//
	
	public void testByIndustryCategory1()
	{
		ArrayList<String> result = new ArrayList<String>();
		result.add("State: CA                   Number of workers: 36.0");
		result.add("State: FL                   Number of workers: 30.0");
		result.add("State: UT                   Number of workers: 19.0");
		result.add("State: ND                   Number of workers: 15.0");
		result.add("State: NE                   Number of workers: 12.0");	 
	
		Assert.assertEquals(result, requirements.FindTopStatesByCategory("industry", "' Agriculture'"));
	}
	
	/**************************** Test By Industry Code *********************************/
	
	
	
	public void testByIndustryCode1()
	{
		 ArrayList<String> result = new ArrayList<String>();
                 ArrayList<Integer> code = new ArrayList<Integer>();
                 code.add(51);

		 result.add("State: UT                   Number of workers: 2.0");
		 result.add("State: AZ                   Number of workers: 2.0");
		 result.add("State: FL                   Number of workers: 1.0");
		 result.add("State: TX                   Number of workers: 1.0");
		 result.add("State: OK                   Number of workers: 1.0");
		 		
		 Assert.assertEquals(result, requirements.FindTopStatesByCode("industry", code));
	}
	
	/***********************************************************************************/
}
