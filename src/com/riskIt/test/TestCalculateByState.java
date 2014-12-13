package com.riskIt.test;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.riskIt.controller.CalculateByState;
import com.riskIt.interfaces.CalculateByStateInterface;
import com.riskIt.util.Factory;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * TestCalculateByState.java
 * Purpose: Tests the CalculateByState class
 * @author Bryan Angone
 * @version 1.0 7-12-09
 */

public class TestCalculateByState extends TestCase {
	
	private Statement statement;
	private ResultSet results;
	Random generator = new Random();
	
	public TestCalculateByState(){
		super();
	}
	
	public void testGetStateList(){
		int count = 0;
    	String stateLable = null;
    	String stateLable2 = null;
		try{
			statement = Factory.getConnection().createStatement();
			results = statement.executeQuery("SELECT DISTINCT STATENAME FROM ziptable");

	    	while(results.next()){
	    		if(results.getString("STATENAME") == null){
	    			continue;
	    		}
	    		count++;
	    		if(count <= 1){
	    			stateLable = results.getString("STATENAME");
	    			stateLable2 = results.getString("STATENAME");
	    		}
	    		int randomIndex = generator.nextInt(20);
	    		if(randomIndex == 2 || randomIndex == 6 || randomIndex == 10 || randomIndex == 14 || randomIndex == 18){
	    			stateLable = results.getString("STATENAME");
	    		}
	    		
	    		if(randomIndex == 3 || randomIndex == 7 || randomIndex == 11 || randomIndex == 15 || randomIndex == 19){
	    			stateLable2 = results.getString("STATENAME");
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
		
		CalculateByStateInterface calc = null;
		Assert.assertTrue(calc == null);
		
		calc = Factory.getCalculateByState();
		Assert.assertTrue(calc != null);
		Assert.assertTrue(calc.getClass().equals(CalculateByState.class));
		
		ArrayList<String> stateList = calc.getStateList();
		Assert.assertEquals(count, stateList.size());
		Assert.assertNotSame(count +1, stateList.size());
		
		Assert.assertTrue(stateList.contains(stateLable.trim()));
		Assert.assertTrue(stateList.contains(stateLable2.trim()));
		
		stateLable = "";
		Assert.assertFalse(stateList.contains(stateLable));
		stateLable = "Random Name";
		
		Assert.assertFalse(stateList.contains(stateLable));
		
		Assert.assertTrue(stateList.contains(stateLable2.trim()));
		
		// Take Out if DB Changes
		stateLable = "IL";
		Assert.assertTrue(stateList.contains(stateLable.trim()));
	}
	
	
	public void testIsInStateList(){
		
    	int count = 0;	
    	String stateLable = null;
    	String stateLable2 = null;
		try{
			statement = Factory.getConnection().createStatement();
	    	results = statement.executeQuery("SELECT DISTINCT STATENAME FROM ziptable");

	    	while(results.next()){
	    		if(results.getString("STATENAME") == null){
	    			continue;
	    		}
	    		count++;
	    		if(count <= 1){
	    			stateLable = results.getString("STATENAME");
	    			stateLable2 = results.getString("STATENAME");
	    		}
	    		int randomIndex = generator.nextInt(20);
	    		if(randomIndex == 2 || randomIndex == 6 || randomIndex == 10 || randomIndex == 14 || randomIndex == 18){
	    			stateLable = results.getString("STATENAME");
	    		}
	    		
	    		if(randomIndex == 3 || randomIndex == 7 || randomIndex == 11 || randomIndex == 15 || randomIndex == 19){
	    			stateLable2 = results.getString("STATENAME");
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
		
		CalculateByStateInterface calc = null;
		Assert.assertTrue(calc == null);
		
		calc = Factory.getCalculateByState();
		Assert.assertTrue(calc != null);
		
		Assert.assertTrue(calc.getClass().equals(CalculateByState.class));
		
		Assert.assertTrue(calc.isInStateList(stateLable));
		Assert.assertTrue(calc.isInStateList(stateLable2));
		Assert.assertFalse(calc.isInStateList(null));
		Assert.assertFalse(calc.isInStateList(""));
		Assert.assertFalse(calc.isInStateList("Fake Name"));
		Assert.assertTrue(calc.isInStateList("Il"));
		Assert.assertTrue(calc.isInStateList("   " + "iL" + "   "));
		Assert.assertFalse(calc.isInStateList(" XX  " + " IL" + " XX  "));
	}
	
	public void testCalculateIncomeByState(){
		double sum = 0;
		double count = 0;
		double average = 0;
		String stateToTest = "IL";
		HashMap<Integer, Integer> ssnSum = new HashMap<Integer, Integer>();
		
		try{
			statement = Factory.getConnection().createStatement();
	    	results = statement.executeQuery("SELECT job.SSN, job.WEEKWAGE, job.WORKWEEKS FROM (job JOIN (userrecord JOIN ziptable on userrecord.ZIP = ziptable.ZIP) on job.SSN = userrecord.SSN)  WHERE ziptable.STATENAME = '" + stateToTest + "'");

	    	while(results.next()){
		    	if(results.getString("SSN") == null || results.getString("WEEKWAGE") == null || results.getString("WORKWEEKS") == null ){
		    		continue;
		    	}
	    		if(results.getInt("WEEKWAGE") > 0 && results.getInt("WORKWEEKS") > 0){
	    			ssnSum.put(results.getInt("SSN"), (results.getInt("WEEKWAGE") * results.getInt("WORKWEEKS")));
	    		}
	    	}
	    	
	    	results = statement.executeQuery("SELECT SSN, ZIP FROM userRecord WHERE ZIP IS NOT NULL");
	    	while(results.next()){
	    		if(results.getString("SSN") == null || results.getString("ZIP") == null){
	    			continue;
	    		}
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
		
		Assert.assertTrue(average == calc.calculateIncomeByState(stateToTest));
		
		Assert.assertFalse(average == calc.calculateIncomeByState(null));
		Assert.assertFalse(average == calc.calculateIncomeByState("Non Valid State"));
		Assert.assertFalse((average +.01) == calc.calculateIncomeByState(stateToTest));
		
		Assert.assertTrue(average == calc.calculateIncomeByState(stateToTest));
		Assert.assertTrue(average == calc.calculateIncomeByState("     " + stateToTest + "    "));
		
		// Only Works with original unaltered DB
		// Assert.assertTrue(36150.74 == calc.calculateIncomeByState(stateToTest));
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
		    		if(results.getString("SSN") == null || results.getString("WEEKWAGE") == null){
		    			continue;
		    		}
		    		if(results.getInt("WEEKWAGE") > 0){
		    			ssnSum.put(results.getInt("SSN"), results.getInt("WEEKWAGE"));
		    		}
		    	}
		    	
		    	results = statement.executeQuery("SELECT SSN, ZIP from userRecord WHERE ZIP IS NOT NULL");
		    	while(results.next()){
		    		if(results.getString("SSN") == null || results.getString("ZIP") == null){
		    			continue;
		    		}
		    		
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
	
	public void testCalculateIncomeByAllStates(){
		double sum = 0;
		double count = 0;
		double average = 0;
		HashMap<Integer, Integer> ssnSum = new HashMap<Integer, Integer>();
		HashMap<String, String> zipStateHM = new HashMap<String, String>();
		
		try{
			statement = Factory.getConnection().createStatement();
			results = statement.executeQuery("SELECT SSN, WEEKWAGE, WORKWEEKS FROM job");
			
	    	while(results.next()){
		    	if(results.getString("SSN") == null || results.getString("WEEKWAGE") == null || results.getString("WORKWEEKS") == null ){
		    		continue;
		    	}
	    		if(results.getInt("WEEKWAGE") > 0 && results.getInt("WORKWEEKS") > 0){
	    			ssnSum.put(results.getInt("SSN"), (results.getInt("WEEKWAGE") * results.getInt("WORKWEEKS")));
	    		}
	    	}
	    	
	    	results = statement.executeQuery("SELECT ZIP, STATENAME FROM ziptable");
	    	while(results.next()){
	    		if(results.getString("ZIP") == null || results.getString("STATENAME") == null){
	    			continue;
	    		}
	    		zipStateHM.put(results.getString("ZIP"), results.getString("STATENAME"));
	    	}
	    	
	    	results = statement.executeQuery("SELECT SSN, ZIP FROM userRecord WHERE ZIP IS NOT NULL");
	    	while(results.next()){
	    		if(results.getString("SSN") == null || results.getString("ZIP") == null){
	    			continue;
	    		}
	    		if(ssnSum.containsKey(results.getInt("SSN")) && zipStateHM.get(results.getString("ZIP")) != null ){
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
		
		Assert.assertTrue(average == calc.calculateIncomeByAllStates());
		
		Assert.assertFalse((average +.01) == calc.calculateIncomeByAllStates());
		
		// Only Works with original unaltered DB
		// Assert.assertTrue(40269.43 == calc.calculateIncomeByAllStates());
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
	
	
	public void testCalculateIncomeByState2(){
		
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
		
			Assert.assertTrue((((500 * 40) + (1500 * 20)) / 2) == calc.calculateIncomeByState("XX"));

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
