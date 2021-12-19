package games.chinesecheckers.ruleset;

import games.chinesecheckers.game.player.Player;

public class MoveDetails {

    private Player player;   
    private int oldDiagonal;
    private int oldRow;
    private int newDiagonal;
    private int newRow;

    public MoveDetails(Player player, String moveLine) {
        this.player = player;
        String[] line = moveLine.split(" ");
        oldDiagonal = Integer.parseInt(line[0]);
        oldRow = Integer.parseInt(line[1]);
        newDiagonal = Integer.parseInt(line[2]);
        newRow = Integer.parseInt(line[3]);
    }

    public int getOldDiagonal() {
        return oldDiagonal;
    }
    
    public int getOldRow() {
        return oldRow;
    }

    public int getNewDiagonal() {
        return newDiagonal;
    }

    public int getNewRow() {
        return newRow;
    }

    public Player getPlayer() {
        return player;
    }
    
    public String moveToString() {
    	return Integer.toString(oldDiagonal) + " " + Integer.toString(oldRow) + " " +
    	Integer.toString(newDiagonal) + " " + Integer.toString(newRow);
    }
}
