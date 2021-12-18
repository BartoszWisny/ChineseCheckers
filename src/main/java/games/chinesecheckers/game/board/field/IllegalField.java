package games.chinesecheckers.game.board.field;

public class IllegalField extends Field {
	public IllegalField(int diagonal, int row) {
        this.diagonal = diagonal;
        this.row = row;
        this.color = FieldColor.NONE;
        this.status = FieldStatus.ILLEGAL;       
    }
}
