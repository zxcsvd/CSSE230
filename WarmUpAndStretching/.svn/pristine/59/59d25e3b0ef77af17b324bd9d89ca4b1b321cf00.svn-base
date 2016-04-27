package adder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextField;



public class CButtonListener implements ActionListener{
	public JTextField textField;
	public ArrayList<Boolean> pressed;
	@Override
	public void actionPerformed(ActionEvent e) {
		this.textField.setText("0");
		this.pressed.set(1, true);
		
		
	}
	
	public CButtonListener(JTextField textField, ArrayList<Boolean> pressed){
		this.textField = textField;
		this.pressed = pressed;
		
	}
	
	
}
