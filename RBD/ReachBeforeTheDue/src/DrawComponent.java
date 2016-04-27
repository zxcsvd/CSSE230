import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.ArrayList;

import javax.swing.JComponent;


public class DrawComponent extends JComponent {
	
	public ArrayList<Shape> components= new ArrayList<Shape>(); 
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		 for(Shape component: this.components){
			 g2.draw(component);
		 }
	}
	 
	 public void add(Shape newCompoment){
		 this.components.add(newCompoment);
	 }

}
