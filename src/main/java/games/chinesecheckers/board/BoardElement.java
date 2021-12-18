package games.chinesecheckers.board;

import games.chinesecheckers.game.board.field.FieldColor;

public interface BoardElement {
	boolean isField();
	boolean isPawn();
	FieldColor getColor();
}
