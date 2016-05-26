package com.isa.algorithms.exercises;

public class FirstChar {
	public static boolean startsWithUpper(String input) {
		if(input == null || input.length() == 0){
			return false;
		}
		
		char firstChar = input.charAt(0);
		return firstChar>='A' && firstChar<='Z';
	}
}
