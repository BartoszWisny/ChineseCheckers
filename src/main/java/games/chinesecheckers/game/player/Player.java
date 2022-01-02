package games.chinesecheckers.game.player;

import games.chinesecheckers.game.board.field.FieldColor;
import games.chinesecheckers.game.board.pawn.Pawn;

/**
 * Klasa definiuje metody ustalaj¹ce dane wybranego gracza.
 */

public interface Player {
	
	/**
     * Metoda ustala pionki dla danego gracza.
     */
	
	void setPawns(Pawn[] pawns);
	
	/**
     * Metoda zwraca kolor danego gracza.
     */
	
	FieldColor getColor();
	
	/**
     * Metoda zwraca kolor przeciwnika danego gracza.
     */
	
	FieldColor getOpponentColor();
}
