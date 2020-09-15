package com.he.util;

public class Validation {

	public static final int LENGTH_CONSTRAINT = 255;
	public static final int DIGIT_CONSTRAINT = 10;
	public static final boolean IS_SAME = true;

	public static final String STRING_WITH_SPACE = "Within char without whitespace and English positive Within char and without whitespace and English positive Within char and without whitespace and English positive Within char and without whitespace and English positive and without whitespace and English ";
	public static final String STRING_WITHOUT_SPACE = "CharWithinWithinWithinWithinWithinWithinWithinWithinWithinWithinWithinWithinWithinWithinWithinWithinWithinWithinWithinWithinWithinWithinWithinWithinWithinWithinWithinWithinWithinWithinWithinWithinWithinWithinWithinWithinCharacterWithinWithinWithinWithChar";
	public static final String NON_ENG_WITH_SPACE = "le gîte";
	public static final String NON_ENG_WITHOUT_SPACE = "домой";

	public static boolean isLengthValid(String text, int size, boolean same) {
		//System.out.println("text-->" + text + " size-->" + size + " same-->" + same);
		if (null == text || text.equals("") || text.trim().length() > size) {
			return false;
		} else if (same == true && text.trim().length() != size) {
			return false;
		}
		//System.out.println("isLengthValid--> true");
		return true;

	}

	public static boolean isValidName(String text) {
		//System.out.println("text-->" + text);
		String regx = "^[a-zA-Z\\s]+$";
		//System.out.println("isValidName-->" + text.matches(regx));
		return text.matches(regx);

	}

	public static boolean isValidNumber(String text) {
		//System.out.println("text-->" + text);
		String regx = "[0-9]+";
		//System.out.println("isValidNumber-->" + text.matches(regx));
		return text.matches(regx);

	}

	public static boolean isNullorEmpty(String text) {
		if (null == text || text.equals(""))
			return true;
		else
			return false;
	}

}
