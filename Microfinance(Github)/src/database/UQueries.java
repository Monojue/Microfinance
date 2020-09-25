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

public class UQueries {
	static Connection con = null;
	static Statement stmt;
	static String query,query1;
	ResultSet rs;
	DefaultTableModel dtm = new DefaultTableModel();
	DBConnection connect = new DBConnection();
	static UQueries usql = new UQueries();
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
	
	public void cleardtm() {
		if (dtm.getRowCount() > 0) {
		    for (int i = dtm.getRowCount() - 1; i > -1; i--) {
		    	dtm.removeRow(i);
		    }
		}
	}
///////////////////// Client Query Start ///////////////////////
	public DefaultTableModel getClient(String data, String type) {
		dtm= new DefaultTableModel();
		String strdataitem[]= new String[9];
		try {
			stmt = con.createStatement();
			if (MyString.All.equals(type)) {
				query ="Select * from Client";
			}else if (MyString.ID.equals(type)) {
				query="Select * from client where clientID= '"+data+"'";
			}else if (MyString.Name.equals(type)) {
				query="Select * from client where Name like '"+data+"%'";
			}
			
			ResultSet rs = stmt.executeQuery(query);
			int count = dtm.getRowCount();
			if (count==0) {
				dtm.addColumn("ClientID");
				dtm.addColumn("Name");
				dtm.addColumn("NRC");
				dtm.addColumn("Address");
				dtm.addColumn("Phone");
				dtm.addColumn("DateOfBirth");
				dtm.addColumn("Home");
				dtm.addColumn("Job");
				dtm.addColumn("Salary");
			}
			while (rs.next()) {
				strdataitem[0] = rs.getString("ClientID");
				strdataitem[1] = rs.getString("Name");
				strdataitem[2] = Calculation.customRemove(rs.getString("NRC"), "-","");
				strdataitem[3] = Calculation.customRemove(rs.getString("Address"), "\\|",",");
				strdataitem[4] = rs.getString("Phone");
				strdataitem[5] = rs.getString("DateOfBirth");
				String Home = rs.getString("Home");
				if(Home.equals("1")) {
					strdataitem[6] = "owned";
				}
				else if(Home.equals("0")) {
					strdataitem[6] = "-";
				}
				strdataitem[7] = rs.getString("Job");
				strdataitem[8] = Calculation.addcomma(rs.getString("Salary"));				
				dtm.addRow(strdataitem);
			}
			return dtm;
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return dtm;
	}
	
	public String getClientNameFormID(String id) {
		query = "Select Name from client where clientID = '"+id+"';";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			return rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean deleteClient(String ID) {
		query = "Delete from client where clientID='"+ID+"'";
		try {
			stmt = con.createStatement();
			if (stmt.executeUpdate(query)==1) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

///////////////////// Client Query End /////////////////////////
	
	public String CheckLogin(String username, String password) {
		query = "Select * from officer where binary username ='"+username+"' and binary password='"+password+"'";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				return rs.getString("Role");	
			} else {
				return "notfound";
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"SQL Exception", JOptionPane.ERROR_MESSAGE);
			return "notfound";
		}
	}
	
	
	public boolean CheckPassword(String username, String password) {
		query = "Select * from officer where binary username ='"+username+"' and binary password='"+password+"'";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				return true;	
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"SQL Exception", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	
		//Duplicate Method
	public boolean IsDuplicate(String tbName, String[] data) {
		if(tbName.equals(MyString.ClientEntry)) {
			query = "select * from client where NRC='"+data[0]+"'";
		}
		else if (tbName.equals(MyString.GroupEntry)) {
			query = "select * from clientgroup where leader='"+data[0]+"', Member_1='"+data[1]+"' and Member_2='"+data[2]
					+"' and Member_3='"+data[3]+"'and Member_4='"+data[4]+"'";
					
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
		query = "Insert into clientGroup(groupID, leader, Member_1, Member_2, Member_3, Member_4, leaderName, M1Name, M2Name, M3Name, M4Name) "
				+ "values('"+data[0]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"','"+data[4]+"','"+data[5]+"','"+data[6]+"','"+data[7]+"','"+data[8]+"','"+data[9]+"','"+data[10]+"')";
	}else if (tbName.equals("Officer")) {
		query = "Insert into Officer(officerID, Name, Address, Phone, NRC, Role, UserName, Password) "
				+ "values('"+data[0]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"','"+data[4]+"','"+data[5]+"','"+data[6]+"','"+data[7]+"')";
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
	
//	public static boolean DeleteData(String tbName, String ID) {
//	
//	query = "Delete "+tbName+" where "
//	try {
//		stmt = con.createStatement();
//		System.out.println(query);
//		if(stmt.executeUpdate(query)==1) {
//			return true;
//		}else {
//			return false;
//		}
//	}catch (SQLException e) {
//		JOptionPane.showMessageDialog(null, e.getMessage());// TODO: handle exception
//		e.printStackTrace();
//		return false;
//	}
//	}
	
//	public String checkBeforeDelete(String tbName, String ID) {
//		if (tbName.equals("client")) {
//			if (checkInTable("group", ID)) {
//				String groupID = getGroupIDFormClientID(ID);
//				if (checkInTable("groupdetails", groupID)) {
//					return MyString.ClientFoundInGroupandLoan;
//				}else {
//					return MyString.ClientFoundInGroup;
//				}
//			}else if (checkInTable("clientdetails", ID)) {
//				return MyString.ClientFoundInLoan;
//			}
//		}else if (tbName.equals("group")) {
//			if (checkInTable("groupdetails", ID)) {
//				return MyString.GroupFoundInLoan;
//			}
//		}
//		return MyString.OkToDelete;
//	}
	
	public Vector<String> checkBeforeDelete(String tbName, String ID) {
		Vector<String> found = new Vector<>();
		if (tbName.equals("client")) {
			if (checkInTable("group", ID)) {
				found.add("Group");
				if (checkInTable("groupdetails", getGroupIDFormClientID(ID))) {
					found.add("Group Loan");
					if (checkInTable("repayment", getLoanIDfromGroupID(getGroupIDFormClientID(ID)))) {
						found.add("Group Repayment");
					}
				}
			}else {
				if (checkInTable("clientdetails", ID)) {
					found.add("Client Loan");
					if (checkInTable("repayment", getLoanIDfromClientID(ID))) {
						found.add("Individual Repayment");
					}
				}
			}
		}else if (tbName.equals("group")) {
			if (checkInTable("groupdetails", ID)) {
				found.add("Group Loan");
				if (checkInTable("repayment", getLoanIDfromGroupID(ID))) {
					found.add("Group Repayment");
				}
			}
		}else if (tbName.equals("loanrequest")) {
			if (checkInTable("repayment", ID)) {
				found.add("Repayment");
			}
		}
		return found;
	}
	
	
	public boolean checkInTable(String tbName, String ID) {
		if (tbName.equals("client")) {
			query = "Select * from client where clientID="+ID+"'";
		}else if (tbName.equals("group")) {
			query = "SELECT * FROM clientgroup where Leader='"+ID+"' or Member_1='"+ID+"' or Member_2='"+ID+"' or Member_3='"+ID+"' or Member_4='"+ID+"'";
		}else if (tbName.equals("groupdetails")) {
			query = "Select * from groupdetails where GroupID='"+ID+"'";
		}else if (tbName.equals("clientdetails")) {
			query  = "Select * from clientdetails where ClientID='"+ID+"'";
		}else if (tbName.equals("repayment")) {
			query  = "Select * from repayment where LoanRequestID='"+ID+"'";
		}
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if(rs.next()) {
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
	
	public String getGroupIDFormClientID(String CID) {
		query = "SELECT * FROM clientgroup where Leader='"+CID+"' or Member_1='"+CID+"' or Member_2='"+CID+"' or Member_3='"+CID+"' or Member_4='"+CID+"'";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				return rs.getString("GroupID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
///////////////////// Group Query Start ////////////////////////
	
	public DefaultTableModel getGroup(String data, String type) {
		dtm = new DefaultTableModel();
		String strdataitem[]= new String[6];
		try {
			con=DBConnection.GetMySQLConnection();
			stmt = con.createStatement();
			if (MyString.All.equals(type)) {
				query ="Select * from Clientgroup";
			}else if (MyString.ID.equals(type)) {
				query="Select * from clientgroup where groupID= '"+data+"'";
			}else if (MyString.Name.equals(type)) {
				query="Select * from clientgroup where leaderName like '"+data+"%'";
			}
			ResultSet rs = stmt.executeQuery(query);
			dtm.addColumn("GroupId");
			dtm.addColumn("Leader");
			dtm.addColumn("Member_1");
			dtm.addColumn("Member_2");
			dtm.addColumn("Member_3");
			dtm.addColumn("Member_4");

			while (rs.next()) {
				strdataitem[0] = rs.getString("GroupId");
				strdataitem[1] = getClientNameFormID(rs.getString("Leader"));
				strdataitem[2] = getClientNameFormID(rs.getString("Member_1"));
				strdataitem[3] = getClientNameFormID(rs.getString("Member_2"));
				strdataitem[4] = getClientNameFormID(rs.getString("Member_3"));
				strdataitem[5] = getClientNameFormID(rs.getString("Member_4"));
				dtm.addRow(strdataitem);
			}
			return dtm;
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e);
		}
		
		return dtm;
	}
	
	public Vector<String> getClientIDFormGroupID(String GroupID) {
		Vector<String> clientID = new Vector<>();
		query = "Select * from clientgroup where groupID='"+GroupID+"'";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				for (int i = 2; i <=6 ; i++) {
					clientID.add(rs.getString(i));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clientID;
	}

///////////////////// Group Query End //////////////////////////
	
///////////////////// LoanRequest Query Start //////////////////
	public String getLoanIDfromClientID(String ClientID) {
		query = "Select * from ClientDetails where clientID='"+ClientID+"'";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				return rs.getString("LoanRequestID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String getLoanIDfromGroupID(String GroupID) {
		query = "Select * from GroupDetails where GroupID='"+GroupID+"'";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				return rs.getString("LoanRequestID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	

	
	
///////////////////// LoanRequest Query End ////////////////////
	
///////////////////// GroupLoan Query Start ////////////////////
	
	
	
	
///////////////////// GroupLoan Query End //////////////////////
	public DefaultTableModel getOfficer(String data, String type) {
		dtm= new DefaultTableModel();
		String strdataitem[]= new String[6];
		try {
			stmt = con.createStatement();
			if (MyString.All.equals(type)) {
				query ="Select * from officer";
			}else if (MyString.ID.equals(type)) {
				query="Select * from officer where officerID= '"+data+"'";
			}else if (MyString.Name.equals(type)) {
				query="Select * from officer where Name like '"+data+"%'";
			}
			
			ResultSet rs = stmt.executeQuery(query);
			int count = dtm.getRowCount();
			if (count==0) {
				dtm.addColumn("OfficerID");
				dtm.addColumn("Name");
				dtm.addColumn("NRC");
				dtm.addColumn("Address");
				dtm.addColumn("Phone");
				dtm.addColumn("Role");
			}
			while (rs.next()) {
				strdataitem[0] = rs.getString("OfficerID");
				strdataitem[1] = rs.getString("Name");
				strdataitem[2] = Calculation.customRemove(rs.getString("NRC"), "-","");
				strdataitem[3] = rs.getString("Address");
				strdataitem[4] = rs.getString("Phone");
				strdataitem[5] = rs.getString("Role");
				dtm.addRow(strdataitem);
			}
			return dtm;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return dtm;
	}
	
	public boolean deleteOfficer(String ID) {
		query = "Delete from officer where officerID='"+ID+"' and Role='Staff'";
		try {
			stmt = con.createStatement();
			if (stmt.executeUpdate(query)==1) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
////////////////////////Delete /////////////////////////
	public boolean deletePayment(String LoanRequestID) {
		query = "Delete from repayment where LoanRequestID='"+LoanRequestID+"'";
		try {
			stmt = con.createStatement();
			if (stmt.executeUpdate(query)>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteclientLoanRequest(String ClientID) {
		String LoanID = getLoanIDfromClientID(ClientID);
		return deleteclientLoanRequestLoanID(LoanID);
	}
	
	public boolean deleteclientLoanRequestLoanID(String LoanID) {
		deleteClientDetailsLoanID(LoanID);
		query = "Delete from LoanRequest where LoanRequestID='"+LoanID+"'"; 
		try {
			stmt = con.createStatement();
			if (stmt.executeUpdate(query)==1) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteGroupLoanRequest(String GroupID) {
		String LoanID = getLoanIDfromGroupID(GroupID);
		return deleteGroupLoanRequestLoanID(LoanID);
	}
	
	public boolean deleteGroupLoanRequestLoanID(String LoanID) {
		deleteGroupDetailsLoanID(LoanID);
		query = "Delete from LoanRequest where LoanRequestID='"+LoanID+"'"; 
		try {
			stmt = con.createStatement();
			if (stmt.executeUpdate(query)==1) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public void deleteClientDetailsLoanID(String LoanID) {
		query = "Delete from ClientDetails where LoanRequestID='"+LoanID+"'";
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteGroupDetailsLoanID(String LoanID) {
		query = "Delete from GroupDetails where LoanRequestID='"+LoanID+"'";
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean deleteGroupFromGroupID(String ID) {
		query = "Delete from clientgroup where GroupID='"+ID+"'";
		try {
			stmt = con.createStatement();
			if (stmt.executeUpdate(query)==1) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean AutoDelete(String tbName, String key, String ID) {
		if (tbName.equals("client")) {
			if (key.equals("Individual Repayment")) {
				if (deletePayment(getLoanIDfromClientID(ID))) {
					return true;
				}
			}if (key.equals("Group Repayment")) {
				if (deletePayment(getLoanIDfromGroupID(getGroupIDFormClientID(ID)))) {
					return true;
				}
			}else if (key.equals("Group Loan")) {
				if (deleteGroupLoanRequest(getGroupIDFormClientID(ID))) {
					return true;
				}
			}else if (key.equals("Client Loan")) {
				if (deleteclientLoanRequest(ID)) {
					return true;
				}
			}else if (key.equals("Group")) {
				if (deleteGroupFromGroupID(getGroupIDFormClientID(ID))) {
					return true;
				}
			}
		}else if (tbName.equals("group")) {
			System.out.println("enter to group");
			if (key.equals("Group Repayment")) {
				if (deletePayment(getLoanIDfromGroupID(ID))) {
					System.out.println("group Repay");
					return true;
				}
			}else if (key.equals("Group Loan")) {
				if (deleteGroupLoanRequest(ID)) {
					System.out.println("group Loankkk");
					return true;
				}
			}
		}else if (tbName.equals("loanrequest")) {
			if (key.equals("Repayment")) {
				if (deletePayment(ID)) {
					return true;
				}
			}
		}
		return false;
	}
}




