package entryForm;

import database.DBConnection;

public class LoanRequest {
	
	DBConnection myDbConnection =  new DBConnection();
	
	public String getAutoID() {
		return myDbConnection.getAutoID("LoanRequestID", "LoanRequest", "LR-");
	}
}
