package cs2012final;

import javafx.geometry.HPos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/*
 * Name:        Juan La Serna
 * CIN:         401689259
 * Course:      CS 2012
 * Section:     01
 * Description: FX Class
 */

public class WallSpike extends Circle {
	private int xLoc = 0;
	private int yLoc = 0;
	
	public WallSpike() {
		super(20.0);
        super.setStroke(Color.BLACK);
        super.setFill(Color.PURPLE);
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
	
	public static void spawnTrap(WallSpike trap1, int rowMax, int columnMin, GridPane board, gridLayout[][] grid) {
    	boolean spawnLoop = true;
    	
    	while(spawnLoop == true) {
    		//spawn the enemy
    		trap1.setxLoc((int)(Math.random()*(rowMax - 0) + 0));  
    		trap1.setyLoc((int)(Math.random()*(columnMin - 0) + 0));  
            
        	
        	if(grid[trap1.getYLoc()][trap1.getXLoc()].getIsOccupied() == false) {
        		board.add(trap1, trap1.getXLoc(), trap1.getYLoc());
                GridPane.setHalignment(trap1, HPos.CENTER);
                grid[trap1.getYLoc()][trap1.getXLoc()].setIsOccupied(true);
                grid[trap1.getYLoc()][trap1.getXLoc()].setWhosOccupied("Trap");
                spawnLoop = false;
            }
    	}
    	
    }
	
	public static boolean trapTriggered(Player player, WallSpike trap) {
		if(trap.getXLoc() == player.getXLoc() && trap.getYLoc() == player.getYLoc()) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
