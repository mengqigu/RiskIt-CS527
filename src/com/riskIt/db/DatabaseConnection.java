package com.riskIt.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * This class is used to establish a database connection as well as 
 * close the connection
 * @author Poornima Tantry
 *
 */
public class DatabaseConnection 
{
	//private static final String driver = "org.apache.derby.jdbc.ClientDriver";
	 
	//private static final String URL = "jdbc:derby://localhost:1527/riskit;create=true;user=se549;password=se549";	
	private static Connection conn;

    private static final   String url = "jdbc:mysql://localhost:3306/riskit";
    private static final   String username = "root";
    private static final   String password = "gmq920407";

	private static boolean created = false;
	
	private DatabaseConnection()
	{	
		
	}
	
	public static Connection  getConnection() throws SQLException
	 {	    
		if(created)
		{
			return conn;
		}
		else
		{
			try 
				{
				    //Class.forName(driver).newInstance();
				    conn = DriverManager.getConnection(url, username, password);	
				    created = true;
				}
				catch(Exception e)
				{
					System.out.println("Exception in CreateConnection");
					e.printStackTrace();
				}			
		        return conn;
		}
	 }	
	
	public static void closeConnection()
	{
		try
		{
			if(created)
			{
				created = false;
				conn.close();
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in closeCOnnection");
			e.printStackTrace();
		}
		
	}
	

}
