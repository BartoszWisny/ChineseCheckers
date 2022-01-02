package games.chinesecheckers.game.board.field;

/**
 * Klasa odpowiada za ustawienie danych pola niedozwolonego. 
 */

public class IllegalField extends Field {
	
	/**
	 * Konstruktor na podstawie danych o po³o¿eniu pola (przek¹tna, wiersz) ustala dane pola niedozwolonego.
	 */
	
	public IllegalField(int diagonal, int row) {
        this.diagonal = diagonal;
        this.row = row;
        this.color = FieldColor.NONE;
        this.status = FieldStatus.ILLEGAL;       
    }
}
