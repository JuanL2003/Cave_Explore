package cs2012final;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
 * Name:        Juan La Serna
 * CIN:         401689259
 * Course:      CS 2012
 * Section:     01
 * Description: FX Class
 */

public class gridLayout extends Rectangle {
	//data fields
	private boolean isOccupied = false;
	private String whosOccupied = "";
	
	//Constructors
	public gridLayout() {
		super(50.0, 50.0);
		super.setStroke(Color.BLACK);
        super.setFill(Color.WHITE);
        
	}

	//Methods
	public boolean getIsOccupied() {
		return isOccupied;
	}


	public void setIsOccupied(boolean b) {
		this.isOccupied = b;
	}
	
	public String getWhosOccupied() {
		return whosOccupied;
	}


	public void setWhosOccupied(String whosOccupied) {
		this.whosOccupied = whosOccupied;
	}
	
	public void onOccupiedSpace() {
		
	}
	
}
