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
 * Klasa odpowiada za ustawienie odpowiednich parametr�w i element�w danej rozgrywki.
 */

public final class Game {
    private GameSettings gameSettings;
    private Player[] players;
    private Pawn[] pawns;
    private List<Player> winners = new ArrayList<Player>();
    private Board board;
    private GameBuilder gameBuilder;

    /**
     * Konstruktor tworzy odpowiednie ustawienia dla danej gry i ustala obiekt zarz�dzaj�cy budow� danej gry.
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
     * Metoda zwraca ustawienia planszy dla danej rozgrywki, mi�dzy innymi wsp�rz�dne p�l planszy czy pola startowe graczy.
     */

    public Board getBoard() {
        return board;
    }
    
    /**
     * Metoda zwraca dane pionk�w, kt�re s� u�ywane w danej rozgrywce przez graczy.
     */
    
    public Pawn[] getPawns() {
    	return pawns;
    }
    
    /**
     * Metoda zwraca dane pionk�w, kt�re s� u�ywane przez wybranego gracza w obecnej rozgrywce.
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
     * Metoda ustawia odpowiedni� plansz� dla danej rozgrywki, mi�dzy innymi odpowiednie wsp�rz�dne p�l planszy.
     */

    public void setBoard(Board board) {
        this.board = board;
    }
    
    /**
     * Metoda ustawia odpowiednich graczy dla danej rozgrywki, mi�dzy innymi odpowiednie kolory graczy.
     */

    public void setPlayers(Player[] players) {
        this.players = players;
    }
    
    /**
     * Metoda ustawia odpowiednie pionki dla graczy dla danej rozgrywki, mi�dzy innymi odpowiednie kolory pionk�w i odpowiednie wsp�rz�dne pionk�w.
     */

    public void setPawns(Pawn[] pawns) {
        this.pawns = pawns;
    }
    
    /**
     * Metoda ustala odpowiednie ustawienia dla danej rozgrywki, mi�dzy innymi na podstawie ilo�ci graczy w danej rozgrywce.
     */

    public void setSettings(GameSettings settings) {
        gameSettings = settings;
    }
    
    /**
     * Metoda zwraca liczb� graczy bior�cych udzia� w danej rozgrywce.
     */

    public int getNumberOfPlayers() {
       return gameSettings.getNumberOfPlayers();
    }
    
    /**
     * Metoda zwraca odpowiednie numery graczy bior�cych udzia� w danej rozgrywce.
     */
    
    public int[] getPlayersNumbers() {
    	return gameSettings.getPlayersNumbers();
    }
    
    /**
     * Metoda buduje gr� w zale�no�ci od parametr�w ustalonych w tej klasie.
     */

    public void setUp() {
        gameBuilder.buildGame(this);
    }
    
    /**
     * Metoda pobiera dane o mo�liwym pionku znajduj�cym si� na przekazanym polu planszy.
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
     * Metoda dodaje wybranego gracza do listy zwyci�zc�w, czyli listy graczy, kt�rzy uko�czyli ju� dan� rozgrywk�.
     */

    public void addWinner(Player winner) {
    	this.winners.add(winner);
    }
    
    /**
     * Metoda sprawdza, czy wybrany gracz znajduje si� na li�cie zwyci�zc�w, czyli czy gracz uko�czy� ju� dan� rozgrywk�.
     */
    
    public boolean isWinner(Player player) {
    	return this.winners.contains(player);
    }
    
    /**
     * Metoda zwraca liczb� zwyci�zc�w, czyli liczb� graczy, kt�rzy uko�czyli swoj� gr� w danej rozgrywce.
     */
    
    public int getNumberOfWinners() {
    	return this.winners.size();
    }
}
