package com.riskIt.test;

import java.util.ArrayList;

import com.riskIt.controller.Requirements19_24;

import junit.framework.Assert;
import junit.framework.TestCase;
/**
 * This test class conducts tests for the following requirements ( Menu Option ):
 * 22. Compute average education for a given occupation or industry
 * 23. List occupations/industries with workers having highest (and lowest) education
 * 
 * @author Selma Tiganj
 *
 */
public class TestAverageHighLowEducationLevel extends TestCase {

	private Requirements19_24 requirements;
	
	public TestAverageHighLowEducationLevel() {
		super();
		requirements = new Requirements19_24();
	}
	
	//********************* Test ComputeAverageEducationString By Occupation  *********************//
	
	public void testComputeAverageEducationStringForOccupation1()
	{
		String result = " Some college but no degree                       ";
	
		Assert.assertEquals(result, requirements.ComputeAverageEducationString("occupation", 46));
	}
	
	public void testComputeAverageEducationStringForOccupation2()
	{
		String result = " Associates degree-academic program               ";	 
	
		Assert.assertEquals(result, requirements.ComputeAverageEducationString("occupation", 3));
	}
	
	//********************* Test ComputeAverageEducationString By Industry  *********************//

	public void testComputeAverageStringEducationForIndustry1()
	{
		String result = " Some college but no degree                       ";	 

		Assert.assertEquals(result, requirements.ComputeAverageEducationString("industry", 51));
	}
	
	public void testComputeAverageEducationStringForIndustry2()
	{

		String result=" 12th grade no diploma                            ";	 

		Assert.assertEquals(result, requirements.ComputeAverageEducationString("industry", 10));
	}
	
	public void testComputeAverageEducationStringForIndustry3()
	{

		String result = " Some college but no degree                       ";	 

		Assert.assertEquals(result, requirements.ComputeAverageEducationString("industry", 20));
	}
	//********************* Test ComputeAverageEducation By Occupation  *********************//
	
	public void testComputeAverageEducationForOccupation1()
	{
		Assert.assertEquals(10, requirements.ComputeAverageEducation("occupation", 46));
	}
	
	public void testComputeAverageEducationForOccupation2()
	{		
		Assert.assertEquals(12, requirements.ComputeAverageEducation("occupation", 3));
	}
	//********************* Test ComputeAverageEducation By Industry  *********************//

	public void testComputeAverageEducationForIndustry1()
	{
		Assert.assertEquals(10, requirements.ComputeAverageEducation("industry", 51));
	}

	public void testComputeAverageEducationForIndustry2()
	{	 
		Assert.assertEquals(8, requirements.ComputeAverageEducation("industry", 10));
	}

	public void testComputeAverageEducationForIndustry3()
	{
		Assert.assertEquals(10, requirements.ComputeAverageEducation("industry", 20));
	}
	
	//****************** Test Find  Occupation  with Highest/Lowest Education Level *******//

	public void testFindOccupationWithHighestLowestEducationLevel1()
	{
		ArrayList<String> result = new ArrayList<String>();
		
		result.add(" Bachelors degree(BA AB BS)                       ");
		
		result.add(" 11th grade                                       ");
		
		result.add("The average income for occupation with highest education level is 25.14% higher than for occupation with lowest education level");
		
		Assert.assertEquals(result, requirements.FindCccupationIndustryWithHighestLowestEducation("occupation"));
	}
	//****************** Test Find  Industry  with Highest/Lowest Education Level *******//

	public void testFindIndustryWithHighestLowestEducationLevel1()
	{
		ArrayList<String> result = new ArrayList<String>();
		
		result.add(" Associates degree-academic program               ");

                result.add(" 11th grade                                       ");
		
		result.add("The average income for industry with highest education level is 90.58% higher than for industry with lowest education level");
		
		Assert.assertEquals(result, requirements.FindCccupationIndustryWithHighestLowestEducation("industry"));
	}	
}
