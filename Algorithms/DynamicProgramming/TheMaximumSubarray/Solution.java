import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Solution solution = new Solution();

		int testCases = scanner.nextInt();

		for (int testCase = 0; testCase < testCases; ++testCase) {

			int elements = scanner.nextInt();
			int[] array = new int[elements];

			for (int i = 0; i < elements; ++i) {
				array[i] = scanner.nextInt();
			}

			int contiguousSum = solution.highestContiguosSum(array);
			int nonContiguousSum = solution.highestNonContiguousSum(array);

			solution.print(contiguousSum, nonContiguousSum);
		}
	}
	
	/**
	 * Using Kadane's algorithm to find the highest contiguous sum,
	 * accounting for situations where all elements might be negative.
	 */
	public int highestContiguosSum(int[] array) {
		int maxEndingHere = array[0];
		int maxSoFar = array[0];

		for (int i = 1; i < array.length; ++i) {
			maxEndingHere = Math.max(array[i], maxEndingHere + array[i]);
			maxSoFar = Math.max(maxSoFar, maxEndingHere);
		}

		return maxSoFar;
	}
	
	public int highestNonContiguousSum(int[] array) {
		int sum = 0;
		int smallestNegative = Integer.MIN_VALUE;

		for (int element : array) {
			if (element >= 0) {
				sum += element;
			} else {
				smallestNegative = Math.max(smallestNegative, element);
			}
		}

		int result = 0;

		if (sum == 0 && smallestNegative > Integer.MIN_VALUE) {
			result = smallestNegative;
		} else {
			result = sum;
		}
		
		return result;
	}

	public void print(int element1, int element2) {
		print(element1);
		print(element2);
		System.out.println("");
	}

	private void print(int element) {
		System.out.print(Integer.toString(element) + " ");
	}
}