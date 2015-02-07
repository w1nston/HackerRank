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

		int testCases = scanner.nextInt();

		for (int testCase = 0; testCase < testCases; ++testCase) {

			int nrOfElements = scanner.nextInt();
			solution.initMemArray(2001);

			int capacity = scanner.nextInt();
			int[] elements = new int[nrOfElements];

			for (int i = 0; i < nrOfElements; ++i) {
				elements[i] = scanner.nextInt();
			}

			solution.calculateAnswer(elements, capacity);
		}
	}

	public void calculateAnswer(int[] elements, int capacity) {

		for (int weight = 1; weight <= capacity; ++weight) {
			for (int i = 0; i < elements.length; ++i) {
				evaluateElement(elements[i], weight);
			}
		}
		
		print(memArray[capacity]);
	}
	
	private void evaluateElement(int element, int weight) {
		if (element <= weight) {		
			memArray[weight] = Math.max(
				memArray[weight],
				(element + memArray[weight - element])
			);
		}
	}

	private void print(int element) {
		System.out.println(Integer.toString(element));
	}
	
	public void initMemArray(int size) {
		memArray = new int[size];
		for (int i = 0; i < size; ++i) {
			memArray[i] = 0;
		}
	}
}
