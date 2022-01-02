package games.chinesecheckers.game;

import games.chinesecheckers.game.board.Board;
import games.chinesecheckers.game.board.BoardInfo;
import games.chinesecheckers.game.board.field.Field;
import games.chinesecheckers.game.board.field.FieldColor;
import games.chinesecheckers.game.board.pawn.Pawn;
import games.chinesecheckers.game.gamebuilder.ConcreteGameBuilder;
import games.chinesecheckers.game.gamebuilder.GameBuilder;
import games.chinesecheckers.game.gamesettings.GameSettings;
import games.chinesecheckers.game.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Klasa odpowiada za ustawienie odpowiednich parametrów i elementów danej rozgrywki.
 */

public final class Game {
    private GameSettings gameSettings;
    private Player[] players;
    private Pawn[] pawns;
    private List<Player> winners = new ArrayList<Player>();
    private Board board;
    private GameBuilder gameBuilder;

    /**
     * Konstruktor tworzy odpowiednie ustawienia dla danej gry i ustala obiekt zarz¹dzaj¹cy budow¹ danej gry.
     */
    
    public Game(GameSettings settings) {
        setSettings(settings);
        gameBuilder = new ConcreteGameBuilder();
    }
    
    /**
     * Metoda zwraca gracza w danej rozgrywce na podstawie nadanego mu przez serwer numeru w danej rozgrywce.
     */

    public Player getPlayerByNumber(int number) {
        return players[number];
    }
    
    /**
     * Metoda zwraca ustawienia obecnej rozgrywki.
     */
   
    public GameSettings getSettings() {
    	return this.gameSettings;
    }
    
    /**
     * Metoda zwraca ustawienia planszy dla danej rozgrywki, miêdzy innymi wspó³rzêdne pól planszy czy pola startowe graczy.
     */

    public Board getBoard() {
        return board;
    }
    
    /**
     * Metoda zwraca dane pionków, które s¹ u¿ywane w danej rozgrywce przez graczy.
     */
    
    public Pawn[] getPawns() {
    	return pawns;
    }
    
    /**
     * Metoda zwraca dane pionków, które s¹ u¿ywane przez wybranego gracza w obecnej rozgrywce.
     */

    public Pawn[] getPlayerPawns(FieldColor playerColor) {
        Pawn[] playerPawns = new Pawn[BoardInfo.NUMBER_OF_HOME_FIELDS];
        int i = 0;
        for (Pawn pawn : pawns) {
            if (pawn.getPawnColor() == playerColor) {
                playerPawns[i] = pawn;
                i++;
            }
        }
        return playerPawns;
    }
    
    /**
     * Metoda ustawia odpowiedni¹ planszê dla danej rozgrywki, miêdzy innymi odpowiednie wspó³rzêdne pól planszy.
     */

    public void setBoard(Board board) {
        this.board = board;
    }
    
    /**
     * Metoda ustawia odpowiednich graczy dla danej rozgrywki, miêdzy innymi odpowiednie kolory graczy.
     */

    public void setPlayers(Player[] players) {
        this.players = players;
    }
    
    /**
     * Metoda ustawia odpowiednie pionki dla graczy dla danej rozgrywki, miêdzy innymi odpowiednie kolory pionków i odpowiednie wspó³rzêdne pionków.
     */

    public void setPawns(Pawn[] pawns) {
        this.pawns = pawns;
    }
    
    /**
     * Metoda ustala odpowiednie ustawienia dla danej rozgrywki, miêdzy innymi na podstawie iloœci graczy w danej rozgrywce.
     */

    public void setSettings(GameSettings settings) {
        gameSettings = settings;
    }
    
    /**
     * Metoda zwraca liczbê graczy bior¹cych udzia³ w danej rozgrywce.
     */

    public int getNumberOfPlayers() {
       return gameSettings.getNumberOfPlayers();
    }
    
    /**
     * Metoda zwraca odpowiednie numery graczy bior¹cych udzia³ w danej rozgrywce.
     */
    
    public int[] getPlayersNumbers() {
    	return gameSettings.getPlayersNumbers();
    }
    
    /**
     * Metoda buduje grê w zale¿noœci od parametrów ustalonych w tej klasie.
     */

    public void setUp() {
        gameBuilder.buildGame(this);
    }
    
    /**
     * Metoda pobiera dane o mo¿liwym pionku znajduj¹cym siê na przekazanym polu planszy.
     */

    public Pawn getPawnByField(final Field field) throws Exception {
        Optional<Pawn> pawn = Arrays.stream(pawns).filter(new Predicate<Pawn>() {
			public boolean test(Pawn pawn) {
				return pawn.getField() == field;
			}
		}).findFirst();
        
        if (pawn.isPresent()) {
            return pawn.get();
        }
        else {
            throw new Exception("Field is empty");
        }
    }
    
    /**
     * Metoda dodaje wybranego gracza do listy zwyciêzców, czyli listy graczy, którzy ukoñczyli ju¿ dan¹ rozgrywkê.
     */

    public void addWinner(Player winner) {
    	this.winners.add(winner);
    }
    
    /**
     * Metoda sprawdza, czy wybrany gracz znajduje siê na liœcie zwyciêzców, czyli czy gracz ukoñczy³ ju¿ dan¹ rozgrywkê.
     */
    
    public boolean isWinner(Player player) {
    	return this.winners.contains(player);
    }
    
    /**
     * Metoda zwraca liczbê zwyciêzców, czyli liczbê graczy, którzy ukoñczyli swoj¹ grê w danej rozgrywce.
     */
    
    public int getNumberOfWinners() {
    	return this.winners.size();
    }
}
