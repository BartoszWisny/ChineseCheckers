package games.chinesecheckers.game.board.field;

public abstract class Field {
    protected int diagonal;
    protected int row;
    protected FieldColor color;
    protected FieldStatus status;

    public boolean isFreeField() {
    	return status == FieldStatus.FREE;
    }

    public boolean isHomeField() {
    	return !color.equals(FieldColor.NONE);
    }

    public boolean isLegalField() {
    	return status != FieldStatus.ILLEGAL;
    }

    public int getDiagonal() {
        return diagonal;
    }
    
    public int getRow() {
        return row;
    }
    
    public FieldColor getColor() {
        return color;
    }

    public void setStatus(FieldStatus status) {
        this.status = status;
    }
    
    public String currentFieldToString() {
    	return Integer.toString(diagonal) + " " + Integer.toString(row);
    }
}
