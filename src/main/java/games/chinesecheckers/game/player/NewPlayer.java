package games.chinesecheckers.game.player;

import games.chinesecheckers.game.board.field.FieldColor;

public final class NewPlayer extends AbstractPlayer {

    public NewPlayer(FieldColor playerColor) {
        homeColor = playerColor;
        opponentColor = FieldColor.getOpponent(playerColor);
    }
}
