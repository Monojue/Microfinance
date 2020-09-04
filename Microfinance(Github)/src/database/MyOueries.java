package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

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
	
	//Duplicate Method
	public boolean IsDuplicate(String tbName, String[] data) {
		if(tbName.equals("client")) {
			query = "select * from client where NRC='"+data[0]+"'";
		}		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				return true;	
			} else {
				return false;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"SQL Exception", JOptionPane.ERROR_MESSAGE);
			return true;
		}
	}
	
	//Insert
	public static boolean InsertData(String tbName, String[] data) {
	if(tbName.equals("client")) {
		query = "insert into client(ClientID,Name,NRC,Address,Phone,DateofBirth,Home,Job,Salary) "
				+ "values('"+data[0]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"','"+Integer.parseInt(data[4])+"','"+data[5]+"','"+Integer.parseInt(data[6])+"','"+data[7]+"','"+Integer.parseInt(data[8])+"')";
	}
	try {
		stmt = con.createStatement();
		System.out.println(query);
		if(stmt.executeUpdate(query)==1) {
			return true;
		}else {
			return false;
		}
	}catch (SQLException e) {
		JOptionPane.showMessageDialog(null, e.getMessage());// TODO: handle exception
		e.printStackTrace();
		return false;
	}
	}
}



