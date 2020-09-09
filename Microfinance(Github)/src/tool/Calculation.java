package tool;

import javax.swing.table.DefaultTableModel;

public class Calculation {

	
	public static DefaultTableModel calculator(int amount, int duration, double rate) {
		int principal;
		int interest;
		int TotalPrincipal=0 ;int TotalInterest =0;int TotalInstallment = 0;
		principal = Math.round(amount/duration);
		DefaultTableModel dtm = new DefaultTableModel(duration+1,5);
		for (int i = 0; i < duration; i++) {
			interest = (int) Math.round((amount*rate)/100);
			dtm.setValueAt(String.valueOf(i+1), i, 0);
			dtm.setValueAt(String.valueOf(amount), i, 1);
			dtm.setValueAt(String.valueOf(principal), i, 2);
			dtm.setValueAt(String.valueOf(interest), i, 3);
			dtm.setValueAt(String.valueOf(principal+interest), i, 4);
			amount = amount - principal;
			TotalPrincipal = TotalPrincipal + principal;
			TotalInterest = TotalInterest + interest;
			TotalInstallment = TotalInstallment + principal+interest;
		}
		int TotalRows = dtm.getRowCount()-1;
		dtm.setValueAt("Total", TotalRows, 1);
		dtm.setValueAt(String.valueOf(TotalPrincipal), TotalRows, 2);
		dtm.setValueAt(String.valueOf(TotalInterest), TotalRows, 3);
		dtm.setValueAt(String.valueOf(TotalInstallment), TotalRows, 4);
		return dtm;
	}
	
	public static void main(String[] args) {
		//calculator(1000000, 12, 2.33);
//		interest = (int) Math.round((amount*rate)/100);
//		System.out.print(String.valueOf(i+1)+"\t");
//		System.out.print(String.valueOf(amount)+"\t");
//		System.out.print(String.valueOf(principal)+"\t");
//		System.out.print(String.valueOf(interest)+"\t");
//		System.out.print(String.valueOf(principal+interest));
//		System.out.println();
//		amount = amount - principal;
	}
}
