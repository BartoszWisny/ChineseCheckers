package games.chinesecheckers.game.board.field.fieldinitializer;

import games.chinesecheckers.game.board.field.Field;

public abstract class FieldInitializer {
	protected Field[] fields;

    public void initializeFields() {
        initializeHexagonFields();
        initializePlayerHomeFields();
    }

    protected abstract void initializePlayerHomeFields();
    protected abstract void initializeHexagonFields();
}
