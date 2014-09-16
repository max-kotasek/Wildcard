package com.java.org.wildcard;

import java.util.Collection;

public class Wildcard {

	
	private static int get_next_interesting_character(String pattern) {
		int w = pattern.indexOf('*');
		int q = pattern.indexOf('?');
		
		if (w == -1 && q == -1) {
			return pattern.length();
		}
		else if (w == -1 && q != -1) {
			return q;
		}
		else if (w != -1 && q == -1) {
			return w;
		}
		else {
			return Math.min(w, q);
		}
		
	}
	
	private static String lstrip(String pattern, String stripChars) {
		int index=0;
		for (int i=0;i<pattern.length();i++) {
			String s = Character.toString(pattern.charAt(i));
			if (stripChars.contains(s)) {
				index++;
			}
			else {
				return pattern.substring(index);
			}
		}
		//All characters were stripped.
		return "";
	}
	
	public static boolean matchOneOf(String pattern, Collection<String> inputs) {
		for (String input: inputs) {
			if (!match(pattern, input)) {
				continue;
			}
			return true;
		}
		return false;
	}
	
	public static boolean match(String pattern, String input) {
		if (pattern == null || pattern.length() == 0) {
			return false;
		}
		
		if (input == null || input.length() == 0) {
			return false;
		}
		
		int next_index = 0;
		String pattern_slice = "";
		while (pattern.length() > 0) {
			
			if (pattern.charAt(0) == '*') {
				pattern = lstrip(pattern, "*?");
				if (pattern.length() == 0) {
					return true;
				}
				
				next_index = get_next_interesting_character(pattern);
				pattern_slice = pattern.substring(0, next_index);
				
				if (input.contains(pattern_slice)) {
					pattern = pattern.substring(next_index);					
					int input_index = input.lastIndexOf(pattern_slice);					
					input = input.substring(input_index + pattern_slice.length());
				}
				else {					
					return false;
				}				
			}
			
			else if (pattern.charAt(0) == '?') {
				if (input.length() < 1) {
					return false;
				}
				pattern = pattern.substring(1);
				input = input.substring(1);
			}
			else {
				next_index = get_next_interesting_character(pattern);
				pattern_slice = pattern.substring(0, next_index);
				pattern = pattern.substring(next_index);
				
				if (input.length() < next_index) {
					return false;
				}
				
				String match_slice = input.substring(0, next_index);
				if (!match_slice.equals(pattern_slice)) {
					return false;
				}
				input = input.substring(next_index);
			}
			
		}
		return (input.length() == 0);
		
	}
}
