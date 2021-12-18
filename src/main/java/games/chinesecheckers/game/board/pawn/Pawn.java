package games.chinesecheckers.game.board.pawn;

import games.chinesecheckers.game.board.field.Field;
import games.chinesecheckers.game.board.field.FieldColor;

public class Pawn {
	private Field currentField;
	private FieldColor pawnColor;

    public Pawn(Field currentField, FieldColor pawnColor) {
        this.currentField = currentField;
        this.pawnColor = pawnColor;
    }

    public Field getField() {
        return currentField;
    }
    
    public void setField(Field field) {
        currentField = field;
    }
    
    public FieldColor getPawnColor() {
        return pawnColor;
    }

    public String currentFieldToString() {
    	return this.currentField.currentFieldToString();
    }
}
