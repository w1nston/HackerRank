import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Solution solution = new Solution();
		// Get how many test cases there are
		int testCases = scanner.nextInt();
		// Go through each test case
		for (int i = 0; i < testCases; ++i) {
			// Get how many elements there are
			int elements = scanner.nextInt();
			// And create an array to store them in
			int[] array = new int[elements];
			// Add the elements to the array ...
			for (int j = 0; j < elements; ++j) {
				array[j] = scanner.nextInt();
			}
			// Calculate the solution for a contiguous sum
			int contiguousSum = solution.highestContiguosSum(array);
			// Calculate the solution for a non-contiguous sum
			int nonContiguousSum = solution.highestNonContiguousSum(array);
			// Print the solution
			solution.print(contiguousSum, nonContiguousSum);
		}
	}
	
	/**
	 * Use Kadane's algorithm to find the highest contiguous sum,
	 * accounting for situations where all elements might be negative.
	 *
	 * @param array - The array to search for the maximum sum.
	 * @return The highest contiguous sum of this array.
	 */
	public int highestContiguosSum(int[] array) {
		// Create an index to keep track of the maximum
		// value when we stop at the current index
		int maxEndingHere = array[0];
		// Create a variable to store the max value so far
		// and initially set it to the first element
		int maxSoFar = array[0];
		// Iterate through the entire array
		for (int i = 1; i < array.length; ++i) {
			// Set the max ending value to the highest of either stopping
			// right now, or adding the next element as well
			maxEndingHere = Math.max(array[i], maxEndingHere + array[i]);
			// Then set the maxium value encountered so far to the maximum
			// of stopping or if the current value is higher save that
			maxSoFar = Math.max(maxSoFar, maxEndingHere);
		}
		// Return the highest sum obtained
		return maxSoFar;
	}
	
	/**
	 * Find the highest non-contiguous sum in
	 * the provided array and return it.
	 *
	 * @param array - The array to search for the maximum sum.
	 * @return The highest non-contiguous sum for this array.
	 */
	public int highestNonContiguousSum(int[] array) {
		// Create a variable to keep track of the sum
		int sum = 0;
		// And a varaiable to keep track of the smallest
		// negative value in the array
		int smallestNegative = Integer.MIN_VALUE;
		// Iterate through the array
		for (int element : array) {
			// Only if the element is positive
			if (element >= 0) {
				// increase the sum with the value of the current element
				sum += element;
			} else { // Otherwise
				// Keep the smallest negative value so far, i.e. either
				// keep the current or add this element if it is smaller
				smallestNegative = Math.max(smallestNegative, element);
			}
		}
		// Create a result variable to determine the result
		int result = 0;
		// If only have negative integers in the array
		if (sum == 0 && smallestNegative > Integer.MIN_VALUE) {
			// Set the result to the smallest negative value we have
			result = smallestNegative;
		} else { // Otherwise just return the sum
			result = sum;
		}
		return result;
	}
	
	/**
	 * Helper method to print two integers.
	 *
	 * @param element1 - The first element to print.
	 * @param element2 - The second element to print.
	 */
	public void print(int element1, int element2) {
		print(element1);
		print(element2);
		System.out.println("");
	}
	
	/**
	 * Helper method to print an integer.
	 *
	 * @param element - The integer to print to System.out
	 */
	private void print(int element) {
		System.out.print(Integer.toString(element) + " ");
	}
}