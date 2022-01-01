package chinesecheckers;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import games.chinesecheckers.board.BoardCurrent;
import games.chinesecheckers.board.BoardField;
import games.chinesecheckers.board.BoardPawn;
import games.chinesecheckers.client.RunClient;
import games.chinesecheckers.game.Game;
import games.chinesecheckers.game.board.Board;
import games.chinesecheckers.game.board.BoardSize;
import games.chinesecheckers.game.board.field.Field;
import games.chinesecheckers.game.board.field.FieldColor;
import games.chinesecheckers.game.board.pawn.Pawn;
import games.chinesecheckers.game.gamesettings.GameSettings;
import games.chinesecheckers.game.player.NewPlayer;
import games.chinesecheckers.game.player.Player;
import games.chinesecheckers.gui.CreateGameStage;
import games.chinesecheckers.gui.InfoStage;
import games.chinesecheckers.gui.LobbyStage;
import games.chinesecheckers.gui.ServerInfoStage;

public class GUIAndBoardTests {
	
    @Test
    public void testCreateGameStage() throws InterruptedException { // just checking if GUI shows, run server before tests
        int i = 0;
        
        if (i == 0) {
	       	Thread thread = new Thread(new Runnable() {
		       	public void run() {
		       		new JFXPanel();
		            Platform.runLater(new Runnable() {
		               	public void run() {
		               		CreateGameStage createGameStage = new CreateGameStage(null);
							createGameStage.showAndWait();
		                }
		            });
		        }
		    });
		        
		    thread.start();
		    Thread.sleep(10000);
		    i++;
	    }	
	        
	    if (i == 1) {
	    	Thread thread = new Thread(new Runnable() {
		       	public void run() {
		            new JFXPanel();
		            Platform.runLater(new Runnable() {
		               	public void run() {
		               		InfoStage infoStage = new InfoStage("This is test text");
		               		infoStage.showAndWait();
		                }
		            });
		        }
		    });
		        
		    thread.start();
		    Thread.sleep(10000);
		    i++;
	    }
	    
	    if (i == 2) {
	    	Thread thread = new Thread(new Runnable() {
		       	public void run() {
		            new JFXPanel();
		            Platform.runLater(new Runnable() {
		               	public void run() {
		               		ArrayList<String> args = new ArrayList<String>();
		               		args.add("possible 0 2 1 false");
		               		LobbyStage lobbyStage = new LobbyStage(null, args);
		               		lobbyStage.showAndWait();
		                }
		            });
		        }
		    });
		        
		    thread.start();
		    Thread.sleep(10000);
		    i++;
	    }
	      
	    if (i == 3) {
	    	Thread thread = new Thread(new Runnable() {
		       	public void run() {
		            new JFXPanel();
		            Platform.runLater(new Runnable() {
		               	public void run() {
		               		ServerInfoStage serverInfoStage = new ServerInfoStage(null);
		               		serverInfoStage.showAndWait();
		                }
		            });
		        }
		    });
		        
		    thread.start();
		    Thread.sleep(10000);
		    i++;
	    }
	    
	    if (i == 4) {
	    	Thread thread = new Thread(new Runnable() {
		       	public void run() {
		            new JFXPanel();
		            Platform.runLater(new Runnable() {
		               	public void run() {
		               		try {
								new RunClient().start(new Stage());
							} catch (IOException e) {
								e.printStackTrace();
							}
		                }
		            });
		        }
		    });
		        
		    thread.start();
		    Thread.sleep(10000);
		    i++;
	    } 
	    
	    
    }
    
    BoardCurrent boardCurrent;
	
	@Test
	public void boardTest() throws InterruptedException {
		GameSettings settings = new GameSettings("6o");
		final Game game = new Game(settings);
		BoardSize boardSize = new BoardSize();
		boardSize.boardSize = 4;
		Board board = new Board();
		game.setBoard(board);
		Player player1 = new NewPlayer(FieldColor.BLUE);
		Player player2 = new NewPlayer(FieldColor.WHITE);
		Player player3 = new NewPlayer(FieldColor.RED);
		Player player4 = new NewPlayer(FieldColor.BLACK);
		Player player5 = new NewPlayer(FieldColor.YELLOW);
		Player player6 = new NewPlayer(FieldColor.GREEN);
		Player[] players = {player1, player2, player3, player4, player5, player6};
		game.setPlayers(players);
		
		Field fieldBlue1 = game.getBoard().getFieldByCoordinates(12, 0);
		Field fieldBlue2 = game.getBoard().getFieldByCoordinates(11, 1);
		Field fieldBlue3 = game.getBoard().getFieldByCoordinates(12, 1);
		Field fieldBlue4 = game.getBoard().getFieldByCoordinates(10, 2);
		Field fieldBlue5 = game.getBoard().getFieldByCoordinates(11, 2);
		Field fieldBlue6 = game.getBoard().getFieldByCoordinates(12, 2);
		Field fieldBlue7 = game.getBoard().getFieldByCoordinates(9, 3);
		Field fieldBlue8 = game.getBoard().getFieldByCoordinates(10, 3);
		Field fieldBlue9 = game.getBoard().getFieldByCoordinates(11, 3);
		Field fieldBlue10 = game.getBoard().getFieldByCoordinates(12, 3);
		Pawn pawnBlue1 = new Pawn(fieldBlue1, FieldColor.BLUE);
		Pawn pawnBlue2 = new Pawn(fieldBlue2, FieldColor.BLUE);
		Pawn pawnBlue3 = new Pawn(fieldBlue3, FieldColor.BLUE);
		Pawn pawnBlue4 = new Pawn(fieldBlue4, FieldColor.BLUE);
		Pawn pawnBlue5 = new Pawn(fieldBlue5, FieldColor.BLUE);
		Pawn pawnBlue6 = new Pawn(fieldBlue6, FieldColor.BLUE);
		Pawn pawnBlue7 = new Pawn(fieldBlue7, FieldColor.BLUE);
		Pawn pawnBlue8 = new Pawn(fieldBlue8, FieldColor.BLUE);
		Pawn pawnBlue9 = new Pawn(fieldBlue9, FieldColor.BLUE);
		Pawn pawnBlue10 = new Pawn(fieldBlue10, FieldColor.BLUE);
		
		Field fieldWhite1 = game.getBoard().getFieldByCoordinates(13, 4);
		Field fieldWhite2 = game.getBoard().getFieldByCoordinates(14, 4);
		Field fieldWhite3 = game.getBoard().getFieldByCoordinates(15, 4);
		Field fieldWhite4 = game.getBoard().getFieldByCoordinates(16, 4);
		Field fieldWhite5 = game.getBoard().getFieldByCoordinates(13, 5);
		Field fieldWhite6 = game.getBoard().getFieldByCoordinates(14, 5);
		Field fieldWhite7 = game.getBoard().getFieldByCoordinates(15, 5);
		Field fieldWhite8 = game.getBoard().getFieldByCoordinates(13, 6);
		Field fieldWhite9 = game.getBoard().getFieldByCoordinates(14, 6);
		Field fieldWhite10 = game.getBoard().getFieldByCoordinates(13, 7);
		Pawn pawnWhite1 = new Pawn(fieldWhite1, FieldColor.WHITE);
		Pawn pawnWhite2 = new Pawn(fieldWhite2, FieldColor.WHITE);
		Pawn pawnWhite3 = new Pawn(fieldWhite3, FieldColor.WHITE);
		Pawn pawnWhite4 = new Pawn(fieldWhite4, FieldColor.WHITE);
		Pawn pawnWhite5 = new Pawn(fieldWhite5, FieldColor.WHITE);
		Pawn pawnWhite6 = new Pawn(fieldWhite6, FieldColor.WHITE);
		Pawn pawnWhite7 = new Pawn(fieldWhite7, FieldColor.WHITE);
		Pawn pawnWhite8 = new Pawn(fieldWhite8, FieldColor.WHITE);
		Pawn pawnWhite9 = new Pawn(fieldWhite9, FieldColor.WHITE);
		Pawn pawnWhite10 = new Pawn(fieldWhite10, FieldColor.WHITE);
		
		Field fieldRed1 = game.getBoard().getFieldByCoordinates(12, 9);
		Field fieldRed2 = game.getBoard().getFieldByCoordinates(11, 10);
		Field fieldRed3 = game.getBoard().getFieldByCoordinates(12, 10);
		Field fieldRed4 = game.getBoard().getFieldByCoordinates(10, 11);
		Field fieldRed5 = game.getBoard().getFieldByCoordinates(11, 11);
		Field fieldRed6 = game.getBoard().getFieldByCoordinates(12, 11);
		Field fieldRed7 = game.getBoard().getFieldByCoordinates(9, 12);
		Field fieldRed8 = game.getBoard().getFieldByCoordinates(10, 12);
		Field fieldRed9 = game.getBoard().getFieldByCoordinates(11, 12);
		Field fieldRed10 = game.getBoard().getFieldByCoordinates(12, 12);
		Pawn pawnRed1 = new Pawn(fieldRed1, FieldColor.RED);
		Pawn pawnRed2 = new Pawn(fieldRed2, FieldColor.RED);
		Pawn pawnRed3 = new Pawn(fieldRed3, FieldColor.RED);
		Pawn pawnRed4 = new Pawn(fieldRed4, FieldColor.RED);
		Pawn pawnRed5 = new Pawn(fieldRed5, FieldColor.RED);
		Pawn pawnRed6 = new Pawn(fieldRed6, FieldColor.RED);
		Pawn pawnRed7 = new Pawn(fieldRed7, FieldColor.RED);
		Pawn pawnRed8 = new Pawn(fieldRed8, FieldColor.RED);
		Pawn pawnRed9 = new Pawn(fieldRed9, FieldColor.RED);
		Pawn pawnRed10 = new Pawn(fieldRed10, FieldColor.RED);
		
		Field fieldBlack1 = game.getBoard().getFieldByCoordinates(4, 13);
		Field fieldBlack2 = game.getBoard().getFieldByCoordinates(5, 13);
		Field fieldBlack3 = game.getBoard().getFieldByCoordinates(6, 13);
		Field fieldBlack4 = game.getBoard().getFieldByCoordinates(7, 13);
		Field fieldBlack5 = game.getBoard().getFieldByCoordinates(4, 14);
		Field fieldBlack6 = game.getBoard().getFieldByCoordinates(5, 14);
		Field fieldBlack7 = game.getBoard().getFieldByCoordinates(6, 14);
		Field fieldBlack8 = game.getBoard().getFieldByCoordinates(4, 15);
		Field fieldBlack9 = game.getBoard().getFieldByCoordinates(5, 15);
		Field fieldBlack10 = game.getBoard().getFieldByCoordinates(4, 16);
		Pawn pawnBlack1 = new Pawn(fieldBlack1, FieldColor.BLACK);
		Pawn pawnBlack2 = new Pawn(fieldBlack2, FieldColor.BLACK);
		Pawn pawnBlack3 = new Pawn(fieldBlack3, FieldColor.BLACK);
		Pawn pawnBlack4 = new Pawn(fieldBlack4, FieldColor.BLACK);
		Pawn pawnBlack5 = new Pawn(fieldBlack5, FieldColor.BLACK);
		Pawn pawnBlack6 = new Pawn(fieldBlack6, FieldColor.BLACK);
		Pawn pawnBlack7 = new Pawn(fieldBlack7, FieldColor.BLACK);
		Pawn pawnBlack8 = new Pawn(fieldBlack8, FieldColor.BLACK);
		Pawn pawnBlack9 = new Pawn(fieldBlack9, FieldColor.BLACK);
		Pawn pawnBlack10 = new Pawn(fieldBlack10, FieldColor.BLACK);
		
		Field fieldYellow1 = game.getBoard().getFieldByCoordinates(3, 9);
		Field fieldYellow2 = game.getBoard().getFieldByCoordinates(2, 10);
		Field fieldYellow3 = game.getBoard().getFieldByCoordinates(3, 10);
		Field fieldYellow4 = game.getBoard().getFieldByCoordinates(1, 11);
		Field fieldYellow5 = game.getBoard().getFieldByCoordinates(2, 11);
		Field fieldYellow6 = game.getBoard().getFieldByCoordinates(3, 11);
		Field fieldYellow7 = game.getBoard().getFieldByCoordinates(0, 12);
		Field fieldYellow8 = game.getBoard().getFieldByCoordinates(1, 12);
		Field fieldYellow9 = game.getBoard().getFieldByCoordinates(2, 12);
		Field fieldYellow10 = game.getBoard().getFieldByCoordinates(3, 12);
		Pawn pawnYellow1 = new Pawn(fieldYellow1, FieldColor.YELLOW);
		Pawn pawnYellow2 = new Pawn(fieldYellow2, FieldColor.YELLOW);
		Pawn pawnYellow3 = new Pawn(fieldYellow3, FieldColor.YELLOW);
		Pawn pawnYellow4 = new Pawn(fieldYellow4, FieldColor.YELLOW);
		Pawn pawnYellow5 = new Pawn(fieldYellow5, FieldColor.YELLOW);
		Pawn pawnYellow6 = new Pawn(fieldYellow6, FieldColor.YELLOW);
		Pawn pawnYellow7 = new Pawn(fieldYellow7, FieldColor.YELLOW);
		Pawn pawnYellow8 = new Pawn(fieldYellow8, FieldColor.YELLOW);
		Pawn pawnYellow9 = new Pawn(fieldYellow9, FieldColor.YELLOW);
		Pawn pawnYellow10 = new Pawn(fieldYellow10, FieldColor.YELLOW);
		
		Field fieldGreen1 = game.getBoard().getFieldByCoordinates(4, 4);
		Field fieldGreen2 = game.getBoard().getFieldByCoordinates(5, 4);
		Field fieldGreen3 = game.getBoard().getFieldByCoordinates(6, 4);
		Field fieldGreen4 = game.getBoard().getFieldByCoordinates(7, 4);
		Field fieldGreen5 = game.getBoard().getFieldByCoordinates(4, 5);
		Field fieldGreen6 = game.getBoard().getFieldByCoordinates(5, 5);
		Field fieldGreen7 = game.getBoard().getFieldByCoordinates(6, 5);
		Field fieldGreen8 = game.getBoard().getFieldByCoordinates(4, 6);
		Field fieldGreen9 = game.getBoard().getFieldByCoordinates(5, 6);
		Field fieldGreen10 = game.getBoard().getFieldByCoordinates(4, 7);
		Pawn pawnGreen1 = new Pawn(fieldGreen1, FieldColor.GREEN);
		Pawn pawnGreen2 = new Pawn(fieldGreen2, FieldColor.GREEN);
		Pawn pawnGreen3 = new Pawn(fieldGreen3, FieldColor.GREEN);
		Pawn pawnGreen4 = new Pawn(fieldGreen4, FieldColor.GREEN);
		Pawn pawnGreen5 = new Pawn(fieldGreen5, FieldColor.GREEN);
		Pawn pawnGreen6 = new Pawn(fieldGreen6, FieldColor.GREEN);
		Pawn pawnGreen7 = new Pawn(fieldGreen7, FieldColor.GREEN);
		Pawn pawnGreen8 = new Pawn(fieldGreen8, FieldColor.GREEN);
		Pawn pawnGreen9 = new Pawn(fieldGreen9, FieldColor.GREEN);
		Pawn pawnGreen10 = new Pawn(fieldGreen10, FieldColor.GREEN);
		
		Pawn[] pawns = {pawnBlue1, pawnBlue2, pawnBlue3, pawnBlue4, pawnBlue5, pawnBlue6, pawnBlue7, pawnBlue8, pawnBlue9, pawnBlue10,
				pawnWhite1, pawnWhite2, pawnWhite3, pawnWhite4, pawnWhite5, pawnWhite6, pawnWhite7, pawnWhite8, pawnWhite9, pawnWhite10,
				pawnRed1, pawnRed2, pawnRed3, pawnRed4, pawnRed5, pawnRed6, pawnRed7, pawnRed8, pawnRed9, pawnRed10, 
				pawnBlack1, pawnBlack2, pawnBlack3, pawnBlack4, pawnBlack5, pawnBlack6, pawnBlack7, pawnBlack8, pawnBlack9, pawnBlack10, 
				pawnYellow1, pawnYellow2, pawnYellow3, pawnYellow4, pawnYellow5, pawnYellow6, pawnYellow7, pawnYellow8, pawnYellow9, pawnYellow10, 
				pawnGreen1, pawnGreen2, pawnGreen3, pawnGreen4, pawnGreen5, pawnGreen6, pawnGreen7, pawnGreen8, pawnGreen9, pawnGreen10};
		
		game.setPawns(pawns);
		
		Thread thread1 = new Thread(new Runnable() {
	       	public void run() {
	       		new JFXPanel();
	            Platform.runLater(new Runnable() {
	               	public void run() {
	               		boardCurrent = new BoardCurrent(game, 1, null);
	               		boardCurrent.setLabel("This is test text");
	               		boardCurrent.activate();
	               		boardCurrent.skipButton.setDisable(false);
	               		boardCurrent.showAndWait();
	                }
	            });
	        }
	    });
		
		thread1.start();
	    Thread.sleep(5000);
	    
	    Thread thread2 = new Thread(new Runnable() {
	       	public void run() {
	       		new JFXPanel();
	            Platform.runLater(new Runnable() {
	               	public void run() {
	               		boardCurrent = new BoardCurrent(game, 1, null);
	               		boardCurrent.setLabel("This is test text");
	               		boardCurrent.setUnactive();
	               		try {
							boardCurrent.makeMove("move 3 11 4 10 4");
						} catch (Exception e) {
							e.printStackTrace();
						}
	               		boardCurrent.skipButton.setDisable(false);
	               		boardCurrent.showAndWait();
	                }
	            });
	        }
	    });
	    
	    thread2.start();
	    Thread.sleep(5000);
	    
	    Field testField1 = game.getBoard().getFieldByCoordinates(7, 7);
	    BoardField boardField = new BoardField(testField1, boardCurrent);
	    Assert.assertEquals(true, boardField.isField());
		Assert.assertEquals(false, boardField.isPawn());
		Assert.assertEquals(testField1, boardField.getField());
		Assert.assertEquals(FieldColor.NONE, boardField.getColor());
		Assert.assertEquals(true, boardField.isFreeField());
		
		Pawn testPawn = pawnBlue2;
	    BoardPawn boardPawn = new BoardPawn(testPawn, boardCurrent);
	    Assert.assertEquals(false, boardPawn.isField());
		Assert.assertEquals(true, boardPawn.isPawn());
		Assert.assertEquals(FieldColor.BLUE, boardPawn.getColor());
		Assert.assertEquals(11, boardPawn.getDiagonal());
		Assert.assertEquals(1, boardPawn.getRow());
		Assert.assertEquals(testPawn, boardPawn.getPawn());
		Field testField2 = fieldBlue4;
		boardPawn.move(testField2, null);
		Assert.assertEquals(10, boardPawn.getDiagonal());
		Assert.assertEquals(2, boardPawn.getRow());
	}
	
	public void stopTest() {
		return;
	}	
}
