import java.io.*;
import java.util.*;

public class Solution {

	public Solution() {}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Solution solution = new Solution();
		// The first integer of the input is the size of the array.
		int size = scanner.nextInt();
		// Create the array
		int[] array = new int[size];
		// Initialize the array
		int index = 0;
		while (scanner.hasNextInt()) {
			array[index++] = scanner.nextInt();
		}
		// Quicksort the array
		solution.quicksort(array, 0, array.length);
	}
	
	/**
	 * Continuously partition the given array in a
	 * divide and conquer fashion to sort the array.
	 *
	 * @param array - The array to sort.
	 * @param left - Pointer to where to start from.
	 * @param right - Pointer to where to stop.
	 */
	public void quicksort(int[] array, int left, int right) {
		// If we have at least two elements ...
		if (right - left >= 2) {
	 	   // Partition the array so all elements smaller
	 	   // than the pivot is moved to the left of it
	 	   // and all elements larger to the right of it
	 	   int p = partition(array, left, right);
	 	   // Call quicksort recursively on the sub array
	 	   // from the left most element up to the pivot
	 	   quicksort(array, left, p);
	 	   // Call quicksort recurively on the sub array
	 	   // from the pivot to the right most element
	 	   quicksort(array, (p + 1), right);
	 	   // Print the progress
	 	   print(array, left, right);
		}
	}
	
	
	/**
	 * Partition the array so that all elements smaller than
	 * the pivot element appears to the left of it, and all
	 * elements larger to the right of it.
	 *
	 * The relative positioning of the elments stay the same
	 * and only change in relation to the pivot element.
	 *
	 * Finally the new order is printed.
	 *
	 * @param array - The array to partition.
	 * @param left - A pointer to the beginning of the sub-array.
	 * @param right - A pointer to the end of the sub-array.
	 */
	public int partition(int[] array, int left, int right) {
		// The pivot is the first element of the sub-array,
		// which is given by the pointer "left"
		int pivot = array[left];
		// Create a List to keep elements smaller than the pivot
		List<Integer> smaller = new ArrayList<Integer>(array.length);
		// Create a list to keep elements larger than the pivot
		List<Integer> larger = new ArrayList<Integer>(array.length);
		// Iterate through the subarray and place the elements,
		// starting from the second element of the sub-array
		for (int i = left + 1; i < right; ++i) {
			// If the element is smaller than the pivot
			if (array[i] < pivot) {
				// place it in the smaller list
				smaller.add(array[i]);
			} else { // Otherwise place it in the larger list
				larger.add(array[i]); // which means if we have an element equal or larger than the pivot..
			}
		}
		// Update the array with the smaller elements to the left
		for (int i = 0; i < smaller.size(); ++i) {
			// Update the i'th element from the offset:
			// start index
			array[left + i] = smaller.get(i);
		}
		// Update the placement of the pivot
		array[left + smaller.size()] = pivot;
		// Update the array with the larger elements
		for (int i = 0; i < larger.size(); ++i) {
			// Update the i'th element from the offset:
			// start index + all elements smaller + the pivot
			array[left + smaller.size() + 1 + i] = larger.get(i);
		}
		// Return the new index of our pivot
		return left + smaller.size();
	}
	
	/**
	 * Helper method to print an integer array.
	 *
	 * @param array - The array to print.
	 */
	private void print(int[] array) {
		for (int element : array) {
			print(element);
		}
		System.out.println("");
	}
	
	/**
	 * Helper method to print part of an integer array.
	 *
	 * @param array - The array to print.
	 * @param left - The start pointer to where to start printing from.
	 * @param right - The end pointer to where to stop printing from.
	 */
	private void print(int[] array, int left, int right) {
		for (int i = left; i < right; ++i) {
			print(array[i]);
		}
		System.out.println("");
	}
	
	/**
	 * Helper method to print integers.
	 *
	 * @param element - The integer to print
	 */
	private void print(int element) {
		System.out.print(Integer.toString(element) + " ");
	}
}
