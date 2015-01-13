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
		solution.partition(array);
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
	 * @param array - The array to partition
	 */
	public void partition(int[] array) {
		// First select the pivot element
		int pivot = chosePivot(array);
		// Create a List to keep elements smaller than the pivot
		List<Integer> smaller = new ArrayList<Integer>(array.length);
		// Create a list to keep elements larger than the pivot
		List<Integer> larger = new ArrayList<Integer>(array.length);
		// Iterate through the list and place the elements in the
		// proper list, and we know the first element is the pivot
		// so start from the second element
		for (int i = 1; i < array.length; ++i) {
			// If the element is smaller than the pivot
			if (array[i] < pivot) {
				// place it in the smaller list
				smaller.add(array[i]);
			} else { // Otherwise place it in the larger list
				larger.add(array[i]); // which means if we have an element equal or larger than the pivot..
			}
		}
		// Print the smaller elements first
		for (int element : smaller) {
			print(element);
		}
		// Then print the pivot element
		print(pivot);
		// Last print all larger elements
		for (int element : larger) {
			print(element);
		}
		// And end it off with a new line...
		System.out.println("");
	}
	
	/**
	 * Returns the first element of the array according to
	 * the specification of this challenge.
	 *
	 * @param array - The array to chose a pivot element from.
	 * @return A pivot element to partition the array according to.
	 */
	private int chosePivot(int[] array) {
		return array[0];
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
