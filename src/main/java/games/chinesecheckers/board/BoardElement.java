package games.chinesecheckers.board;

import games.chinesecheckers.game.board.field.FieldColor;

/**
 * Interfejs definuje metody dla elementu planszy - pola planszy i pionka.
 */

public interface BoardElement {
	boolean isField();
	boolean isPawn();
	FieldColor getColor();
}
