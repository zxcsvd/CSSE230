import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Graph {
	public HashMap<String, Node> nodes;

	public Graph(){
		nodes = new HashMap<String, Node>();
	}

	/** 
	 * A graph is connected, if you can get from every node to every other node. 
	 * A slightly simpler way of looking at this is that you can get from node 1
	 * to node 2, from node 2 to node 3, from node 3 to node 4 etc. For this problem
	 * you may assume that you get a bi-direction graph.
	 * 
	 * @return This method returns true if the given graph is connected and false 
	 * 		   otherwise. 
	 */
	public boolean isConnected(){
		HashSet<String> visitedNodes = new HashSet<String>();
		for(String key: this.nodes.keySet()){
			Node temp = nodes.get(key);
			ArrayList<Edge> edges = temp.neighbors;
			for(Edge edge: edges){
				if(!visitedNodes.contains(edge.otherNode.element)){
					visitedNodes.add((String) edge.otherNode.element.toString());
				}
				
			}
		}
		for(String key: this.nodes.keySet()){
			if(!visitedNodes.contains(key)){
				return false;
			}
		}
		return true;
	}
//	public ArrayList<Edge> someGraphAlgorithm(Graph g){
//		LinkedList<Edge> result = new LinkedList<Edge>();
//		HashSet<String> visitedNodes = new HashSet<String>();
//		Iterator i = g.getNodeIterator();
//		Node initialNode = i.next();
//		visitedNodes.add(initialNode.getName());
//		PriorityQueue<Edge> edges = new PriorityQueue<Edge>();
//		edges.addAll(initialNode.getEdges());
//		while (i.hasNext()) {
//		edges.addAll(i.next().getEdges());
//		}
//		while (!edges.isEmpty()){
//		Edge e = edges.remove();
//		if (!visitedNodes.contains(e.getNeighbor())){
//		result.add(e);
//		visitedNodes.add(e.getNeighbor());
//		}
//		}
//		return result;
//	}
	
	public boolean addNode(String key, Object element) {
		this.nodes.put(key, new Node(element));
		return true;
	}
	
	public boolean addEdge(String key1, String key2, int cost) {
		if (!nodes.keySet().contains(key1) || !nodes.keySet().contains(key2)) return false;
		nodes.get(key1).addEdge(nodes.get(key2), cost);
	    return true;
	}
	

	public class Node {
		public Object element;
		public ArrayList<Edge> neighbors;
		
		public Node(Object element){
			this.element = element;
			neighbors = new ArrayList<Edge>();
		}
		
		public void addEdge(Node n, int cost) {
			neighbors.add(new Edge(n, cost));
		}
		
	}
	
	public class Edge {
		public Node otherNode;
		private int cost;
		
		public Edge(Node n, int c){
			otherNode = n;
			cost = c;
		}
	}
	

}
