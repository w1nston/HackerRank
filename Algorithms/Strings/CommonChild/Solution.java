import java.io.*;
import java.util.*;

public class Solution {
	
	// Create a memoization array to reduce time complexity
	private int[][] memArray;
	
	public Solution() {}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Solution solution = new Solution();
		// Obtain the first input string
		String strA = scanner.next();
		// Obtain the second input string
		String strB = scanner.next();
		// Solve the longest common subsequence problem, and print the answer
		solution.print(solution.lcs(strA, strB));
	}
	
	/**
	 * Calculate the longest subsequence common of the two strings provided.
	 *
	 * @param strA - The first string to use when finding the longest common subsequence.
	 * @param strB - The second string to use when finding the longest common subsequence.
	 * @return The length of the longest string containing a common subsequnce in both strings.
	 */
	public int lcs(String strA, String strB) {
		// Save the length of string A
		int lengthA = strA.length();
		// Save the length of string B
		int lengthB = strB.length();
		// Initialize the memoization array with the dimensions of the two strings
		initMemoization(lengthA, lengthB);
		// Call lcs recursively and return the answer
		return lcs(strA, strB, lengthA, lengthB);
	}
	
	/**
	 * Private overloaded method to calculate the longest common subsequence,
	 * providing a pointer to the current char of each string.
	 *
	 * @param strA - The first string to use when finding the longest common subsequence.
	 * @param strB - The second string to use when finding the longest common subsequence.
	 * @param indexA - The pointer to string A.
	 * @param indexB - The pointer to string B.
	 * @return The length of the longest string containing a common subsequnce in both strings.
	 */
	private int lcs(String strA, String strB, int indexA, int indexB) {
		// If we are located at the beginning of both strings
		if (indexA == 0 || indexB == 0) {
			// Set the length of this position to 0
			memArray[indexA][indexB] = 0;
		// If we haven't already calculated the length of any subsequence at
		// the current position for index A and B at string A and B
		} else if (memArray[indexA][indexB] == -1) {
			// Calculate it ...
			// If the previous two characters are equal
			if (strA.charAt(indexA - 1) == strB.charAt(indexB - 1)) {
				// Set the length to be one character match + recursively call self with
				// the pointers shifted one step towards the beginning of each string
				memArray[indexA][indexB] = lcs(strA, strB, indexA - 1, indexB - 1) + 1;
			} else { // If they are not equal
				// Calculate the length of moving the pointer at string B
				int x = lcs(strA, strB, indexA, indexB - 1);
				// Then calculate the length of moving the pointer at string A
				int y = lcs(strA, strB, indexA - 1, indexB);
				// And set the value to be the maximum of the two
				memArray[indexA][indexB] = Math.max(x, y);
			}
		}
		// Return the by now calculated length of a subsequence at this position
		return memArray[indexA][indexB];
	}
	
	/**
	 * Helper method to initialize the memoization array.
	 *
	 * @param lengthA - The length of the first string to check for longest common subsequence.
	 * @param lengthB - The length of the second string to check for longest common subsequence.
	 */
	private void initMemoization(int lengthA, int lengthB) {
		// Initialize the size of the matrix to represent the lengths of
		// a subsequence located at position memArray[indexA][indexB]
		memArray = new int[lengthA+1][lengthB+1];
		for (int i = 0; i <= lengthA; ++i) {
			for (int j = 0; j <= lengthB; ++j) {
				// Set the initial value to -1 to represent not yet calculated
				memArray[i][j] = -1;
			}
		}
	}
	
	/**
	 * Helper method to print the integer answer.
	 *
	 * @param answer - The answer to print.
	 */
	public void print(int answer) {
		System.out.println(Integer.toString(answer));
	}	
}
