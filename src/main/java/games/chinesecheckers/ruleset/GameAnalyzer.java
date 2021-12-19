package games.chinesecheckers.ruleset;

import games.chinesecheckers.game.Game;
import games.chinesecheckers.game.board.field.Field;
import games.chinesecheckers.game.board.field.FieldColor;
import games.chinesecheckers.game.board.pawn.Pawn;
import games.chinesecheckers.game.player.Player;

public class GameAnalyzer {
    private Game game;

    public GameAnalyzer(Game game) {
        this.game = game;
    }

    public boolean isValid(MoveDetails details, boolean hasJumped, Pawn lastMovedPawn) throws Exception {
        Field oldField = game.getBoard().getFieldByCoordinates(details.getOldDiagonal(), details.getOldRow());
        Field newField = game.getBoard().getFieldByCoordinates(details.getNewDiagonal(), details.getNewRow());

        Pawn movingPawn = game.getPawnByField(oldField);
        FieldColor movingPawnColor = movingPawn.getPawnColor();
        FieldColor opponentColor = FieldColor.getOpponent(movingPawnColor);
        FieldColor oldFieldColor = oldField.getColor();
        if(oldFieldColor.equals(opponentColor)) {
        	FieldColor newFieldColor = newField.getColor();
        	if(!newFieldColor.equals(opponentColor)) {
        		return false;
        	}
        }
        
        if(!oldOccupiedAndNewFree(oldField, newField))
            return false;
        
        Pawn activePawn = game.getPawnByField(oldField);
        
        if (lastMovedPawn != null && activePawn != lastMovedPawn) {
            System.out.println("I was reached");
            return false;
        }
        
        if (!pawnColorMatches(activePawn, details.getPlayer()))
            return false;
        
        if (!hasJumped)
            return newIsLegal(oldField, newField);
        else
            return moveIsJump(oldField, newField);
    }

    private boolean oldOccupiedAndNewFree(Field oldField, Field newField) {
        return !oldField.isFreeField() && newField.isFreeField();
    }

    private boolean pawnColorMatches(Pawn pawn, Player player) {
        return pawn.getPawnColor() == player.getColor();
    }

    private boolean newIsLegal(Field oldField, Field newField) throws Exception {
        return moveIsStandard(oldField, newField) || moveIsJump(oldField, newField);
    }

    private boolean moveIsStandard(Field oldField, Field newField) {
        int diagonalOldField = oldField.getDiagonal();
        int rowOldField = oldField.getRow();
        int diagonalNewField = newField.getDiagonal();
        int rowNewField = newField.getRow();    	
    	
    	return     (diagonalNewField == diagonalOldField && rowNewField == rowOldField - 1)
        		|| (diagonalNewField == diagonalOldField + 1 && rowNewField == rowOldField - 1)
        		|| (diagonalNewField == diagonalOldField + 1 && rowNewField == rowOldField) 
                || (diagonalNewField == diagonalOldField && rowNewField == rowOldField + 1)
                || (diagonalNewField == diagonalOldField - 1 && rowNewField == rowOldField + 1)
                || (diagonalNewField == diagonalOldField - 1 && rowNewField == rowOldField);
    }

    public boolean moveIsJump(Field oldField, Field newField)  {
    	int diagonalOldField = oldField.getDiagonal();
        int rowOldField = oldField.getRow();
        int diagonalNewField = newField.getDiagonal();
        int rowNewField = newField.getRow();    	
    	
    	return     (diagonalNewField == diagonalOldField && rowNewField == rowOldField - 2 && fieldOccupied(diagonalOldField, rowOldField - 1))
        		|| (diagonalNewField == diagonalOldField + 2 && rowNewField == rowOldField - 2 && fieldOccupied(diagonalOldField + 1, rowOldField - 1))
        		|| (diagonalNewField == diagonalOldField + 2 && rowNewField == rowOldField && fieldOccupied(diagonalOldField + 1, rowOldField))
                || (diagonalNewField == diagonalOldField && rowNewField == rowOldField + 2 && fieldOccupied(diagonalOldField, rowOldField + 1))
                || (diagonalNewField == diagonalOldField - 2 && rowNewField == rowOldField + 2 && fieldOccupied(diagonalOldField - 1, rowOldField + 1))
                || (diagonalNewField == diagonalOldField - 2 && rowNewField == rowOldField && fieldOccupied(diagonalOldField - 1, rowOldField));
    }

    private boolean fieldOccupied(int diagonal, int row) {
        Field newField = field(diagonal, row);
        return newField.isLegalField() && !newField.isFreeField();
    }

    public boolean hasPossibleJumps(Pawn targetPawn) {
        Field currentField = targetPawn.getField();
        int diagonal = currentField.getDiagonal();
        int row = currentField.getRow();

        return     (fieldOccupied(diagonal, row - 1) && fieldFree(diagonal, row - 2))
        		|| (fieldOccupied(diagonal + 1, row - 1) && fieldFree(diagonal + 2, row - 2))
        		|| (fieldOccupied(diagonal + 1, row) && fieldFree(diagonal + 2, row))
                || (fieldOccupied(diagonal, row + 1) && fieldFree(diagonal, row + 2))
                || (fieldOccupied(diagonal - 1, row + 1) && fieldFree(diagonal - 2, row + 2))
                || (fieldOccupied(diagonal - 1, row) && fieldFree(diagonal - 2, row));
    }

    private boolean fieldFree(int diagonal, int row) {
        Field newField = field(diagonal, row);
        return newField.isLegalField() && newField.isFreeField();
    }

    private Field field(int diagonal, int row) {
        return game.getBoard().getFieldByCoordinates(diagonal, row);
    }
    
    public boolean hasFinished(Player player) {
    	Pawn[] playerPawns = this.game.getPlayerPawns(player.getColor());
    	for(Pawn pawn: playerPawns) {
    		FieldColor fieldColor = pawn.getField().getColor();
    		FieldColor pawnColor = pawn.getPawnColor();
    		FieldColor opponentColor = FieldColor.getOpponent(pawnColor);
    		
    		if(fieldColor != opponentColor)
    			return false;
    	}
    	return true;
    }
}
