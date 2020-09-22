package tool;


import java.util.*;

public class Checking {
	
	
	public static void main(String[] args) {
		System.out.println(IsAllDigit("0124"));
	}
	
	public static boolean IsValidName(String str) {
		if(str.startsWith(" ")) {
			return false;
		}
		if(!IsLetter(str)) {
			return false;
		}
		return true;
	}
	
	public static boolean IsNull(String str) { //txt.getText()
		if(str.trim().equals("") || str.trim().equals(null)) {
			return true; 
		}
		else {
			return false;
		}
	}
	
	public static boolean IsLetter(String str) {
		for(int i=0;i<str.length();i++) {
			if(!Character.isLetter(str.charAt(0))) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean IsAllDigit(String str) {
		for(int i=0;i<str.length();i++) {
			if(!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
		return true;
	}
	
	public static boolean IsContain(char c,String str) {
		for(int i=0;i<str.length();i++) {
			if(str.charAt(i)==c) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean IsContain(String s,Vector v) {
		for(int i=0;i<v.size();i++) {
			if(s.contentEquals((String)v.elementAt(i))) {
				return true;
			}
		}
		return false;
	}
}

