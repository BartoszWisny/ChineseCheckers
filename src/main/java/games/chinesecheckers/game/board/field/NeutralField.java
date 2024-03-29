package games.chinesecheckers.game.board.field;

/**
 * Klasa odpowiada za ustawienie danych pola wewnętrznego planszy.
 */

public class NeutralField extends Field {
	
	/**
	 * Konstruktor na podstawie danych o położeniu pola (przekątna, wiersz) ustala dane pola pola wewnętrznego planszy.
	 */
	
	public NeutralField(int diagonal, int row) {
        this.diagonal = diagonal;
        this.row = row;
        this.color = FieldColor.NONE;
        this.status = FieldStatus.FREE;    
    }
}
