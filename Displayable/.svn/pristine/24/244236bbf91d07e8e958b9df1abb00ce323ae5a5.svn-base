import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A wrapper class for binary trees that can display the wrapped tree in a
 * window.
 * 
 * @author Curt Clifton. Created Jan 24, 2008.
 */
public class DisplayableBinaryTree {

	private int width;
	private int height;
	private static int margin = 0;
	
	private BinaryTree tree;
	private int radius;

	/**
	 * Constructs a new displayable binary tree, set to default to the given
	 * window size for display.
	 * 
	 * @param tree
	 * @param windowWidth
	 *            in pixels
	 * @param windowHeight
	 *            in pixels
	 */
	public DisplayableBinaryTree(BinaryTree tree, int windowWidth,
			int windowHeight) {

		this.width = windowWidth;
		this.height = windowHeight;
		this.tree = tree;

	}

	/**
	 * Creates a new window, using the current size information stored in this,
	 * and renders the current state of the tree wrapped by this.
	 */
	public void display() {
		JFrame window = new JFrame();
		window.setSize(this.width, this.height);
		this.margin = Math.min(this.width / 8, this.height / 8)/this.tree.getHeight();
		this.radius = (this.width - (2 * this.margin)) / (tree.size() * 2);

		// System.out.println(this.margin + " "+ this.tree.getHeight() +
		// " "+BinaryNode.leaves(this.tree.getRoot()) + " " +this.radius);
		//
		int heightOfTree= this.tree.getHeight();
		int buffer = (this.height - (this.tree.getHeight() * this.radius * 2) - 2 * this.margin)
				/ this.tree.getHeight();
		ArrayList<BinaryNode> treeList = this.tree.getRoot().inOrderList();
		DrawPanel panel = new DrawPanel(treeList, this.margin, this.radius, buffer,heightOfTree,window);

		window.add(panel);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

	}

	/**
	 * Sets the default size for the next window displayed.
	 * 
	 * @param windowWidth
	 *            in pixels
	 * @param windowHeight
	 *            in pixels
	 */
	public void setSize(int windowWidth, int windowHeight) {
		this.width = windowWidth;
		this.height = windowHeight;
	}

}
