import java.io.*;
import java.util.*;

public class Solution {

	public Solution() {}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Solution solution = new Solution();

		int size = scanner.nextInt();
		int[] array = new int[size];
		
		int index = 0;
		while (scanner.hasNextInt()) {
			array[index++] = scanner.nextInt();
		}

		solution.partition(array, 0, array.length);
		solution.print(array, 0, array.length);
	}
	
	public int partition(int[] array, int left, int right) {
		
		int pivot = array[left];
		
		List<Integer> smallerElements = new ArrayList<Integer>(array.length);
		List<Integer> largerElements = new ArrayList<Integer>(array.length);

		for (int i = left + 1; i < right; ++i) {
			if (array[i] < pivot) {
				smallerElements.add(array[i]);
			} else {
				largerElements.add(array[i]);
			}
		}
		
		for (int i = 0; i < smallerElements.size(); ++i) {
			array[left + i] = smallerElements.get(i);
		}

		array[left + smallerElements.size()] = pivot;

		for (int i = 0; i < largerElements.size(); ++i) {
			array[left + smallerElements.size() + 1 + i] = largerElements.get(i);
		}

		return left + smallerElements.size();
	}

	public void print(int[] array, int left, int right) {
		for (int i = left; i < right; ++i) {
			print(array[i]);
		}
		System.out.println("");
	}

	private void print(int element) {
		System.out.print(Integer.toString(element) + " ");
	}
}
