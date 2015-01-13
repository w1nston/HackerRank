import java.io.*;
import java.util.*;

public class Solution {
	
	// Create an array to use for memoization,
	// memArray[i] contains the number closest to
	// the capacity using the numbers up to i
	private int[] memArray;

	public Solution() {}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Solution solution = new Solution();
		// Store the number of test cases
		int testCases = scanner.nextInt();
		// Iterate over all test cases
		for (int testCase = 0; testCase < testCases; ++testCase) {
			// Store n (number of elements)
			int nrOfElements = scanner.nextInt();
			// Initialize the memoization array for every new test case 
			// with the maximum amount of elements possible plus one to
			// account for an extra weight of 0
			solution.initMemArray(2001);
			// Store k (capacity of the knapsack)
			int capacity = scanner.nextInt();
			// Create the array of all elements
			int[] elements = new int[nrOfElements];
			// Initialize the array with the elements
			for (int i = 0; i < nrOfElements; ++i) {
				elements[i] = scanner.nextInt();
			}
			// Calculate the answer by filling out the memoization array.
			solution.calculateAnswer(elements, capacity);
		}
	}
	
	/**
	 * Calculate the answer by filling out the memoization array.
	 *
	 * @param elements - The array to use for values to the calculation.
	 * @param capacity - The maximum capacity of the knapsack.
	 */
	public void calculateAnswer(int[] elements, int capacity) {
		// Iterate over all possible weight capacities smaller than or equal to the maximum capacity
		for (int weight = 1; weight <= capacity; ++weight) {
			// For each weight iterate over all the elements in the array
			for (int i = 0; i < elements.length; ++i) {
				// If this element doesn't exceed the current weight capacity
				if (elements[i] <= weight) {		
					// Store the maximum value at the position of the current weight capacity of either ...
					memArray[weight] = Math.max(
						memArray[weight], // ... not using the element at this position
						(elements[i] + memArray[weight - elements[i]]) // ... or using the element at this position
						// i.e. use this element plus count the value at the weight without using this element
					);
				}
			}
		}
		// Print the value of the elements possible to use
		// at size capacity in the memoization array
		print(memArray[capacity]);
	}
	
	/**
	 * Helper method to print an integer with a following new line.
	 *
	 * @param element - The integer to print.
	 */
	private void print(int element) {
		System.out.println(Integer.toString(element));
	}
	
	/**
	 * Helper method to initialize the memoization
	 * array and set all its elements to 0.
	 *
	 * @param size - The size to set the memoization array to.
	 */
	public void initMemArray(int size) {
		memArray = new int[size];
		for (int i = 0; i < size; ++i) {
			memArray[i] = 0;
		}
	}
}
