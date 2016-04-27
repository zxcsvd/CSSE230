import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

@SuppressWarnings("rawtypes")
//this class is designed for the tree
public class BinarySearchTree<T extends Comparable> implements Iterable {
	//root is the root node; height represents the height of the tree; currentNode points to the node you currently working on.
	
	private MyBoolean b = new MyBoolean(true);
	private BinaryNode root;
	private int height = -1;
	@SuppressWarnings("unused")
	private BinaryNode originalleft = null;
	@SuppressWarnings("unused")
	private BinaryNode originalright = null;
	private BinaryNode currentNode = root;
	
	//constructor
	public BinarySearchTree(){
		root = null;
		height = -1;
	}
	
	//constructor
	public BinarySearchTree(BinaryNode n){
		root = n;
	}
	
	//return the height of the tree
	public Object height() {
		if(root == null){
			return this.height;
		}
		this.height = this.root.getHeight();
		return this.height;
	}
	
	//return the size of the tree;
	public Object size() {
		if(this.root == null){
			return 0;
		}
		return this.root.getSize();
	}

	//to know whether the tree is empty
	public boolean isEmpty() {
		return  this.root == null;
		
	}
	
	//transform the tree to a string (in order)
	public String toString(){
		if(this.root == null){
			return "[]";
		}
		return "[" + this.root.toString() + "]";
	}

	//create an iterator of the root
	@Override
	public Iterator iterator() {
		return new inOrderIterator(this.root);
	}
	
	//transform the tree to an array list
	public ArrayList toArrayList(){
		ArrayList<T> temp = new ArrayList<T>();
		if(root == null) return temp;
		else{
			inOrderIterator it = new inOrderIterator(this.root); 
			while(it.hasNext()){
				temp.add(it.next());
			}
		}
		return temp;
	}
	
	//transform the tree to an array
	public Object[] toArray(){
		return this.toArrayList().toArray();
	}

	
	//create a pre-order-iterator based on root node
	public Iterator preOrderIterator() {
		
		return new preOrderIterator(this.root);
	}
	
	//pre-order-iterator class
	private class preOrderIterator implements Iterator<T> {
		private MyBoolean m = new MyBoolean(); 
	    private Stack<BinaryNode> nodes;
	    private BinaryNode root;
	    private T pre = null;

	    public preOrderIterator(BinaryNode root){
	    	this.root = root;
	    	m.setFalse();
	    	nodes = new Stack<BinaryNode>();
	    	if (root != null) {
	    		nodes.push(root);
	    	}
	    }
	    public boolean hasNext() {
	    	return !nodes.isEmpty();
	    }

	    public T next() {
	    	if (!hasNext()) throw new NoSuchElementException();
	    	BinaryNode temp = nodes.pop();
	    	if (temp.rightChild != null) nodes.push(temp.rightChild);
	    	if (temp.leftChild != null) nodes.push(temp.leftChild);
	    	m.setTrue();
	    	this.pre = temp.element;
	    	return temp.element;
	    }
	    
		@Override
		public void remove() {
			if(this.m.getBoolean()){
				this.root = this.root.remove(this.pre, m);
				m.setFalse();
			}
			else{
				throw new IllegalStateException();
			}	
		}

	
	}
	
	//the class for the in-order-iterator
	private class inOrderIterator implements Iterator {
		private MyBoolean m = new MyBoolean();
		private BinaryNode a = null;
		private T pre = null;
		private Stack<BinaryNode> tempStack= new Stack<BinaryNode>(); 

	
		public inOrderIterator(BinaryNode root) {
			this.a = root;
			this.m.setFalse();
			pushLeftChildren(this.a);
		}
		
		private void pushLeftChildren(BinaryNode node){
			while(node!= null){
				tempStack.push(node);
				node = node.getLeftChild();
			}
		}
		
		@Override
		public void remove() {
			if(this.m.getBoolean()){
				this.a = this.a.remove(this.pre, m);
				m.setFalse();
			}
			else{
				throw new IllegalStateException();
			}

		}
		
		//detect whether there is a next
		@Override
		public boolean hasNext() {
			if(tempStack.isEmpty()){
				return false;
			}
			return true;
		}
		
		//give next
		@Override
		public T next() {
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			BinaryNode b = tempStack.pop();
			pushLeftChildren(b.getRightChild());
			this.m.setTrue();
			this.pre = b.getElement();
			return this.pre;
			
		}

	}
	
	//this is a class for keeping the boolean value down
	private class MyBoolean{
		public Boolean b;
		public MyBoolean(Boolean b){
			this.b = b;
		}
		public MyBoolean(){
			this.b = true;
		}
		public void setFalse(){
			this.b = false;
		}
		
		public void setTrue(){
			this.b = true;
		}
		
		public Boolean getBoolean(){
			return this.b;
		}
	}
	
	//the class for the binary node
	private class BinaryNode{
		private T element;
		private BinaryNode leftChild;
		private BinaryNode rightChild;
		private int height;
		private int size;
		public BinaryNode(T element){
			this.element = element;
			this.leftChild = null;
			this.rightChild = null;		
		}
		
		//general remove method of BinaryNode
		public BinaryNode remove(T t, MyBoolean b){
			int comp = this.element.compareTo(t);
			if(comp > 0){
				if(leftChild == null){
					b.setFalse();
				}else{
					leftChild = leftChild.remove(t, b);
				}
				return this;
			}
			if(comp < 0){
				if(rightChild == null){
					b.setFalse();
				}else{
					rightChild = rightChild.remove(t, b);
				}
				return this;
			}
			if(comp == 0){
				if(leftChild == null){
					if(rightChild != null){
						this.element = this.getRightChild().getElement();
						this.leftChild = this.getRightChild().getLeftChild();
						this.rightChild = this.getRightChild().getRightChild();
					}
					else{
						//b.setFalse();
						return null;
					}
					b.setFalse();
					return this;
				}
				else{
					if(rightChild != null){
						BinaryNode temp = this.getLeftChild();
						while(temp.getRightChild()!=null){
							temp = temp.getRightChild();
						}
						this.element = temp.getElement();
						this.leftChild = this.leftChild.remove(temp.getElement(), b);
					}
					else{
						this.element = this.getLeftChild().getElement();
						this.rightChild = this.getLeftChild().getRightChild();
						this.leftChild = this.getLeftChild().getLeftChild();	
					}
					//b.setFalse();
					return this;
				}
			}
			return this;
			
		}
		
		//set the left node of current node
		public void setLeftChild(BinaryNode leftChild){
			this.leftChild = leftChild;
		}
		
		//set the right node of current node
		public void setRightChild(BinaryNode rightChild){
			this.rightChild = rightChild;
		}
		
		//get left child of the node
		public BinaryNode getLeftChild(){
			return leftChild;
		}
		
		//get right child of the node
		public BinaryNode getRightChild(){
			return rightChild;
		}
		
		//get current node element
		public T getElement(){
			return element;
		}
		
		
		//return the height of current node
		public int getHeight(){
		
			if(this.getLeftChild() == null && this.getRightChild() == null){
				this.height = 0;
				return height;
			}
			if(this.getLeftChild() != null && this.getRightChild()!= null){
				if(this.getLeftChild().getHeight() > this.getRightChild().getHeight()){
					this.height = this.getLeftChild().getHeight() + 1;
				}
				if(this.getLeftChild().getHeight() <= this.getRightChild().getHeight()){
					this.height = this.getRightChild().getHeight() + 1;
				}
			}
			else{
				if(this.getLeftChild()== null && this.getRightChild() != null){
					this.height = this.getRightChild().getHeight() + 1;
				}
				if(this.getRightChild() != null && this.getRightChild() == null){
					this.height = this.getLeftChild().getHeight() + 1;
				}
			}
			return height;
		}
		
		//return the size of the node
		public int getSize(){
			if(this.getLeftChild() == null && this.getRightChild() == null){
				this.size = 1;
				return this.size;
			}
			if(this.getLeftChild() != null && this.getRightChild()!= null){
				
				return this.getLeftChild().getSize() + this.getRightChild().getSize() + 1;	
			}
			else{
				if(this.getLeftChild()== null && this.getRightChild() != null){
					return this.getRightChild().getSize() + 1;
				}
				if(this.getLeftChild() != null && this.getRightChild() == null){
					return this.getLeftChild().getSize() + 1;
				}
			}
			return this.size;
		}
		
		//return the string that represents the node
		public String toString(){
			if(this.getLeftChild() == null && this.getRightChild() == null){
				return "" + this.element;
			}
			if(this.getLeftChild() == null && this.getRightChild() != null){
				return this.element + ", " + this.getRightChild().toString();
			}
			if(this.getLeftChild() != null && this.getRightChild() == null){
				return this.getLeftChild().toString() + ", " + this.element;
			}
			if(this.getLeftChild() != null && this.getRightChild() != null){
				return this.getLeftChild().toString() + ", " + this.element + ", " + this.getRightChild().toString();
			}
			return "";
		}
		
		//compare current node element to specific element 
		public int compareTo(T t) {
			if(t.getClass() == Integer.class){
				if((Integer)currentNode.getElement() > (Integer)t){
					return 1;
				}
				if((Integer)currentNode.getElement() == (Integer)t){
					return 0;
				}
				if((Integer)currentNode.getElement() < (Integer)t){
					return -1;
				}
			}
			return 0;
		}


	}
	
	//the insert method that insert a T type elements node to the tree
	public Boolean insert(T t) {
		if(t == null)
		throw new IllegalArgumentException();
		currentNode = this.root;
		if(currentNode == null){
			this.root = new BinaryNode(t);
			currentNode = this.root;
			return true;
		}
		while(currentNode != null){
			if(currentNode.compareTo(t)==0){
				return false;
			}
			if(currentNode.compareTo(t)>0){
				if(currentNode.getLeftChild() == null){
					currentNode.leftChild = new BinaryNode(t);
					return true;
				}
				currentNode = currentNode.getLeftChild();
			}
			if(currentNode.compareTo(t)<0){
				if(currentNode.getRightChild() == null){
					currentNode.rightChild = new BinaryNode(t);
					return true;
				}
				currentNode = currentNode.getRightChild();
			}
		}
		return false;
	}

	//general remove method
	public boolean remove(T t) {
		if(t == null){
			throw new IllegalArgumentException();
		}
		if(this.root == null){
			return false;
		}
		else{
			this.root = this.root.remove(t, this.b);
		}
		return this.b.getBoolean();
	}
	


}
