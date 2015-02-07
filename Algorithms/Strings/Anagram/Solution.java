import java.io.*;
import java.util.*;

public class Solution {

	public Solution() {}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Solution solution = new Solution();

		int testCases = scanner.nextInt();
		while (scanner.hasNext()) {

			String word = scanner.next();
			int wordLength = word.length();
			
			// It is not possible for two strings of unequal lenghth to be anagram
			if (wordLength % 2 != 0) { 
				System.out.println("-1"); // Signal that it cannot be done
			} else {
				int sum = 0;
				int halfLength = (int)(wordLength / 2);

				int[] occurrence = new int[26];
				for (int i = 0; i < occurrence.length; ++i) {
					occurrence[i] = 0;
				}
				
				/* 
					Note: To get the index of a character in the array the unicode of
				    the letter is subtracted with the unicode value of the letter A.
				*/
				
				// Count up how many letters are used in the first half of the string
				for (int i = 0; i < halfLength; ++i) {
					char letter = Character.toUpperCase(word.charAt(i));
					++occurrence[letter - 'A'];
				}
				
				// Count down all the letters that are used in the second half
				for (int i = halfLength; i < wordLength; ++i) {
					char letter = Character.toUpperCase(word.charAt(i));
					--occurrence[letter - 'A'];
				}
				
				// Sum all occurrences of all characters
				for (int value : occurrence) {
					// ... possibly changing sign on value
					sum += solution.mod(value);
				}
				
				// Divide the total sum since it's only necessary
				// to change one half of the full string and all
				// possible charachters that can change have been counted.
				sum = sum / 2;

				solution.print(sum);
				
			}
		}
	}
	
	private void print(int element) {
		System.out.println(Integer.toString(element));
	}
	
	/*
	 * This is needed in the case we get a negative count
	 * of a character, i.e. a character that is contained
	 * in the second half and NOT in the first half.
	 */
	public int mod(int value) {
		int val = value;
		
		if (value < 0) {
			val = (-1) * value;
		}
		
		return val;
	}
}
