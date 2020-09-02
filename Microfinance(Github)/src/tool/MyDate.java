package tool;

import java.util.*;
public class MyDate {
	public String getdate() {
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
}

