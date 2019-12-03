package com.cs3733kakistocrat.group.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {

	// These should never be stored directly in code.  I am doing this quickly complete the 
	// demonstration code. The appropriate solution is to store these values in environment
	// variables that are accessed by the Lambda function at run time like this
	//
	//  dbUsername = System.getenv("dbUsername");
	//  dbPassword = System.getenv("dbPassword");
	//  rdsMySqlDatabaseUrl = System.getenv("rdsMySqlDatabaseUrl");
	//
	// https://docs.aws.amazon.com/lambda/latest/dg/env_variables.html
	//
	// The above link shows how to do that.
	public final static String rdsMySqlDatabaseUrl = "kakistocratdb.civjozv7atpk.us-east-2.rds.amazonaws.com";
	public final static String dbUsername = "admin";
	public final static String dbPassword = "fjmxmUc8p2mCfFIugnBi";
		
	public final static String jdbcTag = "jdbc:mysql://";
	public final static String rdsMySqlDatabasePort = "3306";
	public final static String multiQueries = "?allowMultiQueries=true";
	   
	public final static String dbName = "innodb";    // default created from MySQL WorkBench
	public final static String testName = "test"; //testing tables

	static Connection conn;
 
	protected static Connection connect() throws Exception {
		if (conn != null) { return conn; }
		
		boolean useTestDB = System.getenv("TESTING") != null;
		
		String schemaName = dbName;
		if (useTestDB) { 
			schemaName = testName;
			System.out.println("USE TEST DB:" + useTestDB);
		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					jdbcTag + rdsMySqlDatabaseUrl + ":" + rdsMySqlDatabasePort + "/" + schemaName + multiQueries,
					dbUsername,
					dbPassword);
			return conn;
		} catch (Exception ex) {
			throw new Exception("Failed in database connection");
		}
	}
}
