import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

/**
 * This class implements a Binary Search Tree. 
 *
 * @author PLEASE PUT YOUR NAME HERE.
 */

public class BinarySearchTree <T extends Comparable<? super T>> implements Iterable<T>{

	private BinaryNode root;
	private int size;
	
	public BinarySearchTree() {
		root = null;	
		size = 0;
	}
	
	/** 
	 * This method determines the difference between the value of the largest and 
	 * the smallest element in a BST. It assumes that the tree only contains Integer
	 * values.
	 * 
	 * This method runs in linear time.
	 * 
	 * @return This method returns the difference between the value of the largest 
	 * and the smallest element in the tree. If the tree is empty, it returns -1.
	 */
	public Integer range(){
		if(this.root == null){
			return -1;
		}
		
		return (int) iterMax(this.root)- (int)iterMin(this.root);
	}
	
	public T iterMax(BinaryNode b){
		BinaryNode temp = b;
		while(temp.rightChild!=null){
			temp = temp.rightChild;
		}
		return temp.element;
	}
	
	public T iterMin(BinaryNode b){
		BinaryNode temp = b;
		while(temp.leftChild!=null){
			temp = temp.leftChild;
		}
		return temp.element;
	}
	
	
	/** 
	 * This method determines the kth element in the BST. K is one based, i.e. the
	 * 1st element is the smallest element in the tree.
	 * If k is negative, zero or larger than the size of the BST, the method will 
	 * throw an IllegalArgument exception.
	 * 
	 * This method runs in k time.
	 * 
	 * @return This method returns the kth element in the BST.
	 */
	public T kthElement(int k){
		if(k <= 0 || k>this.size){
			throw new IllegalArgumentException();
		}
		Iterator<T> it = this.iterator();
		if(it.hasNext()){
			T result = null;
			for(int i=1; i<=k; i++){
				result = it.next();
			}
			return result;
		}else{
			throw new IllegalArgumentException();
		}

	}

	/** 
	 * This method makes the current BST complete by replacing null nodes with nodes that 
	 * contain the value of their parent node until the depths of all paths from the root 
	 * to a leaf are the same.
	 * For example, the following tree would be made complete as shown on the right:
	 * 
	 * 			10							10
	 * 			  \				=>		   /  \
	 * 			   20					 10    20
	 *
	 * The following example is a bit more elaborate. Notice that the left child of node 20
	 * get the value 20:
	 * 
	 * 			10							 10
	 * 			  \				=>		   /    \
	 * 			   20					 10      20
	 * 				 \ 					/  \    /  \
	 * 				  30			   10  10  20  30
	 * 
	 * This method runs in linear time.
	 */
	public void makeComplete(){
		//this.root.CalHeight();
		if(this.root!= null){
			int height = this.root.height();
			this.root.leftChild = checkLeft(this.root, height);
			this.root.rightChild = checkRight(this.root, height);
		}
		else{
			
		}
		
		
		
	}
	
	public BinaryNode checkLeft(BinaryNode b, int height){
		
		height--;
		
		BinaryNode temp = new BinaryNode(b.element);
		if(b.leftChild != null && height >= 0){
			//temp.rightChild.height = height - 1;
			temp.element = b.leftChild.element;
			temp.leftChild = checkLeft(b.leftChild, height);
			temp.rightChild = checkRight(b.leftChild, height);
		}
		
		if(b.leftChild==null  && height >= 0){
			
			temp.leftChild = checkLeft(temp, height);
			temp.rightChild = checkRight(temp, height);
		}
		if(height == -1){
			return null;
		}
//		if(b.leftChild == null && b.rightChild == null && height != 0){
//			temp.leftChild = checkLeft(b.leftChild);
//			temp.rightChild = checkRight(b.leftChild);
//		}
		return temp;
	}
	
	public BinaryNode checkRight(BinaryNode b, int height){
		height = height - 1 ;
		BinaryNode temp = new BinaryNode(b.element);
		if(b.rightChild == null && height >= 0){
			
			temp.leftChild = checkLeft(temp, height);
			temp.rightChild = checkRight(temp, height);
		}
		if(height == -1){
			return null;
		}
		if(b.rightChild != null && height >= 0){
			//temp.rightChild.height = height - 1;
			temp.element = b.rightChild.element;
			temp.leftChild = checkLeft(b.rightChild, height);
			temp.rightChild = checkRight(b.rightChild, height);
		}
		return temp;
		
	}
	
	
	public boolean insert(T element) {
		if (element == null) {
			throw new NullPointerException("Cannot insert null element into binary search tree");
		} else if (root == null) {
			root = new BinaryNode(element);
			size++;
			return true;
		} else {
			return root.insert(element);
		}
	}

	// This method prints out the BST in pre-order.
	public String toString() {
		if (root == null) return "";
		return root.toString();
	}
	
	public Iterator<T> iterator(){
		return new TreeIterator();
	}
	
	public int height(){
		if (root == null) return -1;
		return root.height();
	}

private class BinaryNode {
		
	    private T element;  
	    private BinaryNode leftChild;
	    private BinaryNode rightChild;
	    public int height = 0;
	    
	    public BinaryNode(T e) {  
			element = e;
			leftChild = rightChild = null;
	    }
	    
	    public boolean insert(T element2) {
	 		if (element.compareTo(element2) > 0) {
				if (leftChild == null) {
					leftChild = new BinaryNode(element2);
					size++;
					return true;
				} else {
					return leftChild.insert(element2);
				}
			} else {
				if (rightChild == null) {
					rightChild = new BinaryNode(element2);
					size++;
					return true;
				} else {
					return rightChild.insert(element2);
				}
			} 
		}
	    
	    public int CalHeight(){
	    	int left = -1;
	    	int right = -1;
	    	if(this.leftChild != null){
	    		left = this.leftChild.CalHeight();
	    	}
	    	if(this.rightChild != null){
	    		 right = this.rightChild.CalHeight();
	    	}
	    	this.height =  Math.max(left, right)+1; 
	    	return this.height;
	    }
	    
	    public int getHeight(){
	    	return this.height;
	    }
	    
	    public String toString() {
	    	String s = "[" + element + " " +
	    	 			((leftChild == null)? null : leftChild.element)+ " " +
	    	 			((rightChild == null)? null : rightChild.element)+	
	    	 			"]\n";
	    	if (leftChild != null) {
	    		s += leftChild.toString();
	    	}
	       	if (rightChild != null) {
	       		s += rightChild.toString();
	    	}	
	       	return s;
	    }
	    
		public ArrayList<BinaryNode> getLeftAncestors(){
			ArrayList<BinaryNode> temp = new ArrayList<BinaryNode>();
			BinaryNode ptr = this;
			while (ptr != null) {
				temp.add(0,ptr);
				ptr = ptr.leftChild;
			}
			return temp;
		}
		
		public int height(){
			int lheight = -1;
			int rheight = -1;
			if (leftChild != null){
				lheight = leftChild.height();
			}
			if (rightChild != null){
				rheight = rightChild.height();
			}
			if (lheight > rheight) return lheight + 1;
			return rheight + 1;
		}
	    
}

// This is an in-order iterator
private class TreeIterator implements Iterator<T> {
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

	public T next() {
		if (!hasNext()) throw new NoSuchElementException();
		BinaryNode temp = nodes.remove(0);
		if (temp.rightChild != null){
			nodes.addAll(0, temp.rightChild.getLeftAncestors());
		}
		return temp.element;
	}

	public void remove() {
	}
}

}
