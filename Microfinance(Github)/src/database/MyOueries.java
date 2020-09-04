package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DBConnection;

public class MyOueries {
	static Connection con = null;
	static Statement stmt;
	static String query,query1;
	ResultSet rs;
	DBConnection connect = new DBConnection();
	
	public MyOueries() {
		try {
			con = connect.GetMySQLConnection();
		}catch (ClassNotFoundException e) {
			System.out.println(e);
		}catch (SQLException e) {
			System.out.println(e);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
///////////////////// Client Query Start ///////////////////////
	
	
	
	
///////////////////// Client Query End /////////////////////////
	
///////////////////// Group Query Start ////////////////////////
	
	
	
	
	
///////////////////// Group Query End //////////////////////////
	
///////////////////// LoanRequest Query Start //////////////////
	
	
	
	
///////////////////// LoanRequest Query End ////////////////////
	
///////////////////// GroupLoan Query Start ////////////////////
	
	
	
	
///////////////////// GroupLoan Query End //////////////////////
	
	
	
}
