import java.util.ArrayList;


public class SkipList <T extends Comparable<? super T>>{
	
	public Node root = new Node(null,20);
	public T element;
	
	public SkipList(){
		
		Node current = this.root;
		Node previous = null;
		for (int i=19; i>=0; i--){
			Node empty = new Node(null);
			empty.level = i;
			if(i>0){
				current.down = empty;
			}
			if(i<20){
				current.up = previous;
			}
			previous = current;
			current = current.down;			
		}

	}
	
	public void insert(T i, int j) {
		Node current = this.root;
		ArrayList<Node> list = new ArrayList<Node>();
		while(current != null && current.level >= 1){
			if(current.level <= j){
				while(current.element != null && current.element.compareTo(i) < 0){
					current = current.next;
				}
				list.add(current);
				current = current.down;
			}

		}
		
		Node lastBase = null;
		for(int k=list.size(); k>=0; k--){
			Node p = list.get(k).previous;
			Node n = list.get(k);
			
			Node newNode = new Node(i,n.level);
			if(p!=null){
				p.next = newNode;
			}
			newNode.next = n;
			newNode.previous = p;
			n.previous = newNode;
			if(lastBase != null){
				lastBase.up = newNode;
				newNode.down = lastBase;
			}
			lastBase = newNode;
			
		}
		
	}
	
	
	public class Node{
		ArrayList<Node> links;
		T element;
		Node next = null;
		Node down = null;
		Node up = null;
		Node previous = null;
		int level = 1;
		
		public Node(T e){
			this.element = e;
		}
		
		public Node(T e, int l){
			this.element = e;
			this.level = l;
		}
//		public Node(T e){
//			
//		}
		
		public ArrayList<Node> getLinks() {
			return this.links;
			// TODO Auto-generated method stub
			
		}

		public T getElement() {
			return this.element;
			// TODO Auto-generated method stub

		}
		
	}


	public void remove(T i) {
		// TODO Auto-generated method stub
		
	}


	public void insert(T i) {
		// TODO Auto-generated method stub
		
	}
}
