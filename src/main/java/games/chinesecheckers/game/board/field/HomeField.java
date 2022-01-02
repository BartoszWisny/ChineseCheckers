package games.chinesecheckers.game.board.field;

/**
 * Klasa odpowiada za ustawienie danych pola startowego gracza. 
 */

public class HomeField extends Field  {
	
	/**
	 * Konstruktor na podstawie danych o po³o¿eniu pola (przek¹tna, wiersz) i koloru gracza ustala dane pola startowego gracza.
	 */
	
	public HomeField(int diagonal, int row, FieldColor playercolor) {
        this.diagonal = diagonal;
        this.row = row;
        this.color = playercolor;
        this.status = FieldStatus.OCCUPIED;
    }
}
