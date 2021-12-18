package games.chinesecheckers.client;

import games.chinesecheckers.board.BoardCurrent;
import games.chinesecheckers.game.Game;
import games.chinesecheckers.game.gamesettings.GameSettings;
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
                    } 
                    // ...
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
