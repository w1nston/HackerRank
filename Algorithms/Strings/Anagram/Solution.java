import java.io.*;
import java.util.*;

public class Solution {

	public Solution() {}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Solution solution = new Solution();
		// Just to get rid of the first integer of the input
		int testCases = scanner.nextInt();
		// Iterate over all input strings
		while (scanner.hasNext()) {
			// Saving the string
			String word = scanner.next();
			// And the length of the string
			int length = word.length();
			// It is not possible for two strings of unequal lenghth to be anagram
			if (length % 2 != 0) { // So if the length is not even
				System.out.println("-1"); // It cannot be done!
			} else { // Otherwise
				// Create a variable to store the sum
				int sum = 0;
				// To keep the following for loops tidy, save half the length as well
				int half = (int)(length / 2);
				// Initialize the count array
				int[] count = new int[26];
				for (int i = 0; i < count.length; ++i) {
					// with zero occurrences at first ...
					count[i] = 0;
				}
				// Count up how many letters are used in the first half of the string
				for (int i = 0; i < half; ++i) {
					// Enforce that 'letter' is uppercase
					char letter = Character.toUpperCase(word.charAt(i));
					// Then subtract the Unicode value of 'A' from the unicode value
					// of 'letter' to get the index of where 'letter' is located in
					// the array, and increase the count of that element.
					++count[letter - 'A'];
				}
				// Count down all the letters that are used in the second half
				for (int i = half; i < length; ++i) {
					// Enforce that 'letter' is lowercase
					char letter = Character.toUpperCase(word.charAt(i));
					// Then subtract the Unicode value of 'A' from the unicode value
					// of 'letter' to get the index of where 'letter' is located in
					// the array, and decrease the count of that element.
					--count[letter - 'A'];
				}
				// Sum all occurrences of all characters
				for (int value : count) {
					// ... possibly changing sign on value
					sum += solution.mod(value);
				}
				// Divide the total sum since it's only necessary
				// to change one half of the full string and all
				// possible charachters that can change have been counted.
				sum = sum / 2;
				// Print the answer
				System.out.println(Integer.toString(sum));
				
			}
		}
	}
	
	/**
	 * Helper method to convert negative integers to positive.
	 * Note: This is needed in the case we get a negative count
	 *		 of a character, i.e. a character that is contained
	 *		 in the second half and NOT in the first half.
	 *
	 * @param value - The value to possibly convert to a positive integer.
	 * @return The value sent in, possibly with a sign change.
	 */
	public int mod(int value) {
		// Create a variable to temporarily store the value
		int val = value;
		// If the value is a negative integer
		if (value < 0) {
			// Convert it to a positive integer
			val = (-1) * value;
		} // Otherwise just return it as is
		return val;
	}
}
