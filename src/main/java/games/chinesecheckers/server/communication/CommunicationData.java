package games.chinesecheckers.server.communication;

import java.io.*;
import java.net.Socket;

/**
 * Klasa ustala dane potrzebne do komunikacji klient-serwer i dodaje gracza do odpowiedniej rozgrywki.
 */

public final class CommunicationData {
    private Socket[] playerSockets;
    private BufferedReader[] playerInputReaders;
    private PrintWriter[] playerOutputWriters;
    private boolean[] playersConnected;
    int currentNumberOfPlayers = 0;
    
    /**
     * Metoda ustala dane potrzebne do komunikacji klient-serwer, miêdzy innymi gniazda, bufor wejœcia i wyjœcia oraz czy gracze s¹ pod³¹czeni do gry.
     */

    public void setUp(final int numberOfPlayers) {
        playerSockets = new Socket[numberOfPlayers];
        playerInputReaders = new BufferedReader[numberOfPlayers];
        playerOutputWriters = new PrintWriter[numberOfPlayers];
        playersConnected = new boolean[numberOfPlayers];
    }
    
    /**
     * Metoda dodaje gracza do odpowiedniej rozgrywki, ustalaj¹c dla gracza miêdzy innymi odpowiednie gniazdo, bufor wejœcia i wyjœcia oraz ustawia, ¿e gracz jest pod³¹czony do gry.
     */

    public void addPlayer(Socket playerSocket, BufferedReader in, PrintWriter out) {
        out.println("Hello player");
        out.println(currentNumberOfPlayers);
        playerSockets[currentNumberOfPlayers] = playerSocket;
        playerInputReaders[currentNumberOfPlayers] = in;
        playerOutputWriters[currentNumberOfPlayers] = out;
        playersConnected[currentNumberOfPlayers] = true;
        currentNumberOfPlayers++;
    }
    
    /**
     * Metoda zwraca bufor wejœcia dla gracza o podanym numerze.
     */

    public BufferedReader getInputReaderByNumber(int number) {
        return playerInputReaders[number];
    }
    
    /**
     * Metoda zwraca bufor wyjœcia dla gracza o podanym numerze.
     */

    public PrintWriter getPrintWriterByNumber(int number) {
        return playerOutputWriters[number];
    }
    
    /**
     * Metoda odpowiada za wys³anie komunikatu tekstowego do ka¿dego z graczy bior¹cych udzia³ w danej rozgrywce.
     */
    
    public void sendMessageToAllPlayers(String message) {
    	for(PrintWriter out: playerOutputWriters) {
    		out.println(message);
    	}
    }
}
