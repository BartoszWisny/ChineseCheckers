package games.chinesecheckers.game.board.field.fieldinitializer;

import games.chinesecheckers.game.board.Board;
import games.chinesecheckers.game.board.BoardInfo;
import games.chinesecheckers.game.board.BoardSize;
import games.chinesecheckers.game.board.field.NeutralField;
import games.chinesecheckers.game.board.field.HomeField;
import games.chinesecheckers.game.board.field.FieldColor;

public class ConcreteFieldInitializer extends FieldInitializer {
	public ConcreteFieldInitializer(Board board) {
        this.fields = board.getFields();
    }
	
	public static int /* HEXAGONAL_SIDE - 1 */ n = BoardSize.boardSize;
	private int currentCounter = 0;
	
	public int[][] hexagonBounds() {
        int n = BoardSize.boardSize;
        int NUMBER_OF_ROWS_OF_HEXAGON = 2 * n + 1;
        int[][] HEXAGON_BOUNDS = new int[NUMBER_OF_ROWS_OF_HEXAGON][2];
        int k = 0;

        for (int i = 0; i < n + 1; i++) {
            HEXAGON_BOUNDS[k][0] = 2 * n - i;
            HEXAGON_BOUNDS[k][1] = 3 * n;
            k++;
        }

        for (int i = 0; i < n; i++) {
            HEXAGON_BOUNDS[k][0] = n;
            HEXAGON_BOUNDS[k][1] = 3 * n - 1 - i;
            k++;
        }

        return HEXAGON_BOUNDS;
    }

    @Override
    protected void initializePlayerHomeFields() {
        int colorNumber = 0;
        for (int[][] playerHomeCoordinates : BoardInfo.homeFields()) {
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
        for (int i = n; i < 3 * n + 1; i++) {
            int lowerBound = hexagonBounds()[i - n][0];
            int upperBound = hexagonBounds()[i - n][1];
            for (int j = lowerBound; j < upperBound + 1; j++) {
                fields[currentCounter] = new NeutralField(j, i);
                currentCounter++;
            }
        }
    }
}
