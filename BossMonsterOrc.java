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

public class BossMonsterOrc extends Circle {
	
	private int xLoc = 0;
	private int yLoc = 0;
	
	public BossMonsterOrc() {
		super(20.0);
        super.setStroke(Color.BLACK);
        super.setFill(Color.RED);
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
	
	public static void spawnBoss(BossMonsterOrc boss, int rowMax, int columnMin, GridPane board, gridLayout[][] grid) {
    	boolean spawnLoop = true;
    	
    	while(spawnLoop == true) {
    		//spawn the enemy
    		boss.setxLoc((int)(Math.random()*(rowMax - 0) + 0));  
    		boss.setyLoc((int)(Math.random()*(columnMin - 0) + 0));  
            
        	
        	if(grid[boss.getYLoc()][boss.getXLoc()].getIsOccupied() == false) {
        		board.add(boss, boss.getXLoc(), boss.getYLoc());
                GridPane.setHalignment(boss, HPos.CENTER);
                grid[boss.getYLoc()][boss.getXLoc()].setIsOccupied(true);
                spawnLoop = false;
            }
    	}
    	
    }
	
	public static boolean bossOn(Player player, BossMonsterOrc boss) {
		if(boss.getXLoc() == player.getXLoc() && boss.getYLoc() == player.getYLoc()) {
			return true;
		}
		else {
			return false;
		}
	}

	
	public static String toString(BossMonsterOrc boss) {
		return(String.valueOf(boss.getXLoc()) + String.valueOf(boss.getYLoc()));
	}
}
