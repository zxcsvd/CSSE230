import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;





public class RedBlackTree <T extends Comparable<? super T>> implements Iterable<T> {
	public int height = -1;
	public BinaryNode root;
	public int count = 0;
	public BinaryNode nullNode = new BinaryNode(); 
	public RedBlackTree(){
		this.root = new BinaryNode();
	}

	public enum Color {
		BLACK, RED

	}
	public int getHeight(BinaryNode a){

		return a == null ? -1 : a.height;
	}
	
	public BinaryNode balance(BinaryNode GGP){
		if(GGP ==  null || GGP.element == null){
			
		}else{
			if(GGP.leftChild!= null && GGP.leftChild.element != null){
				BinaryNode GP = GGP.leftChild;
//				if(GGP.color == Color.RED && GP.color == Color.RED){
//					return rightRotation(GGP);
//				}
				if(GGP.leftChild.leftChild!=null && GGP.leftChild.leftChild.element!= null){
					BinaryNode P = GGP.leftChild.leftChild;
					if(GGP.leftChild.leftChild.leftChild!=null && GGP.leftChild.leftChild.leftChild.element!= null){
						BinaryNode X = GGP.leftChild.leftChild.leftChild;
						if(X.color == Color.RED && P.color == Color.RED){
							P.color = Color.BLACK;
							GP.color = Color.RED;
							GGP.leftChild = rightRotation(GP);
							
							return GGP;
						}
						if(GP.color == Color.RED && P.color == Color.RED){
							GP.color = Color.BLACK;
							GGP.color = Color.RED;
							return rightRotation(GGP);
						}
					}else{
						if(P.color == Color.RED && GP.color == Color.RED){
							GP.color = Color.BLACK;
							GGP.color = Color.RED;
							return rightRotation(GGP);
						}
					}
					if(GGP.leftChild.leftChild.rightChild!=null && GGP.leftChild.leftChild.rightChild.element!= null){
						BinaryNode X = GGP.leftChild.leftChild.rightChild;
						if(X.color == Color.RED && P.color == Color.RED){
							X.color = Color.BLACK;
							GP.color = Color.RED;
							GGP.leftChild = doubleRightRotation(GP);
							return GGP;
						}
						if(GP.color == Color.RED && P.color == Color.RED){
							GP.color = Color.BLACK;
							GGP.color = Color.RED;
							return rightRotation(GGP);
						}
						
					}
					else{
						if(P.color == Color.RED && GP.color == Color.RED){
							GP.color = Color.BLACK;
							GGP.color = Color.RED;
							return rightRotation(GGP);
						}
					}
				}
				if(GGP.leftChild.rightChild!=null && GGP.leftChild.rightChild.element!= null){
//					if(GGP.color == Color.RED && GP.color == Color.RED){
//						return leftRotation(GGP);
//					}
					BinaryNode P = GGP.leftChild.rightChild;
					if(GGP.leftChild.rightChild.leftChild!=null && GGP.leftChild.rightChild.leftChild.element!= null){
						BinaryNode X =GGP.leftChild.rightChild.leftChild;
						if(X.color == Color.RED && P.color == Color.RED){
							X.color = Color.BLACK;
							GP.color = Color.RED;
							GGP.leftChild = doubleLeftRotation(GP);
							return GGP;
						}
						if(GP.color == Color.RED && P.color == Color.RED){
							P.color = Color.BLACK;
							GGP.color = Color.RED;
							return doubleRightRotation(GGP);
						}
						
					}
					else{
						if(P.color == Color.RED && GP.color == Color.RED){
							P.color = Color.BLACK;
							GGP.color = Color.RED;
							return doubleRightRotation(GGP);
						}
					}
					if(GGP.leftChild.rightChild.rightChild!=null && GGP.leftChild.rightChild.rightChild.element!= null){
						BinaryNode X =GGP.leftChild.rightChild.rightChild;
						if(X.color == Color.RED && P.color == Color.RED){
							P.color = Color.BLACK;
							GP.color = Color.RED;
							GGP.leftChild = leftRotation(GP);
							return GGP;
						}
						if(P.color == Color.RED && GP.color == Color.RED){
							P.color = Color.BLACK;
							GGP.color = Color.RED;
							return doubleRightRotation(GGP);
						}
						
					}
					else{
						if(P.color == Color.RED && GP.color == Color.RED){
							P.color = Color.BLACK;
							GGP.color = Color.RED;
							return doubleRightRotation(GGP);
						}
					}
				}
			}
			if(GGP.rightChild!= null && GGP.rightChild.element != null){
				BinaryNode GP = GGP.rightChild;
				if(GGP.rightChild.leftChild!=null && GGP.rightChild.leftChild.element!= null){
					BinaryNode P = GGP.rightChild.leftChild;
					if(GGP.rightChild.leftChild.leftChild!=null && GGP.rightChild.leftChild.leftChild.element!= null){
						BinaryNode X = GGP.rightChild.leftChild.leftChild;
						if(X.color == Color.RED && P.color == Color.RED){
							P.color = Color.BLACK;
							GP.color = Color.RED;
							GGP.rightChild = rightRotation(GP);
							return GGP;
						}
						if(P.color == Color.RED && GP.color == Color.RED){
							P.color = Color.BLACK;
							GGP.color = Color.RED;
							return doubleLeftRotation(GGP);
						}
					}
					else{
						if(P.color == Color.RED && GP.color == Color.RED){
							P.color = Color.BLACK;
							GGP.color = Color.RED;
							return doubleLeftRotation(GGP);
						}
					}
					if(GGP.rightChild.leftChild.rightChild!=null && GGP.rightChild.leftChild.rightChild.element!= null){
						BinaryNode X = GGP.rightChild.leftChild.rightChild;
						if(X.color == Color.RED && P.color == Color.RED){
							X.color = Color.BLACK;
							GP.color = Color.RED;
							GGP.rightChild = doubleRightRotation(GP);
							return GGP;
						}
						if(P.color == Color.RED && GP.color == Color.RED){
							P.color = Color.BLACK;
							GGP.color = Color.RED;
							return doubleLeftRotation(GGP);
						}
					}
					else{
						if(P.color == Color.RED && GP.color == Color.RED){
							P.color = Color.BLACK;
							GGP.color = Color.RED;
							return doubleLeftRotation(GGP);
						}
					}
				}
				if(GGP.rightChild.rightChild!=null && GGP.rightChild.rightChild.element!= null){
					BinaryNode P = GGP.rightChild.rightChild;
					if(GGP.rightChild.rightChild.leftChild!=null && GGP.rightChild.rightChild.leftChild.element!= null){
						BinaryNode X = GGP.rightChild.rightChild.leftChild;
						if(X.color == Color.RED && P.color == Color.RED){
							X.color = Color.BLACK;
							GP.color = Color.RED;
							GGP.rightChild = doubleLeftRotation(GP);
							return GGP;
						}
						if(P.color == Color.RED && GP.color == Color.RED){
							GP.color = Color.BLACK;
							GGP.color = Color.RED;
							return leftRotation(GGP);
						}
					}
					else{
						if(P.color == Color.RED && GP.color == Color.RED){
							GP.color = Color.BLACK;
							GGP.color = Color.RED;
							return leftRotation(GGP);
						}
					}
					if(GGP.rightChild.rightChild.rightChild!=null && GGP.rightChild.rightChild.rightChild.element!= null){
						BinaryNode X = GGP.rightChild.rightChild.rightChild;
						if(X.color == Color.RED && P.color == Color.RED){
							P.color = Color.BLACK;
							GP.color = Color.RED;
							GGP.rightChild = leftRotation(GP);
							return GGP;
						}
						if(P.color == Color.RED && GP.color == Color.RED){
							GP.color = Color.BLACK;
							GGP.color = Color.RED;
							return leftRotation(GGP);
						}
					}
					else{
						if(P.color == Color.RED && GP.color == Color.RED){
							GP.color = Color.BLACK;
							GGP.color = Color.RED;
							return leftRotation(GGP);
						}
					}
				}
			}
		}
		return GGP;
		
	}
	
	
	public class BinaryNode {
		
		private Color color;
		private T element = null;
		private BinaryNode leftChild = null;
		private BinaryNode rightChild = null;
		private int height = 0;
		
		
		public BinaryNode(){
			this.color = Color.BLACK;
			this.element = null;
			this.leftChild = null;
			this.rightChild = null;
		}
		

		
		public BinaryNode(T element){
			this.color = Color.RED;
			this.element = element;
			this.leftChild = null;
			this.rightChild = null;		
		}
		
		
		public T getElement() {
			return this.element;
		}

		public Color getColor() {
			return this.color;
		}
		

		

		public BinaryNode insert(BinaryNode X, BinaryNode P, BinaryNode GP, BinaryNode GGP, MyBoolean b){
			GGP = GP;
			GP = P;
			P = this;
			if(this.element == null){
				P = X;
				if(b.getBoolean()){
					X.leftChild = new BinaryNode();
					X.rightChild = new BinaryNode();
					return X;
				}
				return null;
				
			}
			else if(X.element.compareTo(this.element) == 0){
				if(this.rightChild == null){
					this.rightChild = new BinaryNode(null);
				}
				b.setFalse();
				
				BinaryNode temp = this.rightChild.insert(X, P, GP, GGP, b);
				this.rightChild = balance(temp);
				
			}else if(X.element.compareTo(this.element) < 0){
				if(this.leftChild == null){
					this.leftChild = new BinaryNode(null);
				}
				this.leftChild = ColorPre(this.leftChild);
				
				BinaryNode temp = this.leftChild.insert(X, P, GP, GGP, b);
				this.leftChild = balance(temp);
				
				
				
			}else if(X.element.compareTo(this.element) > 0){

				if(this.rightChild == null){
					this.rightChild = new BinaryNode(null);
				}
				this.rightChild = ColorPre(this.rightChild);
				BinaryNode temp = this.rightChild.insert(X, P, GP, GGP, b);
				

				this.rightChild = balance(temp);
			}return this;
			
			
		}



		public int size() {
			if(this == null || this.element == null){
				return 0;
			}

			
			if(this.leftChild == null || this.leftChild.element == null){
				if(this.rightChild == null || this.rightChild.element == null){
					return 1;
				}
				return this.rightChild.size() + 1;
			}
			if(this.rightChild == null || this.rightChild.element == null){
				if(this.leftChild == null || this.leftChild.element == null){
					return 1;
				}
				return this.leftChild.size() +1;
			}
			return this.leftChild.size() + this.rightChild.size() +1;
		}



		public int getHeight() {

			if(this.leftChild == null || this.rightChild == null){
				if(this.leftChild == null && this.rightChild!= null){
					return Math.max(-1, this.rightChild.getHeight()) + 1;
				}
				if(this.rightChild == null && this.leftChild == null){
					return 0;
				}
				if(this.leftChild != null && this.rightChild == null){
					return Math.max(this.leftChild.getHeight(), -1) + 1;
				}
			}
			return Math.max(this.leftChild.getHeight(), this.rightChild.getHeight()) + 1;
		}



		public BinaryNode getRightChild() {
			return this.rightChild;
		}



		public BinaryNode getLeftChild() {
			return this.leftChild;
		}
	}

	@Override
	public preOrderIterator iterator() {
		return new preOrderIterator(this.root);
	}

	public boolean insert(T i) {
		MyBoolean validOperation = new MyBoolean(true); 
		BinaryNode X = new BinaryNode(i);
		this.root = ColorPre(this.root);
		
		this.root = balance(this.root.insert(X, nullNode, nullNode, nullNode, validOperation));
		this.root.color = Color.BLACK;
		return validOperation.getBoolean();
	}
	

	public Object getRotationCount() {
		
		return this.count;
	}
	public BinaryNode ColorPre(BinaryNode b){
		if(b == null){
			return null;
		}
		if(b.element == null || b.leftChild == null || b.rightChild == null){
			return b;
		}
		if(b.leftChild != null && b.rightChild != null && b.leftChild.color == Color.RED && b.rightChild.color == Color.RED){
			b.color = Color.RED;
			b.leftChild.color = Color.BLACK;
			b.rightChild.color = Color.BLACK;
		}
		return b;
	}



	public int size() {
		return this.root.size();
	}

	public int height() {
		// TODO Auto-generated method stub
		if(this.root == null){
			return -1;
		}
		return this.root.getHeight();
	}
	
	public class MyBoolean{
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
	
	public class preOrderIterator<T> implements Iterator<BinaryNode> {

		private MyBoolean m = new MyBoolean(); 
	    private Stack<BinaryNode> nodes;
	    private BinaryNode root;
	    private T pre = null;

	    public preOrderIterator(BinaryNode root){
	    	this.root = root;
	    	m.setFalse();
	    	nodes = new Stack<BinaryNode>();
	    	if (root != null && root.element != null) {
	    		nodes.push(root);
	    	}
	    }
	    public boolean hasNext() {
	    	return !nodes.isEmpty();
	    }

	    public BinaryNode next() {
	    	
	    	if (!hasNext()) throw new NoSuchElementException();
	    	BinaryNode temp = nodes.pop();
	    	if (temp.rightChild != null && temp.rightChild.element!=null) nodes.push(temp.rightChild);
	    	if (temp.leftChild != null && temp.leftChild.element!=null) nodes.push(temp.leftChild);
	    	m.setTrue();
	    	//this.pre = temp.element;
	    	return temp;
	    }
		@Override
		public void remove() {
			
			
		}
	    
	}
	
	
	public BinaryNode leftRotation(BinaryNode t){
		count++;
		BinaryNode temp = t.rightChild;
		t.rightChild = temp.leftChild;
		temp.leftChild = t;
		t.height = Math.max(getHeight(t.leftChild), getHeight(t.rightChild)) + 1;
		temp.height = Math.max(getHeight(temp.rightChild), getHeight(t)) + 1;
		return temp;
	}
	
	public BinaryNode rightRotation(BinaryNode t){
		count++;
		BinaryNode temp = t.leftChild;
		t.leftChild = temp.rightChild;
		temp.rightChild = t;
		t.height = Math.max(getHeight(t.leftChild), getHeight(t.rightChild)) + 1;
		temp.height = Math.max(getHeight(temp.leftChild), getHeight(t)) + 1;
		return temp;
	}
	
	public BinaryNode doubleLeftRotation(BinaryNode t){
		t.rightChild = rightRotation(t.rightChild);
		t = leftRotation(t);
		return t;
	}
	
	public BinaryNode doubleRightRotation(BinaryNode t){
		t.leftChild = leftRotation(t.leftChild);
		return rightRotation(t);
	}
	
	public BinaryNode removeStep1(BinaryNode X, BinaryNode P, BinaryNode T, T e, MyBoolean b){
		if(X.leftChild.getColor() == Color.BLACK && X.rightChild.getColor() == Color.BLACK){
			X.color = Color.RED;
			
			if(e.compareTo(X.element) == 0){
				X = removeStep3(X, P, T, e, b);
			}
			else{
				if(e.compareTo(X.element) > 0){
					X.rightChild = removeStep2(X.rightChild, X, X.leftChild, e, b);
				}
				else if(e.compareTo(X.element) < 0){
					X.leftChild = removeStep2(X.leftChild, X, X.rightChild, e, b);
				}
				else{
					System.out.println("Error case in Remove Step 1");
				}
				
			}
		}else{
			//designate the root as X and proceed directly to step 2B
			X = removeStep2B(this.root, null, null, e, b);
		}
		if(X == null){
			X = new BinaryNode();
		}
		return X;
	}
	
	public BinaryNode removeStep2(BinaryNode X, BinaryNode P, BinaryNode T, T e, MyBoolean b){
		if(X.element == null){
			b.setFalse();
			return new BinaryNode();
		}
		
		if(X.leftChild.getColor() == Color.BLACK && X.rightChild.getColor() == Color.BLACK){
			X = removeStep2A(X, P, T, e, b);
		}
		else if(X.leftChild.getColor() == Color.RED || X.rightChild.getColor() == Color.RED){
			X = removeStep2B(X, P, T, e, b); 
		}
		if(X == null){
			X = new BinaryNode();
		}
		return X; 
	}
	
	public BinaryNode removeStep2A(BinaryNode X, BinaryNode P, BinaryNode T, T e, MyBoolean b){
		BinaryNode O = new BinaryNode();
		BinaryNode I = new BinaryNode();
		if(P.leftChild.element.compareTo(T.element) == 0){
			O = T.leftChild;
			I = T.rightChild;
		}else if(P.rightChild.element.compareTo(T.element) == 0){
			O = T.rightChild;
			I = T.leftChild;
		}else{
			System.out.println("Error case in remove Step 2A");
		}
		if(I == null){
			I = new BinaryNode();
		}
		if(O == null){
			O = new BinaryNode();
		}
		if(O.color == Color.BLACK && I.color == Color.BLACK){
			X = removeStep2A1(X, P, T, e, b);
		}else if(O.color == Color.BLACK && I.color == Color.RED){
			X = removeStep2A2(X, P, T, e, b);
		}else if(O.color == Color.RED && I.color == Color.BLACK){
			X = removeStep2A3(X, P, T, e, b);
		}else if(O.color == Color.RED && O.color == Color.RED){
			X = removeStep2A3(X, P, T, e, b);
		}
		if(X == null){
			X = new BinaryNode();
		}
		return X;
	}
	
	public BinaryNode removeStep2A1(BinaryNode X, BinaryNode P, BinaryNode T, T e, MyBoolean b){
		X.color = Color.RED;
		P.color = Color.BLACK;
		T.color = Color.RED;
		if(e.compareTo(X.element) == 0){
			BinaryNode temp = removeStep3(X, P, T, e, b);
			if(temp == null){
				temp = new BinaryNode();
			}
			return temp;
		}else{
			if(e.compareTo(X.element) > 0){
				X.rightChild = removeStep2(X.rightChild, X, X.leftChild, e, b);
			}
			else if(e.compareTo(X.element) < 0){
				X.leftChild = removeStep2(X.leftChild, X, X.rightChild, e, b);
			}
			else{
				System.out.println("Error case in Remove Step 2A1");
			}
			if(X == null){
				X = new BinaryNode();
			}
			return X;
		}
		
	}
	
	public BinaryNode removeStep2A2(BinaryNode X, BinaryNode P, BinaryNode T, T e, MyBoolean b){
		X.color = Color.RED;
		P.color = Color.BLACK;
		BinaryNode temp = new BinaryNode();
		if(P.leftChild.element == T.element){
			temp = doubleRightRotation(P);
		}
		else if(P.leftChild.element == T.element){
			temp = doubleLeftRotation(P);	
		}
		else{
			System.out.println("Error case in Remove Step 2A2");
		}
		
		if(e.compareTo(temp.leftChild.leftChild.element) == 0){
			X = temp.leftChild.leftChild;
			temp.leftChild.leftChild = removeStep3(temp.leftChild.leftChild, temp.leftChild, temp.leftChild.rightChild, e, b);
		}
		else if(e.compareTo(temp.rightChild.rightChild.element) == 0){
			X = temp.rightChild.rightChild;
			temp.rightChild.rightChild = removeStep3(temp.rightChild.rightChild,temp.rightChild,temp.rightChild.leftChild, e, b);
		}else{
			if(e.compareTo(X.element) > 0){
				X.rightChild = removeStep2(X.rightChild, X, X.leftChild, e, b);
			}
			else if(e.compareTo(X.element) < 0){
				X.leftChild = removeStep2(X.leftChild, X, X.rightChild, e, b);
			}
			else{
				System.out.println("Error case in Remove Step 2A2");
			}
		}
		return temp;
	}
	
	public BinaryNode removeStep2A3(BinaryNode X, BinaryNode P, BinaryNode T, T e, MyBoolean b){
		BinaryNode temp = new BinaryNode();
		
		P.color = Color.RED;
		T.color = Color.BLACK;
		X.color = Color.RED;
		
		if(X.element.compareTo(P.leftChild.element) == 0){
			T.rightChild.color = Color.BLACK;
			temp = leftRotation(P);
		}else if(X.element.compareTo(P.rightChild.element) == 0){
			T.leftChild.color = Color.BLACK;
			temp = rightRotation(P);
		}else{
			System.out.println("Error case in Remove Step 2A3");
		}
		
		if(e.compareTo(X.element) == 0){
			X = removeStep3(X, P, T, e, b);
		}else{
			if(e.compareTo(X.element) > 0){
				X.rightChild = removeStep2(X.rightChild, X, X.leftChild, e, b);
			}
			else if(e.compareTo(X.element) < 0){
				X.leftChild = removeStep2(X.leftChild, X, X.rightChild, e, b);
			}
			else{
				System.out.println("Error case in Remove Step 2A3");
			}
		}
		
		if(X == null){
			X = new BinaryNode();
		}
		return X;
	}
	
	public BinaryNode removeStep2B(BinaryNode X, BinaryNode P, BinaryNode T, T e, MyBoolean b){
		if(e.compareTo(X.element) == 0){
			X = removeStep3(X, P, T, e, b);
		}
		else{
			if(e.compareTo(X.element) > 0){
				if(X.rightChild.color == Color.BLACK){
					X.rightChild = removeStep2B2(X.rightChild, X, X.leftChild, e, b);
				}else if (X.rightChild.color == Color.RED){
					X.rightChild = removeStep2B1(X.rightChild, X, X.leftChild, e, b);
				}
				else{
					System.out.println("Error case in Remove Step 2B");
				}
			}else if(e.compareTo(X.element) < 0){
				if(X.leftChild.color == Color.BLACK){
					X.leftChild = removeStep2B2(X.leftChild, X, X.rightChild, e, b);
				}else if (X.leftChild.color == Color.RED){
					X.leftChild = removeStep2B1(X.leftChild, X, X.rightChild, e, b);
				}
				else{
					System.out.println("Error case in Remove Step 2B");
				}
			}
		}
		if(X == null){
			X = new BinaryNode();
		}
		return X;
	}
	
	public BinaryNode removeStep2B1(BinaryNode X, BinaryNode P, BinaryNode T, T e, MyBoolean b){
		if(e.compareTo(X.element) == 0){
			X = removeStep3(X, P, T, e, b);
		}
		else{
			if(e.compareTo(X.element) > 0){
				X.rightChild = removeStep2(X.rightChild, X, X.leftChild, e, b);
			}else if(e.compareTo(X.element) < 0){
				X.leftChild = removeStep2(X.leftChild, X, X.rightChild, e, b);
			}
			else{
				System.out.println("Error case in Remove Step 2B1");
			}
			
		}
		if(X == null){
			X = new BinaryNode();
		}
		return X;
		
	}
	
	public BinaryNode removeStep2B2(BinaryNode X, BinaryNode P, BinaryNode T, T e, MyBoolean b){
		if(X.element == null){
			b.setFalse();
			return null;
		}
		if(X.element.compareTo(P.leftChild.element) == 0){
			T = leftRotation(P);
		}else if(X.element.compareTo(P.rightChild.element) == 0){
			T = rightRotation(P);
		}else{
			System.out.println("Error Case in Remove Step2B2");
		}
		
		T.color = Color.BLACK;
		P.color = Color.RED;
		T.leftChild.leftChild = removeStep2(X, P, P.rightChild, e, b);
		return T;
	}
	
	public BinaryNode removeStep3(BinaryNode X, BinaryNode P, BinaryNode T, T e, MyBoolean b){
		if(X.leftChild != null && X.rightChild != null && X.leftChild.element != null && X.rightChild.element != null){
			T  v;
			BinaryNode current = X.leftChild;
			while(current.rightChild!=null && current.rightChild.element!=null){
				current = current.rightChild;
			}
			
			v = current.getElement();
			if(X.color == Color.RED){
				X.element = v;
				X.leftChild = removeStep2(X.leftChild, X, X.rightChild, v, b);
			}
			else{
				X = removeStep2B(X, P, T, v, b);
				X.element = v;	
			}

			return X;
		}
		else if(X.leftChild.element == null && X.rightChild.element == null){
			X.color = Color.RED;
			return null;
		}
		else if(X.leftChild != null || X.rightChild != null){
			if(X.color == Color.BLACK){
				if(X.leftChild == null){
					X.rightChild.color = Color.BLACK;
					return X.rightChild;
				}
				else if(X.rightChild == null){
					X.leftChild.color = Color.BLACK;
					return X.leftChild;
				}
			}else{
				
			}
		}
		if(X == null){
			X = new BinaryNode();
		}
		return X;
	}
	
	public void removeStep4(){
		this.root.color = Color.BLACK;
	}
	
	public boolean remove(T e){
		MyBoolean b = new MyBoolean();
		if(this.root.element != null){
			BinaryNode temp = removeStep1(this.root, null, null, e, b);
			if(temp == null){
				temp = new BinaryNode();
			}
			this.root = temp;
		}
		else{
			b.setFalse();
		}
		//if(b.getBoolean())
		removeStep4();
		return b.getBoolean();
	}
}



