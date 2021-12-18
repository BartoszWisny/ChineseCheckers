package games.chinesecheckers.game.player;

import games.chinesecheckers.game.board.field.FieldColor;
import games.chinesecheckers.game.board.pawn.Pawn;

public abstract class AbstractPlayer implements Player {
	protected FieldColor homeColor;
    protected FieldColor opponentColor;
    protected Pawn[] pawns = new Pawn[10];

    public FieldColor getColor() {
        return homeColor;
    }
    public FieldColor getOpponentColor() {
        return opponentColor;
    }

    public void setPawns(Pawn[] pawns) {
        this.pawns = pawns;
    }
    
    public String toString() {
    	return "FieldColor: " + homeColor.toString() + " opponentColor: " + opponentColor.toString();
    }
}
