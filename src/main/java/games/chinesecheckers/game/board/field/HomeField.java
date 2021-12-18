package games.chinesecheckers.game.board.field;

public class HomeField extends Field  {
	public HomeField(int diagonal, int row, FieldColor playercolor) {
        this.diagonal = diagonal;
        this.row = row;
        this.color = playercolor;
        this.status = FieldStatus.OCCUPIED;
    }
}
