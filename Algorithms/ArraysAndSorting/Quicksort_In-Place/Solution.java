import java.io.*;
import java.util.*;

public class Solution {

	public Solution() {}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Solution solution = new Solution();
		// Get the size of the array to sort
		int size = scanner.nextInt();
		// Create the array to sort
		int[] array = new int[size];
		// Fill the array from input
		for (int i = 0; i < size; ++i) {
			array[i] = scanner.nextInt();
		}
		// Sort the array
		solution.quicksort(array);
	}
	
	/**
	 * Performs an in-place quicksort on the provided array.
	 *
	 * @param array - The array to sort.
	 */
	public void quicksort(int[] array) {
		// Call private helper method quicksort with pointers
		// to which part of the array to sort, which starts
		// with the entire array
		quicksort(array, 0, array.length - 1);
	}
	
	/**
	 * Helper method to sort a specific part of an array, between
	 * the elements at indices provided by start and end pointers.
	 *
	 * @param array - The array to sort.
	 * @param from - A start pointer to where in the array the sort should start from.
	 * @param to - An end pointer to where in the array the sort should go.
	 */
	private void quicksort(int[] array, int from, int to) {
		// If the elements to sort consist of at least one element
		if ((to - from) >= 1) {
			// Partition the array
			int pivotPosition = partition(array, from, to);
			// Print the array after it is partitioned
			print(array);
			// Recurisvely call self to sort from the left part
			// of the array, to the new pivot element
			quicksort(array, from, pivotPosition - 1);
			// Then recursively call self to sort from the right
			// of the pivot element and to the right end pointer
			quicksort(array, pivotPosition + 1, to);
		}
	}
	
	/**
	 * Move all elements smaller than the chosen pivot element
	 * to the left of the pivot, and keep all elements larger
	 * than the pivot element in place, and then move the pivot
	 * to its new location, and return the index of the new
	 * pivot placement.
	 *
	 * @param array - The array to partition.
	 * @param from - The index to start partition from.
	 * @param to - The index to stop partition at.
	 */
	private int partition(int[] array, int from, int to) {
		// Always select the pivot element to be the right-most element of the sub-array
		int pivot = array[to];
		// Create an index from which to put smaller elements to the
		// left side of and in the end put the pivot element at
		int wallIndex = from;
		// Iterate over all elements from the beginning of, to the end of
		// the sub-array
		for (int currentIndex = from; currentIndex < to; ++currentIndex) {
			// If an element is smaller than the pivot is should be swapped
			// to the left side of the wall
			if (array[currentIndex] < pivot) {
				// Swap the current element with the element at position
				// of the wall
				swap(array, wallIndex, currentIndex);
				// And move the index of the wall one step towards the right
				++wallIndex;
			}
		}
		// Finally swap the pivot element to the place of the wall,
		// i.e. in the middle of the elements smaller than, and larger 
		// than the current pivot element
		swap(array, wallIndex, to);
		// And return the new pivot index
		return wallIndex;
	}
	
	/**
	 * Swap two elements in an array.
	 *
	 * @param array - The array to swap elements in.
	 * @param indexA - The index of the first element to swap.
	 * @param indexB - The index of the second element to swap with.
	 */
	private void swap(int[] array, int indexA, int indexB) {
		int temp = array[indexA];
		array[indexA] = array[indexB];
		array[indexB] = temp;
	}
	
	/**
	 * Helper method to print the elements of an array.
	 *
	 * @param array - The array to print to System.out.
	 */
	private void print(int[] array) {
		for (int element : array) {
			print(element);
		}
		System.out.println("");
	}
	
	/**
	 * Overloaded helper method to print a single integer.
	 *
	 * @param element - The integer to print to System.out.
	 */
	private void print(int element) {
		System.out.print(Integer.toString(element) + " ");
	}
}
