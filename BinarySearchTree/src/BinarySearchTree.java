import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;




public class BinarySearchTree<T> implements Iterable{
	private BinaryNode root;
	private BinaryNode left;
	private BinaryNode right;
	private Boolean detector = true;
	private int binaD = 2;
	private int count = 0;
//	private T data;
	private final BinaryNode NULL_NODE = new BinaryNode();

	public BinarySearchTree() {
		root = NULL_NODE;
		
	}

	public int size(){
		if(root == NULL_NODE){
			return 0;
		}
		else{
			return root.size();
		}
		

		
	}
	
	
	public int height(){
		return this.root.getHeight();
	}

	
	public boolean contains(T a){
		return root.contains(a);
		
	}
	
	public Iterator<T> inefficientIterator(){
		return new ArrayListIterator();
	}
	
	class ArrayListIterator implements Iterator<T>{
		private ArrayList<T> list;
		int counter = 0;
		
		public ArrayListIterator(){
			list = toArrayList();
			counter = 0;
		}
		
		
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return counter < list.size();
		}

		@Override
		public T next() {
			if(!hasNext()){
				throw new NoSuchElementException("no more items");
			}
			counter++;
			// TODO Auto-generated method stub
			return list.get(counter-1);
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		

		
	}
	
	class preOrderIterator implements Iterator<T>{
		
		private Stack<BinaryNode> s;
		private Boolean detect = false;
		private BinaryNode top;
		public preOrderIterator(){
			top = new BinaryNode();
			s = new Stack<BinaryNode>();
			s.push(root);
		}
		
		
		@Override
		public boolean hasNext() {
			detector = false;
			return (!(root == NULL_NODE) && !(s.isEmpty()));
		}

		@Override
		public T next() {
			if(detector){
				throw new ConcurrentModificationException();
			}
			
			if(s.isEmpty()){
				throw new  NoSuchElementException();
			}
			
			if(!s.isEmpty()){
				top = s.pop();
			}
			if(top.right != NULL_NODE){
				detect =true;
				s.push(top.right);
			}
			if(top.left != NULL_NODE){
				detect =true;
				s.push(top.left);
			}
			return top.data;
		}

		@Override
		public void remove() {
			if(root!=NULL_NODE)
				throw new IllegalStateException();
			else{
				if(!detect){
					throw new IllegalStateException();
				}
				else{
					BinaryNode currentL = root;
					if(root.left!=NULL_NODE){
						BinaryNode current = currentL.left;
						while(currentL.left!=NULL_NODE){
							currentL = currentL.left;
							current = currentL.left;
						}
					}
					else{
						
					}


					
				}
			}
				
		}

		
		
	}
	
	
	class iterator implements Iterator<T>{
		private Stack<BinaryNode> s;
		int counter = 0;
		Boolean detect = false;
//		private BinaryNode Left = new BinaryNode();
		private BinaryNode current = new BinaryNode();
		public iterator(){
			detector = false;
//			Left = new BinaryNode();
			s = new Stack<BinaryNode>();
			s.push(root);
			current = root;
		}
		
		
		@Override
		public boolean hasNext() {
			return (!(root == NULL_NODE) && !(s.isEmpty()));
		}

		@Override
		public T next() {
			detect = true;
			if(detector){
				throw new ConcurrentModificationException();
			}
			if(s.isEmpty()){
				throw new NoSuchElementException();
			}
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			current = s.peek();
			while(current.left != NULL_NODE && counter == 0){
				s.push(current.left);
				current = current.left;
				
				
			}
			counter++;
			BinaryNode result = s.pop();
			if(!s.isEmpty())
				current = s.peek();
			if(result.right!=NULL_NODE){
				s.push(result.right);
				current = s.peek();
				while(current.left != NULL_NODE){
					s.push(current.left);
					current = current.left;
				}
			}
			return result.data;
			
			
		}

		@Override
		public void remove() {
			current = root;
			if(detect){
				
				if(s.isEmpty()){
					throw new IllegalStateException();
				}
				BinaryNode leftN= current.left;
				BinaryNode rightN = current.right;
				next();
				setRoot(NULL_NODE);
				root.left = NULL_NODE;
				root.right = NULL_NODE;
				rightN.setLeft(leftN);
				s.push(rightN);
				setRoot(rightN);
				current = root;
				detect = false;
			}
			else{
				throw new IllegalStateException();
			}
		}

		
		
	}
	
	public String toString(){
		return root.toString();
		
	}
	// For manual tests only
	void setRoot(BinaryNode n) {
//		if(root == NULL_NODE){
			this.root = n;
//		}
//		else{
//			if(n.getData() < this.root.getData()){
//				
//			}
//		}
		
	}
	

	
	// Not private, since we need access for manual testing.
	class BinaryNode implements Comparable<T> {
		
		private T data;
		private BinaryNode left;
		private BinaryNode right;
		BinaryNode parent = root;
		
		public BinaryNode() {
			this.data = null;
			this.left = null;
			this.right = null;
		}
		
		
		public int getHeight() {
			if(this == NULL_NODE){
				return -1;
			}
			else{
				int left = this.left.getHeight()+1;
				int right = this.right.getHeight()+1;
				int larger = Math.max(left, right);
				return larger;
			}
			
		}
		 
		public Boolean insert(T element){
			count= 0;
			if(element == null){
				throw new IllegalArgumentException();
			}
			detector = true;
			BinaryNode e = new BinaryNode(element);
			if(this == NULL_NODE){
				setRoot(e);
				binaD = 2;
				return detector;
			}
			else{
				if(this.compareTo(element) == 0){
					detector = false;
					return false;
					
				}
				else
				{
					if(this.compareTo(element) > 0){
						if(this.right == NULL_NODE){
							this.right = e;
						}
						else{
							this.right.insert(element);
						}
					}
					else if(this.compareTo(element) < 0){
						if(this.left == NULL_NODE){
							this.left = e;
						}
						else{
							this.left.insert(element);
						}
					}
					binaD = 2;
					return detector;
					
				}
				
			}

			
		}
		




		public BinaryNode(T element) {
			this.data = element;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}

		
		public String toString(){
//			if(this == NULL_NODE){
//				return "";
//			}
//			return left.toString() +"," + this.data.toString()+","+right.toString() + ",";
			return (this.toArrayList().toString());
		}
		
		public T getData() {
			return this.data;
		}
		
		public boolean isEmpty(){
			if (this == NULL_NODE){
				return true;
			}
			return !(left.isEmpty() || right.isEmpty());
		}
		
		public boolean contains(T item){
			if(this == NULL_NODE){
				return false;
			}
			return this.data.equals(item) || left.contains(item) || right.contains(item);
		}
		
		public ArrayList<T> toArrayList(){
			ArrayList<T> list = new ArrayList<T>();
			if(this == NULL_NODE){
				return list;
			}
			list.addAll(left.toArrayList());
			list.add(data);
			list.addAll(right.toArrayList());
			return list;
			
		}
		
//		public ArrayList<T> iterator(){
//			ArrayList<T> list = new ArrayList<T>();
//			if(this == NULL_NODE){
//				return list;
//			}
//			list.addAll(left.iterator());
//			list.add(data);
//			
//			list.addAll(right.iterator());
//			return list;
//		}
		
		public BinaryNode getLeft() {
			return this.left;
		}


		public BinaryNode getRight() {
			return this.right;
		}

		// For manual testing
		public void setLeft(BinaryNode left) {
			this.left = left;
		}
		
		public void setRight(BinaryNode right) {
			this.right = right;
		}
		
		public int size(){

			if(this == NULL_NODE){
				return 0;
			}
			return left.size() + right.size() +1;
		}


		public Object[] toArray() {
			return this.toArrayList().toArray();
		}


		@Override
		public int compareTo(T o) {
			if(o.getClass().equals(Integer.class) && this.data.getClass().equals(Integer.class)){
				return Integer.compare((Integer)o, (Integer)this.data);
			}
			else{
				return 0;
			}
		}


		public Boolean remove(T element) {
			
			if(element == null){
				throw new IllegalArgumentException();
			}
			detector = false;
			BinaryNode e = new BinaryNode(element);
			if(this == NULL_NODE){
				return detector;
			}
			else{
				if(count > 1){
					moveParent();
				}

				count++;
				if(this.compareTo(element) == 0){
					detector = true;
					if(this.left == NULL_NODE || this.right == NULL_NODE){
						if(this.left == NULL_NODE && this.right!= NULL_NODE){
							 if(binaD == 0){
								 parent.left = this.right;
							 }
							 if(binaD == 1){
								 parent.right = this.right;
							 }
						}
						else if(this.left != NULL_NODE && this.left == NULL_NODE){
							 if(binaD == 0){
								 parent.left = this.left;
							 }
							 if(binaD == 1){
								 parent.right = this.left;
							 }
						}
						else if(this.left == NULL_NODE && this.right == NULL_NODE){
							 if(binaD == 0){
								 parent.left = NULL_NODE;
							 }
							 if(binaD == 1){
								 parent.right = NULL_NODE;
							 }
							 if(binaD == 2){
								 setRoot(NULL_NODE);
								 parent = NULL_NODE;
								 parent.left = NULL_NODE;
								 parent.right = NULL_NODE;
							 }
							
						}
					}
					else{
						 if(binaD == 0){
							 parent.left = this.left;
							 parent.left.right = this.right;
						 }
						 if(binaD == 1){
							 parent.right = this.left;
							 parent.right.right = this.right;
						 }
						 if(binaD == 2){
							 if(this.left.compareTo(this.right.data)>0){
								 setRoot(this.right);
								 root.left = this.left;
								 parent = root;
							 }
							 else{
								 setRoot(this.left);
								 root.right = this.right;
								 parent = root;

							 }

						 }
					}
					return detector;
					
				}
				else
				{
					if(this.compareTo(element) > 0){
						if(count >= 0){
							binaD = 1;
						}
							
						if(this.right == NULL_NODE){
							return detector;
						}
						else{
							this.right.remove(element);
						}
					}
					else if(this.compareTo(element) < 0){
						if(count >=0){
							binaD = 0;
						}
						if(this.left == NULL_NODE){
							return detector;
						}
						else{
							this.left.remove(element);
						}
					}
					return detector;
				}
				
			}

		}

		public void moveParent(){
			if(binaD == 0){
				parent = parent.left;
			}
			if(binaD == 1){
				parent = parent.right;
			}
		}
//		@Override
//		public int compareTo(Object o) {
//			int a =(int)o - (int)(Object)this.data;
//			return a;
//		}
	}



	public ArrayList<T> toArrayList() {
//		System.out.print(this.root.toArrayList().toString());
		return this.root.toArrayList();
		
	}
	
		
//	public ArrayList<T> toiterator() {
////		System.out.print(this.root.toPreOrderList().toString());
//		return this.root.iterator();
//		
//	}
	public Object[] toArray() {
		return this.root.toArray();
	}

	public boolean isEmpty() {
		
		return (this.root == NULL_NODE);
	}

	public Iterator<T> preOrderIterator() {
		
		return new preOrderIterator();
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
//		System.out.print(this.root.iterator().toString());
		return new iterator();
	}

	public Boolean insert(T i) {
		return this.root.insert(i);
		
	}

	public Boolean remove(T i) {
		// TODO Auto-generated method stub
		return this.root.remove(i);
	}




}