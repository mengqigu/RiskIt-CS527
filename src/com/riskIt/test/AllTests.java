package com.riskIt.test;

import junit.framework.Test;
import junit.framework.TestSuite;


public class AllTests {

	public static Test suite(){
		TestSuite suite = new TestSuite();
		
		suite.addTestSuite(TestAgentAndUser.class);
		suite.addTestSuite(TestCalculateByEducation.class);
		suite.addTestSuite(TestCalculateByOccupationOrIndustryCode.class);
		suite.addTestSuite(TestCalculateByRace.class);
		suite.addTestSuite(TestCalculateByState.class);
		suite.addTestSuite(TestCalculateWeeklyWage.class);
		suite.addTestSuite(TestEstimateIncome.class);
		suite.addTestSuite(TestUnEmploymentAndFilter.class);
		
        suite.addTestSuite(TestFind5StatesWithMostWorkers.class);
        suite.addTestSuite(TestBestStateToWork.class);
        suite.addTestSuite(TestAverageHighLowEducationLevel.class);
        suite.addTestSuite(TestCalculateLikelinessToMoveFactor.class);
		
		return suite;
	}
	
	public static void main (String[] args){
		junit.textui.TestRunner.run(suite());
	}

}
