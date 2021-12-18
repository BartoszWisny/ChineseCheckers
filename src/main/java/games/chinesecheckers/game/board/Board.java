package games.chinesecheckers.game.board;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import games.chinesecheckers.game.board.field.Field;
import games.chinesecheckers.game.board.field.FieldColor;
import games.chinesecheckers.game.board.field.IllegalField;
import games.chinesecheckers.game.board.field.fieldinitializer.ConcreteFieldInitializer;
import games.chinesecheckers.game.board.field.fieldinitializer.FieldInitializer;

public class Board {

    private Field[] fields = new Field[BoardInfo.FIELDS];
    private FieldInitializer fieldInitializer;

    public Board() {
        fieldInitializer = new ConcreteFieldInitializer(this);
        initializeFields();
    }

    private void initializeFields() {
        fieldInitializer.initializeFields();
    }

    public Field[] getFields() {
        return fields;
    }
    
    public Field[] getFields(FieldColor color) {
        Field[] matchingFields = new Field[10];
        int i = 0;
        for (Field field : fields) {
            if (field.getColor() == color) {
                matchingFields[i] = field;
                i++;
            }
        }
        return matchingFields;
    }
    
    public Field getFieldByCoordinates(final int diagonal, final int row) {
    	Optional<Field> field =  Arrays.stream(fields).filter(new Predicate<Field>() {
        	public boolean test(Field field) {
        		return field.getDiagonal() == diagonal && field.getRow() == row;
        	}
        }).findFirst();
        
        if(field.isPresent()) {
            return field.get();
        }
        else {
            return new IllegalField(0,0);
        }
    }
}
