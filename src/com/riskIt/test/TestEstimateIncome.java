package com.riskIt.test;

import java.util.ArrayList;

import com.riskIt.controller.CalculateRegression;
import com.riskIt.controller.EstimateIncome;
import com.riskIt.data.CalculateRegressionDataStructure;
import com.riskIt.interfaces.EstimateIncomeInterface;
import com.riskIt.util.Factory;


import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * TestEstimateIncome.java
 * Purpose: Tests the EstimateIncome and CalculateRegression classes
 * 	used for the estimate income feature.
 * @author Bryan Angone
 * @version 1.0 7-12-09
 */

public class TestEstimateIncome extends TestCase {

	public TestEstimateIncome(){
		super();
	}
	
	public void testCalculateRegressionNumbers(){
		
		ArrayList<Integer> firstDataSet = new ArrayList<Integer>();
		ArrayList<Integer> secondDataSet = new ArrayList<Integer>();
		
		firstDataSet.add(-2);
		firstDataSet.add(0);
		firstDataSet.add(2);
		firstDataSet.add(4);
		firstDataSet.add(6);
		firstDataSet.add(8);
		
		secondDataSet.add(0);
		secondDataSet.add(2);
		secondDataSet.add(4);
		secondDataSet.add(6);
		secondDataSet.add(8);
		secondDataSet.add(10);
		
		// The linear regression of (-2,0), (0,2), (2,4), (4,6), (6,8), (8,10), is y = 1x + 2.
		// The linear regression of the middle 2/3 is also y = 1x + 2
		
		CalculateRegression calc = new CalculateRegression();
		
		CalculateRegressionDataStructure info = calc.calculateRegressionNumbers(firstDataSet, secondDataSet);
		
		Assert.assertEquals(1.0, info.getSlopeAll());
		Assert.assertEquals(2.0, info.getInterceptAll());
		Assert.assertEquals(1.0, info.getSlopeMiddleTwoThirds());
		Assert.assertEquals(2.0, info.getInterceptMiddleTwoThirds());
		
		try {
			info = calc.calculateRegressionNumbers(null, secondDataSet);
		} catch (IllegalArgumentException e) {
			
		}
		
		try {
			info = calc.calculateRegressionNumbers(firstDataSet, null);
		} catch (IllegalArgumentException e) {
			
		}
	}
	
	public void testCalculateRegressionDataStructure(){
		CalculateRegressionDataStructure calc = new CalculateRegressionDataStructure(1, 2, 3, 4);
		
		Assert.assertEquals(1.0,calc.getSlopeAll());
		Assert.assertEquals(2.0,calc.getInterceptAll());
		Assert.assertEquals(3.0,calc.getSlopeMiddleTwoThirds());
		Assert.assertEquals(4.0,calc.getInterceptMiddleTwoThirds());
	}
	
	public void testGivenWorkWeeksEstimateIncomeAll(){
		EstimateIncomeInterface calc = Factory.getEstimateIncome();
		Assert.assertTrue(calc.getClass().equals(EstimateIncome.class));
		// Only Works with original unaltered DB
		// Assert.assertEquals(51480.59, calc.givenWorkWeeksEstimateIncomeAll(50));
	}
	
	public void testGivenWorkWeeksEstimateIncomeMiddleTwoThirds(){
		EstimateIncomeInterface calc = Factory.getEstimateIncome();
		Assert.assertTrue(calc.getClass().equals(EstimateIncome.class));
		// Only Works with original unaltered DB
		// Assert.assertEquals(50278.02, calc.givenWorkWeeksEstimateIncomeMiddleTwoThirds(49));
	}

	public void testGivenWorkWeeksEstimateIncomeGetSlopeAll(){
		EstimateIncomeInterface calc = Factory.getEstimateIncome();
		Assert.assertTrue(calc.getClass().equals(EstimateIncome.class));
		// Only Works with original unaltered DB
		// Assert.assertEquals(1223.21, calc.givenWorkWeeksEstimateIncomeGetSlopeAll());
	}
	
	public void testGivenWorkWeeksEstimateIncomeGetInterceptAll(){
		EstimateIncomeInterface calc = Factory.getEstimateIncome();
		Assert.assertTrue(calc.getClass().equals(EstimateIncome.class));
		// Only Works with original unaltered DB
		// Assert.assertEquals(-9679.91, calc.givenWorkWeeksEstimateIncomeGetInterceptAll());
	}
	
	public void testGivenWorkWeeksEstimateIncomeGetSlopeMiddleTwoThirds(){
		EstimateIncomeInterface calc = Factory.getEstimateIncome();
		Assert.assertTrue(calc.getClass().equals(EstimateIncome.class));
		// Only Works with original unaltered DB
		// Asert.assertEquals(1223.46, calc.givenWorkWeeksEstimateIncomeGetSlopeMiddleTwoThirds());
	}
	
	public void testGivenWorkWeeksEstimateIncomeGetInterceptMiddleTwoThirds(){
		EstimateIncomeInterface calc = Factory.getEstimateIncome();
		Assert.assertTrue(calc.getClass().equals(EstimateIncome.class));
		// Only Works with original unaltered DB
		// Assert.assertEquals(-9671.52, calc.givenWorkWeeksEstimateIncomeGetInterceptMiddleTwoThirds());
	}
	
	public void testGivenWeeklyWageEstimateIncomeAll(){
		EstimateIncomeInterface calc = Factory.getEstimateIncome();
		Assert.assertTrue(calc.getClass().equals(EstimateIncome.class));
		// Only Works with original unaltered DB
		// Assert.assertEquals(31767.51, calc.givenWeeklyWageEstimateIncomeAll(450));
	}
	
	public void testGivenWeeklyWageEstimateIncomeMiddleTwoThirds(){
		EstimateIncomeInterface calc = Factory.getEstimateIncome();
		Assert.assertTrue(calc.getClass().equals(EstimateIncome.class));
		// Only Works with original unaltered DB
		// Assert.assertEquals(31942.26, calc.givenWeeklyWageEstimateIncomeMiddleTwoThirds(450));
	}
	
	public void testGivenWeeklyWageEstimateIncomeGetSlopeAll(){
		EstimateIncomeInterface calc = Factory.getEstimateIncome();
		Assert.assertTrue(calc.getClass().equals(EstimateIncome.class));
		// Only Works with original unaltered DB
		// Assert.assertEquals(28.36, calc.givenWeeklyWageEstimateIncomeGetSlopeAll());
	}
	
	public void testGivenWeeklyWageEstimateIncomeGetInterceptAll(){
		EstimateIncomeInterface calc = Factory.getEstimateIncome();
		Assert.assertTrue(calc.getClass().equals(EstimateIncome.class));
		// Only Works with original unaltered DB
		// Assert.assertEquals(19005.51, calc.givenWeeklyWageEstimateIncomeGetInterceptAll());
	}
	
	public void testGivenWeeklyWageEstimateIncomeGetSlopeMiddleTwoThirds(){
		EstimateIncomeInterface calc = Factory.getEstimateIncome();
		Assert.assertTrue(calc.getClass().equals(EstimateIncome.class));
		// Only Works with original unaltered DB
		// Assert.assertEquals(28.85, calc.givenWeeklyWageEstimateIncomeGetSlopeMiddleTwoThirds());
	}
	
	public void testGivenWeeklyWageEstimateIncomeGetInterceptMiddleTwoThirds(){
		EstimateIncomeInterface calc = Factory.getEstimateIncome();
		Assert.assertTrue(calc.getClass().equals(EstimateIncome.class));
		// Only Works with original unaltered DB
		// Assert.assertEquals(18959.76, calc.givenWeeklyWageEstimateIncomeGetInterceptMiddleTwoThirds());
	}
	
	public void testGivenInvestmentIncomeEstimateIncomeAll(){
		EstimateIncomeInterface calc = Factory.getEstimateIncome();
		Assert.assertTrue(calc.getClass().equals(EstimateIncome.class));
		// Only Works with original unaltered DB
		// Assert.assertEquals(66540.83, calc.givenInvestmentIncomeEstimateIncomeAll(500));
	}
	
	public void testGivenInvestmentIncomeEstimateIncomeMiddleTwoThirds(){
		EstimateIncomeInterface calc = Factory.getEstimateIncome();
		Assert.assertTrue(calc.getClass().equals(EstimateIncome.class));
		// Only Works with original unaltered DB
		// Assert.assertEquals(66499.44, calc.givenInvestmentIncomeEstimateIncomeMiddleTwoThirds(500));
	}
	
	
	public void testGivenInvestmentIncomeEstimateIncomeGetSlopeAll(){
		EstimateIncomeInterface calc = Factory.getEstimateIncome();
		Assert.assertTrue(calc.getClass().equals(EstimateIncome.class));
		// Only Works with original unaltered DB
		// Assert.assertEquals(-2.26, calc.givenInvestmentIncomeEstimateIncomeGetSlopeAll());
	}
	
	public void testGivenInvestmentIncomeEstimateIncomeGetInterceptAll(){
		EstimateIncomeInterface calc = Factory.getEstimateIncome();
		Assert.assertTrue(calc.getClass().equals(EstimateIncome.class));
		// Only Works with original unaltered DB
		// Assert.assertEquals(67670.83, calc.givenInvestmentIncomeEstimateIncomeGetInterceptAll());
	}
	
	public void testGivenInvestmentIncomeEstimateIncomeGetSlopeMiddleTwoThirds(){
		EstimateIncomeInterface calc = Factory.getEstimateIncome();
		Assert.assertTrue(calc.getClass().equals(EstimateIncome.class));
		// Only Works with original unaltered DB
		// Assert.assertEquals(-2.63, calc.givenInvestmentIncomeEstimateIncomeGetSlopeMiddleTwoThirds());
	}
	
	public void testGivenInvestmentIncomeEstimateIncomeGetInterceptMiddleTwoThirds(){
		EstimateIncomeInterface calc = Factory.getEstimateIncome();
		Assert.assertTrue(calc.getClass().equals(EstimateIncome.class));
		// Only Works with original unaltered DB
		// Assert.assertEquals(67814.44, calc.givenInvestmentIncomeEstimateIncomeGetInterceptMiddleTwoThirds());
	}
}
