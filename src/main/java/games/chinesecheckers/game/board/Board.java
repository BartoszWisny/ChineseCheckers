package games.chinesecheckers.game.board;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import games.chinesecheckers.game.board.field.Field;
import games.chinesecheckers.game.board.field.FieldColor;
import games.chinesecheckers.game.board.field.IllegalField;
import games.chinesecheckers.game.board.field.fieldinitializer.ConcreteFieldInitializer;
import games.chinesecheckers.game.board.field.fieldinitializer.FieldInitializer;

/**
 * Klasa odpowiada za wygenerowanie pól planszy w zale¿noœci od typu rozgrywki (rozmiaru planszy).
 */

public class Board {
	private Field[] fields = new Field[BoardInfo.NUMBER_OF_FIELDS];
    private FieldInitializer fieldInitializer;

    /**
     * Konstruktor generuje planszê, generuj¹c ka¿de pole, w zale¿noœci od ustawionego rozmiaru planszy.
     */
    
    public Board() {
        fieldInitializer = new ConcreteFieldInitializer(this);
        initializeFields();
    }
    
    /**
     * Metoda wywo³uje metodê generuj¹c¹ pola planszy w zale¿noœci od jej rozmiaru.
     */

    private void initializeFields() {
        fieldInitializer.initializeFields();
    }
    
    /**
     * Metoda zwraca dane pól planszy dla wybranej planszy.
     */

    public Field[] getFields() {
        return fields;
    }
    
    /**
     * Metoda zwraca dane pól planszy dla wybranej planszy w zale¿noœci od koloru danego gracza.
     */
    
    public Field[] getFields(FieldColor color) {
        Field[] matchingFields = new Field[BoardInfo.NUMBER_OF_HOME_FIELDS];
        int i = 0;
        for (Field field : fields) {
            if (field.getColor() == color) {
                matchingFields[i] = field;
                i++;
            }
        }
        return matchingFields;
    }
    
    /**
     * Metoda zwraca dane pole planszy na podstawie przekazanych danych o po³o¿eniu pola na planszy (przek¹tna, wiersz).
     */
    
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
