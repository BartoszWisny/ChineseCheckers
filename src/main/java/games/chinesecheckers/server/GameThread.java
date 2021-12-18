package games.chinesecheckers.server;

import games.chinesecheckers.game.Game;
import games.chinesecheckers.game.gamesettings.GameSettings;
import games.chinesecheckers.game.player.Player;

import games.chinesecheckers.server.communication.CommunicationData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class GameThread extends Thread {
    private boolean started = false;
    private CommunicationData communicationData = new CommunicationData();
    private Game game;
    private int currentPlayerNumber;

    private int numberOfJoinedPlayers = 0;
    private GameSettings settings;

    public GameThread(GameSettings settings, Socket host, BufferedReader in, PrintWriter out) throws IOException {
        this.settings = settings;
        communicationData.setUp(settings.getNumberOfPlayers());
        game = new Game(settings);
        addPlayer(host, in, out);
    }

    public int getNumberOfJoinedPlayers() {
        return numberOfJoinedPlayers;
    }

    public GameSettings getSettings() {
        return settings;
    }

    @Override
    public void run()  {
        Random rand = new Random();
        
        while(numberOfJoinedPlayers < game.getNumberOfPlayers()) {
            System.out.println("Waiting for " + (game.getNumberOfPlayers() - numberOfJoinedPlayers)  + " more players");
            try {
                synchronized(this) {
                    wait(5000);
                }
            }
            catch (InterruptedException ex) {}
        }
        
        communicationData.sendMessageToAllPlayers("Game started");
        game.setUp();
        started = true;
        currentPlayerNumber = rand.nextInt(game.getNumberOfPlayers());
        
        
    }

    public void addPlayer(Socket player, BufferedReader in, PrintWriter out) throws IOException {
        communicationData.addPlayer(player, in, out);
        GameSettings gameSettings = this.game.getSettings();
        out.println(gameSettings.toString());
        numberOfJoinedPlayers++;
    }

    public boolean hasStarted() {
        return started;
    }
}