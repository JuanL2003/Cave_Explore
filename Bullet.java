package cs2012final;

import javafx.geometry.HPos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
 * Name:        Juan La Serna
 * CIN:         401689259
 * Course:      CS 2012
 * Section:     01
 * Description: FX Class
 */

public class Bullet extends Rectangle {
	
	private int xLoc = 0;
	private int yLoc = 0;
	
	public Bullet() {
		super(10.0, 20.0);
        super.setStroke(Color.BLACK);
        super.setFill(Color.YELLOW);
	}
	
	public int getXLoc() {
		return this.xLoc;
	}
	public int getYLoc() {
		return this.yLoc;
	}

	public void setxLoc(int xLoc) {
		this.xLoc = xLoc;
	}

	public void setyLoc(int yLoc) {
		this.yLoc = yLoc;
	}
	
	public static void spawnAmmo(Bullet ammo, int rowMax, int columnMin, GridPane board, gridLayout[][] grid) {
    	boolean spawnLoop = true;
    	
    	while(spawnLoop == true) {
    		//spawn the enemy
    		ammo.setxLoc((int)(Math.random()*(rowMax - 0) + 0));  
    		ammo.setyLoc((int)(Math.random()*(columnMin - 0) + 0));  
            
        	
        	if(grid[ammo.getYLoc()][ammo.getXLoc()].getIsOccupied() == false) {
        		board.add(ammo, ammo.getXLoc(), ammo.getYLoc());
                GridPane.setHalignment(ammo, HPos.CENTER);
                grid[ammo.getYLoc()][ammo.getXLoc()].setIsOccupied(true);
                spawnLoop = false;
            }
    	}
    	
    }
	
	public static boolean bulletOn(Player player, Bullet ammo) {
		if(ammo.getXLoc() == player.getXLoc() && ammo.getYLoc() == player.getYLoc()) {
			return true;
		}
		else {
			return false;
		}
	}

}