import java.io.*;
import java.util.*;

public class Solution {

	private boolean[] isVisited;
	
	public Solution(int nrOfAstronauts) {
		isVisited = new boolean[nrOfAstronauts];

		for (int i = 0; i < isVisited.length; ++i) {
			isVisited[i] = false;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int nrOfAstronauts = scanner.nextInt();
		int nrOfPairs = scanner.nextInt();

		Solution solution = new Solution(nrOfAstronauts);

		// Create an adjency list representation of a graph, each key represents an astronaut
		// and the value for each astronaut (or vertex) in the graph is an adjency list containing
		// all other astronauts that the current astronaut (or key) is connected to, or is from
		// the same country as.
		HashMap<Integer, ArrayList<Integer>> graph = new HashMap<Integer, ArrayList<Integer>>(nrOfAstronauts);

		for (int i = 0; i < nrOfPairs; ++i) {
			int astronautA = scanner.nextInt();
			int astronautB = scanner.nextInt();
			solution.addEdge(graph, astronautA, astronautB);
		}
		
		// Perform a depth first search to find how many connected components
		// there are, calculate the amount of combinations there are to 
		// select two astronauts from different countries, and print the answer
		System.out.println(Long.toString(solution.dfs(graph)));
	}
	
	public long dfs(HashMap<Integer, ArrayList<Integer>> graph) {

		HashMap<Integer, Integer> connectedComponents = new HashMap<Integer, Integer>();
		Iterator<Integer> vertexIterator = graph.keySet().iterator();

		while (vertexIterator.hasNext()) {
			int currentVertex = vertexIterator.next();
			
			if (!isVisited[currentVertex]) {
				isVisited[currentVertex] = true;
				findConnectedComponents(graph, currentVertex, connectedComponents);
			}
		}
		
		long nrOfAstronauts = (long)isVisited.length;
		long totalCombinations = (nrOfAstronauts * (nrOfAstronauts - 1)) / 2;
		long sameCombinations = 0;

		for (int vertexCount : connectedComponents.values()) {
			sameCombinations += (vertexCount * (vertexCount - 1)) / 2;
		}

		// To get the number of combinations of how to choose two astronauts
		// from two different countries subtract how many combinations there
		// are to select two astronauts from the same country with how many
		// combinations there are to select two astronauts out of the total
		// amount of astronauts. 
		// I.e.: (N choose 2) - (Sum(C_i choose 2) for i = 1 to C), where
		// N is the total amount of astronauts, 
		// C_i is the number of of astronauts of the connected component (country) i
		// C is the total amount of connected components
		return totalCombinations - sameCombinations;
	}
	
	private void findConnectedComponents(HashMap<Integer, ArrayList<Integer>> graph, 
											int startVertex, HashMap<Integer, Integer> connectedComponents) {
		Stack<Integer> stack = new Stack<Integer>();
		
		// Use the vertex as a key in the connectedComponents map,
		// since this is the first time we add this component put the key
		// with a count of one vertex
		connectedComponents.put(startVertex, 1);
		
		stack.push(startVertex);
		
		while (!stack.isEmpty()) {
			int vertex = stack.pop();

			ArrayList<Integer> adjacentVertices = graph.get(vertex);
			
			for (int v : adjacentVertices) {
				if (!isVisited[v]) {
					isVisited[v] = true;
					int count = connectedComponents.get(startVertex);
					connectedComponents.put(startVertex, ++count);
					stack.push(v);
				}
			}
			
		}
	}
	
	public void addEdge(HashMap<Integer, ArrayList<Integer>> graph, int vertex1, int vertex2) {
		updateAdjencyList(graph, vertex1, vertex2);
		updateAdjencyList(graph, vertex2, vertex1);
	}
	
	private void updateAdjencyList(HashMap<Integer, ArrayList<Integer>> graph, int vertex1, int vertex2) {
		ArrayList<Integer> adjencyList = graph.get(vertex1);
		
		if (adjencyList == null) {
			adjencyList = new ArrayList<Integer>();
		}
		
		adjencyList.add(vertex2);
		graph.put(vertex1, adjencyList);
	}
	
}
