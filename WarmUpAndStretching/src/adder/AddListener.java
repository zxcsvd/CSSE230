package adder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextField;

public class AddListener implements ActionListener{
	
	public JTextField textField;
	public int previous = 0;
	public ArrayList<Boolean> pressed;
	public int temp = 0;

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(pressed.get(1)){
			clearPrevious();
			
		}
		else{
			this.pressed.set(0, true);
			this.temp = Integer.parseInt(this.textField.getText().substring(0, this.textField.getText().length()));
		}
		int result = this.previous + this.temp;
		String text = new String(result+"");
		this.textField.setText(text);
		this.previous = result;
	}
	/**
	 * pass the parameter: frame
	 *
	 * @param frame
	 */
	public AddListener(JTextField textField, ArrayList<Boolean> pressed){
		this.textField = textField;
		this.pressed = pressed;
	}
	
	public void clearPrevious(){
		this.textField.setText("0");
		this.temp = 0;
		this.previous = 0;
		this.pressed.set(1, false);
		
	}

}
