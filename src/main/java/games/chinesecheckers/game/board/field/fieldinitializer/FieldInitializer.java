package games.chinesecheckers.game.board.field.fieldinitializer;

import games.chinesecheckers.game.board.field.Field;

/**
 * Klasa definiuje metody generujące pola planszy.
 */

public abstract class FieldInitializer {
	protected Field[] fields;

	/**
	 * Metoda definiuje metody inicjalizujące pola planszy o danym rozmiarze.
	 */
	
    public void initializeFields() {
        initializeHexagonFields();
        initializePlayerHomeFields();
    }

    /**
	 * Metoda inicjalizuje pola startowe graczy w zależności od ustalonego rozmiaru planszy oraz kolorów pól startowych.
	 */
    
    protected abstract void initializePlayerHomeFields();
    
    /**
	 * Metoda inicjalizuje pola wewnętrzne planszy w zależności od ustalonego rozmiaru planszy.
	 */
    
    protected abstract void initializeHexagonFields();
}
