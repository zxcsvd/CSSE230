import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sun.org.mozilla.javascript.internal.JavaAdapter;

public class DrawPanel extends JPanel {
	private ArrayList<BinaryNode> treeList;
	private int margin;
	private int radius;
	private int size;
	private int buffer;
	private int heightOfTree;

	public DrawPanel(ArrayList<BinaryNode> treeList, int margin, int radius,
			int buffer, int heightOfTree, JFrame frame) {
		this.treeList = treeList;
		this.margin = margin;
		this.radius = radius ;
		this.size = this.treeList.size();
		this.buffer = buffer;
		this.heightOfTree = heightOfTree;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Font font = new Font("Times New Roman", Font.PLAIN, radius);
		g2.setFont(font);
		int nodeheight = 0;
		for (int i = 0; i < DrawPanel.this.size; i++) {
			nodeheight = this.treeList.get(i).depth(this.treeList.get(i))-1;
			g2.draw(circleObj(i, nodeheight));
			g2.drawString("" + this.treeList.get(i).getElement(), this.margin
					+ (radius * 2 * i) + radius - (radius / 4),
					(this.heightOfTree - nodeheight) * (radius * 2 + buffer)
							+ radius + (radius / 3));
		}
	}

	public Ellipse2D circleObj(int i, int nodeheight) {
		return new Ellipse2D.Double(this.margin + (radius * 2 * i),
				(this.heightOfTree - nodeheight) * (radius * 2 + buffer),
				radius * 2, radius * 2);
	}

}
