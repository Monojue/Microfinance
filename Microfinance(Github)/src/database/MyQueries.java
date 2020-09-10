package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import javax.swing.JOptionPane;


import database.DBConnection;
import tool.MyString;

public class MyQueries {
	static Connection con = null;
	static Statement stmt;
	static String query,query1;
	ResultSet rs;
	DBConnection connect = new DBConnection();
	
	public MyQueries() {
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
		//hh
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
	
	public Vector<String> getCodefromNo(String number) {
		Vector<String> code = new Vector<>();
		query = "Select *  from nrc where number = "+number+" order by Code";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				code.add(rs.getString(3));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return code;
	}
	
	//Insert
	public static boolean InsertData(String tbName, String[] data) {
	if(tbName.equals(MyString.ClientEntry)) {
		query = "insert into client(ClientID,Name,NRC,Address,Phone,DateofBirth,Home,Job,Salary) "
				+ "values('"+data[0]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"','"+Integer.parseInt(data[4])+"','"+data[5]+"','"+Integer.parseInt(data[6])+"','"+data[7]+"','"+Integer.parseInt(data[8])+"')";
	}else if (tbName.equals(MyString.GroupEntry)) {
		query = "Insert into clientGroup(groupID, leader, Member_1, Member_2, Member_3, Member_4) "
				+ "values('"+data[0]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"','"+data[4]+"','"+data[5]+"')";
	}
	else if(tbName.equals("loanrequest")) {
		query = "insert into loanrequest(LoanRequestID,LoanType,Amount,Duration,InterestRate) "
				+ "values('"+data[0]+"','"+data[1]+"','"+Integer.parseInt(data[2])+"','"+Integer.parseInt(data[3])+"','"+Float.parseFloat(data[4])+"')";
	}
	else if(tbName.equals("Iloansetting")) {
		query = "insert into loansetting(ID,MinAmount,MaxAmount,MinDuration,MaxDuration,AmountInterval,DurationInterval,InterestRate,ServiceRate,Date,Type,OfficerID) "
				+ "values('"+data[0]+"','"+Integer.parseInt(data[1])+"','"+Integer.parseInt(data[2])+"','"+Integer.parseInt(data[3])+"','"+Integer.parseInt(data[4])+"','"+Integer.parseInt(data[5])+"','"+Integer.parseInt(data[6])+"','"+Float.parseFloat(data[7])+"','"+Float.parseFloat(data[8])+"','"+data[9]+"','"+data[10]+"','"+data[11]+"')";
	}
	else if(tbName.equals("Gloansetting")) {
		query = "insert into loansetting(ID,MinAmount,MaxAmount,MinDuration,MaxDuration,AmountInterval,DurationInterval,InterestRate,ServiceRate,Date,Type,OfficerID) "
				+ "values('"+data[0]+"','"+Integer.parseInt(data[1])+"','"+Integer.parseInt(data[2])+"','"+Integer.parseInt(data[3])+"','"+Integer.parseInt(data[4])+"','"+Integer.parseInt(data[5])+"','"+Integer.parseInt(data[6])+"','"+Float.parseFloat(data[7])+"','"+Float.parseFloat(data[8])+"','"+data[9]+"','"+data[10]+"','"+data[11]+"')";
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
	
	//Guarantor Update!!!
	public static boolean UpdateData(String tbName, String[] data) {
	if(tbName.equals("guarantor")) {
		query = "update client set GName= '"+data[1]+"',GJob= '"+data[2]+"',GSalary= '"+Integer.parseInt(data[3])+"' ,Relationship= '"+data[4]+"' ,GAddress= '"+data[5]+"' ,GPhone= '"+Integer.parseInt(data[6])+"' ,GNRC= '"+data[7]+"' where ClientID= '"+data[0]+"'";
	}
	else if(tbName.equals("Iloansetting")) {
		query = "update loansetting set Duration= '"+Integer.parseInt(data[0])+"',Intervall= '"+Integer.parseInt(data[1])+"' ,InterestRate= '"+Float.parseFloat(data[2])+"' ,ServiceRate= '"+Float.parseFloat(data[3])+"' ,Date= '"+data[4]+"' where ID= '"+"Ls-1"+"'";
	}
	else if(tbName.equals("Gloansetting")) {
		query = "update loansetting set Duration= '"+Integer.parseInt(data[0])+"',Intervall= '"+Integer.parseInt(data[1])+"' ,InterestRate= '"+Float.parseFloat(data[2])+"' ,ServiceRate= '"+Float.parseFloat(data[3])+"' ,Date= '"+data[4]+"' where ID= '"+"Ls-2"+"'";
	}
	try {
		stmt = con.createStatement();
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
	
/////////////////Get Loan Setting////////////////////////////////////
	public String GetIndividualLoanSettingID() throws ClassNotFoundException {
		try {
			con=DBConnection.GetMySQLConnection();
			stmt = con.createStatement();
			String str ="select * from loansetting where Type='"+"Individual"+"' order by ID desc limit 1 ";
			ResultSet rs = stmt.executeQuery(str);
			rs.next();
			String ID = rs.getString(1);
			return ID;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public String GetGroupLoanSettingID() throws ClassNotFoundException {
		try {
			con=DBConnection.GetMySQLConnection();
			stmt = con.createStatement();
			String str ="select * from loansetting where Type='"+"Group"+"' order by ID desc limit 1 ";
			ResultSet rs = stmt.executeQuery(str);
			rs.next();
			String ID = rs.getString(1);
			return ID;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public String[] GetIndividualLoanSetting() {
		try {
			String ID = GetIndividualLoanSettingID();
			String data[] = new String[11];
			stmt = con.createStatement();
			query = "select * from loansetting where ID='"+ID+"'";
			rs = stmt.executeQuery(query);
			rs.next();
			data[0] = rs.getString(2);
			data[1] = rs.getString(3);
			data[2] = rs.getString(4);
			data[3] = rs.getString(5);
			data[4] = rs.getString(6);
			data[5] = rs.getString(7);
			data[6] = rs.getString(8);
			data[7] = rs.getString(9);
			data[8] = rs.getString(10);
			data[9] = rs.getString(11);
			data[10] = rs.getString(12);
			return data;
			}
		catch(SQLException e) {
			return null;
		}
		catch(ClassNotFoundException e) {
			return null;
		}
	}
	public String[] GetGroupLoanSetting() {
		try {
			String ID = GetGroupLoanSettingID();
			String data[] = new String[11];
			stmt = con.createStatement();
			query = "select * from loansetting where ID='"+ID+"'";
			rs = stmt.executeQuery(query);
			rs.next();
			data[0] = rs.getString(2);
			data[1] = rs.getString(3);
			data[2] = rs.getString(4);
			data[3] = rs.getString(5);
			data[4] = rs.getString(6);
			data[5] = rs.getString(7);
			data[6] = rs.getString(8);
			data[7] = rs.getString(9);
			data[8] = rs.getString(10);
			data[9] = rs.getString(11);
			data[10] = rs.getString(12);
			return data;
		}
		catch(SQLException e) {
			return null;
		}
		catch(ClassNotFoundException e) {
			return null;
		}
	}
///////////////////// Get Loan Setting End ////////////////////////
	
///////////////////// Group Query Start ////////////////////////
	
	
	
	
	
///////////////////// Group Query End //////////////////////////
	
///////////////////// LoanRequest Query Start //////////////////
	
	
	
	
///////////////////// LoanRequest Query End ////////////////////
	
///////////////////// GroupLoan Query Start ////////////////////
	
	
	
	
///////////////////// GroupLoan Query End //////////////////////
	

}




