package com.riskIt.test;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Random;

import com.riskIt.controller.CalculateByEducation;
import com.riskIt.controller.CalculateByOccupationOrIndustryCode;
import com.riskIt.controller.CalculateByRace;
import com.riskIt.controller.CalculateByState;
import com.riskIt.interfaces.CalculateByEducationInterface;
import com.riskIt.interfaces.CalculateByOccupationOrIndustryCodeInterface;
import com.riskIt.interfaces.CalculateByRaceInterface;
import com.riskIt.interfaces.CalculateByStateInterface;
import com.riskIt.util.Factory;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * TestCalculateWeeklyWage.java
 * Purpose: Tests the CalculateWeeklyWage class
 * @author Bryan Angone
 * @version 1.0 7-12-09
 */

public class TestCalculateWeeklyWage extends TestCase{
	
	private Statement statement;
	private ResultSet results;
	Random generator = new Random();
	
	public TestCalculateWeeklyWage(){
		super();
	}

	public void testCalculateWeeklyWageByState(){
		{
			double sum = 0;
			double count = 0;
			double average = 0;
			String stateToTest = "IL";
			HashMap<Integer, Integer> ssnSum = new HashMap<Integer, Integer>();
			
			try{
				statement = Factory.getConnection().createStatement();
		    	results = statement.executeQuery("SELECT job.SSN, job.WEEKWAGE FROM (job JOIN (userrecord JOIN ziptable on userrecord.ZIP = ziptable.ZIP) on job.SSN = userrecord.SSN)  WHERE ziptable.STATENAME = '" + stateToTest + "'");
		    	
		    	while(results.next()){
		    		if(results.getInt("WEEKWAGE") > 0){
		    			ssnSum.put(results.getInt("SSN"), results.getInt("WEEKWAGE"));
		    		}
		    	}
		    	
		    	results = statement.executeQuery("SELECT SSN, ZIP from userRecord WHERE ZIP IS NOT NULL");
		    	while(results.next()){
		    		if(ssnSum.containsKey(results.getInt("SSN")) ){
		    			sum = sum + ssnSum.get(results.getInt("SSN"));
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
			
			CalculateByStateInterface calc = null;
			Assert.assertTrue(calc == null);
			
			calc = Factory.getCalculateByState();
			Assert.assertTrue(calc != null);
			
			Assert.assertTrue(calc.getClass().equals(CalculateByState.class));
			
			Assert.assertTrue(average == calc.calculateWeeklyWageByState(stateToTest));
			
			Assert.assertFalse(average == calc.calculateWeeklyWageByState(null));
			Assert.assertFalse(average == calc.calculateWeeklyWageByState("Non Valid State"));
			Assert.assertFalse((average +.01) == calc.calculateWeeklyWageByState(stateToTest));
			
			Assert.assertTrue(average == calc.calculateWeeklyWageByState(stateToTest));
			Assert.assertTrue(average == calc.calculateWeeklyWageByState("     " + stateToTest + "    "));
			
			// Only Works with original unaltered DB
			// Assert.assertTrue(779.25 == calc.calculateWeeklyWageByState(stateToTest));
		}
	}
	
	public void testCalculagteWeeklyWageByAllState(){
		double sum = 0;
		double count = 0;
		double average = 0;
		HashMap<Integer, Integer> ssnSum = new HashMap<Integer, Integer>();
		HashMap<String, String> zipStateHM = new HashMap<String, String>();
		
		try{
			statement = Factory.getConnection().createStatement();
			results = statement.executeQuery("SELECT SSN, WEEKWAGE FROM job");
			
	    	while(results.next()){
	    		if(results.getString("SSN") == null || results.getString("WEEKWAGE") == null){
	    			continue;
	    		}
	    		if(results.getInt("WEEKWAGE") > 0){
	    			ssnSum.put(results.getInt("SSN"), results.getInt("WEEKWAGE"));
	    		}
	    	}
	    	
	    	results = statement.executeQuery("SELECT ZIP, STATENAME FROM ziptable");
	    	while(results.next()){
	    		if(results.getString("ZIP") == null || results.getString("STATENAME") == null){
	    			continue;
	    		}
	    		zipStateHM.put(results.getString("ZIP"), results.getString("STATENAME"));
	    	}
	    	
	    	results = statement.executeQuery("SELECT SSN, ZIP FROM userRecord");
	    	while(results.next()){	    		
	    		if(results.getString("SSN") == null || results.getString("ZIP") == null){
	    			continue;
	    		}

	    		if(ssnSum.containsKey(results.getInt("SSN")) && zipStateHM.get(results.getString("ZIP")) != null  ){
	    			sum = sum + ssnSum.get(results.getInt("SSN"));
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
		
		CalculateByStateInterface calc = null;
		Assert.assertTrue(calc == null);
		
		calc = Factory.getCalculateByState();
		Assert.assertTrue(calc != null);
		
		Assert.assertTrue(calc.getClass().equals(CalculateByState.class));
		
		Assert.assertTrue(average == calc.calculagteWeeklyWageByAllState());
				
		Assert.assertFalse((average +.01) == calc.calculagteWeeklyWageByAllState());
		
		// Only Works with original unaltered DB
		// Assert.assertTrue(862.72 == calc.calculagteWeeklyWageByAllState());
	}
	
	public void testCalculateWeeklyWageByOccupationCode(){
    	double sum = 0;
    	double count = 0;
    	double average;
    	int randomOccupationCode = generator.nextInt(20);
		try{
			statement = Factory.getConnection().createStatement();
	    	results = statement.executeQuery("SELECT WEEKWAGE, job.OCCUPATIONCODE FROM(job JOIN wage ON wage.OCCUPATIONCODE = job.OCCUPATIONCODE)");

	    	while(results.next()){
	    		if(results.getInt("WEEKWAGE") > 0 && results.getInt("OCCUPATIONCODE") == randomOccupationCode){
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
		
		CalculateByOccupationOrIndustryCodeInterface calc = null;
		Assert.assertTrue(calc == null);
		
		calc = Factory.getCalculateByOccupationOrIndustryCode();
		Assert.assertTrue(calc != null);
		
		Assert.assertTrue(calc.getClass().equals(CalculateByOccupationOrIndustryCode.class));
		
		Assert.assertTrue(average == calc.calculateWeeklyWageByOccupationCode(randomOccupationCode));
		
		Assert.assertFalse((average + .01) == calc.calculateWeeklyWageByOccupationCode(randomOccupationCode));
	}
	
	public void testCalculateWeeklyWageByIndustryCode(){
    	double sum = 0;
    	double count = 0;
    	double average;
    	int randomIndustryCode = generator.nextInt(20);
		try{
			statement = Factory.getConnection().createStatement();
	    	results = statement.executeQuery("SELECT WEEKWAGE, job.INDUSTRYCODE FROM(job JOIN wage ON wage.INDUSTRYCODE = job.INDUSTRYCODE)");

	    	while(results.next()){
	    		if(results.getInt("WEEKWAGE") > 0 && results.getInt("INDUSTRYCODE") == randomIndustryCode){
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
		
		CalculateByOccupationOrIndustryCodeInterface calc = null;
		Assert.assertTrue(calc == null);
		
		calc = Factory.getCalculateByOccupationOrIndustryCode();
		Assert.assertTrue(calc != null);
		
		Assert.assertTrue(calc.getClass().equals(CalculateByOccupationOrIndustryCode.class));
		
		Assert.assertTrue(average == calc.calculateWeeklyWageByIndustryCode(randomIndustryCode));
		
		Assert.assertFalse((average + .01) == calc.calculateWeeklyWageByIndustryCode(randomIndustryCode));
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
		// Assert.assertTrue(2396.31 == calc.calculateWeeklyWageByEducationLevel(" Doctorate degree(PhD EdD)"));
		
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
		// Assert.assertTrue(976.56 == calc.calculateWeeklyWageByAllEducationLevels());
	}
	
	public void testCalculateWeeklyWageByRace(){
    	double sum = 0;
    	double count = 0;
    	double average;
		try{
			statement = Factory.getConnection().createStatement();
	    	results = statement.executeQuery("SELECT job.WEEKWAGE from job JOIN userrecord on job.SSN = userrecord.SSN WHERE userrecord.RACE = ' White'");
	    	
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
		// Assert.assertTrue(976.56 == calc.calculateWeeklyWageByRace(" all"));
		// Assert.assertTrue(976.56 == calc.calculateWeelkyWageByAllRaces());
	}
	
	public void testCalculateIncomeAndWeeklyWageByOccupationCodeAndIndustryCodeAddingToDB(){
		
		try{
			statement = Factory.getConnection().createStatement();
			
	    	statement.execute("INSERT INTO industry (INDUSTRYCODE) VALUES (95)");
	    	statement.execute("INSERT INTO occupation (OCCUPATIONCODE) VALUES (95)");
	    	statement.execute("INSERT INTO wage (INDUSTRYCODE, OCCUPATIONCODE) VALUES (95, 95)");
			
	    	statement.execute("INSERT INTO userRecord (NAME, SSN, RACE) VALUES (' Test Name 1', 999999980, 'Test Race 1')");
	    	statement.execute("INSERT INTO education (SSN, EDUCATION) VALUES (999999980, ' Test Education 1')");
	    	statement.execute("INSERT INTO job (SSN, INDUSTRYCODE, OCCUPATIONCODE, WEEKWAGE, WORKWEEKS) VALUES (999999980, 95, 95, 500, 40)");
	    	
	    	statement.execute("INSERT INTO userRecord (NAME, SSN, RACE) VALUES (' Test Name 2', 999999981, 'Test Race 1')");
	    	statement.execute("INSERT INTO job (SSN, INDUSTRYCODE, OCCUPATIONCODE, WEEKWAGE, WORKWEEKS) VALUES (999999981, 95, 95, 1500, 40)");
	    	statement.execute("INSERT INTO education (SSN, EDUCATION) VALUES (999999981, ' Test Education 1')");
	    	
			CalculateByOccupationOrIndustryCodeInterface calc = null;
			Assert.assertTrue(calc == null);
			
			calc = Factory.getCalculateByOccupationOrIndustryCode();
			Assert.assertTrue(calc != null);
			
			Assert.assertTrue(calc.getClass().equals(CalculateByOccupationOrIndustryCode.class));
		
			Assert.assertTrue(((500 + 1500) / 2) == calc.calculateWeeklyWageByIndustryCode(95));
			Assert.assertTrue(((500 + 1500) / 2) == calc.calculateWeeklyWageByOccupationCode(95));
			
			Assert.assertTrue((((500 * 40) + (1500 * 40)) / 2) == calc.calculateIncomeByIndustryCode(95));
			Assert.assertTrue((((500 * 40) + (1500 * 40)) / 2) == calc.calculateIncomeByOccupationCode(95));
			
			statement.execute("DELETE from job WHERE SSN = 999999980");
			statement.execute("DELETE from education WHERE SSN = 999999980");
			statement.execute("DELETE from userRecord WHERE SSN = 999999980");

			statement.execute("DELETE from job WHERE SSN = 999999981");
	    	statement.execute("DELETE from education WHERE SSN = 999999981");
	    	statement.execute("DELETE from userRecord WHERE SSN = 999999981");
	    	
	    	statement.execute("DELETE from wage WHERE INDUSTRYCODE = 95 AND OCCUPATIONCODE = 95");
	    	statement.execute("DELETE from occupation WHERE OCCUPATIONCODE = 95");
	    	statement.execute("DELETE from industry WHERE INDUSTRYCODE = 95");


			
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
	
	public void testCalculateWeeklyWageByState2(){
		
		try{
			statement = Factory.getConnection().createStatement();
			
	    	statement.execute("INSERT INTO userRecord (NAME, ZIP, SSN, RACE) VALUES (' Test Name 1','99994', 999999980, ' Test Race 1')");
	    	statement.execute("INSERT INTO job (SSN, WEEKWAGE, WORKWEEKS) VALUES (999999980, 500, 40)");
	    	
	    	statement.execute("INSERT INTO userRecord (NAME, ZIP, SSN, RACE) VALUES (' Test Name 2','99994', 999999981, ' Test Race 1')");
	    	statement.execute("INSERT INTO job (SSN, WEEKWAGE, WORKWEEKS) VALUES (999999981, 1500, 20)");
	    	
	    	statement.execute("INSERT INTO ziptable (ZIP, STATENAME) VALUES ('99994', 'XX')");

	    	
			CalculateByStateInterface calc = null;
			Assert.assertTrue(calc == null);
			
			calc = Factory.getCalculateByState();
			Assert.assertTrue(calc != null);
			
			Assert.assertTrue(calc.getClass().equals(CalculateByState.class));
		
			Assert.assertTrue(((500 + 1500 ) / 2) == calc.calculateWeeklyWageByState("XX"));

			statement.execute("DELETE from ziptable WHERE ZIP = '99994' AND STATENAME = 'XX'");
			
			statement.execute("DELETE from job WHERE SSN = 999999980");
			statement.execute("DELETE from userRecord WHERE SSN = 999999980");

			statement.execute("DELETE from job WHERE SSN = 999999981");
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
