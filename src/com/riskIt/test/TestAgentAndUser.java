package com.riskIt.test;

import com.riskIt.controller.*;
import junit.framework.Assert;
import junit.framework.TestCase;
import com.riskIt.data.*;
import java.util.ArrayList;

/**
 * This test class conducts tests for the following requirements
 * 1. Creating a new user
 * 2. Calculating the customer's score
 * 3. Creating invitations for customers
 * 4. Getting customer's quote
 * 
 * @author Poornima Tantry
 *
 */

public class TestAgentAndUser extends TestCase
{
	private AgentManager agent;
	private UserManager user;
	
	public TestAgentAndUser()
	{
		super();
		agent = new AgentManager();
		user = new UserManager();
	}
	
	
	//*****************test for createNewUser() *************************//
	
	
	public void testCreateNewUser1()
	{
		agent.deleteUser("612345686");
		//new data
		Assert.assertEquals(1, agent.createNewUser("Veda", "60503", 612345686, 55, 
				"Female", "Married-civilian spouse present", "Indian", 
				"Joint both under 65", "Householder", "Householder", 
				"India", "India", "India", "Inida", 55, 20, 35, 
				"Mother only present", "Re-entrant", "Unemployed full-time", 
				"Bachelors degree(BA AB BS)", "College or university", "Private",
				"No", 2, 17, 2, 20, 0, 52));
	}
	public void testCreateNewUser2()
	{
		//try to insert same data - its a fail as the data is already present
		Assert.assertEquals(0, agent.createNewUser("Veda", "60503", 612345686, 55, 
				"Female", "Married-civilian spouse present", "Indian", 
				"Joint both under 65", "Householder", "Householder", 
				"India", "India", "India", "Inida", 55, 20, 35, 
				"Mother only present", "Re-entrant", "Unemployed full-time", 
				"Bachelors degree(BA AB BS)", "College or university", "Private",
				"No", 2, 17, 2, 20, 0, 52));
		
	}
	
	public void testCreateNewUser3()
	{
		agent.deleteUser("812345686");
		//new data
		Assert.assertEquals(1, agent.createNewUser("Balu", "60504", 812345686, 28, 
				"Male", "Never married", "Indian", 
				"Joint both under 65", "Single", "Nonfamily householder", 
				"India", "India", "India", "Inida", 80, 0, 35, 
				"Both parents present", "null", "Full-time schedules", 
				"Bachelors degree(BA AB BS)", "College or university", "Private",
				"No", 42, 30, 50, 80, 0, 52));
		
	}
	public void testCreateNewUser4()
	{
		//try to insert same data - its a fail as the data is already present
		Assert.assertEquals(0, agent.createNewUser("Balu", "60504", 812345686, 28, 
				"Male", "Never married", "Indian", 
				"Joint both under 65", "Single", "Nonfamily householder", 
				"India", "India", "India", "Inida", 80, 0, 35, 
				"Both parents present", "null", "Full-time schedules", 
				"Bachelors degree(BA AB BS)", "College or university", "Private",
				"No", 42, 30, 50, 80, 0, 52));
		
	}
	
	
	//*****************test for calculateScore() *************************//
	
	//tests to calculate scores and compare the results.
	
	public void testCalculateScore1()
	{
		Assert.assertEquals(88.0, agent.calculateScore(101315640), 1.0);
	}
	public void testCalculateScore2()
	{
		Assert.assertEquals(72.0, agent.calculateScore(101316138), 1.0);
	}
	public void testCalculateScore3()
	{
		Assert.assertEquals(90.0, agent.calculateScore(101315670), 1.0);
	}
	public void testCalculateScore4()
	{
		Assert.assertEquals(88.0, agent.calculateScore(101315841), 1.0);
	}
	
	//*****************test for Create Invitations() *************************//
	
	public void testCreateInvitation1()
	{
		Invitation inv1 = new Invitation();
		inv1.setUserSSN(102212412);
		inv1.setlossCoverage(3000000);
		inv1.setExpoUnit(100000);
		inv1.setPropertyValue(1800000);
		inv1.setDeductible(1000);
		
		double premium = agent.calculateTotalValue(inv1);
		Assert.assertEquals(58.08, premium, 1.0);
	}
	
	public void testCreateInvitation2()
	{
		Invitation inv2 = new Invitation();
		inv2.setUserSSN(101315640);
		inv2.setlossCoverage(25000);
		inv2.setExpoUnit(50000);
		inv2.setPropertyValue(100000);
		inv2.setDeductible(500);
		
		double premium = agent.calculateTotalValue(inv2);
		Assert.assertEquals(3.025, premium, 1.0);
	}
	
	public void testCreateInvitation3()
	{
		Invitation inv3 = new Invitation();
		inv3.setUserSSN(101315841);
		inv3.setlossCoverage(50000);
		inv3.setExpoUnit(5000);
		inv3.setPropertyValue(100000);
		inv3.setDeductible(2500);
		
		double premium = agent.calculateTotalValue(inv3);
		Assert.assertEquals(36.30, premium, 1.0);
	}
	
	public void testCreateInvitation4()
	{
		Invitation inv4 = new Invitation();
		inv4.setUserSSN(101315670);
		inv4.setlossCoverage(200000);
		inv4.setExpoUnit(50000);
		inv4.setPropertyValue(1000000);
		inv4.setDeductible(5000);
		
		double premium = agent.calculateTotalValue(inv4);
		Assert.assertEquals(29.04, premium, 1.0);
	}
	
	public void testCreateInvitation5()
	{
		Invitation inv5 = new Invitation();
		inv5.setUserSSN(101315544);
		inv5.setlossCoverage(2000);
		inv5.setExpoUnit(500);
		inv5.setPropertyValue(10000);
		inv5.setDeductible(50);
		
		double premium = agent.calculateTotalValue(inv5);
		Assert.assertEquals(29.04, premium, 1.0);
	}
	
	
	//*****************test for customer quotes() *************************//
	
	public void testCustomerQuotes1()
	{
		ArrayList<Invitation> inv1 = new ArrayList<Invitation>();
		Invitation cust1 = new Invitation();
		long ssn = 101315544;
		double deductible = 50;
		double worth = 10000;
		
		cust1 = user.calculateQuoteForCustomer(ssn, deductible, worth, inv1);
		Assert.assertEquals(5.0, cust1.getPremium(), 1.0);
	}
	public void testCustomerQuotes2()
	{
		ArrayList<Invitation> inv2 = new ArrayList<Invitation>();
		Invitation cust2 = new Invitation();
		long ssn = 102212412;
		double deductible = 5000;
		double worth = 200000;
		
		cust2 = user.calculateQuoteForCustomer(ssn, deductible, worth, inv2);
		Assert.assertEquals(5.5, cust2.getPremium(), 1.0);
	}
	public void testCustomerQuotes3()
	{
		ArrayList<Invitation> inv3 = new ArrayList<Invitation>();
		Invitation cust3 = new Invitation();
		long ssn = 101315841;
		double deductible = 500;
		double worth = 2000000;
		
		cust3 = user.calculateQuoteForCustomer(ssn, deductible, worth, inv3);
		Assert.assertEquals(5.5, cust3.getPremium(), 1.0);
	}
	public void testCustomerQuotes4()
	{
		ArrayList<Invitation> inv4 = new ArrayList<Invitation>();
		Invitation cust4 = new Invitation();
		long ssn = 101315571;
		double deductible = 5000;
		double worth = 200000;
		
		cust4 = user.calculateQuoteForCustomer(ssn, deductible, worth, inv4);
		Assert.assertEquals(6.05, cust4.getPremium(), 1.0);
	}
	public void testCustomerQuotes5()
	{
		ArrayList<Invitation> inv5 = new ArrayList<Invitation>();
		Invitation cust5 = new Invitation();
		long ssn = 101315670;
		double deductible = 100;
		double worth = 500000;
		
		cust5 = user.calculateQuoteForCustomer(ssn, deductible, worth, inv5);
		Assert.assertEquals(5.5, cust5.getPremium(), 1.0);
	}
	//*****************test for UpdateWageTable() *************************//
	public void testUpdateWageTable(){
		
		try{
		Assert.assertEquals(887,user.updatewagetable(33,13),13);	
		Assert.assertEquals(661,user.updatewagetable(1,2),15);
		Assert.assertEquals(1071,user.updatewagetable(2,44),35);
		Assert.assertEquals(0,user.updatewagetable(13,18),85);
		Assert.assertEquals(663.0,user.updatewagetable(18,33),85);
		 Assert.assertEquals(1038,user.updatewagetable( 49,26),15);
		}catch(NullPointerException e){
			e.printStackTrace();
		}
	}
	//*****************test for UpdateUserInformation() *************************//
	public void testUpdateUserInformation(){
		try{
		 Assert.assertEquals(861,user.userinformation(101686287,33,41), 25);
		 Assert.assertEquals(1097,user.userinformation(102063225,33,16), 35);
		 Assert.assertEquals(825,user.userinformation(101686308,33,19), 45);
		}catch(NullPointerException e){
			e.printStackTrace();
		}
	}
	//*****************test for UpdateTable() *************************//
	public void testUpdateTable(){
		try{
		 Assert.assertEquals(1565,user.updatetable(102142818, 4,34),15);	
		 Assert.assertEquals(1084,user.updatetable(101816610, 4,38),25);
		 Assert.assertEquals(800,user.updatetable(101483259,9,14),85);
		 Assert.assertEquals(718,user.updatetable(101483217,33,29),75);
		 Assert.assertEquals(874,user.updatetable(101482689, 37,17),45);
		 Assert.assertEquals(887,user.updatetable(101478156,50,23),95);
		}catch(NullPointerException e){
			e.printStackTrace();
		}
	}
	//*****************test for UpdateStabilty() *************************//
	public void testUpdateStability(){
		try{
			Assert.assertEquals(2,user.updatestability("23441",32,36), 0);
			Assert.assertEquals(2,user.updatestability("33071",31,34), 20);
			Assert.assertEquals(1,user.updatestability("74963",12,2), 10);
			Assert.assertEquals(0,user.updatestability("77227",44,10), 90);
			
		}catch(NullPointerException e){
			e.printStackTrace();
		}
	}
	//*****************test for Find Top5Occupationcode() *************************//
public void testTopFiveOccupationcode1(){
	try{
		Assert.assertEquals(5,user.FindTopOccupationCode("IL"),5);
	}catch(NullPointerException e){
		e.printStackTrace();
		
	}
}
public void testTopFiveOccupationcode2(){
	try{
		Assert.assertEquals(5,user.FindTopOccupationCode("CA"),1);
	}catch(NullPointerException e){
		e.printStackTrace();
		
	}
}
public void testTopFiveOccupationcode3(){
	try{
		Assert.assertEquals(5,user.FindTopOccupationCode("NY"),1);
	}catch(NullPointerException e){
		e.printStackTrace();
		
	}
}
public void testTopFiveOccupationcode4(){
	try{
		Assert.assertEquals(5,user.FindTopOccupationCode("VA"),1);
	}catch(NullPointerException e){
		e.printStackTrace();
		
	}
}
//*****************test for FindTop5IndustryCode() *************************//
public void testTopFiveIndustrycode1(){
	try{
		Assert.assertEquals(5,user.FindTopIndustryCode("VA"),1);
	}catch(NullPointerException e){
		e.printStackTrace();
		
	}
}
public void testTopFiveIndustrycode2(){
	try{
		Assert.assertEquals(5,user.FindTopIndustryCode("WI"),1);
	}catch(NullPointerException e){
		e.printStackTrace();
		
	}
}
public void testTopFiveIndustrycode3(){
	try{
		Assert.assertEquals(5,user.FindTopIndustryCode("MS"),1);
	}catch(NullPointerException e){
		e.printStackTrace();
		
	}
}
public void testTopFiveIndustrycode4(){
	try{
		Assert.assertEquals(5,user.FindTopIndustryCode("MI"),1);
	}catch(NullPointerException e){
		e.printStackTrace();
		
	}
}
}

















