import java.util.Iterator;


public class AVLTree <T extends Comparable<? super T>> implements Iterable<T>{

	private BinaryNode root = new BinaryNode();
	
	
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new PreOrderIterator();
	}

	public boolean insert(T t) {
		MyBoolean b = new MyBoolean(t);
		this.root = this.root.insert(this.root, b);
		return b.getValid();
	}

	public int getRotationCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean remove(T t) {
		return false;
		// TODO Auto-generated method stub
		
	}
	
	private class BinaryNode{
		private T element = null;
		private BinaryNode leftChild = null;
		private BinaryNode rightChild = null;
		private int height = -1;
		//private int size = 0;
		
		private BinaryNode(T element){
			this.element = element;
			this.height = 0;
			
		}

		public BinaryNode() {
			// TODO Auto-generated constructor stub
		}
		
		private BinaryNode insert(BinaryNode node, MyBoolean b){
			if(node.element == null){
				b.setTrue();
				return new BinaryNode(b.value);
			}
			if(node.element.compareTo(b.value) == 0){
				b.setFalse();
				return this;
			}
			return node;
		}
		
		private BinaryNode remove(BinaryNode node, MyBoolean b){
			if(node.element.compareTo(b.value) > 0){
				node.leftChild = node.leftChild.remove(node.leftChild, b);
			}
			else if(node.element.compareTo(b.value) < 0){
				node.rightChild = node.rightChild.remove(node.rightChild, b);
			}
			else if(node.element.compareTo(b.value) == 0){
				
			}
			return node;
		}

		
	}
	
	private class PreOrderIterator implements Iterator<T> {

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class InOrderIterator implements Iterator<T> {

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class MyBoolean{
		private boolean validOperation;
		private T value;
		private MyBoolean(T value){
			this.validOperation = false;
			this.value = value;
		}
		
		public void setTrue(){
			this.validOperation = true;
		}
		
		public void setFalse(){
			this.validOperation = false;
		}
		
		public boolean getValid(){
			return this.validOperation;
		}
	}
}
