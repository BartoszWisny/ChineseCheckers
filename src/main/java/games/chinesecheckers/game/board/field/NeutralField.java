package games.chinesecheckers.game.board.field;

/**
 * Klasa odpowiada za ustawienie danych pola wewnêtrznego planszy.
 */

public class NeutralField extends Field {
	
	/**
	 * Konstruktor na podstawie danych o po³o¿eniu pola (przek¹tna, wiersz) ustala dane pola pola wewnêtrznego planszy.
	 */
	
	public NeutralField(int diagonal, int row) {
        this.diagonal = diagonal;
        this.row = row;
        this.color = FieldColor.NONE;
        this.status = FieldStatus.FREE;    
    }
}
