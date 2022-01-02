package games.chinesecheckers.board;

import games.chinesecheckers.game.board.BoardInfo;

/**
 * Klasa ustawia rozmiar promienia pola planszy i rozmiar odstêpu pomiêdzy polami planszy w zale¿noœci od obecnych rozmiarów planszy (liczby wierszy planszy).
 */

public class BoardData {
	public static final double fieldSize = 320 / BoardInfo.NUMBER_OF_ROWS;
	public static final double gapSize = 120 / (BoardInfo.NUMBER_OF_ROWS - 1);
}
