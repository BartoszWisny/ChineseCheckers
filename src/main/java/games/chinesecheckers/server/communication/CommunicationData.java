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
     * Metoda ustala dane potrzebne do komunikacji klient-serwer, mi�dzy innymi gniazda, bufor wej�cia i wyj�cia oraz czy gracze s� pod��czeni do gry.
     */

    public void setUp(final int numberOfPlayers) {
        playerSockets = new Socket[numberOfPlayers];
        playerInputReaders = new BufferedReader[numberOfPlayers];
        playerOutputWriters = new PrintWriter[numberOfPlayers];
        playersConnected = new boolean[numberOfPlayers];
    }
    
    /**
     * Metoda dodaje gracza do odpowiedniej rozgrywki, ustalaj�c dla gracza mi�dzy innymi odpowiednie gniazdo, bufor wej�cia i wyj�cia oraz ustawia, �e gracz jest pod��czony do gry.
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
     * Metoda zwraca bufor wej�cia dla gracza o podanym numerze.
     */

    public BufferedReader getInputReaderByNumber(int number) {
        return playerInputReaders[number];
    }
    
    /**
     * Metoda zwraca bufor wyj�cia dla gracza o podanym numerze.
     */

    public PrintWriter getPrintWriterByNumber(int number) {
        return playerOutputWriters[number];
    }
    
    /**
     * Metoda odpowiada za wys�anie komunikatu tekstowego do ka�dego z graczy bior�cych udzia� w danej rozgrywce.
     */
    
    public void sendMessageToAllPlayers(String message) {
    	for(PrintWriter out: playerOutputWriters) {
    		out.println(message);
    	}
    }
}
