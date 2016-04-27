package editortrees;

// A node in a height-balanced binary tree with rank.
// Except for the NULL_sNODE (if you choose to use one), one node cannot
// belong to two different trees.

public class Node {

	enum Code {
		SAME, LEFT, RIGHT
	};

	// The fields would normally be private, but for the purposes of this class,
	// we want to be able to test the results of the algorithms in addition to
	// the
	// "publicly visible" effects

	char element;
	Node left, right; // subtrees
	int rank; // inorder position of this node within its own subtree.
	Code balance;
	Node parent; // You may want this field.


	// Feel free to add other fields that you find useful

	// You will probably want to add several other methods
	Node() {
		this.element = '\0';
		this.left = EditTree.NULL_NODE;
		this.right = EditTree.NULL_NODE;
		this.rank = -1;
		this.balance = Code.SAME;
		this.parent = EditTree.NULL_NODE;

	}

	Node(char element, Node left, Node right, int rank, Code balance,
			Node parent) {
		this.element = element;
		this.left = left;
		this.right = right;
		this.rank = rank;
		this.balance = balance;
		this.parent = parent;

	}

	// For the following methods, you should fill in the details so that they
	// work correctly
	public int height() {
		// TODO Milestone 1
		if (this == EditTree.NULL_NODE) {
			return -1;
		}
		return Math.max(left.height() + 1, right.height() + 1);
	}

	public int size() {
		if (this == EditTree.NULL_NODE) {
			return 0;
		} else {
			return 1 + this.left.size() + this.right.size();
		}
	}

	public void add(char c, int pos) throws IndexOutOfBoundsException {
		if(this.rank == -1){
			if(pos!=0){
				throw new IndexOutOfBoundsException();
			}
		}
		if(pos == this.rank){
			if(this.left == EditTree.NULL_NODE){
				if(this.balance == Code.SAME){
					this.balance = Code.LEFT;
				}
				else if(this.balance == Code.RIGHT){
					this.balance = Code.SAME;
				}
				this.rank++;
				this.left = new Node(c, EditTree.NULL_NODE, EditTree.NULL_NODE, pos, Code.SAME, this);
			}
			else{
				this.rank++;
				this.left.add(c,pos);
			}

		}
		else if(pos > this.rank){
			if(this.right == EditTree.NULL_NODE){
				if(pos != this.rank+1){
					throw new IndexOutOfBoundsException();
				}
				this.right = new Node(c, EditTree.NULL_NODE, EditTree.NULL_NODE, this.rank+1, Code.SAME, this);
			}
			else{
				this.right.add(c,pos);
			}
		}
		else if(pos < this.rank){
			this.rank++;
			if(this.left == EditTree.NULL_NODE){
				if(pos!= this.rank-1){
					throw new IndexOutOfBoundsException();
				}
				this.left = new Node(c, EditTree.NULL_NODE, EditTree.NULL_NODE, pos, Code.SAME, this);
			}
			else{
				this.left.add(c, pos);
			}
		}
		else{
			throw new IndexOutOfBoundsException();
		}
//		// TODO: Milestone 1
//		if (pos == this.rank) {
//			Node t = this.left;
//			while(t.isRightThread!=true){
//				t = t.right;
//			}
//			t.right = new Node(c,t,t.right,t.rank+1,Code.SAME,t,true,true);
//			Node n = t.right.right;
//			while(n.right!=EditTree.NULL_NODE){
//				n.rank++;
//				n = n.right;
//			}
//		} 		
//		else if (pos > this.rank) {
//			if (this.right == EditTree.NULL_NODE) {
//				this.right = new Node(c, this, this.right, pos, Code.SAME,
//						this, true, true);
//			} else {
//				this.right.add(c, pos);
//			}
//		} else if (pos < this.rank) {
//			if (this.left == EditTree.NULL_NODE) {
//				this.left = new Node(c, this.left, this, pos, Code.SAME, this,
//						true, true);
//			} else {
//				this.left.add(c, pos);
//			}
//		} else {
//			throw new IndexOutOfBoundsException();
//		}
	}

	public void add(char c) {
		// TODO: Milestone 1
		if (this.right == EditTree.NULL_NODE) {
			this.right = new Node(c, EditTree.NULL_NODE, EditTree.NULL_NODE, 10000,
					Code.SAME, this);
		} else {
			this.right.add(c);
		}

	}

	public void rotate() {
		if (this.balance == Code.SAME) {
			this.parent.rotate();
		} else if (this.balance == Code.LEFT) {
			Node temp = this;

		} else if (this.balance == Code.RIGHT) {

		}
	}

	public String toString() {
		StringBuilder result = new StringBuilder();
		if (this == EditTree.NULL_NODE) {
			return "";
		} else {
			result.append(this.left.toString());
			result.append(this.element);
			result.append(this.right.toString());
			return result.toString();
		}
	}

}