package com.riskIt.test;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import com.riskIt.controller.CalculateByRace;
import com.riskIt.interfaces.CalculateByRaceInterface;
import com.riskIt.util.Factory;


import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * TestCalculateByRace.java
 * Purpose: Tests the CalculateByRace class
 * @author Bryan Angone
 * @version 1.0 7-12-09
 */

public class TestCalculateByRace extends TestCase{
	
	private Statement statement;
	private ResultSet results;
	Random generator = new Random();
	
	public TestCalculateByRace(){
		super();
	}
	
	public void testGetRaceList(){
		int count = 0;
    	String raceLable = null;
    	String raceLable2 = null;
		try{
			statement = Factory.getConnection().createStatement();
			results = statement.executeQuery("SELECT DISTINCT RACE FROM userrecord");

	    	while(results.next()){
	    		count++;
	    		if(count <= 1){
	    			raceLable = results.getString("RACE");
	    			raceLable2 = results.getString("RACE");
	    		}
	    		int randomIndex = generator.nextInt(5);
	    		if(randomIndex == 2){
	    			raceLable = results.getString("RACE");
	    		}
	    		
	    		if(randomIndex == 4){
	    			raceLable2 = results.getString("RACE");
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
		
		CalculateByRaceInterface calc = null;
		Assert.assertTrue(calc == null);
		
		calc = Factory.getCalculateByRace();
		Assert.assertTrue(calc != null);
		Assert.assertTrue(calc.getClass().equals(CalculateByRace.class));
		
		ArrayList<String> raceList = calc.getRaceList();
		Assert.assertEquals(count, raceList.size());
		Assert.assertNotSame(count +1, raceList.size());
		
		Assert.assertTrue(raceList.contains(raceLable.trim()));
		Assert.assertTrue(raceList.contains(raceLable2.trim()));
		
		raceLable = "";
		Assert.assertFalse(raceList.contains(raceLable));
		raceLable = "Random Name";
		
		Assert.assertFalse(raceList.contains(raceLable));
		
		raceLable = "White";
		Assert.assertTrue(raceList.contains(raceLable.trim()));
		
		Assert.assertTrue(raceList.contains(raceLable2.trim()));
	}
	
	public void testIsInRaceList(){
		
    	int count = 0;	
    	String raceLable = null;
    	String raceLable2 = null;
		try{
			statement = Factory.getConnection().createStatement();
	    	results = statement.executeQuery("SELECT DISTINCT RACE from userrecord");

	    	while(results.next()){
	    		count++;
	    		if(count <= 1){
	    			raceLable = results.getString("RACE");
	    			raceLable2 = results.getString("RACE");
	    		}
	    		int randomIndex = generator.nextInt(5);
	    		if(randomIndex == 2){
	    			raceLable = results.getString("RACE");
	    		}
	    		
	    		if(randomIndex == 4){
	    			raceLable2 = results.getString("RACE");
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
		
		CalculateByRaceInterface calc = null;
		Assert.assertTrue(calc == null);
		
		calc = Factory.getCalculateByRace();
		Assert.assertTrue(calc != null);
		
		Assert.assertTrue(calc.getClass().equals(CalculateByRace.class));
		
		Assert.assertTrue(calc.isInRaceList(raceLable));
		Assert.assertTrue(calc.isInRaceList(raceLable2));
		Assert.assertFalse(calc.isInRaceList(null));
		Assert.assertFalse(calc.isInRaceList(""));
		Assert.assertFalse(calc.isInRaceList("Fake Name"));
		Assert.assertTrue(calc.isInRaceList("White"));
		Assert.assertTrue(calc.isInRaceList("   " + "White" + "   "));
		Assert.assertFalse(calc.isInRaceList(" XX  " + " White" + " XX  "));
	}
	
	
	
	public void testCalculateWeeklyWageByRace(){
    	double sum = 0;
    	double count = 0;
    	double average;
		try{
			statement = Factory.getConnection().createStatement();
	    	results = statement.executeQuery("SELECT job.WEEKWAGE from job JOIN userrecord on job.SSN = userrecord.SSN WHERE userrecord.RACE = ' White'");
	    	
	    	while(results.next()){
	    		if(results.getString("WEEKWAGE") == null){
	    			continue;
	    		}
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
		
		CalculateByRaceInterface calc = null;
		Assert.assertTrue(calc == null);
		
		calc = Factory.getCalculateByRace();
		Assert.assertTrue(calc != null);
		
		Assert.assertTrue(calc.getClass().equals(CalculateByRace.class));
		
		Assert.assertTrue(average == calc.calculateWeeklyWageByRace(" White"));
		
		Assert.assertFalse(average == calc.calculateWeeklyWageByRace(null));
		Assert.assertFalse(average == calc.calculateWeeklyWageByRace("Non Valid Race"));
		Assert.assertFalse((average +.01) == calc.calculateWeeklyWageByRace(" White"));
		
		Assert.assertTrue(average == calc.calculateWeeklyWageByRace("White"));
		Assert.assertTrue(average == calc.calculateWeeklyWageByRace("     White    "));

		// Only Works with original unaltered DB
		// Assert.assertTrue(986.97 == calc.calculateWeeklyWageByRace(" White"));
		
	}
	
	
	
	public void testCalculateWeelkyWageByAllRaces(){
    	double sum = 0;
    	double count = 0;
    	double average;
		try{
			statement = Factory.getConnection().createStatement();
	    	results = statement.executeQuery("SELECT job.WEEKWAGE from job");
	    	
	    	while(results.next()){
	    		if(results.getString("WEEKWAGE") == null){
	    			continue;
	    		}
	    		
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
		
		CalculateByRaceInterface calc = null;
		Assert.assertTrue(calc == null);
		
		calc = Factory.getCalculateByRace();
		Assert.assertTrue(calc != null);
		
		Assert.assertTrue(calc.getClass().equals(CalculateByRace.class));
		
		Assert.assertTrue(average == calc.calculateWeelkyWageByAllRaces());
		Assert.assertTrue(average == calc.calculateWeeklyWageByRace("All"));
		
		Assert.assertFalse(average == calc.calculateWeeklyWageByRace(null));
		Assert.assertFalse((average +.01) == calc.calculateWeelkyWageByAllRaces());
		
		Assert.assertTrue(average == calc.calculateWeeklyWageByRace("    all    "));
		
		// Only Works with original unaltered DB
		//Assert.assertTrue(976.56 == calc.calculateWeeklyWageByRace(" all"));
		//Assert.assertTrue(976.56 == calc.calculateWeelkyWageByAllRaces());
	}
	
	
	public void testCalculateIncomeByRace(){
    	double sum = 0;
    	double count = 0;
    	double average;
		try{
			statement = Factory.getConnection().createStatement();
	    	results = statement.executeQuery("SELECT job.WEEKWAGE, job.WORKWEEKS from job JOIN userrecord on job.SSN = userrecord.SSN WHERE userrecord.RACE = ' White'");
	    	
	    	while(results.next()){
	    		if(results.getString("WEEKWAGE") == null || results.getString("WORKWEEKS") == null){
	    			continue;
	    		}
	    		
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
		
		CalculateByRaceInterface calc = null;
		Assert.assertTrue(calc == null);
		
		calc = Factory.getCalculateByRace();
		Assert.assertTrue(calc != null);
		
		Assert.assertTrue(calc.getClass().equals(CalculateByRace.class));
		
		Assert.assertTrue(average == calc.calculateIncomeByRace(" White"));
		
		Assert.assertFalse(average == calc.calculateIncomeByRace(null));
		Assert.assertFalse(average == calc.calculateIncomeByRace("Non Valid Race"));
		Assert.assertFalse((average +.01) == calc.calculateIncomeByRace(" White"));
		
		Assert.assertTrue(average == calc.calculateIncomeByRace("White"));
		Assert.assertTrue(average == calc.calculateIncomeByRace("     White    "));

		// Only Works with original unaltered DB
		// Assert.assertTrue(47437.96 == calc.calculateIncomeByRace(" White"));
		
	}
	

	public void testCalculateIncomeByAllRaces(){
    	double sum = 0;
    	double count = 0;
    	double average;
		try{
			statement = Factory.getConnection().createStatement();
	    	results = statement.executeQuery("SELECT WEEKWAGE, WORKWEEKS from job");
	    	
	    	while(results.next()){
	    		if(results.getString("WEEKWAGE") == null || results.getString("WORKWEEKS") == null){
	    			continue;
	    		}
	    		
	    		if(results.getInt("WEEKWAGE") > 0 && results.getInt("WORKWEEKS") > 0){
		    		sum = sum + (results.getInt("WEEKWAGE") * results.getInt("WORKWEEKS") );
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
		
		CalculateByRaceInterface calc = null;
		Assert.assertTrue(calc == null);
		
		calc = Factory.getCalculateByRace();
		Assert.assertTrue(calc != null);
		
		Assert.assertTrue(calc.getClass().equals(CalculateByRace.class));
		
		Assert.assertTrue(average == calc.calculateIncomeByAllRaces());
		Assert.assertTrue(average == calc.calculateIncomeByRace("All"));
		
		Assert.assertFalse(average == calc.calculateIncomeByRace(null));
		Assert.assertFalse((average +.01) == calc.calculateIncomeByAllRaces());
		
		Assert.assertTrue(average == calc.calculateIncomeByRace("    all    "));
		
		// Only Works with original unaltered DB
		// Assert.assertTrue(46945.07 == calc.calculateIncomeByRace(" all"));
		// Assert.assertTrue(46945.07 == calc.calculateIncomeByAllRaces());
	}
	
	
	
	public void testCalculateWeeklyWageByRace2(){
		
		try{
			statement = Factory.getConnection().createStatement();
			
	    	statement.execute("INSERT INTO userRecord (NAME, SSN, RACE) VALUES (' Test Name 1', 999999980, ' Test Race 1')");
	    	statement.execute("INSERT INTO job (SSN, WEEKWAGE, WORKWEEKS) VALUES (999999980, 500, 40)");
	    	statement.execute("INSERT INTO education (SSN, EDUCATION) VALUES (999999980, ' Test Education 1')");
	    	statement.execute("INSERT INTO userRecord (NAME, SSN, RACE) VALUES (' Test Name 2', 999999981, ' Test Race 1')");
	    	statement.execute("INSERT INTO job (SSN, WEEKWAGE, WORKWEEKS) VALUES (999999981, 1500, 20)");
	    	statement.execute("INSERT INTO education (SSN, EDUCATION) VALUES (999999981, ' Test Education 1')");
	    	
			CalculateByRaceInterface calc = null;
			Assert.assertTrue(calc == null);
			
			calc = Factory.getCalculateByRace();
			Assert.assertTrue(calc != null);
			
			Assert.assertTrue(calc.getClass().equals(CalculateByRace.class));
		
			Assert.assertTrue(((500 + 1500) / 2) == calc.calculateWeeklyWageByRace(" Test Race 1"));

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
	
	public void testCalculateIncomeByRace2(){
		
		try{
			statement = Factory.getConnection().createStatement();
			
	    	statement.execute("INSERT INTO userRecord (NAME, SSN, RACE) VALUES (' Test Name 1', 999999980, ' Test Race 1')");
	    	statement.execute("INSERT INTO job (SSN, WEEKWAGE, WORKWEEKS) VALUES (999999980, 500, 40)");
	    	statement.execute("INSERT INTO education (SSN, EDUCATION) VALUES (999999980, ' Test Education 1')");
	    	statement.execute("INSERT INTO userRecord (NAME, SSN, RACE) VALUES (' Test Name 2', 999999981, ' Test Race 1')");
	    	statement.execute("INSERT INTO job (SSN, WEEKWAGE, WORKWEEKS) VALUES (999999981, 1500, 20)");
	    	statement.execute("INSERT INTO education (SSN, EDUCATION) VALUES (999999981, ' Test Education 1')");
	    	
			CalculateByRaceInterface calc = null;
			Assert.assertTrue(calc == null);
			
			calc = Factory.getCalculateByRace();
			Assert.assertTrue(calc != null);
			
			Assert.assertTrue(calc.getClass().equals(CalculateByRace.class));
		
			Assert.assertTrue((((500 * 40) + (1500 * 20)) / 2) == calc.calculateIncomeByRace(" Test Race 1"));

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
