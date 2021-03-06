
/**
 * Binary Tree practice problems
 * 
 * @author Matt Boutell and <<<YOUR NAME HERE>>>. 2014.
 * @param <T>
 */

/*
 * TODO: 0 You are to implement the four methods below. I took most of them from
 * a CSSE230 exam given in a prior term. These can all be solved by either
 * recursion (or iterators if you are creative). I encourage you to use
 * recursion on at least 2 of them (I used it on all 3), since most students
 * find practicing recursion to be more useful. Also, some problems (like tree
 * height) are much, much easier to solve recursively.
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

	private BinaryNode root;
	private final BinaryNode NULL_NODE = new BinaryNode(null);
	public BinarySearchTree() {
		root = NULL_NODE;
	}

	/**
	 * This method counts the number of occurrences of positive Integers in the
	 * tree that is of type Integer. Hint: You may assume this tree contains
	 * integers, so may use a cast.
	 * 
	 * @return The number of positive integers in the tree.
	 */

	public int countPositives() {
		return this.root.countPositive();
	}

	/**
	 * Counts how many leaf nodes this Binary Tree has. Don't count NULL NODES.
	 * 
	 * @return The number of leaf nodes in this tree.
	 */
	public int countLeaves() {
		return this.root.leaves();
	}

	/**
	 * This method visits each node of the BST in pre-order and determines the
	 * number of children of each node. It produces a string of those numbers.
	 * If the tree is empty, an empty string is to be returned. For the
	 * following tree, the method returns the string: "2200110"
	 * 
	 * 10 5 15 2 7 18 10
	 * 
	 * @return A string representing the number of children of each node when
	 *         the nodes are visited in pre-order.
	 */

	public String numChildrenOfEachNode() {
		
		return this.root.nodeCount();
	}

	/**
	 * This method determines if a BST forms a zig-zag pattern. By this we mean
	 * that each node has exactly one child, except for the leaf. In addition,
	 * the nodes alternate between being a left and a right child. An empty tree
	 * or a tree consisting of just the root are both said to form a zigzag
	 * pattern. For example, if you insert the elements 10, 5, 9, 6, 7 into a
	 * BST in that order. , you will get a zig-zag.
	 * 
	 * @return True if the tree forms a zigzag and false otherwise.
	 */
	public boolean isZigZag() {
		if(this.root == NULL_NODE){
			return true;
		}
		if (this.root.leftChild!= NULL_NODE && this.root.rightChild == NULL_NODE)
			return this.root.leftChild.isZigZag(0);
		if(this.root.leftChild== NULL_NODE && this.root.rightChild != NULL_NODE)
			return this.root.rightChild.isZigZag(1);
		if(this.root.leftChild== NULL_NODE && this.root.rightChild == NULL_NODE){
			return true;
		}
		return false;
	}

	public void insert(T e) {
		root = root.insert(e);
	}

	// /////////////// BinaryNode
	public class BinaryNode {

		public T element;
		public BinaryNode leftChild;
		public BinaryNode rightChild;

		public BinaryNode(T element) {
			this.element = element;
			this.leftChild = NULL_NODE;
			this.rightChild = NULL_NODE;
		}

		public BinaryNode insert(T e) {
			if (this == NULL_NODE) {
				return new BinaryNode(e);
			} else if (e.compareTo(element) < 0) {
				leftChild = leftChild.insert(e);
			} else if (e.compareTo(element) > 0) {
				rightChild = rightChild.insert(e);
			} else {
				// do nothing
			}
			return this;
		}
		
		public int leaves(){
			if(this == NULL_NODE){
				return 0;
			}
			if(this.leftChild == NULL_NODE && this.rightChild == NULL_NODE){
				return 1;
			}
			else{
				return this.leftChild.leaves()+ this.rightChild.leaves();
			}
//			return 0;
		}
		
		public int countPositive(){
			if(this == NULL_NODE){
				return 0;
			}
			int self = 0;
			T zero = (T) new Integer(0);
			if(this.element.compareTo(zero)>0){
				self = 1;
			}
			return self+this.leftChild.countPositive()+this.rightChild.countPositive();
		}
		
		public String nodeCount(){
			if(this == NULL_NODE){
				return "";
			}
			if(this.leftChild == NULL_NODE || this.rightChild == NULL_NODE){
				if(this.leftChild == NULL_NODE && this.rightChild == NULL_NODE){
					return "0";
				}
				else{
					return "1"+this.leftChild.nodeCount()  + this.rightChild.nodeCount();
				}
			}
			else
				return "2"+this.leftChild.nodeCount()+ this.rightChild.nodeCount();
		}
		
		public boolean isZigZag(int previous){
			if(this.leftChild !=NULL_NODE && this.rightChild != NULL_NODE){
				return false;
			}
			
			if(previous == 1){
				if(this.leftChild != NULL_NODE && this.rightChild == NULL_NODE){
					return true && this.leftChild.isZigZag(0);
				}
				if(this.leftChild == NULL_NODE && this.rightChild == NULL_NODE){
					return true;
				}
				if(this.leftChild == NULL_NODE && this.rightChild != NULL_NODE){
					return false;
				}
			}
			else if(previous == 0){
				if(this.leftChild == NULL_NODE && this.rightChild != NULL_NODE){
					return true && this.rightChild.isZigZag(1);
				}
				if(this.leftChild == NULL_NODE && this.rightChild == NULL_NODE){
					return true;
				}
				if(this.leftChild != NULL_NODE && this.rightChild == NULL_NODE){
					return false;
				}
			}

				return false;

		}
		
	}
}