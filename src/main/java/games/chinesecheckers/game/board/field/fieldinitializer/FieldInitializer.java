package games.chinesecheckers.game.board.field.fieldinitializer;

import games.chinesecheckers.game.board.field.Field;

/**
 * Klasa definiuje metody generuj�ce pola planszy.
 */

public abstract class FieldInitializer {
	protected Field[] fields;

	/**
	 * Metoda definiuje metody inicjalizuj�ce pola planszy o danym rozmiarze.
	 */
	
    public void initializeFields() {
        initializeHexagonFields();
        initializePlayerHomeFields();
    }

    /**
	 * Metoda inicjalizuje pola startowe graczy w zale�no�ci od ustalonego rozmiaru planszy oraz kolor�w p�l startowych.
	 */
    
    protected abstract void initializePlayerHomeFields();
    
    /**
	 * Metoda inicjalizuje pola wewn�trzne planszy w zale�no�ci od ustalonego rozmiaru planszy.
	 */
    
    protected abstract void initializeHexagonFields();
}
