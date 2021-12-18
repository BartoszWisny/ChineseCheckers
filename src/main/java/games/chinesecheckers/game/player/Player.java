package games.chinesecheckers.game.player;

import games.chinesecheckers.game.board.field.FieldColor;
import games.chinesecheckers.game.board.pawn.Pawn;

public interface Player {
	void setPawns(Pawn[] pawns);
	FieldColor getColor();
	FieldColor getOpponentColor();
}
