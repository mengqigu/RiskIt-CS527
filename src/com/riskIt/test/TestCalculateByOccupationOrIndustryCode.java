package com.riskIt.test;

import java.math.BigDecimal;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.riskIt.controller.CalculateByOccupationOrIndustryCode;
import com.riskIt.interfaces.CalculateByOccupationOrIndustryCodeInterface;
import com.riskIt.util.Factory;



import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * TestCalculateByOccupationOrIndustryCode.java
 * Purpose: Tests the CalculateByOccupationOrIndustryCode class
 * @author Bryan Angone
 * @version 1.0 7-12-09
 */

public class TestCalculateByOccupationOrIndustryCode extends TestCase{
	
	private Statement statement;
	private ResultSet results;
	Random generator = new Random();
	
	public TestCalculateByOccupationOrIndustryCode(){
		super();
	}
	
	public void testGetOccupationCodeList(){
		int count = 1;
		int occupationCode = 9999;
		int occupationCode2 = 9999;
		
		try{
			statement = Factory.getConnection().createStatement();
	    	results = statement.executeQuery("SELECT DISTINCT OCCUPATIONCODE from wage");

	    	while(results.next()){
	    		count++;
	    		if(count <= 1){
	    			occupationCode = results.getInt("OCCUPATIONCODE");
	    			occupationCode2 = results.getInt("OCCUPATIONCODE");
	    		}
	    		int randomIndex = generator.nextInt(10);
	    		if(randomIndex == 4 || randomIndex == 6 || randomIndex == 8){
	    			occupationCode = results.getInt("OCCUPATIONCODE");
	    		}
	    		
	    		if(randomIndex == 3 || randomIndex == 5 || randomIndex == 7){
	    			occupationCode2 = results.getInt("OCCUPATIONCODE");
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
		
		CalculateByOccupationOrIndustryCodeInterface calc = null;
		
		Assert.assertTrue(calc == null);
		
		calc = Factory.getCalculateByOccupationOrIndustryCode();
		
		Assert.assertTrue(calc != null);
		Assert.assertTrue(calc.getClass().equals(CalculateByOccupationOrIndustryCode.class));
		
		ArrayList<Integer> occupationCodeList = calc.getOccupationCodeList();
		Assert.assertEquals(count, occupationCodeList.size());
		
		Assert.assertNotSame(count +1, occupationCodeList.size());
		
		Assert.assertTrue(occupationCodeList.contains(occupationCode));
		Assert.assertTrue(occupationCodeList.contains(occupationCode2));
		
		occupationCode = 9999999;
		Assert.assertFalse(occupationCodeList.contains(occupationCode));
		occupationCode = -1;
		
		Assert.assertFalse(occupationCodeList.contains(occupationCode));
		
		Assert.assertTrue(occupationCodeList.contains(occupationCode2));
		
		// Take out if DB changes
		occupationCode = 5;
		Assert.assertTrue(occupationCodeList.contains(occupationCode));
	}
	
	public void testIsValidOccupationCode(){
		int count = 1;
		int occupationCode = 9999;
		int occupationCode2 = 9999;
		try{
			statement = Factory.getConnection().createStatement();
	    	results = statement.executeQuery("SELECT DISTINCT OCCUPATIONCODE from wage");

	    	while(results.next()){
	    		count++;
	    		if(count <= 1){
	    			occupationCode = results.getInt("OCCUPATIONCODE");
	    			occupationCode2 = results.getInt("OCCUPATIONCODE");
	    		}
	    		int randomIndex = generator.nextInt(10);
	    		if(randomIndex == 4 || randomIndex == 6 || randomIndex == 8){
	    			occupationCode = results.getInt("OCCUPATIONCODE");
	    		}
	    		
	    		if(randomIndex == 3 || randomIndex == 5 || randomIndex == 7){
	    			occupationCode2 = results.getInt("OCCUPATIONCODE");
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
		
		CalculateByOccupationOrIndustryCodeInterface calc = null;
		
		Assert.assertTrue(calc == null);
		
		calc = Factory.getCalculateByOccupationOrIndustryCode();
		
		Assert.assertTrue(calc != null);
		Assert.assertTrue(calc.getClass().equals(CalculateByOccupationOrIndustryCode.class));
		
		Assert.assertTrue(calc.isValidOccupationCode(occupationCode));
		Assert.assertTrue(calc.isValidOccupationCode(occupationCode2));
		Assert.assertFalse(calc.isValidOccupationCode(-1));
		
		// Take out if DB changes
		occupationCode = 9999;
		Assert.assertFalse(calc.isValidOccupationCode(occupationCode));
	}
	
	public void testGetOccupationCodeAndNameMap(){
		
		
		int count = 1;
		int occupationCode = 9999;
		String occupationName = null;
		int occupationCode2 = 9999;
		String occupationName2 = null;
		try{
			statement = Factory.getConnection().createStatement();
	    	results = statement.executeQuery("SELECT OCCUPATIONCODE, OCCUPATION from occupation"); 

	    	while(results.next()){
	    		count++;
	    		if(count <= 1){
	    			occupationCode = results.getInt("OCCUPATIONCODE");
	    			occupationName  = results.getString("OCCUPATION");
	    			occupationCode2 = results.getInt("OCCUPATIONCODE");
	    			occupationName2 = results.getString("OCCUPATION");
	    		}
	    		int randomIndex = generator.nextInt(10);
	    		if(randomIndex == 4 || randomIndex == 6 || randomIndex == 8){
	    			occupationCode = results.getInt("OCCUPATIONCODE");
	    			occupationName  = results.getString("OCCUPATION");
	    		}
	    		
	    		if(randomIndex == 3 || randomIndex == 5 || randomIndex == 7){
	    			occupationCode2 = results.getInt("OCCUPATIONCODE");
	    			occupationName2 = results.getString("OCCUPATION");
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
		
		CalculateByOccupationOrIndustryCodeInterface calc = null;
		
		Assert.assertTrue(calc == null);
		
		calc = Factory.getCalculateByOccupationOrIndustryCode();
		
		Assert.assertTrue(calc != null);
		Assert.assertTrue(calc.getClass().equals(CalculateByOccupationOrIndustryCode.class));
		
		HashMap<Integer, String> occupationHM = calc.getOccupationCodeAndNameMap();
		
		Assert.assertTrue(occupationHM.containsKey(occupationCode));
		assertEquals(occupationName.trim(),(String)occupationHM.get(occupationCode));
		
		Assert.assertTrue(occupationHM.containsKey(occupationCode2));
		assertEquals(occupationName2.trim(),(String)occupationHM.get(occupationCode2));
		
		occupationCode = 9999;
		Assert.assertFalse(occupationHM.containsKey(occupationCode));
		
		Assert.assertFalse(occupationHM.containsValue(null));
	}
	
	public void testGetIndustryCodeList(){
		int count = 1;
		int industryCode = 9999;
		int industryCode2 = 9999;
		
		try{
			statement = Factory.getConnection().createStatement();
	    	results = statement.executeQuery("SELECT DISTINCT INDUSTRYCODE from wage");

	    	while(results.next()){
	    		count++;
	    		if(count <= 1){
	    			industryCode = results.getInt("INDUSTRYCODE");
	    			industryCode2 = results.getInt("INDUSTRYCODE");
	    		}
	    		int randomIndex = generator.nextInt(10);
	    		if(randomIndex == 4 || randomIndex == 6 || randomIndex == 8){
	    			industryCode = results.getInt("INDUSTRYCODE");
	    		}
	    		
	    		if(randomIndex == 3 || randomIndex == 5 || randomIndex == 7){
	    			industryCode2 = results.getInt("INDUSTRYCODE");
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
		
		CalculateByOccupationOrIndustryCodeInterface calc = null;
		
		Assert.assertTrue(calc == null);
		
		calc = Factory.getCalculateByOccupationOrIndustryCode();
		
		Assert.assertTrue(calc != null);
		Assert.assertTrue(calc.getClass().equals(CalculateByOccupationOrIndustryCode.class));
		
		ArrayList<Integer> industryCodeList = calc.getIndustryCodeList();
		Assert.assertEquals(count, industryCodeList.size());
		
		Assert.assertNotSame(count +1, industryCodeList.size());
		
		Assert.assertTrue(industryCodeList.contains(industryCode));
		Assert.assertTrue(industryCodeList.contains(industryCode2));
		
		industryCode = 9999999;
		Assert.assertFalse(industryCodeList.contains(industryCode));
		industryCode= -1;
		
		Assert.assertFalse(industryCodeList.contains(industryCode));
		
		Assert.assertTrue(industryCodeList.contains(industryCode2));
		
		// Take out if DB changes
		industryCode= 5;
		Assert.assertTrue(industryCodeList.contains(industryCode));
	}
	
	public void testIsValidIndustryCode(){
		int count = 1;
		int industryCode = 9999;
		int industryCode2 = 9999;
		try{
			statement = Factory.getConnection().createStatement();
	    	results = statement.executeQuery("SELECT DISTINCT INDUSTRYCODE from wage");

	    	while(results.next()){
	    		count++;
	    		if(count <= 1){
	    			industryCode = results.getInt("INDUSTRYCODE");
	    			industryCode2 = results.getInt("INDUSTRYCODE");
	    		}
	    		int randomIndex = generator.nextInt(10);
	    		if(randomIndex == 4 || randomIndex == 6 || randomIndex == 8){
	    			industryCode = results.getInt("INDUSTRYCODE");
	    		}
	    		
	    		if(randomIndex == 3 || randomIndex == 5 || randomIndex == 7){
	    			industryCode2 = results.getInt("INDUSTRYCODE");
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
		
		CalculateByOccupationOrIndustryCodeInterface calc = null;
		
		Assert.assertTrue(calc == null);
		
		calc = Factory.getCalculateByOccupationOrIndustryCode();
		
		Assert.assertTrue(calc != null);
		Assert.assertTrue(calc.getClass().equals(CalculateByOccupationOrIndustryCode.class));
		
		Assert.assertTrue(calc.isValidIndustryCode(industryCode));
		Assert.assertTrue(calc.isValidIndustryCode(industryCode2));
		Assert.assertFalse(calc.isValidIndustryCode(-1));
		
		// Take out if DB changes
		industryCode = 9999;
		Assert.assertFalse(calc.isValidIndustryCode(industryCode));
	}
	
	public void testGetIndustryCodeAndNameMap(){
		
		
		int count = 1;
		int industryCode = 9999;
		String industryName = null;
		int industryCode2 = 9999;
		String industryName2 = null;
		try{
			statement = Factory.getConnection().createStatement();
	    	results = statement.executeQuery("SELECT INDUSTRYCODE, INDUSTRY from industry"); 

	    	while(results.next()){
	    		count++;
	    		if(count <= 1){
	    			industryCode = results.getInt("INDUSTRYCODE");
	    			industryName  = results.getString("INDUSTRY");
	    			industryCode2 = results.getInt("INDUSTRYCODE");
	    			industryName2 = results.getString("INDUSTRY");
	    		}
	    		int randomIndex = generator.nextInt(10);
	    		if(randomIndex == 4 || randomIndex == 6 || randomIndex == 8){
	    			industryCode = results.getInt("INDUSTRYCODE");
	    			industryName  = results.getString("INDUSTRY");
	    		}
	    		
	    		if(randomIndex == 3 || randomIndex == 5 || randomIndex == 7){
	    			industryCode2 = results.getInt("INDUSTRYCODE");
	    			industryName2 = results.getString("INDUSTRY");
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
		
		CalculateByOccupationOrIndustryCodeInterface calc = null;
		
		Assert.assertTrue(calc == null);
		
		calc = Factory.getCalculateByOccupationOrIndustryCode();
		
		Assert.assertTrue(calc != null);
		Assert.assertTrue(calc.getClass().equals(CalculateByOccupationOrIndustryCode.class));
		
		HashMap<Integer, String> occupationHM = calc.getIndustryCodeAndNameMap();
		
		Assert.assertTrue(occupationHM.containsKey(industryCode));
		assertEquals(industryName.trim(),(String)occupationHM.get(industryCode));
		
		Assert.assertTrue(occupationHM.containsKey(industryCode2));
		assertEquals(industryName2.trim(),(String)occupationHM.get(industryCode2));
		
		industryCode = 9999;
		Assert.assertFalse(occupationHM.containsKey(industryCode));
		
		Assert.assertFalse(occupationHM.containsValue(null));
	}
	
	
	
	public void testCalculateIncomeByOccupationCode(){
    	double sum = 0;
    	double count = 0;
    	double average;
    	int randomOccupationCode = generator.nextInt(20);
		try{
			statement = Factory.getConnection().createStatement();
	    	results = statement.executeQuery("SELECT WEEKWAGE, WORKWEEKS, job.OCCUPATIONCODE FROM(job JOIN wage ON wage.OCCUPATIONCODE = job.OCCUPATIONCODE)");

	    	while(results.next()){
	    		if(results.getString("WEEKWAGE") == null || results.getString("WORKWEEKS") == null || results.getString("OCCUPATIONCODE") == null){
	    			continue;
	    		}
	    		
	    		if(results.getInt("WEEKWAGE") > 0 && results.getInt("WORKWEEKS") > 0 && results.getInt("OCCUPATIONCODE") == randomOccupationCode){
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
		
		CalculateByOccupationOrIndustryCodeInterface calc = null;
		Assert.assertTrue(calc == null);
		
		calc = Factory.getCalculateByOccupationOrIndustryCode();
		Assert.assertTrue(calc != null);
		
		Assert.assertTrue(calc.getClass().equals(CalculateByOccupationOrIndustryCode.class));
		
		Assert.assertTrue(average == calc.calculateIncomeByOccupationCode(randomOccupationCode));
		
		Assert.assertFalse((average + .01) == calc.calculateIncomeByOccupationCode(randomOccupationCode));
	}
	
	
	public void testCalculateIncomeByIndustryCode(){
    	double sum = 0;
    	double count = 0;
    	double average;
    	int randomIndustryCode = generator.nextInt(20);
		try{
			statement = Factory.getConnection().createStatement();
	    	results = statement.executeQuery("SELECT WEEKWAGE, WORKWEEKS, job.INDUSTRYCODE FROM(job JOIN wage ON wage.INDUSTRYCODE = job.INDUSTRYCODE)");

	    	while(results.next()){
	    		if(results.getString("WEEKWAGE") == null || results.getString("WORKWEEKS") == null || results.getString("INDUSTRYCODE") == null){
	    			continue;
	    		}
	    		
	    		if(results.getInt("WEEKWAGE") > 0 && results.getInt("WORKWEEKS") > 0 && results.getInt("INDUSTRYCODE") == randomIndustryCode){
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

		CalculateByOccupationOrIndustryCodeInterface calc = null;
		Assert.assertTrue(calc == null);
		
		calc = Factory.getCalculateByOccupationOrIndustryCode();
		Assert.assertTrue(calc != null);
		
		Assert.assertTrue(calc.getClass().equals(CalculateByOccupationOrIndustryCode.class));
		
		Assert.assertTrue(average == calc.calculateIncomeByIndustryCode(randomIndustryCode));
		
		Assert.assertFalse((average + .01) == calc.calculateIncomeByIndustryCode(randomIndustryCode));
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
	    		if(results.getString("WEEKWAGE") == null || results.getString("OCCUPATIONCODE") == null){
	    			continue;
	    		}
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
	    		if(results.getString("WEEKWAGE") == null || results.getString("INDUSTRYCODE") == null){
	    			continue;
	    		}
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

}
