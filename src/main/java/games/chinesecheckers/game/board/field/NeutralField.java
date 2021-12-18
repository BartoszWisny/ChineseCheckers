package games.chinesecheckers.game.board.field;

public class NeutralField extends Field {
	public NeutralField(int diagonal, int row) {
        this.diagonal = diagonal;
        this.row = row;
        this.color = FieldColor.NONE;
        this.status = FieldStatus.FREE;    
    }
}
