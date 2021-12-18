package games.chinesecheckers.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import games.chinesecheckers.game.gamesettings.GameSettings;


public class Server extends ServerSocket {
	private boolean serverRunning = true;
    private List<GameThread> games = new ArrayList<GameThread>();

    public Server(int port) throws IOException {
        super(port);
    }

    public void listen() throws IOException {
        while (serverRunning) {
            System.out.println("Start");
            Socket newPlayer = accept();
            System.out.println("Hello!");
            BufferedReader newPlayerInputReader = getPlayerInputStreamReader(newPlayer);
            System.out.println("Waiting for player type...");
            String playerType = newPlayerInputReader.readLine();
            System.out.println("Processing...");
            processPlayerType(playerType, newPlayer);
        }
    }

    private void processPlayerType(String playerType, Socket player) throws IOException {
        BufferedReader hostInputReader = getPlayerInputStreamReader(player);
        PrintWriter hostOutoutWriter = getPlayerOutputStreamWriter(player);
        if (playerType.equals("host")) {
            System.out.println("I got here");
            String line = "You are host";
            hostOutoutWriter.println(line);
            GameSettings settings = setUpGame(hostInputReader);
            GameThread newGame = new GameThread(settings, player, hostInputReader, hostOutoutWriter);
            games.add(newGame);
            newGame.start();
            System.out.println("Thread started");
        }
        else if(playerType.equals("join")) {
        	String message = "";
        		for (GameThread thread : games) {
                    GameSettings settings = thread.getSettings();
                    int started = thread.hasStarted() ? 1 : 0;
                    int numberOfJoinedPlayers = thread.getNumberOfJoinedPlayers();
                    int numberOfPlayers = settings.getNumberOfPlayers();
                    int gameId = games.indexOf(thread);
                    message += "possible" + " " + numberOfPlayers + " " + 1 + " " + numberOfJoinedPlayers
                            + " " + gameId + " " + started + "x";
                }
        		
                message = message.substring(0, message.length() - 1);
        		hostOutoutWriter.println(message);
        		String chosenIDLine = hostInputReader.readLine();
        		System.out.println(chosenIDLine);
        		int id = Integer.parseInt(chosenIDLine.split(" ")[1]);
        		GameThread gameThread = findOpenGame(id);
        		gameThread.addPlayer(player, hostInputReader, hostOutoutWriter);
        }
    }

    private GameThread findOpenGame(int id) {
        return games.get(id);
    }

    private GameSettings setUpGame(BufferedReader hostInputReader) throws IOException {
        System.out.println("Inside setUp");
        String gameOptionsLine = hostInputReader.readLine();
        System.out.println("Options: "+ gameOptionsLine.charAt(0));
        return new GameSettings(gameOptionsLine);
    }

    private BufferedReader getPlayerInputStreamReader(Socket player) throws IOException {
        return new BufferedReader(new InputStreamReader(player.getInputStream()));
    }

    private PrintWriter getPlayerOutputStreamWriter(Socket player) throws IOException {
        return new PrintWriter(new OutputStreamWriter(player.getOutputStream()), true);
    }
}
