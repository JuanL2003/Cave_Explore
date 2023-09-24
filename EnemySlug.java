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

public class EnemySlug extends Circle {
	
	private int xLoc = 0;
	private int yLoc = 0;
	
	public EnemySlug() {
		super(20.0);
        super.setStroke(Color.BLACK);
        super.setFill(Color.GREEN);
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
	
	public static void spawnEnemy(EnemySlug slug1, int rowMax, int columnMin, GridPane board, gridLayout[][] grid) {
    	boolean spawnLoop = true;
    	
    	while(spawnLoop == true) {
    		//spawn the enemy
    		slug1.setxLoc((int)(Math.random()*(rowMax - 0) + 0));  
    		slug1.setyLoc((int)(Math.random()*(columnMin - 0) + 0));  
            
        	
        	if(grid[slug1.getYLoc()][slug1.getXLoc()].getIsOccupied() == false) {
        		board.add(slug1, slug1.getXLoc(), slug1.getYLoc());
        		GridPane.setHalignment(slug1, HPos.CENTER);
                grid[slug1.getYLoc()][slug1.getXLoc()].setIsOccupied(true);
                spawnLoop = false;
            }
    	}
    	
    }

	public static boolean enemyActivate(Player player, EnemySlug slug) {
		if(slug.getXLoc() == player.getXLoc() && slug.getYLoc() == player.getYLoc()) {
			return true;
		}
		else {
			return false;
		}
	}

}
