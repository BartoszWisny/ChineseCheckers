package games.chinesecheckers.game.board.field;

/**
 * Klasa definiuje kolory pól planszy.
 */

public enum FieldColor {
	BLUE, WHITE, RED, BLACK, YELLOW, GREEN, NONE;

	/**
	 * Metoda ustala kolory pól planszy w zale¿noœci od numeru danego gracza.
	 */
	
    public static FieldColor setColor(int number) {
        switch (number) {
            case 0:
                return FieldColor.BLUE;
            case 1:
                return FieldColor.WHITE;
            case 2:
                return FieldColor.RED;
            case 3:
                return FieldColor.BLACK;
            case 4:
                return FieldColor.YELLOW;
            case 5:
                return FieldColor.GREEN;
            default:
                return FieldColor.NONE;
        }
    }
    
    /**
     * Metoda ustala kolory pól planszy przeciwnika w zale¿noœci od koloru danego gracza.
     */
	
	public static FieldColor getOpponent(FieldColor color) {
        switch (color) {
            case BLUE:
                return BLACK;
            case WHITE:
                return YELLOW;
            case RED:
                return GREEN;
            case BLACK:
                return BLUE;
            case YELLOW:
                return WHITE;
            case GREEN:
                return RED;
            default:
                return NONE;
        }
    }
    
	/**
	 * Metoda zwraca informacjê o kolorze w postaci tekstowej.
	 */
	
    public String toString() {
    	switch (this) {
    	case BLUE:
    		return "Blue";
    	case WHITE:
            return "White";
        case RED:
            return "Red";
        case BLACK:
            return "Black";
        case YELLOW:
            return "Yellow";
        case GREEN:
            return "Green";
        default:
            return "";
    	}
    }
}
