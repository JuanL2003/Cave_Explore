package cs2012final;

import javafx.geometry.HPos;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/*
 * Name:        Juan La Serna
 * CIN:         401689259
 * Course:      CS 2012
 * Section:     01
 * Description: FX Class
 */

public class Player extends Polygon{
	private int xLoc = 0;
	private int yLoc = 0;
	private String playerDirection = "UP";
	private int shot = 3;
	private int steps = 1;
	private int movement = 0;
	
	//Constructors
	public Player() {
		super(20.0, 0.0, 20.0, 30.0, 0.0, 15.0);
        super.setStroke(Color.BLACK);
        super.setFill(Color.BLUE);
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
	
	public String getPlayerDirection() {
		return this.playerDirection;
	}

	public void setPlayerDirection(String playerDirection) {
		this.playerDirection = playerDirection;
	}
	
	public int getShot() {
		return this.shot;
	}

	public void setShot(int shot) {
		this.shot = shot;
	}
	
	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}
	
	public int getMovement() {
		return movement;
	}

	public void setMovement(int movement) {
		this.movement = movement;
	}
	
	//Methods
	public static void relocatePlayer(KeyCode code, Player p, int maxRow, int maxColumn, TextBox message)  {
		if (code == KeyCode.UP) {
        	if(p.getYLoc() <= 0) {
        		message.setText("Invalid command");
        	}
        	else {
        		p.setMovement(p.getMovement() + 1);
            	if(p.getMovement() == p.getSteps()) {
            		p.setyLoc(p.getYLoc() - 1);
                	GridPane.setRowIndex(p, p.getYLoc());
                    message.setText("Ammo: "+ p.shot + "\n" + "Position: " + ToString(p));
                	p.getPoints().setAll(10.0, 10.0, 35.0, 10.0, 22.5, -15.0);
                	p.setPlayerDirection("UP");
                	p.setMovement(0);
                	
            	}
        		
        	} 	
        }
        else if(code == KeyCode.DOWN) {
        	if(p.getYLoc() >= (maxColumn - 1)) {
        		message.setText("Invalid command");
        	}
        	else {
        		p.setMovement(p.getMovement() + 1);
            	if(p.getMovement() == p.getSteps()) {
            		p.setyLoc(p.getYLoc() + 1);
                	GridPane.setRowIndex(p, p.getYLoc());
                    message.setText("Ammo: "+ p.shot + "\n" + "Position: " + ToString(p));
                	p.getPoints().setAll(10.0, 10.0, 35.0, 10.0, 22.5, 35.0);
                	p.setPlayerDirection("DOWN");
                	p.setMovement(0);
            	}
        		
        	}
        }
        else if(code == KeyCode.RIGHT) {
        	if(p.getXLoc() >= (maxRow - 1)) {
        		message.setText("Invalid command");
        		
        	}
        	else {
        		p.setMovement(p.getMovement() + 1);
            	if(p.getMovement() == p.getSteps()) {
            		p.setxLoc(p.getXLoc() + 1);
                	GridPane.setColumnIndex(p, p.getXLoc());
                    message.setText("Ammo: "+ p.shot + "\n" + "Position: " + ToString(p));
                	p.getPoints().setAll(20.0, 0.0, 20.0, 30.0, 40.0, 15.0);
                	p.setPlayerDirection("RIGHT");
                	p.setMovement(0);
            	}
        		
        	}
        	
        }
        else if(code == KeyCode.LEFT) {
        	if(p.getXLoc() <= 0) {
        		message.setText("Invalid command");
        	}
        	else {
        		p.setMovement(p.getMovement() + 1);
            	if(p.getMovement() == p.getSteps()) {
            		p.setxLoc(p.getXLoc() - 1);
                    GridPane.setColumnIndex(p, p.getXLoc());
                    message.setText("Ammo: "+ p.shot + "\n" + "Position: " + ToString(p));
                    p.getPoints().setAll(20.0, 0.0, 20.0, 30.0, 0.0, 15.0);
                    p.setPlayerDirection("LEFT");
                    p.setMovement(0);
            	}
        	}
        }
        else {
        	message.setText("Invalid command");
        }
        		
	}
	
	public static void spawnPlayer(Player player, int rowMax, int columnMin, GridPane board) {
    	//spawn the player
    	player.setxLoc((int)(Math.random()*(rowMax - 0) + 0));  
        player.setyLoc((int)(Math.random()*(columnMin - 0) + 0));  
        board.add(player, player.getXLoc(), player.getYLoc());
        
        GridPane.setHalignment(player, HPos.CENTER);
        
    }
	
	public static String blasterPosition(Player player) {
		int blastXLoc = player.getXLoc();
		int blastYLoc = player.getYLoc();
		
		if(player.getPlayerDirection() == "UP") {
			blastYLoc -= 1;
		}
		if(player.getPlayerDirection() == "DOWN") {
			blastYLoc += 1;
		}
		if(player.getPlayerDirection() == "RIGHT") {
			blastXLoc += 1;
		}
		if(player.getPlayerDirection() == "LEFT") {
			blastXLoc -= 1;
		}
		
		return (String.valueOf(blastXLoc) + String.valueOf(blastYLoc));
	}
	
	public static String ToString(Player p) {
		return String.valueOf(p.getXLoc())+ ", " + String.valueOf(p.getYLoc());
		
	}

	

	

	
	
}
