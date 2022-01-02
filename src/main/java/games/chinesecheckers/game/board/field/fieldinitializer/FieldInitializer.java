package games.chinesecheckers.game.board.field.fieldinitializer;

import games.chinesecheckers.game.board.field.Field;

/**
 * Klasa definiuje metody generuj¹ce pola planszy.
 */

public abstract class FieldInitializer {
	protected Field[] fields;

	/**
	 * Metoda definiuje metody inicjalizuj¹ce pola planszy o danym rozmiarze.
	 */
	
    public void initializeFields() {
        initializeHexagonFields();
        initializePlayerHomeFields();
    }

    /**
	 * Metoda inicjalizuje pola startowe graczy w zale¿noœci od ustalonego rozmiaru planszy oraz kolorów pól startowych.
	 */
    
    protected abstract void initializePlayerHomeFields();
    
    /**
	 * Metoda inicjalizuje pola wewnêtrzne planszy w zale¿noœci od ustalonego rozmiaru planszy.
	 */
    
    protected abstract void initializeHexagonFields();
}
