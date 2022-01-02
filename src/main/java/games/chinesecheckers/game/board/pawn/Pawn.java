package games.chinesecheckers.game.board.pawn;

import games.chinesecheckers.game.board.field.Field;
import games.chinesecheckers.game.board.field.FieldColor;

/**
 * Klasa ustala dane wybranego pionka.
 */

public class Pawn {
	private Field currentField;
	private FieldColor pawnColor;

    /**
     * 	Konstruktor ustala dane pola i koloru pionka na te przekazane do konstruktora.
     */
	
	public Pawn(Field currentField, FieldColor pawnColor) {
        this.currentField = currentField;
        this.pawnColor = pawnColor;
    }
	
	/**
	 * Metoda zwraca pole, na kt�rym znajduje si� pionek.
	 */

    public Field getField() {
        return currentField;
    }
    
    /**
     * Metoda ustala pole, na kt�rym ma znajdowa� si� pionek.
     */
    
    public void setField(Field field) {
        currentField = field;
    }
    
    /**
	 * Metoda zwraca kolor daengo pionka.
	 */
    
    public FieldColor getPawnColor() {
        return pawnColor;
    }

    /**
     * Metoda zwraca dane o po�o�eniu pola danego pionka (przek�tna, wiersz) w postaci tekstowej.
     */
    
    public String currentFieldToString() {
    	return this.currentField.currentFieldToString();
    }
}
