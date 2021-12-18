package games.chinesecheckers.server.communication;

import java.io.*;
import java.net.Socket;

public final class CommunicationData {
    private Socket[] playerSockets;
    private BufferedReader[] playerInputReaders;
    private PrintWriter[] playerOutputWriters;
    private boolean[] playersConnected;
    int currentNumberOfPlayers = 0;

    public void setUp(final int numberOfPlayers) {
        playerSockets = new Socket[numberOfPlayers];
        playerInputReaders = new BufferedReader[numberOfPlayers];
        playerOutputWriters = new PrintWriter[numberOfPlayers];
        playersConnected = new boolean[numberOfPlayers];
    }

    public void addPlayer(Socket playerSocket, BufferedReader in, PrintWriter out) {
        out.println("Hello player");
        out.println(currentNumberOfPlayers);
        playerSockets[currentNumberOfPlayers] = playerSocket;
        playerInputReaders[currentNumberOfPlayers] = in;
        playerOutputWriters[currentNumberOfPlayers] = out;
        playersConnected[currentNumberOfPlayers] = true;
        currentNumberOfPlayers++;
    }

    public BufferedReader getInputReaderByNumber(int number) {
        return playerInputReaders[number];
    }

    public PrintWriter getPrintWriterByNumber(int number) {
        return playerOutputWriters[number];
    }
    
    public void sendMessageToAllPlayers(String message) {
    	for(PrintWriter out: playerOutputWriters) {
    		out.println(message);
    	}
    }
}
