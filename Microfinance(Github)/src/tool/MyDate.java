package tool;

import java.util.*;
public class MyDate {
	public static String getdate() {
		StringBuffer str=new StringBuffer("");
		Date date=new Date();		
		
		str.append(String.valueOf(date).substring(24));
		str.append("-");
		str.append(String.valueOf(date).substring(4,7));
		str.append("-");
		str.append(String.valueOf(date).substring(8,10));
//		str.append("(");
//		str.append(String.valueOf(date).substring(0,3));
//		str.append(")");
		System.out.println(String.valueOf(date));
		return str.toString();
	}
	
	public static String getdate2(Date date, int plus) {
		StringBuffer str=new StringBuffer("");
		
		Date UpdateDate = addMonth(date,plus);
		str.append(String.valueOf(UpdateDate).substring(24));
		str.append("-");
		str.append(String.valueOf(UpdateDate).substring(4,7));
		str.append("-");
		str.append(String.valueOf(UpdateDate).substring(8,10));
		return str.toString();
	}
	
	public static Date addMonth(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, i);
        return cal.getTime();
    }
}

