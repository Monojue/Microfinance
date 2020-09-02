package tool;

public class Calculation {

	
	public static void calculator(int amount, int duration, double rate) {
		int principal;
		int interest;
		System.out.println("No\tAmount\tPrincipal\tInterest\tInstallment");
		principal = Math.round(amount/duration);
		for (int i = 0; i < duration; i++) {
			interest = (int) Math.round((amount*rate)/100);
			System.out.print(String.valueOf(i+1)+"\t");
			System.out.print(String.valueOf(amount)+"\t");
			System.out.print(String.valueOf(principal)+"\t");
			System.out.print(String.valueOf(interest)+"\t");
			System.out.print(String.valueOf(principal+interest));
			System.out.println();
			amount = amount - principal;
		}
			
	}
	
	public static void main(String[] args) {
		calculator(1000000, 12, 2.33);
	}
}
