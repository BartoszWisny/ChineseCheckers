package games.chinesecheckers.game.gamebuilder;

import games.chinesecheckers.game.Game;
import games.chinesecheckers.game.board.Board;
import games.chinesecheckers.game.board.BoardInfo;
import games.chinesecheckers.game.board.field.Field;
import games.chinesecheckers.game.board.field.FieldColor;
import games.chinesecheckers.game.board.pawn.Pawn;
import games.chinesecheckers.game.player.NewPlayer;
import games.chinesecheckers.game.player.Player;

/**
 * Klasa odpowiada za zbudowanie odpowiedniej gry (plansza, pionki, gracze).
 */

public class ConcreteGameBuilder implements GameBuilder {
    private Game game;
    private Board board;
    private Player[] players;
    private Pawn[] pawns;

    /**
     * Metoda buduje grê - buduje planszê gry, buduje pionki dla graczy i buduje graczy dla danej gry.
     */
    
    public Game buildGame(Game game) {
        this.game = game;
        board = buildBoard();
        game.setBoard(board);
        pawns = buildPawns(game.getPlayersNumbers());
        game.setPawns(pawns);
        players = buildPlayers(game.getPlayersNumbers());
        game.setPlayers(players);

        return game;
    }
    
    /**
     * Metoda buduje planszê dla danej gry.
     */

    private Board buildBoard() {
        Board board = new Board();
        return board;
    }
    
    /**
     * Metoda buduje pionki dla danej gry w zale¿noœci od iloœci graczy w danej grze.
     */

    public Pawn[] buildPawns(int playersNumbers[]) {
    	Pawn[] pawns = new Pawn[BoardInfo.NUMBER_OF_HOME_FIELDS * playersNumbers.length];
        int currentCounter = 0;
        for (int playerCounter = 0; playerCounter < playersNumbers.length; playerCounter++) {
            FieldColor currentPlayerColor = FieldColor.setColor(playersNumbers[playerCounter]);
            for (Field field : board.getFields(currentPlayerColor)) {
                pawns[currentCounter] = new Pawn(field, currentPlayerColor);
                currentCounter++;
            }
        }
        return pawns;
    }
    
    /**
     * Metoda buduje graczy dla danej gry (inicjalizuje graczy w zale¿noœci od iloœci graczy w danej grze).
     */

    private Player[] buildPlayers(int[] playersNumbers) {
        Player[] players = new Player[playersNumbers.length];
        initializePlayers(playersNumbers, players);
        return players;
    }
    
    /**
     * Metoda inicjalizuje graczy w zale¿noœci od iloœci graczy w danej grze.
     */

    private void initializePlayers(int[] playersNumbers, Player[] players) {
        for (int i = 0; i < playersNumbers.length; i++) {
            initializePlayer(i, playersNumbers[i], players);
        }
    }
    
    /**
     * Metoda inicjalizuje wybranego gracza w danej grze, ustala jego kolor i pionki.
     */

    private void initializePlayer(int index, int playerNumber, Player[] players) {
        FieldColor newPlayerColor = FieldColor.setColor(playerNumber);
        Player newPlayer = new NewPlayer(newPlayerColor);
        players[index] = newPlayer;

        Pawn[] newPlayerPawns = game.getPlayerPawns(newPlayerColor);
        newPlayer.setPawns(newPlayerPawns);
    }
}
