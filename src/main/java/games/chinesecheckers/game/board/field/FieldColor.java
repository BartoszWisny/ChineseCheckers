package games.chinesecheckers.game.board.field;

public enum FieldColor {
	BLUE, WHITE, RED, BLACK, YELLOW, GREEN, NONE;

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
