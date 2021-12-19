package games.chinesecheckers.client;

import games.chinesecheckers.board.BoardCurrent;
import games.chinesecheckers.game.Game;
import games.chinesecheckers.game.gamesettings.GameSettings;
import games.chinesecheckers.game.player.Player;
import games.chinesecheckers.gui.InfoStage;
import games.chinesecheckers.gui.LobbyStage;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.application.Platform;

public class ListenerThread extends Thread {
    private BufferedReader in;
    private Client client;
    private int playerNumber;
    private Game game;
    private BoardCurrent board;
    private volatile List<String> gameLobbyArgs;

    public ListenerThread(BufferedReader in, Client client) {
        this.in = in;
        this.client = client;
    }

    public void run() {
        while (true) {
            try {
            	final String currentLine;
            	
            	if (in.ready() && (currentLine = in.readLine()) != null) {
                    if (isNumber(currentLine)) {
                        this.playerNumber = Integer.parseInt(currentLine);
                        System.out.println("I got number " + playerNumber);
                    } else if (currentLine.contains("possible")) {
                        List<String> args = new ArrayList<String>();
                        String[] string = currentLine.split("x");
                        args.addAll(Arrays.asList(string));
                        gameLobbyArgs = args;
                        
                        Platform.runLater(new Runnable() {
							public void run() {
							    LobbyStage stage = new LobbyStage(client, gameLobbyArgs);
							    stage.showAndWait();
							}
						});
                    } else if (isGameSettings(currentLine)) {
                        System.out.println("I got settings " + currentLine.charAt(0));
                        this.game = new Game(new GameSettings(currentLine));
                        game.setUp();
                    } else if (currentLine.contains("Game started")) {
                    	System.out.println("I got game started");
                    	
                        Platform.runLater(new Runnable() {
							public void run() {
							    client.closePreviousStage();
							    board = new BoardCurrent(game, playerNumber, client);
							    board.show();
							}
						});
                    } else if (currentLine.contains("No game found")) {
                        Platform.runLater(new Runnable() {
							public void run() {
							    InfoStage stage = new InfoStage("Game not found");
							    stage.show();
							}
						});
                    } else if (currentLine.contains("move")) {
                        Platform.runLater(new Runnable() {
							public void run() {
							    try {
							        ListenerThread.this.board.makeMove(currentLine);
							        System.out.println(currentLine);
							        ListenerThread.this.board.setLabel("Wait for your turn...");
							    }
							    catch (Exception e) {
							        e.printStackTrace();
							    }
							}
						});
                    } else if (currentLine.contains("Your turn.")) {
                        Platform.runLater(new Runnable() {
							public void run() {
							    board.activate();
							    System.out.println(currentLine);
							    board.setLabel("Your turn!");
							}
						});
                    } else if (currentLine.contains("winner")) {
                        String[] string = currentLine.split(" ");
                        int number = Integer.parseInt(string[1]);

                        if (number == this.playerNumber) {
                            Platform.runLater(new Runnable() {
								public void run() {
								    InfoStage newStage = new InfoStage("You won!");
								    System.out.println(currentLine);
								    newStage.show();
								}
							});
                        } else {
                            Player player = this.game.getPlayerByNumber(number);
                            final String color = player.getColor().toString();

                            Platform.runLater(new Runnable() {
								public void run() {
								    InfoStage newStage = new InfoStage(color + " won!");
								    System.out.println(currentLine);
								    newStage.show();
								}
							});
                        }
                    }
                }
            }
            catch (Exception e) {
            }
        }
    }

    private boolean isNumber(String line) {
        try {
            Integer.parseInt(line);
        }
        catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private boolean isGameSettings(String line) {
    	return isNumber(line.substring(0,1)) && line.charAt(1) == 'o';
    }
}
