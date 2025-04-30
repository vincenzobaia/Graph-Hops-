package template;

import java.util.*;

public class Graph {

	private List<GraphNode> nodeList = new ArrayList<>();

	public Boolean addNode(GraphNode node) {
		GraphNode currentNode = getNode(node.getValue());
		if (currentNode != null) {
			return false;
		} else { 
		nodeList.add(node);
		return true;
		}

		// if it doesn't exist in the of nodes already, the graph, create it
		// --> a node has been supplied, add it to the nodeList ( the graph!)
		
		//otherwise the node is there, and cannot be added.
	}

	public Boolean addEdge(GraphNode fromNode, GraphNode toNode, Integer weight) {
		GraphNode currentFromNode = getNode(fromNode.getValue());
		GraphNode currentToNode = getNode(toNode.getValue());

		if (currentFromNode == null || currentToNode == null) {
			return false;
		}

		currentFromNode.addNeighbor(currentToNode, weight);
		return true;
		//get the source and target nodes from the existing graph
		
		//if either dont exist, cant make the edge
		
		//otherwise ( they both exist ) add neighbor & weight 
	}

	/**
	 * In my implementation of isReachable I put a Set of nodes to represent places
	 * already reached. Modifying that algorithm to use a map, and keep an
	 * ever incrementing number along with the number that says how far away is an
	 * approach.
	 * 
	 * All the nodes I can get to from the start are 1 away. All the nodes I can get
	 * to from N away are N+1 away If I find a node the second time, I dont add it;
	 * once I get there, the shortest path to the destination has X hops; if I got
	 * there in N and N+1 hops, N + X will be the smallest number of hops
	 * 
	 */
	public int fewestHops(GraphNode fromNode, GraphNode toNode) {

		GraphNode targetfromNode = getNode(fromNode.getValue());
		GraphNode targetToNode = getNode(toNode.getValue());

		Queue<GraphNode> queue = new LinkedList<>();
		Set<GraphNode> visitedNodes = new HashSet<>();

		queue.add(targetfromNode);
		int hops = 0;

		while(queue.peek() != null){
			GraphNode thisNode = queue.poll();
			for (GraphNode thisNeighbor : thisNode.getNeighbors()) {
				if(visitedNodes.add(thisNeighbor)){
					queue.add(thisNeighbor);
					hops++;
				}
				if (visitedNodes.contains(targetToNode)){
					System.out.println("Here is the number of hops: " + hops);
					return hops;
				}
			}
		}
		System.out.println("No path found");
		return -1;
	}

	public GraphNode getNode(String nodeValue) {
		for (GraphNode thisNode : nodeList) {
			if (thisNode.getValue().equals(nodeValue))
				return thisNode;
		}
		return null;
	}

	public List<GraphNode> getNodes() {
		return nodeList;
	}

}
