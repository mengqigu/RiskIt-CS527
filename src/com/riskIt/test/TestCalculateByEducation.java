package com.riskIt.test;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import com.riskIt.controller.CalculateByEducation;
import com.riskIt.interfaces.CalculateByEducationInterface;
import com.riskIt.util.Factory;


import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * TestCalculateByEducation.java
 * Purpose: Tests the CalculateByEducation class
 * @author Bryan Angone
 * @version 1.0 7-12-09
 */

public class TestCalculateByEducation extends TestCase{
	
	private Statement statement;
	private ResultSet results;
	Random generator = new Random();
	
	public TestCalculateByEducation(){
		super();
	}
	
	public void testGetEducationLevelList(){
    	int count = 0;	
    	String educationLable = null;
    	String educationLable2 = null;
		try{
			statement = Factory.getConnection().createStatement();
	    	results = statement.executeQuery("SELECT DISTINCT EDUCATION from education");

	    	while(results.next()){
	    		count++;
	    		if(count <= 1){
	    			educationLable = results.getString("EDUCATION");
	    			educationLable2 = results.getString("EDUCATION");
	    		}
	    		int randomIndex = generator.nextInt(5);
	    		if(randomIndex == 2){
	    			educationLable = results.getString("EDUCATION");
	    		}
	    		
	    		if(randomIndex == 4){
	    			educationLable2 = results.getString("EDUCATION");
	    		}
	    	}
			
		} catch (SQLException e) {
			while (e != null) {
				System.err.println("\n----- SQLException -----");
				System.err.println("  SQL State:  " + e.getSQLState());
				System.err.println("  Error Code: " + e.getErrorCode());
				System.err.println("  Message:    " + e.getMessage());
				// for stack traces, refer to derby.log or uncomment this:
				// e.printStackTrace(System.err);
				e = e.getNextException();
			}
		}
		
		CalculateByEducationInterface calc = null;
		Assert.assertTrue(calc == null);
		
		calc = Factory.getCalculateByEducation();
		Assert.assertTrue(calc != null);
		Assert.assertTrue(calc.getClass().equals(CalculateByEducation.class));
		
		ArrayList<String> educationList = calc.getEducationLevelList();
		Assert.assertEquals(count-1, educationList.size());
		Assert.assertNotSame(count +1, educationList.size());
		
		Assert.assertTrue(educationList.contains(educationLable.trim()));
		Assert.assertTrue(educationList.contains(educationLable2.trim()));
		
		educationLable = "";
		Assert.assertFalse(educationList.contains(educationLable));
		educationLable = "Random Name";
		
		Assert.assertFalse(educationList.contains(educationLable));
		
		educationLable = "Doctorate degree(PhD EdD)";
		Assert.assertTrue(educationList.contains(educationLable));
		
		Assert.assertTrue(educationList.contains(educationLable2.trim()));
		
	}
	
	public void testIsInEducationList(){
		
    	int count = 0;	
    	String educationLable = null;
    	String educationLable2 = null;
		try{
			statement = Factory.getConnection().createStatement();
	    	results = statement.executeQuery("SELECT DISTINCT EDUCATION from education");

	    	while(results.next()){
	    		count++;
	    		if(count <= 1){
	    			educationLable = results.getString("EDUCATION");
	    			educationLable2 = results.getString("EDUCATION");
	    		}
	    		int randomIndex = generator.nextInt(5);
	    		if(randomIndex == 2){
	    			educationLable = results.getString("EDUCATION");
	    		}
	    		
	    		if(randomIndex == 4){
	    			educationLable2 = results.getString("EDUCATION");
	    		}
	    	}
			
		} catch (SQLException e) {
			while (e != null) {
				System.err.println("\n----- SQLException -----");
				System.err.println("  SQL State:  " + e.getSQLState());
				System.err.println("  Error Code: " + e.getErrorCode());
				System.err.println("  Message:    " + e.getMessage());
				// for stack traces, refer to derby.log or uncomment this:
				// e.printStackTrace(System.err);
				e = e.getNextException();
			}
		}
		
		CalculateByEducationInterface calc = null;
		Assert.assertTrue(calc == null);
		
		calc = Factory.getCalculateByEducation();
		Assert.assertTrue(calc != null);
		
		Assert.assertTrue(calc.getClass().equals(CalculateByEducation.class));
		
		Assert.assertTrue(calc.isInEducationList(educationLable));
		Assert.assertTrue(calc.isInEducationList(educationLable2));
		Assert.assertFalse(calc.isInEducationList(null));
		Assert.assertFalse(calc.isInEducationList(""));
		Assert.assertFalse(calc.isInEducationList("Fake Name"));
		Assert.assertTrue(calc.isInEducationList("Doctorate degree(PhD EdD)"));
		Assert.assertTrue(calc.isInEducationList("   " + " Doctorate degree(PhD EdD)" + "   "));
		Assert.assertFalse(calc.isInEducationList(" XX  " + " Doctorate degree(PhD EdD)" + " XX  "));
	}
	
	
	
	public void testCalculateIncomeByEducationLevel(){
    	double sum = 0;
    	double count = 0;
    	double average;
		try{
			statement = Factory.getConnection().createStatement();
	    	results = statement.executeQuery("SELECT WEEKWAGE, WORKWEEKS FROM (job JOIN education ON education.SSN = job.SSN) WHERE education.EDUCATION  = ' Doctorate degree(PhD EdD)'");

	    	while(results.next()){
	    		if(results.getInt("WEEKWAGE") > 0 && results.getInt("WORKWEEKS") > 0){
		    		sum = sum + (results.getInt("WEEKWAGE") * results.getInt("WORKWEEKS"));
		    		count++;
	    		}
	    	}
			
		} catch (SQLException e) {
			while (e != null) {
				System.err.println("\n----- SQLException -----");
				System.err.println("  SQL State:  " + e.getSQLState());
				System.err.println("  Error Code: " + e.getErrorCode());
				System.err.println("  Message:    " + e.getMessage());
				// for stack traces, refer to derby.log or uncomment this:
				// e.printStackTrace(System.err);
				e = e.getNextException();
			}
		}
		
		if(count == 0 || sum == 0){
			average = 0;
		} else {
			average = sum / count;
			
			BigDecimal bd = new BigDecimal(average);
		    bd = bd.setScale(2,BigDecimal.ROUND_HALF_EVEN);
		    average = bd.doubleValue();
		}
		
		CalculateByEducationInterface calc = null;
		Assert.assertTrue(calc == null);
		
		calc = Factory.getCalculateByEducation();
		Assert.assertTrue(calc != null);
		
		Assert.assertTrue(calc.getClass().equals(CalculateByEducation.class));
		
		Assert.assertTrue(average == calc.calculateIncomeByEducationLevel(" Doctorate degree(PhD EdD)"));
		Assert.assertFalse(average == calc.calculateIncomeByEducationLevel(null));
		Assert.assertFalse(average == calc.calculateIncomeByEducationLevel("Non Valid Education Level"));
		Assert.assertFalse((average +.01) == calc.calculateIncomeByEducationLevel(" Doctorate degree(PhD EdD)"));
		
		Assert.assertTrue(average == calc.calculateIncomeByEducationLevel("Doctorate degree(PhD EdD)"));
		Assert.assertTrue(average == calc.calculateIncomeByEducationLevel("    Doctorate degree(PhD EdD)    "));
		
		// Only Works with original unaltered DB
		// Assert.assertTrue(115422.96 == calc.calculateIncomeByEducationLevel(" Doctorate degree(PhD EdD)"));
	}
	
	
	public void testCalculateWeeklyWageByEducationLevel(){
    	double sum = 0;
    	double count = 0;
    	double average;
		try{
			statement = Factory.getConnection().createStatement();
	    	results = statement.executeQuery("SELECT WEEKWAGE FROM (job JOIN education ON education.SSN = job.SSN) WHERE education.EDUCATION  = ' Doctorate degree(PhD EdD)'");

	    	while(results.next()){
	    		if(results.getInt("WEEKWAGE") > 0){
		    		sum = sum + results.getInt("WEEKWAGE");
		    		count++;
	    		}
	    	}
			
		} catch (SQLException e) {
			while (e != null) {
				System.err.println("\n----- SQLException -----");
				System.err.println("  SQL State:  " + e.getSQLState());
				System.err.println("  Error Code: " + e.getErrorCode());
				System.err.println("  Message:    " + e.getMessage());
				// for stack traces, refer to derby.log or uncomment this:
				// e.printStackTrace(System.err);
				e = e.getNextException();
			}
		}
		
		if(count == 0 || sum == 0){
			average = 0;
		} else {
			average = sum / count;
			
			BigDecimal bd = new BigDecimal(average);
		    bd = bd.setScale(2,BigDecimal.ROUND_HALF_EVEN);
		    average = bd.doubleValue();
		}
		
		CalculateByEducationInterface calc = null;
		Assert.assertTrue(calc == null);
		
		calc = Factory.getCalculateByEducation();
		Assert.assertTrue(calc != null);
		
		Assert.assertTrue(calc.getClass().equals(CalculateByEducation.class));
		
		Assert.assertTrue(average == calc.calculateWeeklyWageByEducationLevel(" Doctorate degree(PhD EdD)"));
		Assert.assertFalse(average == calc.calculateWeeklyWageByEducationLevel(null));
		Assert.assertFalse(average == calc.calculateWeeklyWageByEducationLevel("Non Valid Education Level"));
		Assert.assertFalse((average +.01) == calc.calculateWeeklyWageByEducationLevel(" Doctorate degree(PhD EdD)"));
		
		Assert.assertTrue(average == calc.calculateWeeklyWageByEducationLevel("Doctorate degree(PhD EdD)"));
		Assert.assertTrue(average == calc.calculateWeeklyWageByEducationLevel("     Doctorate degree(PhD EdD)    "));

		// Only Works with original unaltered DB
		//Assert.assertTrue(2396.31 == calc.calculateWeeklyWageByEducationLevel(" Doctorate degree(PhD EdD)"));
		
	}
	
	
	public void testCalculateIncomeByAllEducationLevels(){
    	double sum = 0;
    	double count = 0;
    	double average;
		try{
			statement = Factory.getConnection().createStatement();
	    	results = statement.executeQuery("SELECT WEEKWAGE, WORKWEEKS FROM (job JOIN education ON education.SSN = job.SSN)");

	    	while(results.next()){
	    		if(results.getInt("WEEKWAGE") > 0 && results.getInt("WORKWEEKS") > 0){
		    		sum = sum + (results.getInt("WEEKWAGE") * results.getInt("WORKWEEKS"));
		    		count++;
	    		}
	    	}
			
		} catch (SQLException e) {
			while (e != null) {
				System.err.println("\n----- SQLException -----");
				System.err.println("  SQL State:  " + e.getSQLState());
				System.err.println("  Error Code: " + e.getErrorCode());
				System.err.println("  Message:    " + e.getMessage());
				// for stack traces, refer to derby.log or uncomment this:
				// e.printStackTrace(System.err);
				e = e.getNextException();
			}
		}
		
		if(count == 0 || sum == 0){
			average = 0;
		} else {
			average = sum / count;
			
			BigDecimal bd = new BigDecimal(average);
		    bd = bd.setScale(2,BigDecimal.ROUND_HALF_EVEN);
		    average = bd.doubleValue();
		}
		
		CalculateByEducationInterface calc = null;
		Assert.assertTrue(calc == null);
		
		calc = Factory.getCalculateByEducation();
		Assert.assertTrue(calc != null);
		
		Assert.assertTrue(calc.getClass().equals(CalculateByEducation.class));
		
		Assert.assertTrue(average == calc.calculateIncomeByAllEducationLevels());
		Assert.assertTrue(average == calc.calculateIncomeByEducationLevel("all"));
		
		
		Assert.assertFalse((average +.01) == calc.calculateIncomeByAllEducationLevels());
		
		Assert.assertTrue(average == calc.calculateIncomeByEducationLevel(" aLL"));
		Assert.assertTrue(average == calc.calculateIncomeByEducationLevel("    All    "));
		
		// Only Works with original unaltered DB
		// Assert.assertTrue(46945.07 == calc.calculateIncomeByAllEducationLevels());
	}
	
	
	public void testCalculateWeeklyWeageByAllEducationLevels(){
    	double sum = 0;
    	double count = 0;
    	double average;
		try{
			statement = Factory.getConnection().createStatement();
	    	results = statement.executeQuery("SELECT WEEKWAGE FROM (job JOIN education ON education.SSN = job.SSN) WHERE EDUCATION IS NOT NULL");

	    	while(results.next()){
	    		if(results.getInt("WEEKWAGE") > 0){
		    		sum = sum + (results.getInt("WEEKWAGE"));
		    		count++;
	    		}
	    	}
			
		} catch (SQLException e) {
			while (e != null) {
				System.err.println("\n----- SQLException -----");
				System.err.println("  SQL State:  " + e.getSQLState());
				System.err.println("  Error Code: " + e.getErrorCode());
				System.err.println("  Message:    " + e.getMessage());
				// for stack traces, refer to derby.log or uncomment this:
				// e.printStackTrace(System.err);
				e = e.getNextException();
			}
		}
		
		if(count == 0 || sum == 0){
			average = 0;
		} else {
			average = sum / count;
			
			BigDecimal bd = new BigDecimal(average);
		    bd = bd.setScale(2,BigDecimal.ROUND_HALF_EVEN);
		    average = bd.doubleValue();
		}
		
		CalculateByEducationInterface calc = null;
		Assert.assertTrue(calc == null);
		
		calc = Factory.getCalculateByEducation();
		Assert.assertTrue(calc != null);
		
		Assert.assertTrue(calc.getClass().equals(CalculateByEducation.class));
		
		Assert.assertTrue(average == calc.calculateWeeklyWageByAllEducationLevels());
		Assert.assertTrue(average == calc.calculateWeeklyWageByEducationLevel("all"));
		
		Assert.assertFalse((average +.01) == calc.calculateWeeklyWageByAllEducationLevels());
		
		Assert.assertTrue(average == calc.calculateWeeklyWageByEducationLevel(" aLL"));
		Assert.assertTrue(average == calc.calculateWeeklyWageByEducationLevel("    All    "));
		
		// Only Works with original unaltered DB
		//Assert.assertTrue(976.56 == calc.calculateWeeklyWageByAllEducationLevels());
	}
	
	
	
	public void testCalculateIncomeByEducationLevel2(){
		
		try{
			statement = Factory.getConnection().createStatement();
			
	    	statement.execute("INSERT INTO userRecord (NAME, SSN, RACE) VALUES (' Test Name 1', 999999980, 'Test Race 1')");
	    	statement.execute("INSERT INTO job (SSN, WEEKWAGE, WORKWEEKS) VALUES (999999980, 500, 40)");
	    	statement.execute("INSERT INTO education (SSN, EDUCATION) VALUES (999999980, ' Test Education 1')");
	    	statement.execute("INSERT INTO userRecord (NAME, SSN, RACE) VALUES (' Test Name 2', 999999981, 'Test Race 1')");
	    	statement.execute("INSERT INTO job (SSN, WEEKWAGE, WORKWEEKS) VALUES (999999981, 1500, 20)");
	    	statement.execute("INSERT INTO education (SSN, EDUCATION) VALUES (999999981, ' Test Education 1')");
	    	
			CalculateByEducationInterface calc = null;
			Assert.assertTrue(calc == null);
			
			calc = Factory.getCalculateByEducation();
			Assert.assertTrue(calc != null);
			
			Assert.assertTrue(calc.getClass().equals(CalculateByEducation.class));
		
			Assert.assertTrue((((500 * 40) + (1500 * 20)) / 2) == calc.calculateIncomeByEducationLevel(" Test Education 1"));

			statement.execute("DELETE from job WHERE SSN = 999999980");
			statement.execute("DELETE from education WHERE SSN = 999999980");
			statement.execute("DELETE from userRecord WHERE SSN = 999999980");

			statement.execute("DELETE from job WHERE SSN = 999999981");
	    	statement.execute("DELETE from education WHERE SSN = 999999981");
	    	statement.execute("DELETE from userRecord WHERE SSN = 999999981");


			
		} catch (SQLException e) {
			while (e != null) {
				System.err.println("\n----- SQLException -----");
				System.err.println("  SQL State:  " + e.getSQLState());
				System.err.println("  Error Code: " + e.getErrorCode());
				System.err.println("  Message:    " + e.getMessage());
				// for stack traces, refer to derby.log or uncomment this:
				// e.printStackTrace(System.err);
				e = e.getNextException();
			}
		}
		
	}
	
	public void testCalculateWeeklyWageByEducationLevel2(){
		
		try{
			statement = Factory.getConnection().createStatement();
			
	    	statement.execute("INSERT INTO userRecord (NAME, SSN, RACE) VALUES (' Test Name 1', 999999980, 'Test Race 1')");
	    	statement.execute("INSERT INTO job (SSN, WEEKWAGE, WORKWEEKS) VALUES (999999980, 500, 40)");
	    	statement.execute("INSERT INTO education (SSN, EDUCATION) VALUES (999999980, ' Test Education 1')");
	    	statement.execute("INSERT INTO userRecord (NAME, SSN, RACE) VALUES (' Test Name 2', 999999981, 'Test Race 1')");
	    	statement.execute("INSERT INTO job (SSN, WEEKWAGE, WORKWEEKS) VALUES (999999981, 1500, 20)");
	    	statement.execute("INSERT INTO education (SSN, EDUCATION) VALUES (999999981, ' Test Education 1')");
	    	
			CalculateByEducationInterface calc = null;
			Assert.assertTrue(calc == null);
			
			calc = Factory.getCalculateByEducation();
			Assert.assertTrue(calc != null);
			
			Assert.assertTrue(calc.getClass().equals(CalculateByEducation.class));
		
			Assert.assertTrue(((500 + 1500) / 2) == calc.calculateWeeklyWageByEducationLevel(" Test Education 1"));

			statement.execute("DELETE from job WHERE SSN = 999999980");
			statement.execute("DELETE from education WHERE SSN = 999999980");
			statement.execute("DELETE from userRecord WHERE SSN = 999999980");

			statement.execute("DELETE from job WHERE SSN = 999999981");
	    	statement.execute("DELETE from education WHERE SSN = 999999981");
	    	statement.execute("DELETE from userRecord WHERE SSN = 999999981");


			
		} catch (SQLException e) {
			while (e != null) {
				System.err.println("\n----- SQLException -----");
				System.err.println("  SQL State:  " + e.getSQLState());
				System.err.println("  Error Code: " + e.getErrorCode());
				System.err.println("  Message:    " + e.getMessage());
				// for stack traces, refer to derby.log or uncomment this:
				// e.printStackTrace(System.err);
				e = e.getNextException();
			}
		}
		
	}
	
}
