package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import javax.swing.JOptionPane;


import database.DBConnection;
import tool.Calculation;
import tool.MyString;

public class MyQueries {
	static Connection con = null;
	static Statement stmt;
	static String query,query1;
	ResultSet rs;
	DBConnection connect = new DBConnection();
	UQueries msql = new UQueries();
	
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
	//Get Client Details from ClientID
	public String[] getClientDetailsFormID(String id) {
		String[] ClientDetails = new String[16];
		query = "Select * from client where clientID = '"+id+"';";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			ClientDetails[0] = rs.getString("ClientID");
			ClientDetails[1] = rs.getString("Name");
			ClientDetails[2] = rs.getString("NRC");
			ClientDetails[3] = rs.getString("Address");
			ClientDetails[4] = rs.getString("Phone");
			ClientDetails[5] = rs.getString("DateOfBirth");
			ClientDetails[6] = rs.getString("Home");
			ClientDetails[7] = rs.getString("Job");
			ClientDetails[8] = rs.getString("Salary");
			ClientDetails[9] = rs.getString("GName");
			ClientDetails[10] = rs.getString("GJob");
			ClientDetails[11] = rs.getString("GSalary");
			ClientDetails[12] = rs.getString("Relationship");
			ClientDetails[13] = rs.getString("GAddress");
			ClientDetails[14] = rs.getString("GPhone");
			ClientDetails[15] = rs.getString("GNRC");
			return ClientDetails;
		} catch (SQLException e) {
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
				return false;	
			} else {
				return true;
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
		query = "Insert into clientGroup(groupID, leader, Member_1, Member_2, Member_3, Member_4, leaderName, M1Name, M2Name, M3Name, M4Name) "
				+ "values('"+data[0]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"','"+data[4]+"','"+data[5]+"','"+data[6]+"','"+data[7]+"','"+data[8]+"','"+data[9]+"','"+data[10]+"')";
	}
	else if(tbName.equals("loanrequest")) {
		query = "insert into loanrequest(LoanRequestID,LoanType,Amount,Duration,InterestRate) "
				+ "values('"+data[0]+"','"+data[1]+"','"+Integer.parseInt(data[2])+"','"+Integer.parseInt(data[3])+"','"+Float.parseFloat(data[4])+"')";
	}
	else if(tbName.equals("clientdetails")) {
		query = "insert into clientdetails(ClientID,LoanRequestID,RequestDate) "
				+ "values('"+data[0]+"','"+data[1]+"','"+data[2]+"')";
	}
	else if(tbName.equals("groupdetails")) {
		query = "insert into groupdetails(GroupID,LoanRequestID,RequestDate) "
				+ "values('"+data[0]+"','"+data[1]+"','"+data[2]+"')";
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
	
	//Client And Guarantor And Group Update!!!
	public static boolean UpdateData(String tbName, String[] data) {
	if(tbName.equals("guarantor")) {
		query = "update client set GName= '"+data[1]+"',GJob= '"+data[2]+"',GSalary= '"+Integer.parseInt(data[3])+"' ,Relationship= '"+data[4]+"' ,GAddress= '"+data[5]+"' ,GPhone= '"+Integer.parseInt(data[6])+"' ,GNRC= '"+data[7]+"' where ClientID= '"+data[0]+"'";
	}
	if(tbName.equals("client")) {
		query = "update client set Name= '"+data[1]+"',NRC= '"+data[2]+"',Address= '"+data[3]+"' ,Phone= '"+Integer.parseInt(data[4])+"' ,DateOfBirth= '"+data[5]+"' ,Home= '"+Integer.parseInt(data[6])+"' ,Job= '"+data[7]+"',Salary= '"+Integer.parseInt(data[8])+"' where ClientID= '"+data[0]+"'";
	}
	if(tbName.equals("clientgroup")) {
		query = "update clientgroup set leader= '"+data[1]+"',Member_1= '"+data[2]+"',Member_2= '"+data[3]+"' ,Member_3= '"+data[4]+"' ,Member_4= '"+data[5]+"' ,leaderName= '"+data[6]+"' ,M1Name= '"+data[7]+"',M2Name= '"+data[8]+"',M3Name= '"+data[8]+"',M4Name= '"+data[8]+"' where GroupID= '"+data[0]+"'";
	}
	if(tbName.equals("loanrequest")) {
		query = "update loanrequest set Approved= '"+data[1]+"' where LoanrequestID= '"+data[0]+"'";
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
	//Get Client Details from ClientID
	public String[] getGroupDetailsFormID(String id) {
		String[] GroupDetails = new String[11];
		query = "Select * from clientgroup where GroupID = '"+id+"';";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			GroupDetails[0] = rs.getString(1); //ID
			GroupDetails[1] = rs.getString(2); //l1 id
			GroupDetails[2] = rs.getString(3); //M1 ID
			GroupDetails[3] = rs.getString(4); //M2 ID
			GroupDetails[4] = rs.getString(5); //M3 ID
			GroupDetails[5] = rs.getString(6); //M4 ID
			GroupDetails[6] = rs.getString(7); //L Name
			GroupDetails[7] = rs.getString(8); //M1 Name
			GroupDetails[8] = rs.getString(9); //M2 Name
			GroupDetails[9] = rs.getString(10); //M3 Name
			GroupDetails[10] = rs.getString(11); //M4 Name
			return GroupDetails;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}	
	
	
	
	
///////////////////// Group Query End //////////////////////////
	
///////////////////// LoanRequest Query Start //////////////////
	public DefaultTableModel getIndividualLoanRequest() {
		DefaultTableModel dtm = new DefaultTableModel();
		String dataitem[]= new String[6];
		try {
			stmt = con.createStatement();
			query ="Select * from clientdetails";
			ResultSet rs = stmt.executeQuery(query);
			int count = dtm.getRowCount();
			if (count==0) {
				dtm.addColumn("Loan Request ID");
				dtm.addColumn("Client ID");
				dtm.addColumn("Client Name");
				dtm.addColumn("Amount");
				dtm.addColumn("Duration");
				dtm.addColumn("Date");
			}
			while (rs.next()) {
				String[] LoanRequestDetails = GetLoanRequestData(rs.getString("LoanRequestID"));
				dataitem[0] = rs.getString("LoanRequestID");
				dataitem[1] = rs.getString("ClientID");
				dataitem[2] = msql.getClientNameFormID(rs.getString("ClientID"));
				dataitem[3] = Calculation.addcomma(LoanRequestDetails[1]);
				dataitem[4] = LoanRequestDetails[2];
				dataitem[5] = rs.getString("RequestDate");
				
				String approave = LoanRequestDetails[5];
				if(approave ==null) {
					dtm.addRow(dataitem);
				}
			}
			return dtm;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}
	
	public DefaultTableModel getGroupLoanRequest() {
		DefaultTableModel dtm = new DefaultTableModel();
		String dataitem[]= new String[10];
		try {
			stmt = con.createStatement();
			query ="Select * from groupdetails";
			ResultSet rs = stmt.executeQuery(query);
			int count = dtm.getRowCount();
			if (count==0) {
				dtm.addColumn("Loan Request ID");
				dtm.addColumn("Group ID");
				dtm.addColumn("Leader Name");
				dtm.addColumn("Member1 Name");
				dtm.addColumn("Member2 Name");
				dtm.addColumn("Member3 Name");
				dtm.addColumn("Member4 Name");
				dtm.addColumn("Amount");
				dtm.addColumn("Duration");
				dtm.addColumn("Date");
			}
			while (rs.next()) {
				String[] LoanRequestDetails = GetLoanRequestData(rs.getString("LoanRequestID"));
				String data = rs.getString("GroupID");
				String query2 = "Select * from clientgroup where groupID= '"+data+"'";
				ResultSet rs2 = stmt.executeQuery(query2);
				rs2.next();
				dataitem[0] = rs.getString("LoanRequestID");
				dataitem[1] = rs.getString("GroupID");
				dataitem[2] = rs2.getString("leadername");
				dataitem[3] = rs2.getString("M1Name");
				dataitem[4] = rs2.getString("M2Name");
				dataitem[5] = rs2.getString("M3Name");
				dataitem[6] = rs2.getString("M4Name");
				dataitem[7] = Calculation.addcomma(LoanRequestDetails[1]);
				dataitem[8] = LoanRequestDetails[2];
				dataitem[9] = rs.getString("RequestDate");
				
				String approave = LoanRequestDetails[5];
				if(approave ==null) {
					dtm.addRow(dataitem);
				}
			}
			return dtm;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}
	
	public DefaultTableModel getIndividualApprovedLoanRequest() {
		DefaultTableModel dtm = new DefaultTableModel();
		String dataitem[]= new String[7];
		try {
			stmt = con.createStatement();
			query ="Select * from clientdetails";
			ResultSet rs = stmt.executeQuery(query);
			int count = dtm.getRowCount();
			if (count==0) {
				dtm.addColumn("Loan Request ID");
				dtm.addColumn("Client ID");
				dtm.addColumn("Client Name");
				dtm.addColumn("Amount");
				dtm.addColumn("Duration");
				dtm.addColumn("Date");
			}
			while (rs.next()) {
				String[] LoanRequestDetails = GetLoanRequestData(rs.getString("LoanRequestID"));
				dataitem[0] = rs.getString("LoanRequestID");
				dataitem[1] = rs.getString("ClientID");
				dataitem[2] = msql.getClientNameFormID(rs.getString("ClientID"));
				dataitem[3] = Calculation.addcomma(LoanRequestDetails[1]);
				dataitem[4] = LoanRequestDetails[2];
				dataitem[5] = rs.getString("RequestDate");
				
				String approave = LoanRequestDetails[5];
				if(approave !=null) {
					dtm.addRow(dataitem);
				}
			}
			return dtm;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}
	
	public DefaultTableModel getGroupApprovedLoanRequest() {
		DefaultTableModel dtm = new DefaultTableModel();
		String dataitem[]= new String[10];
		try {
			stmt = con.createStatement();
			query ="Select * from groupdetails";
			ResultSet rs = stmt.executeQuery(query);
			int count = dtm.getRowCount();
			if (count==0) {
				dtm.addColumn("Loan Request ID");
				dtm.addColumn("Group ID");
				dtm.addColumn("Leader Name");
				dtm.addColumn("Member1 Name");
				dtm.addColumn("Member2 Name");
				dtm.addColumn("Member3 Name");
				dtm.addColumn("Member4 Name");
				dtm.addColumn("Amount");
				dtm.addColumn("Duration");
				dtm.addColumn("Date");
			}
			while (rs.next()) {
				String[] LoanRequestDetails = GetLoanRequestData(rs.getString("LoanRequestID"));
				String data = rs.getString("GroupID");
				String query2 = "Select * from clientgroup where groupID= '"+data+"'";
				ResultSet rs2 = stmt.executeQuery(query2);
				rs2.next();
				dataitem[0] = rs.getString("LoanRequestID");
				dataitem[1] = rs.getString("GroupID");
				dataitem[2] = rs2.getString("leadername");
				dataitem[3] = rs2.getString("M1Name");
				dataitem[4] = rs2.getString("M2Name");
				dataitem[5] = rs2.getString("M3Name");
				dataitem[6] = rs2.getString("M4Name");
				dataitem[7] = Calculation.addcomma(LoanRequestDetails[1]);
				dataitem[8] = LoanRequestDetails[2];
				dataitem[9] = rs.getString("RequestDate");
				
				String approave = LoanRequestDetails[5];
				if(approave !=null) {
					dtm.addRow(dataitem);
				}
			}
			return dtm;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}
	
	public String[] GetLoanRequestData(String id) {
		String[] data = new String[6];
		try {
			stmt = con.createStatement();
			query ="Select * from loanrequest where LoanRequestID = '"+id+"'";
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			data[0] = rs.getString(2); //Type
			data[1] = rs.getString(3); //Amount
			data[2] = rs.getString(4); //Duration
			data[3] = rs.getString(5); //Rate
			data[4] = rs.getString(6); //PayDay
			data[5] = rs.getString(7); //Approved
			return data;
		}
		catch(SQLException e) {
			System.out.println(e);
			return null;
		}
	}
	
	
	
///////////////////// LoanRequest Query End ////////////////////
	
///////////////////// GroupLoan Query Start ////////////////////
	
	
	
	
///////////////////// GroupLoan Query End //////////////////////
	

}




