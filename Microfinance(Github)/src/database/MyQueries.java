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
	static String query,query1,query2;
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
		}else if (tbName.equals("officer")) {
			query = "select * from officer where NRC='"+data[0]+"'";
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
	else if(tbName.equals("repayment")) {
		query = "insert into repayment(RepaymentID,LoanRequestID,PaymentDate,Amount) "
				+ "values('"+data[0]+"','"+data[1]+"','"+data[2]+"','"+Integer.parseInt(data[3])+"')";
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
	/////////////////////////////Insert End//////////////////////////////////////
	
	/////////////////////////////Delete Start//////////////////////////////////////
	public boolean DeleteData(String tbName , String ID) {
		if(tbName.equals("loanrequest1")) {
			query = "delete from clientdetails where loanRequestID ='"+ID+"'";
			query1 = "delete from loanrequest where loanRequestID ='"+ID+"'";
		}
		if(tbName.equals("loanrequest2")) {
			query = "delete from groupdetails where loanRequestID ='"+ID+"'";
			query1 = "delete from loanrequest where loanRequestID ='"+ID+"'";
		}
		try {
			stmt = con.createStatement();
			System.out.println(query);
			if(stmt.executeUpdate(query)==1) {
				if(stmt.executeUpdate(query1)==1) {
				return true;}
				else {
					return false;
				}
			}else {
				return false;
			}
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	//////////////////// Update Start!!!/////////////////////////////////////
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
	if(tbName.equals("loanrequestDecline")) {
		query = "update loanrequest set Approved= '"+data[1]+"',Remark= '"+data[2]+"' where LoanrequestID= '"+data[0]+"'";
	}
	if(tbName.equals("paidday")) {
		query = "update loanrequest set PayDay= '"+data[1]+"' where LoanrequestID= '"+data[0]+"'";
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
	
	public DefaultTableModel GetAllLoanSetting() {
		try {
			DefaultTableModel dtm = new DefaultTableModel();
			String data[] = new String[11];
			stmt = con.createStatement();
			query = "select * from loansetting limit 20";
			rs = stmt.executeQuery(query);
			rs.next();
			int count = dtm.getRowCount();
			if (count==0) {
				dtm.addColumn("Name");
				dtm.addColumn("Type");
				dtm.addColumn("Min Amount");
				dtm.addColumn("Max Amount");
				dtm.addColumn("Min Duration");
				dtm.addColumn("Max Duration");
				dtm.addColumn("Amount Interval");
				dtm.addColumn("Duration Interval");
				dtm.addColumn("Interest Rate");
				dtm.addColumn("Service Rate");
				dtm.addColumn("Updated Date");
			}
			while(rs.next()) {
				String OfficerName = GetOfficerData(rs.getString("OfficerID"));
				data[0] = OfficerName;
				data[1] = rs.getString("Type");
				data[2] = rs.getString("MinAmount");
				data[3] = rs.getString("MaxAmount");
				data[4] = rs.getString("MinDuration");
				data[5] = rs.getString("MaxDuration");
				data[6] = rs.getString("AmountInterval");
				data[7] = rs.getString("DurationInterval");
				data[8] = rs.getString("InterestRate");
				data[9] = rs.getString("ServiceRate");
				data[10] = rs.getString("Date");
				dtm.addRow(data);
			}
			return dtm;
		}
		catch(SQLException e) {
			return null;
		}
	}
	
	public String GetOfficerData(String id) {
		String Name;
		try {
			stmt = con.createStatement();
			query ="Select * from officer where OfficerID = '"+id+"'";
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			Name = rs.getString("Name"); //Name
			return Name;
		}
		catch(SQLException e) {
			System.out.println(e);
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
	
	public boolean CheckClientIsInGroup(String ID){
		try {
			stmt = con.createStatement();
			String q1 = "Select * from clientgroup where Leader= '"+ID+"'";
			String q2 = "Select * from clientgroup where Member_1= '"+ID+"'";
			String q3 = "Select * from clientgroup where Member_2= '"+ID+"'";
			String q4 = "Select * from clientgroup where Member_3= '"+ID+"'";
			String q5 = "Select * from clientgroup where Member_4= '"+ID+"'";
			ResultSet rs1 = stmt.executeQuery(q1);
			if(rs1.next()) {
				return true;
			}
			ResultSet rs2 = stmt.executeQuery(q2);
			if(rs2.next()) {
				return true;
			}
			ResultSet rs3 = stmt.executeQuery(q3);
			if(rs3.next()) {
				return true;
			}
			ResultSet rs4 = stmt.executeQuery(q4);
			if(rs4.next()) {
				return true;
			}
			ResultSet rs5 = stmt.executeQuery(q5);
			if(rs5.next()) {
				return true;
			}
				return false;					
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
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
	
	public String[] GetLoanRequestedDate(String Type,String LID) {
		try {
			stmt = con.createStatement();
			if(Type.equals("Individual")) {
			query ="Select * from clientdetails where LoanRequestID = '"+LID+"'";
			}
			else if(Type.equals("Group")) {
			query ="Select * from groupdetails where LoanRequestID = '"+LID+"'";
				}
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			String[] data = new String[3];
			data[0] = rs.getString(1);
			data[1] = rs.getString(2);
			data[2] = rs.getString(3);
			return data;
		}
		catch(SQLException e){
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
				String PayDay = LoanRequestDetails[4];
				String Remark = LoanRequestDetails[6];
				if(approave !=null && PayDay == null && Remark == null) {
					dtm.addRow(dataitem);
				}
			}
			return dtm;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}
	
	public DefaultTableModel getIndividualRejectedLoanRequest() {
		DefaultTableModel dtm = new DefaultTableModel();
		String dataitem[]= new String[9];
		try {
			stmt = con.createStatement();
			query ="Select * from clientdetails";
			ResultSet rs = stmt.executeQuery(query);
			int count = dtm.getRowCount();
			if (count==0) {
				dtm.addColumn("Loan Request ID");
				dtm.addColumn("Client ID");
				dtm.addColumn("Client Name");
				dtm.addColumn("Client Phone");
				dtm.addColumn("Amount");
				dtm.addColumn("Duration");
				dtm.addColumn("Remark");
				dtm.addColumn("Date");
			}
			while (rs.next()) {
				String[] LoanRequestDetails = GetLoanRequestData(rs.getString("LoanRequestID"));
				dataitem[0] = rs.getString("LoanRequestID");
				dataitem[1] = rs.getString("ClientID");
				dataitem[2] = msql.getClientNameFormID(rs.getString("ClientID"));
				String[] Phone = getClientDetailsFormID(rs.getString("ClientID"));
				dataitem[3] = Phone[4];
				dataitem[4] = Calculation.addcomma(LoanRequestDetails[1]);
				dataitem[5] = LoanRequestDetails[2];
				dataitem[6] = LoanRequestDetails[6];
				dataitem[7] = rs.getString("RequestDate");
				
				String approave = LoanRequestDetails[5];
				String PayDay = LoanRequestDetails[4];
				if(approave !=null && approave.equals("2")) {
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
				String PayDay = LoanRequestDetails[4];
				String Remark = LoanRequestDetails[6];
				if(approave !=null && PayDay == null && Remark == null) {
					dtm.addRow(dataitem);
				}
			}
			return dtm;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}
	
	public DefaultTableModel getGroupRejectedLoanRequest() {
		DefaultTableModel dtm = new DefaultTableModel();
		String dataitem[]= new String[12];
		try {
			stmt = con.createStatement();
			query ="Select * from groupdetails";
			ResultSet rs = stmt.executeQuery(query);
			int count = dtm.getRowCount();
			if (count==0) {
				dtm.addColumn("Loan Request ID");
				dtm.addColumn("Group ID");
				dtm.addColumn("Leader Name");
				dtm.addColumn("Leader Phone");
				dtm.addColumn("Member1 Name");
				dtm.addColumn("Member2 Name");
				dtm.addColumn("Member3 Name");
				dtm.addColumn("Member4 Name");
				dtm.addColumn("Amount");
				dtm.addColumn("Duration");
				dtm.addColumn("Remark");
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
				String[] Phone = getClientDetailsFormID(rs2.getString("Leader"));
				dataitem[3] = Phone[4];
				dataitem[4] = rs2.getString("M1Name");
				dataitem[5] = rs2.getString("M2Name");
				dataitem[6] = rs2.getString("M3Name");
				dataitem[7] = rs2.getString("M4Name");
				dataitem[8] = Calculation.addcomma(LoanRequestDetails[1]);
				dataitem[9] = LoanRequestDetails[2];
				dataitem[10] = LoanRequestDetails[6];
				dataitem[11] = rs.getString("RequestDate");
				
				String approave = LoanRequestDetails[5];
				String PayDay = LoanRequestDetails[4];
				if(approave !=null && approave.equals("2")) {
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
		String[] data = new String[8];
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
			data[5] = rs.getString(7); //Approve
			data[6] = rs.getString(8); //Remark
			data[7] = rs.getString("PayDay"); //Pay Day
			return data;
		}
		catch(SQLException e) {
			System.out.println(e);
			return null;
		}
	}
	
	public boolean CheckAvaliable(String Type,String ID) {
		try {
			stmt = con.createStatement();
			if(Type.equals("Individual")) {
			query = "Select * from clientdetails where ClientID= '"+ID+"'";
			}
			else if(Type.equals("Group")) {
			query = "Select * from groupdetails where GroupID= '"+ID+"'";	
			}
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				return true;
			}
			else {
				return false;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
///////////////////// LoanRequest Query End ////////////////////
	
///////////////////// Repayment Start ////////////////////////
	public DefaultTableModel getIRepaymentTable() {
		DefaultTableModel dtm = new DefaultTableModel();
		String dataitem[]= new String[9];
		try {
			stmt = con.createStatement();
			query ="Select * from clientdetails";
			ResultSet rs = stmt.executeQuery(query);
			int count = dtm.getRowCount();
			if (count==0) {
				dtm.addColumn("Loan Request ID");
				dtm.addColumn("Client ID");
				dtm.addColumn("Client Name");
				dtm.addColumn("Client Phone");
				dtm.addColumn("Loan Amount");
				dtm.addColumn("Total Amount");
				dtm.addColumn("Remaining Amount");
				dtm.addColumn("Total Duration");
				dtm.addColumn("Remaining Duration");
			}
			while (rs.next()) {
				String[] LoanRequestDetails = GetLoanRequestData(rs.getString("LoanRequestID"));
				dataitem[0] = rs.getString("LoanRequestID");
				dataitem[1] = rs.getString("ClientID");
				dataitem[2] = msql.getClientNameFormID(rs.getString("ClientID"));
				String[] Phone = getClientDetailsFormID(rs.getString("ClientID"));
				dataitem[3] = Phone[4];
				dataitem[4] = Calculation.addcomma(LoanRequestDetails[1]);
				DefaultTableModel dtm2 = Calculation.calculator(Integer.parseInt(LoanRequestDetails[1]), Integer.parseInt(LoanRequestDetails[2]), Float.parseFloat(LoanRequestDetails[3]));
				dataitem[5] = Calculation.addcomma((String)dtm2.getValueAt( Integer.parseInt(LoanRequestDetails[2]), 4));
				dataitem[7] = LoanRequestDetails[2];
				
				String query3 = "Select * from repayment where LoanRequestID= '"+dataitem[0]+"'";
				ResultSet rs2 = stmt.executeQuery(query3);
				int RemainingAmount = Integer.parseInt(Calculation.removecomma(dataitem[5]));
				int RemainingDuration = Integer.parseInt(LoanRequestDetails[2]);
				while(rs2.next()) {
					RemainingAmount = RemainingAmount - Integer.parseInt(rs2.getString("Amount"));
					RemainingDuration = RemainingDuration - 1;
				}
				dataitem[6] = Calculation.addcomma(Integer.toString(RemainingAmount));
				dataitem[8] = Integer.toString(RemainingDuration);
				
				if(RemainingDuration<=0) {
					DeleteLoanRequest(dataitem[0],"Individual");
				}
				
				String PayDay = LoanRequestDetails[4];
				if(PayDay !=null && RemainingDuration>0) {
					dtm.addRow(dataitem);
				}
			}
			return dtm;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	
	}
	
	public DefaultTableModel getGRepaymentTable() {
		DefaultTableModel dtm = new DefaultTableModel();
		String dataitem[]= new String[9];
		try {
			stmt = con.createStatement();
			query ="Select * from groupdetails";
			ResultSet rs = stmt.executeQuery(query);
			int count = dtm.getRowCount();
			if (count==0) {
				dtm.addColumn("Loan Request ID");
				dtm.addColumn("Group ID");
				dtm.addColumn("Leader Name");
				dtm.addColumn("Leader Phone");
				dtm.addColumn("Loan Amount");
				dtm.addColumn("Total Amount");
				dtm.addColumn("Remaining Amount");
				dtm.addColumn("Total Duration");
				dtm.addColumn("Remaining Duration");
			}
			while (rs.next()) {
				String[] LoanRequestDetails = GetLoanRequestData(rs.getString("LoanRequestID"));
				String data = rs.getString("GroupID");
				query2 = "Select * from clientgroup where groupID= '"+data+"'";
				ResultSet rs2 = stmt.executeQuery(query2);
				rs2.next();
				dataitem[0] = rs.getString("LoanRequestID");
				dataitem[1] = rs.getString("GroupID");
				dataitem[2] = rs2.getString("leadername");
				String[] Phone = getClientDetailsFormID(rs2.getString("Leader"));
				dataitem[3] = Phone[4];
				dataitem[4] = Calculation.addcomma(LoanRequestDetails[1]);
				DefaultTableModel dtm2 = Calculation.calculator(Integer.parseInt(LoanRequestDetails[1]), Integer.parseInt(LoanRequestDetails[2]), Float.parseFloat(LoanRequestDetails[3]));
				dataitem[5] = Calculation.addcomma((String)dtm2.getValueAt( Integer.parseInt(LoanRequestDetails[2]), 4));
				dataitem[7] = LoanRequestDetails[2];
				
				String query3 = "Select * from repayment where LoanRequestID= '"+dataitem[0]+"'";
				ResultSet rs3 = stmt.executeQuery(query3);
				int RemainingAmount = Integer.parseInt(Calculation.removecomma(dataitem[5]));
				int RemainingDuration = Integer.parseInt(LoanRequestDetails[2]);
				while(rs3.next()) {
					RemainingAmount = RemainingAmount - Integer.parseInt(rs3.getString("Amount"));
					RemainingDuration = RemainingDuration - 1;
				}
				dataitem[6] = Calculation.addcomma(Integer.toString(RemainingAmount));
				dataitem[8] = Integer.toString(RemainingDuration);
				
				if(RemainingDuration<=0) {
					DeleteLoanRequest(dataitem[0],"Group");
				}
				
				String PayDay = LoanRequestDetails[4];
				if(PayDay != null) {
					dtm.addRow(dataitem);
				}
			}
			return dtm;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}
	
	public Integer GetPaymentNumber(String ID) {
		int num=0;
		try {
			query = "Select * from repayment where LoanRequestID= '"+ID+"'";
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
			num = num+1;
			}
			return num;
		}
		catch (SQLException e) {
			System.out.println(e);
			return null;		
		}
	}
	
	public void DeleteLoanRequest(String ID,String Type) {
		try {
			stmt = con.createStatement();
			query = "Delete from repayment where LoanRequestID= '"+ID+"'";
			query1 = "Delete from loanrequest where LoanRequestID= '"+ID+"'";
			if(Type.equals("Individual")) 
			{
				query2 = "Delete from clientdetails where LoanRequestID= '"+ID+"'";
			}
			else if(Type.equals("Group")) 
			{
				query2 = "Delete from groupdetails where LoanRequestID= '"+ID+"'";
			}
			stmt.executeUpdate(query);
			stmt.executeUpdate(query2);
			stmt.executeUpdate(query1);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

	}
///////////////////// Repayment End //////////////////////


}




