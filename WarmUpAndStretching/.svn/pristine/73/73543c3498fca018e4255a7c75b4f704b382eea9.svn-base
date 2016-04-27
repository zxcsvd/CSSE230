package adder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextField;

public class NumButtonListener implements ActionListener{
	/**
	 * the window frame.
	 */
	public JTextField textField;
	public int num;
	public ArrayList<Boolean> pressed;
	
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!this.pressed.get(0)){
				String text = new String(this.textField.getText());
				if(text.equals("0")){
					this.textField.setText(this.num+"");				
				}
				else{
					this.textField.setText(text+this.num+"");	
				}
			}
			else{
				this.textField.setText(this.num+"");	
				this.pressed.set(0, false);
				}
		}
		/**
		 * pass the parameter: frame
		 *
		 * @param frame
		 */
		public NumButtonListener(JTextField textField, int num, ArrayList<Boolean> pressed){
			this.textField = textField;
			this.num = num;
			this.pressed = pressed;
			
		
		}


	}