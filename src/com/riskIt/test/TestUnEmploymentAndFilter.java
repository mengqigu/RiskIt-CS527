package com.riskIt.test;

import junit.framework.Assert;
import junit.framework.TestCase;
import java.util.ArrayList;
import com.riskIt.controller.UserManager;


/**
 * This file contains 5 test cases for each method the is needed in requirement
 * The methods that are tested here are
 * 1. filterZipcode
 * 2. filterEducation
 * 3. filterOccupation
 * 4. filterMaritalStatus
 * 5. filterEstimatedIncome
 * 6. filterScore
 * 7. calculateUnEmploymentRate
 * 8. browseUserProperties
 * 
 * @author Poornima Tantry
 *
 */


public class TestUnEmploymentAndFilter extends TestCase 
{		
	private UserManager user;
		
	  public TestUnEmploymentAndFilter() 
	  {
	    super();
	    user = new UserManager();
	  }
	  
	  //**********Test for FilterZipcode **********************//
	  
	//test to check if filter work for multiple records	  
	  public void testFilterZipcode1()
	  {	  	  
		  Assert.assertEquals(3, user.filterZipcode("35578"));
	  
	  }
	  public void testFilterZipcode2()
	  {	  	  
		  Assert.assertEquals(1, user.filterZipcode("28665"));
	  
	  }
	  public void testFilterZipcode3()
	  {	  	  
		  Assert.assertEquals(1, user.filterZipcode("41262"));
	  
	  }
	  public void testFilterZipcode4()
	  {	  	  
		  Assert.assertEquals(1, user.filterZipcode("89170"));
	  
	  }
	  
	  //test case to show no records with this zipcode
	  public void testFilterZipcode5()
	  {	  	  
		  Assert.assertEquals(0, user.filterZipcode("9999"));
	  
	  }
	  
	//**********Test for FilterEducation **********************//
	  
	  //test to show multiple record selection
	  public void testFilterEducation1()
	  {	  	  
		  Assert.assertEquals(72554, user.filterEducation("High school graduate"));
	  
	  }
	  public void testFilterEducation2()
	  {	  	  
		  Assert.assertEquals(70864, user.filterEducation("Children"));
	  
	  }
	  public void testFilterEducation3()
	  {	  	  
		  Assert.assertEquals(29750, user.filterEducation("Bachelors degree(BA AB BS)"));
	  
	  }
	  public void testFilterEducation4()
	  {	  	  
		  Assert.assertEquals(41774, user.filterEducation("Some college but no degree"));
	  
	  }
	  //test to find no customers with Ph.D degree
	  public void testFilterEducation5()
	  {	  	  
		  Assert.assertEquals(0, user.filterEducation("PhD"));
	  
	  }
	  
	 
//**********Test for FilterOccupation **********************//
	  
	  //test to show multiple record selection
	  public void testFilterOccupation1()
	  {	  	  
		  Assert.assertEquals(20809, user.filterOccupation("Professional specialty"));
	  
	  }
	  public void testFilterOccupation2()
	  {	  	  
		  Assert.assertEquals(1206, user.filterOccupation("Private household services"));
	  
	  }
	  public void testFilterOccupation3()
	  {	  	  
		  Assert.assertEquals(4490, user.filterOccupation("Technicians and related support"));
	  
	  }
	  public void testFilterOccupation4()
	  {	  	  
		  Assert.assertEquals(52, user.filterOccupation("Armed Forces"));
	  
	  }
	  //test to find no customers with teaching occupation
	  public void testFilterOccupation5()
	  {	  	  
		  Assert.assertEquals(0, user.filterOccupation("Teaching"));
	  
	  }
	  
	  
//**********Test for FilterMatitalStatus **********************//
	 
	  //test to show multiple record selection
	  public void testFilterMaritalStatus1()
	  {	  	  
		  Assert.assertEquals(129628, user.filterMaritalStatus("Never married"));
	  
	  }
	  public void testFilterMaritalStatus2()
	  {	  	  
		  Assert.assertEquals(15788, user.filterMaritalStatus("Widowed"));
	  
	  }
	  public void testFilterMaritalStatus3()
	  {	  	  
		  Assert.assertEquals(19160, user.filterMaritalStatus("Divorced"));
	  
	  }
	  public void testFilterMaritalStatus4()
	  {	  	  
		  Assert.assertEquals(126315, user.filterMaritalStatus("Married-civilian spouse present"));
	  
	  }
	  //test to find no customers if different words are provided
	  public void testFilterMaritalStatus5()
	  {	  	  
		  Assert.assertEquals(0, user.filterMaritalStatus("Single"));
	  
	  }
	  
	  
//**********Test for FilterEstimatedIncome **********************//
	  
	  //test to show multiple record selection
	  public void testFilterEstimatedIncome1()
	  {	  	  
		  Assert.assertEquals(11, user.filterEstimatedIncome("2150"));
	  
	  }
	  public void testFilterEstimatedIncome2()
	  {	  	  
		  Assert.assertEquals(785, user.filterEstimatedIncome("1000"));
	  
	  }
	  public void testFilterEstimatedIncome3()
	  {	  	  
		  Assert.assertEquals(1, user.filterEstimatedIncome("8650"));
	  
	  }
	  public void testFilterEstimatedIncome4()
	  {	  	  
		  Assert.assertEquals(4, user.filterEstimatedIncome("4800"));
	  
	  }
	  //test to find no customers with zero income
	  public void testFilterEstimatedIncome5()
	  {	  	  
		  Assert.assertEquals(0, user.filterEstimatedIncome("0"));
	  
	  }
	 
	  
  //**********Test for calculateUnEmploymentRate **********************//
	  
	  //test to show multiple record selection
	  public void testcalculateUnEmploymentRate1()
	  {	  	  
		  Assert.assertEquals(6.85, user.calculateUnEmploymentRate("CA") );
	  }
	  public void testcalculateUnEmploymentRate2()
	  {	  	  
		  Assert.assertEquals(3.88, user.calculateUnEmploymentRate("IL"));
	  }
	  public void testcalculateUnEmploymentRate3()
	  {	  	  
		  Assert.assertEquals(5.14, user.calculateUnEmploymentRate("MI"));
	  }
	  public void testcalculateUnEmploymentRate4()
	  {	  	  
		  Assert.assertEquals(5.71, user.calculateUnEmploymentRate("MO"));
	  }
 	  
	 
	 
	  
//**********Test for browseUserProperties() **********************//
	  
	  //test to show multiple record selection
	  
	  //search using only ssn
	  public void testbrowseUserProperties1()
	  {	  	  
		  ArrayList<String> prop = new ArrayList<String>();
		  prop.add(0, "");
		  prop.add(1, "101315544");
		  prop.add(2,"");
		  prop.add(3,"");
		  prop.add(4,"");
		  prop.add(5,"");
		  prop.add(6,"");
		  prop.add(7,"");
		  prop.add(8,"");
		  prop.add(9,"");
		  
		  Assert.assertEquals(1, user.browseUserProperties(prop));
	  
	  }
	  //search using age and sex
	  public void testbrowseUserProperties2()
	  {	  	  
		  ArrayList<String> prop = new ArrayList<String>();
		  
		  prop.add(0, "");
		  prop.add(1, "");
		  prop.add(2, "55");
		  prop.add(3, "Male");
		  prop.add(4,"");
		  prop.add(5,"");
		  prop.add(6,"");
		  prop.add(7,"");
		  prop.add(8,"");
		  prop.add(9,"");
		 
		  Assert.assertEquals(1238, user.browseUserProperties(prop));
	  
	  }
	  //search using age and marital-status
	  public void testbrowseUserProperties3()
	  {	  	  
		  ArrayList<String> prop = new ArrayList<String>();
		  prop.add(0, "");
		  prop.add(1, "");
		  prop.add(2, "45");
		  prop.add(3, "");
		  prop.add(4, "Divorced");
		  prop.add(5,"");
		  prop.add(6,"");
		  prop.add(7,"");
		  prop.add(8,"");
		  prop.add(9,"");
		  
		  Assert.assertEquals(599, user.browseUserProperties(prop));
	  
	  }
	  //search based on sex and marital status
	  public void testbrowseUserProperties4()
	  {	  	  
		  ArrayList<String> prop = new ArrayList<String>();
		  
		  prop.add(0, "");
		  prop.add(1, "");
		  prop.add(2, "");
		  prop.add(3, "Female");
		  prop.add(4, "Divorced");
		  prop.add(5,"");
		  prop.add(6,"");
		  prop.add(7,"");
		  prop.add(8,"");
		  prop.add(9,"");
		  
		  Assert.assertEquals(11518, user.browseUserProperties(prop));
	  
	  }
	  
	  //test to show 0 customer based on ssn, age, sex, marital status and race
	  public void testbrowseUserProperties5()
	  {	  	  
		  ArrayList<String> prop = new ArrayList<String>();
		  prop.add(0, "");
		  prop.add(1, "11223344");
		  prop.add(2, "99");
		  prop.add(3, "Male");
		  prop.add(4, "Widowed");
		  prop.add(5, "White");
		  prop.add(6,"");
		  prop.add(7,"");
		  prop.add(8,"");
		  prop.add(9,"");
		  
		  Assert.assertEquals(0, user.browseUserProperties(prop));
	  
	  }
	  
	  
}
