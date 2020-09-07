package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.swing.table.DefaultTableModel;

import javax.swing.JOptionPane;


import database.DBConnection;

public class UQueries {
	static Connection con = null;
	static Statement stmt;
	static String query,query1;
	ResultSet rs;
	DBConnection connect = new DBConnection();
	
	public UQueries() {
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
		String strdataitem[]= new String[9];
		try {
			stmt = con.createStatement();
			String str ="Select * from Client";
			ResultSet rs = stmt.executeQuery(str);
			dtm.addColumn("ClientID");
			dtm.addColumn("Name");
			dtm.addColumn("NRC");
			dtm.addColumn("Address");
			dtm.addColumn("Phone");
			dtm.addColumn("DateOfBirth");
			dtm.addColumn("Home");
			dtm.addColumn("Job");
			dtm.addColumn("Salary");
			while (rs.next()) {
				strdataitem[0] = rs.getString("ClientID");
				strdataitem[1] = rs.getString("Name");
				strdataitem[2] = rs.getString("NRC");
				strdataitem[3] = rs.getString("Address");
				strdataitem[4] = rs.getString("Phone");
				strdataitem[5] = rs.getString("DateOfBirth");
				strdataitem[6] = rs.getString("Home");
				strdataitem[7] = rs.getString("Job");
				strdataitem[8] = rs.getString("Salary");
				dtm.addRow(strdataitem);
			}
			return dtm;
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return dtm;
	}
	
	public String getClientNameFormID(String id) {
		query = "Select Name where clientID = '"+id+"';";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			return rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
///////////////////// Client Query End /////////////////////////
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
	
///////////////////// Group Query Start ////////////////////////
	
	public DefaultTableModel getAllGroup() {
		DefaultTableModel dtm = new DefaultTableModel();
		String strdataitem[]= new String[6];
		try {
			con=DBConnection.GetMySQLConnection();
			stmt = con.createStatement();
			String str ="Select * from ClientGroup";
			ResultSet rs = stmt.executeQuery(str);
			dtm.addColumn("GroupId");
			dtm.addColumn("Leader");
			dtm.addColumn("Member_1");
			dtm.addColumn("Member_2");
			dtm.addColumn("Member_3");
			dtm.addColumn("Member_4");

			while (rs.next()) {
				strdataitem[0] = rs.getString("GroupId");
				strdataitem[1] = rs.getString("Leader");
				strdataitem[2] = rs.getString("Member_1");
				strdataitem[3] = rs.getString("Member_2");
				strdataitem[4] = rs.getString("Member_3");
				strdataitem[5] = rs.getString("Member_4");
				dtm.addRow(strdataitem);
			}
			return dtm;
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e);
		}
		
		return dtm;
	}
	
	
	
///////////////////// Group Query End //////////////////////////
	
///////////////////// LoanRequest Query Start //////////////////
	
	
	
	
///////////////////// LoanRequest Query End ////////////////////
	
///////////////////// GroupLoan Query Start ////////////////////
	
	
	
	
///////////////////// GroupLoan Query End //////////////////////
	

}




