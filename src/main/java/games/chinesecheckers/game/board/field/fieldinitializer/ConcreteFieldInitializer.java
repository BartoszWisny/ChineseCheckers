package games.chinesecheckers.game.board.field.fieldinitializer;

import games.chinesecheckers.game.board.Board;
import games.chinesecheckers.game.board.BoardInfo;
import games.chinesecheckers.game.board.field.NeutralField;
import games.chinesecheckers.game.board.field.HomeField;
import games.chinesecheckers.game.board.field.FieldColor;

public class ConcreteFieldInitializer extends FieldInitializer {
	public ConcreteFieldInitializer(Board board) {
        this.fields = board.getFields();
    }

    private int[][] hexagonBounds = {{8, 12}, {7, 12}, {6, 12}, {5, 12}, {4, 12}, {4, 11}, {4, 10}, {4, 9}, {4, 8}};
    private int currentCounter = 0;

    @Override
    protected void initializePlayerHomeFields() {
        int colorNumber = 0;
        for (int[][] playerHomeCoordinates : BoardInfo.PLAYER_FIELDS_COORDINATES) {
            for (int[] pawnCoordinates : playerHomeCoordinates) {
                int diagonal = pawnCoordinates[0];
                int row = pawnCoordinates[1];
                
                fields[currentCounter] = new HomeField(diagonal, row, FieldColor.setColor(colorNumber));
                currentCounter++;
            }
            colorNumber++;
        }
    }

    @Override
    protected void initializeHexagonFields() {
        for (int i = 4; i < 13; i++) {
            int lowerBound = hexagonBounds[i - 4][0];
            int upperBound = hexagonBounds[i - 4][1];
            for (int j = lowerBound; j < upperBound + 1; j++) {
                fields[currentCounter] = new NeutralField(j, i);
                currentCounter++;
            }
        }
    }
}
