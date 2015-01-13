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
		// Partition the array
		solution.partition(array, 0, array.length);
		// Print the partition
		solution.print(array, 0, array.length);
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
	 * Helper method to print part of an integer array.
	 *
	 * @param array - The array to print.
	 * @param left - The start pointer to where to start printing from.
	 * @param right - The end pointer to where to stop printing from.
	 */
	public void print(int[] array, int left, int right) {
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
