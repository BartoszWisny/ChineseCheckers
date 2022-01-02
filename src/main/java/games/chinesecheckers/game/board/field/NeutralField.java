package games.chinesecheckers.game.board.field;

/**
 * Klasa odpowiada za ustawienie danych pola wewn�trznego planszy.
 */

public class NeutralField extends Field {
	
	/**
	 * Konstruktor na podstawie danych o po�o�eniu pola (przek�tna, wiersz) ustala dane pola pola wewn�trznego planszy.
	 */
	
	public NeutralField(int diagonal, int row) {
        this.diagonal = diagonal;
        this.row = row;
        this.color = FieldColor.NONE;
        this.status = FieldStatus.FREE;    
    }
}
