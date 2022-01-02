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
import javax.naming.CommunicationException;

import games.chinesecheckers.game.gamesettings.GameSettings;

/**
 * Klasa ustala dane serwera gry.
 */

public class Server extends ServerSocket {
	private boolean serverRunning = true;
    private List<GameThread> games = new ArrayList<GameThread>();

    /**
     * Konstruktor ustala gniazdo serwera pod odpowiednim portem.
     */
    
    public Server(int port) throws IOException {
        super(port);
    }
    
    /**
     * Metoda uruchamia dzia�anie serwera i wysy�a odpowiednie komunikaty w zale�no�ci od akcji wykonanej przez gracza.
     */

    public void listen() throws IOException, CommunicationException {
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
    
    /**
     * Metoda sprawdza, czy dany gracz tworzy now� rozgrywk�, czy chce do��czy� do utworzonej rozgrywki. Dla hosta ustawia odpowiednie parametry gry w zale�no�ci od wybor�w hosta, natomiast dla gracza do��czaj�cego do gry sprawdza, czy gracz mo�e do��czy� do danej rogrywki i ustala dane tego gracza w danej rozgrywce. 
     */

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
        	try {
        		String message = "";
        		for (GameThread thread : games) {
        			if (!thread.isOver) {
	        			GameSettings settings = thread.getSettings();
	                    String started = Boolean.toString(thread.hasStarted());
	                    int gameID = games.indexOf(thread);
	                    int numberOfJoinedPlayers = thread.getNumberOfJoinedPlayers();
	                    int numberOfPlayers = settings.getNumberOfPlayers();
	                    message += "possible" + " " + gameID + " " + numberOfPlayers + " " + numberOfJoinedPlayers + " " + started + "x";
        			}    
                }
        		
                message = message.substring(0, message.length() - 1);
        		hostOutoutWriter.println(message);
        		String chosenIDLine = hostInputReader.readLine();
        		System.out.println(chosenIDLine);
        		int id = Integer.parseInt(chosenIDLine.split(" ")[1]);
        		GameThread gameThread = findOpenGame(id);
        		gameThread.addPlayer(player, hostInputReader, hostOutoutWriter);
        	}
        	catch(Exception e) {
        		hostOutoutWriter.println("No game found");
        	}
        }
    }
    
    /**
     * Metoda wyszukuje gr� z listy gier na podstawie ID rozgrywki.
     */

    private GameThread findOpenGame(int id) {
        return games.get(id);
    }
    
    /**
     * Metoda ustala ustawienia gry na podstawie informacji wej�ciowych.
     */

    private GameSettings setUpGame(BufferedReader hostInputReader) throws IOException {
        System.out.println("Inside setUp");
        String gameOptionsLine = hostInputReader.readLine();
        System.out.println("Options: "+ gameOptionsLine.charAt(0));
        return new GameSettings(gameOptionsLine);
    }
    
    /**
     * Metoda zwraca bufor wej�cia danego gracza na podstawie jego gniazda.
     */

    private BufferedReader getPlayerInputStreamReader(Socket player) throws IOException {
        return new BufferedReader(new InputStreamReader(player.getInputStream()));
    }
    
    /**
     * Metoda zwraca bufor wyj�cia danego gracza na podstawie jego gniazda.
     */

    private PrintWriter getPlayerOutputStreamWriter(Socket player) throws IOException {
        return new PrintWriter(new OutputStreamWriter(player.getOutputStream()), true);
    }
}
