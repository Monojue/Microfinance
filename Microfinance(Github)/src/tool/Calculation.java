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
			dtm.setValueAt(addcomma(String.valueOf(amount)), i, 1);
			dtm.setValueAt(addcomma(String.valueOf(principal)), i, 2);
			dtm.setValueAt(addcomma(String.valueOf(interest)), i, 3);
			dtm.setValueAt(addcomma(String.valueOf(principal+interest)), i, 4);
			amount = amount - principal;
			TotalPrincipal = TotalPrincipal + principal;
			TotalInterest = TotalInterest + interest;
			TotalInstallment = TotalInstallment + principal+interest;
		}
		int TotalRows = dtm.getRowCount()-1;
		dtm.setValueAt("Total", TotalRows, 1);
		dtm.setValueAt(addcomma(String.valueOf(TotalPrincipal)), TotalRows, 2);
		dtm.setValueAt(addcomma(String.valueOf(TotalInterest)), TotalRows, 3);
		dtm.setValueAt(addcomma(String.valueOf(TotalInstallment)), TotalRows, 4);
		return dtm;
	}
	
	public static String addcomma(String amount) {
		String value = "";
		String reverse="";
		for (int i = amount.length(); i >=1; i--) {
			reverse += amount.substring(i-1,i);
		}
		for (int i = reverse.length(); i >= 1; i--) {
			
			if (i%3==0 && i!=reverse.length()) {
				value+=",";
			}
			value += reverse.substring(i-1,i);
		}
		return value;
	}
	
	public static String removecomma(String amount) {
		return amount.replaceAll(",", "");
	}
	
	public static String customRemove(String str, String oldChar, String newChar) {
		return str.replaceAll(oldChar, newChar);
	}
	
	public static String[] splitNRC(String str) {
		
		return str.replace("/", "").split("-");
	}
	
	public static String[] splitAddress(String str) {
		return str.split("\\|");
	}
	
	public static String[] splitBirthday(String str) {
		return str.split("-");
	}
	public static void main(String[] args) {
		System.out.println(splitNRC("12/-DAGARA-(N)-654321")[1]);
		
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
