package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

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
	public DefaultTableModel getAllClient() {
		DefaultTableModel dtm = new DefaultTableModel();
		String strdataitem[]= new String[5];
		try {
			con=DBConnection.GetMySQLConnection();
			stmt = con.createStatement();
			String str ="Select * from Client";
			ResultSet rs = stmt.executeQuery(str);
			while (rs.next()) {
				strdataitem[0] = rs.getString("ClientID");
				strdataitem[1] = rs.getString("Name");
				strdataitem[2] = rs.getString("NRC");
				strdataitem[3] = rs.getString("Address");
				strdataitem[4] = rs.getString(5);
				dtm.addRow(strdataitem);
			}
			return dtm;
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e);
		}
		
		return dtm;
		
	}
	
	
	
///////////////////// Client Query End /////////////////////////
	
///////////////////// Group Query Start ////////////////////////
	
	
	
	
	
///////////////////// Group Query End //////////////////////////
	
///////////////////// LoanRequest Query Start //////////////////
	
	
	
	
///////////////////// LoanRequest Query End ////////////////////
	
///////////////////// GroupLoan Query Start ////////////////////
	
	
	
	
///////////////////// GroupLoan Query End //////////////////////
	
	
	
}
