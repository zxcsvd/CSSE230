import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * This class implements a Binary Search Tree. 
 *
 * @author wollowsk.
 */

public class BinarySearchTree <T extends Comparable<? super T>> implements Iterable<BinarySearchTree<T>.BinaryNode> {

	private BinaryNode root;
	private BinaryNode result;
	public BinarySearchTree() {
		this.root = null;
	}
	
	public String myName(){
		return "<Bo Peng>: "; 
	}
	
	/** 
	 * This method takes a forest, i.e. an ArrayList of BinarySearchTrees and 
	 * returns a single tree consisting of all the elements in the forest.
	 * 
	 * @return A single, merged tree. 
	 */
	public void makeTree(ArrayList<BinarySearchTree<T>> forest){
		for(BinarySearchTree<T> tree: forest){
			Iterator it = tree.iterator();
			while(it.hasNext()){
				BinaryNode temp = (BinarySearchTree<T>.BinaryNode) it.next();
				this.root.insert(temp.element);
			}
		}
		
	}
	
	
//	public void add(T element){
//		
//		if(this.root.element.compareTo(element) > 0){
//			
//		}
//		
//	}
	/** 
	 * This method determines the largest complete subtree in a given tree.
	 * By complete, we mean that the length of each path form a given node to
	 * a leaf is the same.
	 * By largest, we mean the subtree with the most number of nodes.
	 * The largest complete subtree in the following tree is rooted on 30.
	 * 
	 *        		  50
     *				/    \
  	 *			  30       60
 	 *			 /  \     /  \ 
	 *		    5   20   55    70
     *         				     \
     *       				     80
	 * 
	 * @return The node at the root of the largest subtree. 
	 */
	public BinaryNode largestCompleteSubTree(){
		this.result = null;
		if(this.root == null){
			return null;
		}
		findComplete(this.root);
		return result;
	}
	
	private BinaryNode findComplete(BinaryNode node){
		BinaryNode left = node.leftChild;
		BinaryNode right = node.rightChild;
		if(node.leftChild!=null){
			left = findComplete(node.leftChild);
		}
		if(node.rightChild!=null){
			right = findComplete(node.rightChild);
		}
		if(left == null && right == null){
			if(this.result == null){
				this.result = node;
			}
		}else{
			if(left!=null && right!=null && left.size() == right.size()){
				if (left.complete && right.complete){
					node.complete = true;
					if(node.size() >= this.result.size()){
						this.result = node;
					}
				}
				else{
					node.complete = false;
				}
			}
			else{
				node.complete = false;
			}
		}
		return node;
	}
	
	public boolean insert(T element) {
		if (element == null) {
			throw new NullPointerException("Cannot insert null element into binary search tree");
		} else if (root == null) {
			root = new BinaryNode(element);
			return true;
		} else {
			root.insert(element);
			return true;
		}
	}

//	// This method prints out the BST in pre-order.
//	public String toString() {
//		if (root == null) return "";
//		return root.toString();
//	}
	
	public Iterator<BinarySearchTree<T>.BinaryNode> iterator(){
		return new TreeIterator();
	}

public class BinaryNode {
		public boolean complete = true;
	    private T element;  
	    private BinaryNode leftChild;
	    private BinaryNode rightChild;
	    
	    public BinaryNode(T e) {  
			element = e;
			leftChild = rightChild = null;
	    }
	    
	    public Object getElement(){
	    	return this.element;
	    }
	    
	    public void insert(T element) {
	    	BinaryNode temp = root;
	    	while (true) {
		 		if (temp.element.compareTo(element) > 0) {
					if (temp.leftChild == null) {
						temp.leftChild = new BinaryNode(element);
						return;
					} else {
						temp = temp.leftChild;
					}
				} else {
					if (temp.rightChild == null) {
						temp.rightChild = new BinaryNode(element);
						return;
					} else {
						temp = temp.rightChild;
					}
				}
	    	}
		}
	    
//	    public String toString() {
//	    	String s = "[" + element + " " +
//	    	 			((leftChild == null)? null : leftChild.element)+ " " +
//	    	 			((rightChild == null)? null : rightChild.element)+	
//	    	 			"]\n";
//	    	if (leftChild != null) {
//	    		s += leftChild.toString();
//	    	}
//	       	if (rightChild != null) {
//	       		s += rightChild.toString();
//	    	}	
//	       	return s;
//	    }
	    
		public ArrayList<BinaryNode> getLeftAncestors(){
			ArrayList<BinaryNode> temp = new ArrayList<BinaryNode>();
			BinaryNode ptr = this;
			while (ptr != null) {
				temp.add(0,ptr);
				ptr = ptr.leftChild;
			}
			return temp;
		}
	
		
		public int size(){
			int size = 1;
			if (leftChild != null){
				size += leftChild.size();
			}
			if (rightChild != null){
				size += rightChild.size();
			} 
			return size;
		}

}

// This is an in-order iterator.
public class TreeIterator implements Iterator<BinarySearchTree<T>.BinaryNode> {
	private ArrayList<BinaryNode> nodes;

	public TreeIterator(){
		nodes = new ArrayList<BinaryNode>();
		if (root != null){
			nodes.addAll(root.getLeftAncestors());
		}
	}
	public boolean hasNext() {
		return !nodes.isEmpty();
	}

	public BinaryNode next() {
		if (!hasNext()) throw new NoSuchElementException();
		BinaryNode temp = nodes.remove(0);
		if (temp.rightChild != null){
			nodes.addAll(0, temp.rightChild.getLeftAncestors());
		}
		return temp;
	}

	public void remove() {
	}
}

}
