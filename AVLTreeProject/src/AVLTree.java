import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;


public class AVLTree <T extends Comparable<? super T>> implements Iterable<T>{
	
	private MyBoolean b = new MyBoolean(true);
	private BinaryNode root;
	private BinaryNode currentNode = root;
	private int height = -1;
	private int count = 0;
	
	public AVLTree(){
		root = null;
		height = -1;		
	}
	
	public AVLTree(BinaryNode n){
		root = n;
	}
	
	@Override
	public Iterator iterator() {
		return new preOrderIterator(this.root);
	}

	public int getRotationCount() {
		return this.count;
	}

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
	
	public int getHeight(){
		return 0;
	}
	
	public int getSize(){
		return 0;
	}
	
	public boolean insert(T t){
		if(this.root == null){
			this.root = new BinaryNode(t);
			this.root.height = 0;
			return true;
		}else{
			MyBoolean validIndicator = new MyBoolean(false);
			this.root =  this.root.balanceCheck(this.root.insert(t, validIndicator));
			return validIndicator.getBoolean();
		}
	}
	
	public int getHeight(BinaryNode a){

		return a == null ? -1 : a.height;
	}
	
	//the class for the binary node
	private class BinaryNode{
		private T element;
		private BinaryNode leftChild;
		private BinaryNode rightChild;
		private int height;
		private int size;
		@SuppressWarnings("unused")
		
		public BinaryNode(T element){
			this.height = 0;
			this.element = element;
			this.leftChild = null;
			this.rightChild = null;		
		}
		
		//general remove method of BinaryNode
		@SuppressWarnings("unchecked")
		public BinaryNode remove(T t, MyBoolean b){
//			
//			int comp = this.element.compareTo(t);
//			if(comp > 0){
//				if(leftChild == null){
//					b.setFalse();
//					return null;
//				}
//				this.leftChild = this.leftChild.remove(t, b);
//				return balanceCheck(this);
//			}
//			else if(comp < 0){
//				if(rightChild == null){
//					b.setFalse();
//					return null;
//				}
//				this.rightChild = this.rightChild.remove(t, b);
//				return balanceCheck(this);
//				
//			}
//			else if(comp == 0){
//				if(this.leftChild == null && this.rightChild == null){
//					return new BinaryNode(null);
//				}else if(this.leftChild == null && this.rightChild != null){
//					return this.rightChild;
//				}else if (this.leftChild != null && this.rightChild == null){
//					return this.leftChild;
//				}else if(this.leftChild != null && this.rightChild != null){
//					BinaryNode current = this.leftChild;
//					while(current.rightChild!= null){
//						current = current.rightChild;
//					}
//					this.element = current.element;
//					this.leftChild = this.leftChild.remove(this.element, b);
//					return balanceCheck(this);
//				}
//			}
//			
//			return null;
			int comp = this.element.compareTo(t);
			if(comp > 0){
				BinaryNode result = null;
				if(leftChild == null){
					b.setFalse();
				}else{
					leftChild = leftChild.remove(t, b);
					result = this.balanceCheck(this); 
				}
				return result;
			}
			else if(comp < 0){
				BinaryNode result = null;
				if(rightChild == null){
					b.setFalse();
				}else{
					rightChild = rightChild.remove(t, b);
					result = this.balanceCheck(this);
				}
				return result;
			}
			else if(comp == 0){
				BinaryNode left = this.leftChild;
				BinaryNode right = this.rightChild;
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
					return balanceCheck(this);
				}
				else{
					BinaryNode result = null;
					if(rightChild != null){
						BinaryNode temp = this.getLeftChild();
						while(temp.getRightChild()!=null){
							temp = temp.getRightChild();
						}
//						
//						temp.rightChild = right;
//						left = left.balanceCheck(left.remove(temp.getElement(), b));
//						temp.leftChild = left;
//						return temp.balanceCheck(temp);
						this.element = temp.getElement();
						this.leftChild = this.leftChild.remove(temp.getElement(), b);
						//this.leftChild = left;
						result = this.balanceCheck(this);
					}
					else{
						this.element = this.getLeftChild().getElement();
						this.rightChild = this.getLeftChild().getRightChild();
						this.leftChild = this.getLeftChild().getLeftChild();	
						result = this.balanceCheck(this);
					}
					//b.setFalse();
					return result;
				}
			}
			return this;
			
		}
		
		 
		private BinaryNode leftRotation(BinaryNode t){
			count++;
			BinaryNode temp = t.rightChild;
			t.rightChild = temp.leftChild;
			temp.leftChild = t;
			t.height = Math.max(getHeight(t.leftChild), getHeight(t.rightChild)) + 1;
			temp.height = Math.max(getHeight(temp.rightChild), getHeight(t)) + 1;
			return temp;
		}
		
		private BinaryNode rightRotation(BinaryNode t){
			count++;
			BinaryNode temp = t.leftChild;
			t.leftChild = temp.rightChild;
			temp.rightChild = t;
			t.height = Math.max(getHeight(t.leftChild), getHeight(t.rightChild)) + 1;
			temp.height = Math.max(getHeight(temp.leftChild), getHeight(t)) + 1;
			return temp;
		}
		
		private BinaryNode doubleLeftRotation(BinaryNode t){
			t.rightChild = rightRotation(t.rightChild);
			t = leftRotation(t);
			return t;
		}
		
		private BinaryNode doubleRightRotation(BinaryNode t){
			t.leftChild = leftRotation(t.leftChild);
			return rightRotation(t);
		}
		
		public BinaryNode balanceCheck(BinaryNode t){
			int leftHeight = getHeight(t.leftChild);
			int rightHeight = getHeight(t.rightChild);
			int delta = leftHeight - rightHeight;
			if(delta == -2){
				if(getHeight(t.rightChild.rightChild) < getHeight(t.rightChild.leftChild)){
					t = doubleLeftRotation(t);
				}else{
					t = leftRotation(t);
				}
			}
			else if(delta == 2){
				if(getHeight(t.leftChild.rightChild) > getHeight(t.leftChild.leftChild)){
					t = doubleRightRotation(t);
				}else{
					t = rightRotation(t);
				}
			}
			return t;
			
			
		}
		

		
		@SuppressWarnings("unchecked")
		public BinaryNode insert(T t, MyBoolean v){
			BinaryNode i = null;
			if(this.element == t){
				v.setFalse();
				return this;
			}
			else if(t.compareTo(this.element) < 0){
				if(this.leftChild == null){
					 this.leftChild = new BinaryNode(t);
				}else{
					i = this.leftChild.insert(t, v);
					this.height = Math.max(getHeight(this.leftChild), getHeight(this.rightChild)) + 1;
					this.leftChild = balanceCheck(i);
					return this.balanceCheck(this);
				}
			}else if (t.compareTo(this.element) > 0){
				
				if(this.rightChild == null){
					this.rightChild =  new BinaryNode(t);
				}
				else{					
					i = this.rightChild.insert(t, v);
					this.height = Math.max(getHeight(this.leftChild), getHeight(this.rightChild)) + 1;
					this.rightChild = balanceCheck(i);
					return this.balanceCheck(this);
				}
			}
			this.height = Math.max(getHeight(this.leftChild), getHeight(this.rightChild)) + 1;
			return this;
			
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

}


