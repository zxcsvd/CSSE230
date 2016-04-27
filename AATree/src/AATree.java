import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class AATree <T extends Comparable<? super T>>{
	
	public int rotationCount = 0;
	public BinaryNode root = null;
	public T removeCache = null;
	public MyBoolean validOperation = new MyBoolean(true);
	
	
	
	public class MyBoolean{
		public boolean b = false;
		
		public MyBoolean(){
			
		}
		
		
		public MyBoolean(boolean b){
			this.b = b;
		}
		
		public void setFalse(){
			this.b = false;
		}
		
		public void setTrue(){
			this.b = true;
		}
		
		public boolean getBoolean(){
			return this.b;
		}
		
	}

	
	
	public class BinaryNode {
		
		public T element = null;
		public int level = 0;
		public BinaryNode leftChild = null;
		public BinaryNode rightChild = null;
		public boolean leftH = false;
		public boolean rightH = false;
		
		public BinaryNode(T element){
			this.element = element;
		}
		
		public BinaryNode(T element, int level){
			this.level = level;
			this.element = element;
		}
		
		public T getElement() {
			return this.element;
		}

		public int getLevel() {
			return this.level;
		}
		
		public BinaryNode insert(T e, MyBoolean b){
			if(e.compareTo(this.element) == 0){
				b.setFalse();
				return this;
			}
			
			BinaryNode temp;
			if(e.compareTo(this.element) > 0){
				
				
				if(this.rightChild!=null){
					temp = this.rightChild.insert(e, b);
				}
				else{
					temp = new BinaryNode(e, 1);
				}
				if(readLeftH(temp) == 1){
					temp = skew(temp);
				}
				if(readRightH(temp) == 2){
					temp = split(temp);
				}
				
				
				this.rightChild = temp;
			}else if(e.compareTo(this.element) < 0){
				if(this.leftChild!=null){
					temp = this.leftChild.insert(e, b);
				}
				else{
					temp = new BinaryNode(e, 1);
				}
				if(readLeftH(temp) == 1){
					temp = skew(temp);
				}
				if(readRightH(temp) == 2){
					temp = split(temp);
				}
				this.leftChild = temp;	
			}
			return this;
			
		}

		public BinaryNode getLeftChild() {
			return this.leftChild;
		}
		
		
		
		public int size(BinaryNode b){
			if(b == null){
				return 0;
			}
			return size(b.leftChild) + 1 + size(b.rightChild);
		}

		public BinaryNode remove(T e, MyBoolean b) {
			if(e.compareTo(this.element) == 0){
				if(this.rightChild!=null){
					this.rightChild = findRightMin(this.rightChild);
					this.element = removeCache;
					this.level = newLevel(this);
				}
				else{
					return null;
				}
			}
			else if(e.compareTo(this.element) > 0){
				this.rightChild = this.rightChild.remove(e, b);
				
				BinaryNode temp;
				temp = this.rightChild;
				
				if(readLeftH(temp) == 1){
					temp = skew(temp);
				}
				if(readRightH(temp) == 2){
					temp = split(temp);
				}
				this.rightChild = temp;
				this.level = newLevel(this);
				if(this.rightChild!=null){
					if(this.level < this.rightChild.level){
						this.rightChild.level = this.level;
						
						this.rightChild = skewOnly(this.rightChild);
						this.rightChild.rightChild.level = newLevel(this.rightChild.rightChild);
						BinaryNode alpha = split(this);
						if(readLeftH(alpha) == 1){
							alpha = skew(alpha);
							return alpha;
						}

					}
					
				}
				
			}
			else if(e.compareTo(this.element) < 0){
				if(this.leftChild!=null)
				this.leftChild = this.leftChild.remove(e, b);
				BinaryNode temp;
				temp = this.leftChild;
				if(readLeftH(temp) == 1){
					temp = skew(temp);
				}
				if(readRightH(temp) == 2){
					temp = split(temp);
				}
				
				this.leftChild = temp;
				this.level = newLevel(this);
				if(this.rightChild!=null){
					if(this.level < this.rightChild.level){
						this.rightChild.level = this.level;
						
						this.rightChild = skewOnly(this.rightChild);
						if(this.rightChild!=null)
						this.rightChild.rightChild.level = newLevel(this.rightChild.rightChild);
						BinaryNode alpha = split(this);
//						if(readLeftH(alpha) == 1){
//							alpha = skew(alpha);
//						}
						return alpha;

					}
					
				}
				
				
			}
			return this;
		}

		public BinaryNode getRightChild() {
			return this.rightChild;
		}
		
		public BinaryNode dealRightMin(T e){
			if(e.compareTo(this.element) == 0){
				if(this.rightChild!=null){
					return this.rightChild;
				}
				else{
					return null;
				}
			}
			else if(e.compareTo(this.element) > 0){
				BinaryNode temp;
				temp = this.rightChild.dealRightMin(e);
				
				if(readLeftH(temp) == 1){
					temp = skew(temp);
				}
				if(readRightH(temp) == 2){
					temp = split(temp);
				}
				this.rightChild = temp;
				this.level = newLevel(this);
				if(this.rightChild!=null){
					if(this.level < this.rightChild.level){
						this.rightChild.level = this.level;
						
						this.rightChild = skewOnly(this.rightChild);
						if(this.rightChild!=null)
						this.rightChild.rightChild.level = newLevel(this.rightChild.rightChild);
						BinaryNode alpha = split(this);
						if(readLeftH(alpha) == 1){
							alpha = skew(alpha);
							return alpha;
						}

					}
					
				}
				
			}
			else if(e.compareTo(this.element) < 0){
				BinaryNode temp;
				temp = this.leftChild.dealRightMin(e);
				
				if(readLeftH(temp) == 1){
					temp = skew(temp);
				}
				if(readRightH(temp) == 2){
					temp = split(temp);
				}
				
				this.leftChild = temp;
				this.level = newLevel(this);
				if(this.rightChild!=null){
					if(this.level < this.rightChild.level){
						this.rightChild.level = this.level;
						
						this.rightChild = skewOnly(this.rightChild);
						if(this.rightChild!=null)
						this.rightChild.rightChild.level = newLevel(this.rightChild.rightChild);
						BinaryNode alpha = split(this);
						if(readLeftH(alpha) == 1){
							alpha = skew(alpha);
							return alpha;
						}

					}
					
				}
			}
			return this;
		}
		
	}
	
	public BinaryNode findRightMin(BinaryNode r){
		T tempMin = null;
		BinaryNode current = r;
		if(current == null){
			return null;
		}
		else if(current.leftChild == null){
			tempMin = current.getElement();
			removeCache = tempMin;
			return current.rightChild;
		}else{
			while(current.leftChild != null){
				current = current.leftChild;
			}
			tempMin = current.getElement();
			removeCache = tempMin;
			BinaryNode temp;
			temp = r.dealRightMin(tempMin);
			if(readLeftH(temp) == 1){
				temp = skew(temp);
			}
			if(readRightH(temp) == 2){
				temp = split(temp);
			}
			temp.level = newLevel(temp);
			r = temp;
			return r;
		}
	}
	

	
	

	
	public int readRightH(BinaryNode x){
		if(x == null){
			return -1;
		}
		
		int count = 0;
		while(x.rightChild!=null && x.rightChild.level == x.level){
			x = x.rightChild;
			count++;
		}
		return count;
	}
	
	public int readLeftH(BinaryNode x){
		if(x == null){
			return -1;
		}
		
		int count = 0;
		while(x.leftChild!=null && x.leftChild.level == x.level){
			x = x.leftChild;
			count++;
		}
		return count;
	}
	
	public BinaryNode split(BinaryNode x){
		this.rotationCount++;
		if(x.rightChild!=null)
		x.rightChild.level++;
		BinaryNode a = x.leftChild;
		BinaryNode r = x.rightChild;
		
		BinaryNode b;
		if(r!=null){
			b = r.leftChild;
		}
		else{
			b = null;
		}
		x.rightChild = b;
		if(r!=null){
			r.leftChild = x;
		}else{
			
		}
		
		return r;
	}
	
	public BinaryNode skew(BinaryNode x){
		this.rotationCount++;
		BinaryNode p = x.leftChild;
		BinaryNode b = p.rightChild;
		x.leftChild = b;
		p.rightChild = x;
		if(readRightH(p)==2){
			p = split(p);
		}
		return p;
	}
	
	public BinaryNode skewOnly(BinaryNode x){
		this.rotationCount++;

		BinaryNode p = x.leftChild;
		BinaryNode b;
		
		if(p!=null){
			b = p.rightChild;
		}else{
			b = null;
		}
		
		x.leftChild = b;
		if(p!=null){
			p.rightChild = x;
		}
		
		return p;
	}

	public void insert(T e) {
		if(this.root == null){
			this.root = new BinaryNode(e, 1);
		}else{
			MyBoolean valid = new MyBoolean(false);
			BinaryNode temp;
			temp = this.root.insert(e, valid);
			if(readRightH(temp) == 2){
				temp = split(temp);
			}
			if(readLeftH(temp) == 1){
				temp = skew(temp);
			}
			this.root = temp;
		}
		
	}
	
	public void remove(T e) {
		
		MyBoolean valid = new MyBoolean(false);
		
		if(this.root == null){
			valid.setFalse();
		}else{
			BinaryNode temp;
			temp = this.root.remove(e, valid);
			if(readLeftH(temp) == 1){
				temp = skew(temp);
			}
			if(readRightH(temp) == 2){
				temp = split(temp);
			}
			if(temp!=null){
				temp.level = newLevel(temp);
			}
			
			this.root = temp;
		}
		
	}

	public ArrayList<Object> toArrayList() {
		ArrayList<Object> resultList = new ArrayList<Object>();
		if(this.root!=null){
			preOrderIterator it = new preOrderIterator(this.root);
			while(it.hasNext()){
				BinaryNode temp = (BinaryNode) it.next();
				resultList.add(temp);
			}
		}
		return resultList;
	}



	public int size() {
		if(this.root==null){
			return 0;
		}
		int temp = this.root.size(this.root);

		return temp;
	}

	private class preOrderIterator implements Iterator {

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

	    public BinaryNode next() {
	    	if (!hasNext()) throw new NoSuchElementException();
	    	BinaryNode temp = nodes.pop();
	    	if (temp.rightChild != null) nodes.push(temp.rightChild);
	    	if (temp.leftChild != null) nodes.push(temp.leftChild);
	    	m.setTrue();
	    	this.pre = temp.element;
	    	return temp;
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
	
	public int newLevel(BinaryNode b){
		int l = 0;
		int r = 0;
		int o = b.getLevel();
		if(b.leftChild != null){
			l = b.leftChild.getLevel();
		}
		if(b.rightChild != null){
			r = b.rightChild.getLevel();
		}
		if(l == r){
			return l+1;
		}
		else if(l < r){
			if(o-l == 2){
				return o-1;
			}
			return r;
		}
		else{
			if(o-r == 2){
				return o-1;
			}
			return l;
		}
		
	}
	
	
}
