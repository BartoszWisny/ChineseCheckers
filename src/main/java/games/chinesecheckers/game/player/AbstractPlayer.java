package games.chinesecheckers.game.player;

import games.chinesecheckers.game.board.BoardInfo;
import games.chinesecheckers.game.board.field.FieldColor;
import games.chinesecheckers.game.board.pawn.Pawn;

/**
 * Klasa ustala parametry danego gracza.
 */

public abstract class AbstractPlayer implements Player {
	protected FieldColor homeColor;
    protected FieldColor opponentColor;
    protected Pawn[] pawns = new Pawn[BoardInfo.NUMBER_OF_HOME_FIELDS];

    /**
     * Metoda zwraca kolor danego gracza.
     */
    
    public FieldColor getColor() {
        return homeColor;
    }
    
    /**
     * Metoda zwraca kolor przeciwnika danego gracza.
     */
    
    public FieldColor getOpponentColor() {
        return opponentColor;
    }
    
    /**
     * Metoda ustala pionki dla danego gracza.
     */

    public void setPawns(Pawn[] pawns) {
        this.pawns = pawns;
    }
    
    /**
     * Metoda zwraca kolor danego gracza i kolor jego przeciwnika w postaci tekstowej.
     */
    
    public String toString() {
    	return "FieldColor: " + homeColor.toString() + " opponentColor: " + opponentColor.toString();
    }
}
