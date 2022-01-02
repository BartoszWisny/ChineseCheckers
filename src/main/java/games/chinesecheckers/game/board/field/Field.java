package games.chinesecheckers.game.board.field;

/**
 * Klasa odpowiada za ustawienie odpowiednich danych dla okreœonego pola planszy i sprawdza informacje o danym polu.
 */

public abstract class Field {
    protected int diagonal;
    protected int row;
    protected FieldColor color;
    protected FieldStatus status;

    /**
     * Metoda sprawdza, czy wybrane pole jest wolne (czyli nie jest zajête przez pionek któregoœ z graczy).
     */
    
    public boolean isFreeField() {
    	return status == FieldStatus.FREE;
    }
    
    /**
     * Metoda sprawdza, czy wybrane pole jest polem startowym któregoœ z graczy.
     */

    public boolean isHomeField() {
    	return !color.equals(FieldColor.NONE);
    }
    
    /**
     * Metoda sprawdza, czy wybrane pole jest polem dozwolonym (czyli czy pole znajduje siê na wygenerowanej planszy).
     */

    public boolean isLegalField() {
    	return status != FieldStatus.ILLEGAL;
    }
    
    /**
	 * Metoda zwraca numer przek¹tnej dla pola planszy.
	 */

    public int getDiagonal() {
        return diagonal;
    }
    
    /**
	 * Metoda zwraca numer wiersza dla pola planszy.
	 */
    
    public int getRow() {
        return row;
    }
    
    /**
	 * Metoda zwraca kolor pola planszy.
	 */
    
    public FieldColor getColor() {
        return color;
    }
    
    /**
	 * Metoda zwraca obecny status pola planszy (wolne, zajête lub niedozwolone).
	 */

    public void setStatus(FieldStatus status) {
        this.status = status;
    }
    
    /**
     * Metoda zwraca dane o po³o¿eniu pola (przek¹tna, wiersz) w postaci tekstowej.
     */
    
    public String currentFieldToString() {
    	return Integer.toString(diagonal) + " " + Integer.toString(row);
    }
}
