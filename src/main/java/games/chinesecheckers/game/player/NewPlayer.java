package games.chinesecheckers.game.player;

import games.chinesecheckers.game.board.field.FieldColor;

/**
 * Klasa ustala kolor gracza i kolor jego przeciwnika.
 */

public final class NewPlayer extends AbstractPlayer {

	/**
	 * Konstruktor ustala kolor danego gracza i kolor jego przeciwnika w zależności od przekazanego koloru.
	 */
	
    public NewPlayer(FieldColor playerColor) {
        homeColor = playerColor;
        opponentColor = FieldColor.getOpponent(playerColor);
    }
}
