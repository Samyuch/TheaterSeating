package com.samyuktha.util;

/**
 * Utility class that contains methods to check if the string is
 * null or empty or only contains whitespace characters.
 */
public final class StringUtil {

	/**
     * method to check if string is null or empty (has no characters, i.e. length = 0).
     *
     * @param value
     *            the string on which the condition is validated.
     * 
     * @return boolean
     *            true if the string is null or empty
     *            false otherwise
     */
	public static boolean isNullOrEmpty(String value) {
		if (value != null)
			return value.isEmpty();
		else
			return true;
	}

	/**
     * method to check if string is null or empty (has no characters, i.e. length = 0)
     * or only contains whitespace characters (i.e '\0', ' ', '\r', '\n', etc.).
     *
     * @param value
     *            the string on which the condition is validated.
     * 
     * @return boolean
     *            true if the string is null or empty
     *            false otherwise
     */
	public static boolean isNullOrWhitespace(String value) {
		if (value != null)
			return value.trim().isEmpty();
		else
			return true;
	}
}
