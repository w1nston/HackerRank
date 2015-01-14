import java.io.*;
import java.util.*;

public class Solution {

	// Create an array to keep track of which vertices that have been visited
	private boolean[] isVisited;
	
	public Solution(int nrOfAstronauts) {
		// Initialize the size to be equal to the number of astronauts
		isVisited = new boolean[nrOfAstronauts];
		// Initialize the isVisited array to not have visited any nodes yet
		for (int i = 0; i < isVisited.length; ++i) {
			isVisited[i] = false;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		// Save the number of astronauts
		int nrOfAstronauts = scanner.nextInt();
		// Save the number of pairs
		int nrOfPairs = scanner.nextInt();
		// Create a new instance of the solution with amount of astronauts
		Solution solution = new Solution(nrOfAstronauts);
		// Create an adjency list representation of a graph, each key represents an astronaut
		// and the value for each astronaut (or vertex) in the graph is an adjency list containing
		// all other astronauts that the current astronaut (or key) is connected to, or is from
		// the same country as.
		HashMap<Integer, ArrayList<Integer>> graph = new HashMap<Integer, ArrayList<Integer>>(nrOfAstronauts);
		// Iterate over all pairs of astronauts and add them to the list
		for (int i = 0; i < nrOfPairs; ++i) {
			// Save the first astronaut
			int a = scanner.nextInt();
			// Save the second astronaut
			int b = scanner.nextInt();
			// Add a new edge to the graph
			solution.addEdge(graph, a, b);
		}
		// Perform a depth first search to find how many connected components
		// there are, calculate the amount of combinations there are to 
		// select two astronauts from different countries, and print the answer
		System.out.println(Long.toString(solution.dfs(graph)));
	}
	
	
	/**
	 * Performs a depth first search to find how many connected components there
	 * exist in this graph, and then calculates how many combinations there are
	 * to chose two astronauts from different countries.
	 *
	 * @param graph - The graph to search depth first.
	 * @return The number of combinations of how to choose two vertices from different connected components.
	 */
	public long dfs(HashMap<Integer, ArrayList<Integer>> graph) {
		// Create a HashMap to store the currentComponent with its count of vertices
		HashMap<Integer, Integer> connectedComponents = new HashMap<Integer, Integer>();
		// Obtain an iterator for the keys (vertices) of the graph
		Iterator<Integer> it = graph.keySet().iterator();
		// Iterate over all vertices
		while (it.hasNext()) {
			// Get the current vertex to start using as root in a dfs
			int currentVertex = it.next();
			// Create a stack to use for the DFS
			Stack<Integer> stack = new Stack<Integer>();
			// Check if it visited or not
			if (!isVisited[currentVertex]) {
				// Set the vertex to visited
				isVisited[currentVertex] = true;
				// Use the currentVertex as a key in the connectedComponents map,
				// since this is the first time we add this component simply put
				// the key with a count of one vertex
				connectedComponents.put(currentVertex, 1);
				// Push this "root" vertex to the stack
				stack.push(currentVertex);
				// And find all connected components
				while (!stack.isEmpty()) {
					// Pop next available vertex from the stack
					int vertex = stack.pop();
					// Get all adjacent vertices and iterate over them
					ArrayList<Integer> adjacentVertices = graph.get(vertex);
					for (int v : adjacentVertices) {
						// If we haven't visited the vertex already
						if (!isVisited[v]) {
							// Mark it as visited
							isVisited[v] = true;
							// Update the counter for the currentVertex
							int count = connectedComponents.get(currentVertex);
							connectedComponents.put(currentVertex, ++count);
							// Push this vertex on the stack to check its adjacent vertices later on
							stack.push(v);
						}
					}
					
				}
			}
		}
		// Note: (N choose 2) == (n * (n - 1) / 2)
		// The amount of astronauts are equal to the number of vertices in the graph
		long nrOfAstronauts = (long)isVisited.length;
		// Number of ways to chose two astronauts from N astronauts
		long totalCombinations = (nrOfAstronauts * (nrOfAstronauts - 1)) / 2;
		// Create a variable to keep track of how many different combinations
		// there are to select two astronauts from the same connected component
		long sameCombinations = 0;
		// Iterate over all connected components
		for (int vertexCount : connectedComponents.values()) {
			// Sum the number of combinations that are possible when
			// chosing two astronauts from the number of vertices at
			// the current vertexCount
			sameCombinations += (vertexCount * (vertexCount - 1)) / 2;
		}
		// To get the number of combinations of how to choose two astronauts
		// from two different countries subtract how many combinations there
		// are to select two astronauts from the same country with how many
		// combinations there are to select two astronauts out of the total
		// amount of astronauts. 
		// I.e.: (N choose 2) - (Sum(C_i choose 2) for i = 1 to C) where
		// N is the total amount of astronauts, 
		// C_i is the number of of astronauts of the connected component (country) i
		// C is the total amount of connected components
		return totalCombinations - sameCombinations;
	}
	
	/**
	 * Add an edge to the provided graph between the provided vertices.
	 *
	 * @param graph - The graph representation.
	 * @param vertex1 - The first vertex of the edge to create.
	 * @param vertex2 - The second vertex of the edge to create.
	 */
	public void addEdge(HashMap<Integer, ArrayList<Integer>> graph, int vertex1, int vertex2) {
		// Update the adjency list for vertex 1
		updateAdjencyList(graph, vertex1, vertex2);
		// Update the adjency list for vertex 2
		updateAdjencyList(graph, vertex2, vertex1);
	}
	
	
	/**
	 * Add the second vertex to the first vertex' adjency list.
	 *
	 * @param graph - The graph representation.
	 * @param vertex1 - The vertex whos adjency list to update.
	 * @param vertex2 - The vertex to add to the adjency list with.
	 */
	private void updateAdjencyList(HashMap<Integer, ArrayList<Integer>> graph, int vertex1, int vertex2) {
		// Create the adjency list to connect with vertex1
		ArrayList<Integer> adjencyList = graph.get(vertex1);
		// If the adjency list has not yet been created
		if (adjencyList == null) {
			// Create a new one
			adjencyList = new ArrayList<Integer>();
		}
		// Then add vertex2 to that list
		adjencyList.add(vertex2);
		// And update the graph with this new adjency list
		graph.put(vertex1, adjencyList);
	}
	
}
