package cs2012final;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;  

/*
 * Name:        Juan La Serna
 * CIN:         401689259
 * Course:      CS 2012
 * Section:     01
 * Description: FX Class
 */

public class GameSetup extends Application  {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Menu");
        
     // create a text field for asking the players how many # of elements there are
        TextField rowInput = new TextField("");
        TextField columnInput = new TextField("");
        
        // create a label for giving the player instruction
        Label rowLabel = new Label("Enter number of rows (min 5 & max 10)");
        Label columnLabel = new Label("Enter number of columns (min 5 & max 10)");
        Label errorLabel = new Label("");
        
        //create button for confirming your choice
        Button enterButton = new Button("Enter");
        
      //create the grid & scene for setting up the game
        VBox introScene = new VBox();
        introScene.setSpacing(5);
        introScene.getChildren().addAll(rowInput, rowLabel, columnInput, columnLabel, enterButton, errorLabel);
        Scene menu = new Scene(introScene, 250, 250); 
        
        //create a different scene for the game itself
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        Scene game = new Scene(gridPane, 600, 600);  
    	
        // action event if player enter their response
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
            	int gridRowInt = Integer.parseInt((rowInput.getText()));
            	int gridColumnInt = Integer.parseInt((columnInput.getText()));
            	
            	//check if the row & column input is valid
            	if(gridRowInt < 5 || gridRowInt > 10 || gridColumnInt < 5 || gridColumnInt > 10) {
            		//If the user put a invalid response, print out error 
            		errorLabel.setText("Invalid Responce");
            		
            	}
            	
            	else if(gridRowInt >= 5 && gridRowInt <= 10 || gridColumnInt <= 5 && gridColumnInt >= 10) {
            		
            		//set a new scene & reset title
            		primaryStage.setScene(game);
            		primaryStage.setTitle("Game");
            		
            		//create a gridPane and add 'squareSpace' as representing each element
                	int gridRow = gridRowInt;
                	int gridColumn = gridColumnInt;
                	gridLayout[][] grid = new gridLayout[gridColumn][gridRow];
                                
                	//gridPane attribute (object, column, row)
            		for (int i = 0; i < gridColumn; i++) {
                        for (int j = 0; j < gridRow; j++) {
                        	
                        	//define the square to create an element for the grid
                        	grid[i][j] = new gridLayout();
                                       
                            //adding that object to the grid pane
                        	gridPane.add(grid[i][j], j, i);
                        	
                        }
                        System.out.println();
                    }
            		
            		//add a textBox for message system
            		TextBox description = new TextBox();
            		gridPane.add(description,1,(gridColumnInt+1), gridRow, 1);
            		
            		//Spawn different assets [[
                    Player player = new Player();
                    Player.spawnPlayer(player, gridRowInt, gridColumnInt, gridPane);
                    grid[player.getYLoc()][player.getXLoc()].setIsOccupied(true);
                    
                    ArrayList<EnemySlug> slugs = new ArrayList<>();
                    slugs.add(new EnemySlug());
                    slugs.add(new EnemySlug());
                    slugs.add(new EnemySlug());  
                    
                    for(EnemySlug slug: slugs){                                                     
                    	EnemySlug.spawnEnemy(slug, gridRowInt, gridColumnInt, gridPane, grid);
                    }
                    
                    ArrayList<Bullet> bullets = new ArrayList<>();
                    bullets.add(new Bullet());
                    bullets.add(new Bullet());
                    for(Bullet bullet: bullets){                                                     
                    	Bullet.spawnAmmo(bullet, gridRowInt, gridColumnInt, gridPane, grid);
                    }
                    
                    ArrayList<WallSpike> spikes = new ArrayList<>();
                    spikes.add(new WallSpike());
                    spikes.add(new WallSpike());
                    spikes.add(new WallSpike());
                    
                    for(WallSpike spike: spikes) {
                    	WallSpike.spawnTrap(spike, gridRowInt, gridColumnInt, gridPane, grid);
                    }
                    
                    BossMonsterOrc Orc = new BossMonsterOrc();
                    BossMonsterOrc.spawnBoss(Orc, gridRowInt, gridColumnInt, gridPane, grid);
                    
                    // ]]
                    
                    //add action for key press
                    game.setOnKeyPressed(a -> {
                    	
                		if(a.getCode() == KeyCode.UP || a.getCode() == KeyCode.DOWN
                    			|| a.getCode() == KeyCode.RIGHT || a.getCode() == KeyCode.LEFT ) {
                			
                			//reset player position
                    		grid[player.getYLoc()][player.getXLoc()].setIsOccupied(false);
                        	Player.relocatePlayer(a.getCode(), player, gridRow, gridColumn, description);
                        	grid[player.getYLoc()][player.getXLoc()].setIsOccupied(true);
                        	
                        	//if player lands on a trap 
                        	for(WallSpike spike: spikes) {
                            	boolean trapActivated = WallSpike.trapTriggered(player,spike);
                            	if(trapActivated == true) {
                            		//create a game over screen
                            		Label gameOverLabel = new Label("GameOver \n you fell on a trap");
                                	StackPane endScene = new StackPane();  
                                	endScene.getChildren().add(gameOverLabel);
                                	Scene gameOver = new Scene(endScene, 500, 500); 
                                	primaryStage.setScene(gameOver);
                            		primaryStage.setTitle("Game Over");
                            	}
                            }
                        	
                        	//if player lands on a Ammo 
                        	for(Bullet bullet: bullets) {
                            	boolean bulletCollect = Bullet.bulletOn(player,bullet);
                            	if(bulletCollect == true) {
                            		player.setShot(player.getShot() + 1);
                            		gridPane.getChildren().remove(bullet);
                            		bullets.remove(bullet);
                            		description.setText(String.valueOf("You picked up an ammo"));
                            	}
                            }
                            	
                        	//if player lands on a slug 
                        	for(EnemySlug slug: slugs){                                                     
                        		boolean enemyTrigger = EnemySlug.enemyActivate(player, slug);
                        		if(enemyTrigger == true) {
                            		player.setSteps(player.getSteps() + 1);
                            		description.setText("You step on a slug");
                            		slugs.remove(slug);
                            		gridPane.getChildren().remove(slug);
                            		
                            	}
                            }
                        	
                        	//If player lands on boss
                        	boolean bossTrigger = BossMonsterOrc.bossOn(player, Orc);
                    		if(bossTrigger == true) {
                        		Label gameOverLabel = new Label("GameOver \n got killed by the orc");
                            	StackPane endScene = new StackPane();  
                            	endScene.getChildren().add(gameOverLabel);
                            	Scene gameOver = new Scene(endScene, 500, 500); 
                            	primaryStage.setScene(gameOver);
                        		primaryStage.setTitle("Game Over");
                        	
                    	    }
                		}
                		//if player press Z key; use Blaster
                    	if(a.getCode() == KeyCode.Z){
                    		if(player.getShot() > 0){
                    			int blasterRange = Integer.parseInt(Player.blasterPosition(player));
                        		int WithinRange = Integer.parseInt(BossMonsterOrc.toString(Orc));
                        		
                        		if(blasterRange == WithinRange) {
                        			gridPane.getChildren().remove(Orc);
                        			
                        			Label debugLabel = new Label("You Win \n You killed the orc");
                                	StackPane debugScene = new StackPane();  
                                	debugScene.getChildren().add(debugLabel);
                            		Scene winScene = new Scene(debugScene, 250, 250);
                            		primaryStage.setScene(winScene);
                            		primaryStage.setTitle("You Win");
                        			
                        		}
                        		
                        		player.setShot(player.getShot() - 1);
                        		description.setText(String.valueOf("Ammo:" + player.getShot() + "\n" + "You shot at: " + blasterRange));
                    		}	
                    		else {
                    			description.setText("No bullet");
                    		}
                    	}
                    	//Pressing D key bring up the Debug menu
                    	if(a.getCode() == KeyCode.D){
                    		Label debugLabel = new Label("Debug menu");
                    		//create button for confirming your choice
                            Button ShowTrap = new Button("StepOnEnemy");
                          //create button for confirming your choice
                            Button ShowSlug = new Button("StepOnSlug");
                            VBox DebugSpacing = new VBox();
                            
                            DebugSpacing.setSpacing(5);
                            DebugSpacing.getChildren().addAll(debugLabel, ShowTrap, ShowSlug);
                            
                        	StackPane debugScene = new StackPane();  
                        	debugScene.getChildren().add(DebugSpacing);
                    		Scene Debug = new Scene(debugScene, 250, 250);
                    		primaryStage.setScene(Debug);
                    		primaryStage.setTitle("Debug");
                    		
                    		EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
                    			public void handle(ActionEvent e){
                    				if(a.getSource() == ShowTrap){
                    					for(WallSpike spike: spikes) {
                    						gridPane.add(spike, spike.getXLoc(), spike.getYLoc());
                        	                GridPane.setHalignment(spike, HPos.CENTER);
                                        }
                    				}
                    			}
                    		};
                    		ShowTrap.setOnAction(event2);
                    	
	                    	EventHandler<ActionEvent> event3 = new EventHandler<ActionEvent>() {
	                			public void handle(ActionEvent e){
			                		if(a.getSource() == ShowSlug){
				    					for(EnemySlug slug: slugs){                                                     
				    						gridPane.add(slug, slug.getXLoc(), slug.getYLoc());
				        	                GridPane.setHalignment(slug, HPos.CENTER);
				                        }
			                		}
	                			}
	                		};
	                		ShowSlug.setOnAction(event3);
	                		Debug.setOnKeyPressed(b -> { 
	            				if (b.getCode() == KeyCode.G){
		                			primaryStage.setScene(game);
	                        		primaryStage.setTitle("Game");
			    					
			    				}
	                		});
                    	}
                    });
            	}
            	else {
            		errorLabel.setText("Invalid Responce");
            	}
            }
        };
        
     // when button is pressed, launch the game of the given elements for the grid
        enterButton.setOnAction(event);
                
      //Open the application
        primaryStage.setScene(menu);
        primaryStage.show();
        
    } 

	//launch the application
    public static void main(String[] args) {
        Application.launch(args);
        
        }
        
}