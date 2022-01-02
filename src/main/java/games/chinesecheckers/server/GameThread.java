package games.chinesecheckers.server;

import games.chinesecheckers.game.Game;
import games.chinesecheckers.game.board.field.Field;
import games.chinesecheckers.game.board.field.FieldStatus;
import games.chinesecheckers.game.board.pawn.Pawn;
import games.chinesecheckers.game.gamesettings.GameSettings;
import games.chinesecheckers.game.player.Player;
import games.chinesecheckers.ruleset.GameAnalyzer;
import games.chinesecheckers.ruleset.MoveDetails;
import games.chinesecheckers.server.communication.CommunicationData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

/**
 * Klasa uruchamia dan¹ rozgrywkê jako w¹tek i zarz¹dza przebiegiem danej rozgrywki.
 */

public class GameThread extends Thread {
    private boolean started = false;
    public boolean isOver = false;
    private CommunicationData communicationData = new CommunicationData();
    private Game game;
    private GameAnalyzer validator;
    private int currentPlayerNumber;
    private boolean hasJumped = false;
    private Pawn lastMovedPawn = null;
    private boolean skip = false;

    private int numberOfJoinedPlayers = 0;
    private GameSettings settings;

    /**
     * Konstruktor ustala ustawienia gry, dodaje graczy ustalaj¹c odpowiednie gniazdo, bufor wejœcia i wyjœcia, ustala komunikacjê klient-serwer, ustala walidatora dla rozgrywki. 
     */
    
    public GameThread(GameSettings settings, Socket host, BufferedReader in, PrintWriter out) throws IOException {
        this.settings = settings;
        communicationData.setUp(settings.getNumberOfPlayers());
        game = new Game(settings);
        addPlayer(host, in, out);
        validator = new GameAnalyzer(game);
    }
    
    /**
     * Metoda zwraca liczbê graczy, którzy do³¹czyli do danej rozgrywki.
     */

    public int getNumberOfJoinedPlayers() {
        return numberOfJoinedPlayers;
    }
    
    /**
     * Meotda zwraca dane o ustawieniach danej rozgrywki.
     */

    public GameSettings getSettings() {
        return settings;
    }
    
    /**
     * Metoda zarz¹dza przeprowadzeniem danej rozgrywki (oczekiwanie na graczy, ustawienie parametrów rozgrywki, wylosowanie gracza rozpoczynaj¹cego grê, kolejkowanie ruchów graczy, pobieranie ruchów i ich wykonywanie).
     */

    @Override
    public void run()  {
        Random rand = new Random();
        
        while(numberOfJoinedPlayers < game.getNumberOfPlayers()) {
            System.out.println("Waiting for " + (game.getNumberOfPlayers() - numberOfJoinedPlayers)  + " more players");
            try {
                synchronized(this) {
                    wait(2000);
                }
            }
            catch (InterruptedException ex) {}
        }
        
        communicationData.sendMessageToAllPlayers("Game started");
        game.setUp();
        started = true;
        currentPlayerNumber = rand.nextInt(game.getNumberOfPlayers());
        
        while (!isOver) {
           try {
                if(!this.isWinner(currentPlayerNumber)) {
                	MoveDetails newMoveDetails = listenForMove();
                	makeMove(newMoveDetails);
                	endMove();
                } else {
                	currentPlayerNumber = (currentPlayerNumber + 1) % game.getNumberOfPlayers();
                }         
           }
           catch (Exception e) {
                System.out.println("Making move error");
            }
        }
    }
    
    /**
     * Metoda oczekuje na ruch danego gracza, wysy³a odpowiednie komunikaty, sprawdza, czy gracz pomin¹³ ruch w danej kolejce, sprawdza, czy ruch wykonany przez gracza jest ruchem dozwolonym. 
     */

    private MoveDetails listenForMove() throws Exception {
        boolean isMoveLegal = false;
        MoveDetails details;
        System.out.println("Waiting for move of player " + currentPlayerNumber);
        BufferedReader playerInputReader = communicationData.getInputReaderByNumber(currentPlayerNumber);
        PrintWriter playerPrintWriter = communicationData.getPrintWriterByNumber(currentPlayerNumber);
        playerPrintWriter.println("Your turn.");
        
        Player currentPlayer = game.getPlayerByNumber(currentPlayerNumber);
        
        do {
            String moveLine = playerInputReader.readLine();
            
            if(moveLine.contains("skip")) {
            	this.skip = true;
            	return null;
            }
            
            details = new MoveDetails(currentPlayer, moveLine);
            isMoveLegal = validator.isValid(details, hasJumped, lastMovedPawn);
        } while (!isMoveLegal);
        
        return details;
    }

    /**
     * Metoda wczytuje dane o ruchu danego gracza (wspó³rzêdne pola pocz¹tkowego, wspó³rzêdne pola koñcowego), ustawia odpowiednio status pól planszy na podstawie ruchu gracza, sprawdza rodzaj ruchu i wysy³a wszystkim graczom informacjê o ruchu wykonanym przez danego gracza.
     */
    
    private void makeMove(MoveDetails details) throws Exception {
    	if(details != null) {
            int oldDiagonal = details.getOldDiagonal();
            int oldRow = details.getOldRow();
            int newDiagonal = details.getNewDiagonal();
            int newRow = details.getNewRow();
            
            Field oldField = game.getBoard().getFieldByCoordinates(oldDiagonal, oldRow);
            Field newField = game.getBoard().getFieldByCoordinates(newDiagonal, newRow);
            Pawn targetPawn = game.getPawnByField(oldField);

            oldField.setStatus(FieldStatus.FREE);
            targetPawn.setField(newField);
            newField.setStatus(FieldStatus.OCCUPIED);
            this.communicationData.sendMessageToAllPlayers("move: " + details.moveToString() + " " + currentPlayerNumber /* game.getPlayersNumbers()[currentPlayerNumber] */);

            lastMovedPawn = targetPawn;
            hasJumped = validator.moveIsJump(oldField, newField);
    	}
    }
    
    /**
     * Metoda sprawdza, czy gracz o danym numerze znajduje siê na liœcie zwyciêzców, czyli czy gracz ukoñczy³ ju¿ dan¹ rozgrywkê.
     */
    
    private boolean isWinner(int number) {
    	Player player = this.game.getPlayerByNumber(number);
    	return this.game.isWinner(player);
    }
    
    /**
     * Metoda dodaje gracza do odpowiedniej rozgrywki, ustala ustawienia rozgrywki dla danego gracza i wysy³a komunikat o ustawieniach.
     */

    public void addPlayer(Socket player, BufferedReader in, PrintWriter out) throws IOException {
        communicationData.addPlayer(player, in, out);
        GameSettings gameSettings = this.game.getSettings();
        out.println(gameSettings.toString());
        numberOfJoinedPlayers++;
    }
    
    /**
     * Metoda sprawdza, czy dana rozgrywka zosta³a rozpoczêta.
     */

    public boolean hasStarted() {
        return started;
    }
    
    /**
     * Metoda sprawdza, czy gracz zakoñczy³ swój ruch oraz sprawdza, czy po tym ruchu gracz zakoñczy³ swoj¹ rozgrywkê.
     */
    
    public void endMove() throws Exception {
    	if(skip == true) {
    		if(this.hasFinished(currentPlayerNumber) && !this.isWinner(currentPlayerNumber)) {
            	this.addWinner(currentPlayerNumber);
            	this.communicationData.sendMessageToAllPlayers("winner " + currentPlayerNumber);
            }
    		
            if(this.over())
            	this.isOver = true;
			
            currentPlayerNumber = (currentPlayerNumber + 1) % game.getNumberOfPlayers();
			hasJumped = false;
			lastMovedPawn = null;
			skip = false;
    	}
    	else if (!hasPossibleJumps(lastMovedPawn)) {
            hasJumped = false;
            lastMovedPawn = null;
            
            if(this.hasFinished(currentPlayerNumber) && !this.isWinner(currentPlayerNumber)) {
            	this.addWinner(currentPlayerNumber);
            	this.communicationData.sendMessageToAllPlayers("winner " + currentPlayerNumber);
            }
            if(this.over())
            	this.isOver = true;
            
           currentPlayerNumber = (currentPlayerNumber + 1) % game.getNumberOfPlayers();            
        }
    }
    
    /**
     * Metoda sprawdza, czy wybrany pionek ma mo¿liwoœæ wykonania skoku w jakimkolwiek kierunku oraz czy wykonany ruch by³ skokiem.
     */

    private boolean hasPossibleJumps(Pawn pawn) throws Exception {
        return validator.hasPossibleJumps(pawn) && hasJumped;
    }
    
    /**
     * Metoda sprawdza, czy dana rozgrywka zosta³a zakoñczona.
     */
    
    private boolean over() {
    	return this.numberOfJoinedPlayers == this.game.getNumberOfWinners();
    }
    
    /**
     * Metoda sprawdza, czy gracz o danym numerze zakoñczy³ grê.
     */
    
    private boolean hasFinished(int number) {
    	return this.validator.hasFinished(this.game.getPlayerByNumber(number));
    }
    
    /**
     * Metoda dodaje gracza o danym numerze do listy zwyciêzców, czyli listy graczy, którzy ukoñczyli ju¿ dan¹ rozgrywkê.
     */
    
    private void addWinner(int number) {
    	this.game.addWinner(this.game.getPlayerByNumber(number));
    }
}
