package com.riskIt.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.riskIt.db.DatabaseConnection;
import com.riskIt.util.*;
import com.riskIt.data.*;

/**
 * This class mainly works on methods that deals with zipcode properties
 * @author Poornima Tantry
 *
 */
public class ZipcodeManager 
{
	private AccessorMethods acc;
	
	public ZipcodeManager()
	{
		acc = new AccessorMethods();
	}
	/**
	 * This method gets all persons living in same zipcode and then calculated range 
	 * @param zipcode - zipcode to be compared with
	 * @param c - connection from the calling method
	 * @author Poornima Tantry
	 */
	public ArrayList<ScoreData> getAllZipcodes(String zipcode)
	{
		String cmds_getAllZipcode = "";
		ResultSet result = null;
		ArrayList<ScoreData> sData = new ArrayList<ScoreData>();
		
		try
		{
			Connection conn = DatabaseConnection.getConnection();
			Statement s = conn.createStatement();
			
			//get all zipcode similar to the given person
			if(zipcode != null)
			{
				cmds_getAllZipcode = "SELECT userrecord.zip, userrecord.ssn, investment.capitalgains, " 
									+ "investment.capitallosses, investment.stockdividends, " 
									+ "job.workweeks, job.weekwage " 
									+ "FROM  investment, userrecord, job WHERE "
									+ " investment.ssn = userrecord.ssn and "
									+ " job.ssn = userrecord.ssn and "
									+ "userrecord.zip = '" + zipcode + "'" ;
				
				// find all the persons with same zipcode		
				result = s.executeQuery(cmds_getAllZipcode);
		
				while(result.next())
				{		
					//calculate the range for each person in resultSet
					sData = acc.calculateRange(	result.getInt("workweeks"),
												result.getInt("weekwage"),
												result.getInt("capitalGains"),
												result.getInt("capitallosses"),
												result.getInt("stockdividends"),
												(long)result.getInt("ssn"), sData);				
				}		
				result.close();
			}
			s.close();
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in getAllZipcode");
			e.printStackTrace();
		}	
		return sData;
	}
	
	/**
	 * This method gets the name of state by first searching the zipcode
	 * from the userrecord
	 * @param inv - customer's information unit
	 * @return - customer object with updated values
	 * @author Poornima Tantry
	 */
	public Invitation getOneZipcode(Invitation inv)
	{
		String stateName = "";
		int age = 0;
		String cmd_zipcode = "select zip, age from userrecord where ssn = " + inv.getUserSSN();
		String zipcode = "";
		
		try
		{
			Connection conn = DatabaseConnection.getConnection();
			Statement s = conn.createStatement();
			
			ResultSet result = s.executeQuery(cmd_zipcode);
			if(result.next())
			{
				zipcode = result.getString("zip");
				age = result.getInt("age");
			}
			//set data in the object
			inv.setZipcode(zipcode);
			inv.setAge(age);
			
			result.close();
			
			//get the state name from zipcode
			String cmd_state = "select * from ziptable where zip = '" + zipcode + "'";
			
			result = s.executeQuery(cmd_state);
			if(result.next())
				stateName = result.getString("statename");
			inv.setStateName(stateName);
			result.close();
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in getOneZipcode");
			e.printStackTrace();
		}
		return inv;
	}
	
}
