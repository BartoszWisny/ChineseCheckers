package games.chinesecheckers.board;

import games.chinesecheckers.game.board.BoardInfo;

/**
 * Klasa ustawia rozmiar promienia pola planszy i rozmiar odst�pu pomi�dzy polami planszy w zale�no�ci od obecnych rozmiar�w planszy (liczby wierszy planszy).
 */

public class BoardData {
	public static final double fieldSize = 320 / BoardInfo.NUMBER_OF_ROWS;
	public static final double gapSize = 120 / (BoardInfo.NUMBER_OF_ROWS - 1);
}
