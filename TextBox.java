package cs2012final;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/*
 * Name:        Juan La Serna
 * CIN:         401689259
 * Course:      CS 2012
 * Section:     01
 * Description: FX Class
 */

public class TextBox extends Text {
	
	
	public TextBox() {
		super("Enter a command");
		super.setFont(Font.font("Arial", FontWeight.BOLD, 20));        
	}
	
	
	
}
