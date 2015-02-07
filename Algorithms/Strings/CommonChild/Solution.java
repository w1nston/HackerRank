import java.io.*;
import java.util.*;

public class Solution {
	
	private int[][] memArray;
	
	public Solution() {}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Solution solution = new Solution();

		String strA = scanner.next();
		String strB = scanner.next();
		
		// Solve the longest common subsequence problem, and print the answer
		solution.print(solution.lcs(strA, strB));
	}
	
	public int lcs(String strA, String strB) {
		int lengthA = strA.length();
		int lengthB = strB.length();
		// Initialize the memoization array with the dimensions of the two strings
		initMemoization(lengthA, lengthB);
		// Call lcs recursively and return the answer
		return lcs(strA, strB, lengthA, lengthB);
	}
	
	private int lcs(String strA, String strB, int indexA, int indexB) {

		if (indexA == 0 || indexB == 0) {
			memArray[indexA][indexB] = 0;
		} else if (memArray[indexA][indexB] == -1) { // If not yet calculated

			if (strA.charAt(indexA - 1) == strB.charAt(indexB - 1)) {
				// Set the length to be one character match + recursively call self with
				// the pointers shifted one step towards the beginning of each string
				memArray[indexA][indexB] = lcs(strA, strB, indexA - 1, indexB - 1) + 1;
			} else {
				// Calculate the length of moving the pointer at string B
				int x = lcs(strA, strB, indexA, indexB - 1);
				// Then calculate the length of moving the pointer at string A
				int y = lcs(strA, strB, indexA - 1, indexB);
				// And set the value to be the maximum of the two
				memArray[indexA][indexB] = Math.max(x, y);
			}
		}

		return memArray[indexA][indexB];
	}

	private void initMemoization(int lengthA, int lengthB) {
		memArray = new int[lengthA+1][lengthB+1];
		
		for (int i = 0; i <= lengthA; ++i) {
			for (int j = 0; j <= lengthB; ++j) {
				memArray[i][j] = -1;
			}
		}
	}

	public void print(int answer) {
		System.out.println(Integer.toString(answer));
	}	
}
