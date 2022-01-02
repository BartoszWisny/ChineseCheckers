package games.chinesecheckers.ruleset;

import games.chinesecheckers.game.Game;
import games.chinesecheckers.game.board.field.Field;
import games.chinesecheckers.game.board.field.FieldColor;
import games.chinesecheckers.game.board.pawn.Pawn;
import games.chinesecheckers.game.player.Player;

/**
 * Klasa analizuje ruchy graczy, czyli sprawdza, czy wykonywane ruchy s¹ zgodne z ustalonymi zasadami gry.
 */

public class GameAnalyzer {
    private Game game;

    /**
     * Konstruktor ustala, dla której z gier bêdzie wykonywana analiza ruchów graczy.
     */
    
    public GameAnalyzer(Game game) {
        this.game = game;
    }
    
    /**
     * Metoda sprawdza na podstawie danych o ruchu, danych o pionku i danych, czy ruch by³ skokiem, czy ruch wykonany przez gracza jest zgodny z ustalonymi zasadami gry.
     */

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
    
    /**
     * Metoda sprawdza, czy kolor pionka jest zgodny z kolorem gracza, który wykonuje ruch.
     */

    private boolean pawnColorMatches(Pawn pawn, Player player) {
        return pawn.getPawnColor() == player.getColor();
    }
    
    /**
     * Metoda sprawdza, czy ruch na nowe pole wykonany przez pionek jest ruchem dozwolonym.
     */

    private boolean newIsLegal(Field oldField, Field newField) throws Exception {
        return moveIsStandard(oldField, newField) || moveIsJump(oldField, newField);
    }
    
    /**
     * Metoda sprawdza, czy ruch wykonany przez pionek jest ruchem o jedno pole w wybranym kierunku po planszy.
     */

    public boolean moveIsStandard(Field oldField, Field newField) {
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
    
    /**
     * Metoda sprawdza, czy ruch wykonany przez pionek jest ruchem o dwa pola w wybranym kierunku po planszy oraz czy pole oddalone o jeden jest polem zajêtym przez inny pionek (czyli sprawdzane jest, czy ruch jest dozwolonym skokiem).
     */

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
    
    /**
     * Metoda sprawdza, czy wybrane pole planszy jest polem zajêtym przez pionek któregoœ z graczy.
     */

    private boolean fieldOccupied(int diagonal, int row) {
        Field newField = field(diagonal, row);
        return newField.isLegalField() && !newField.isFreeField();
    }
    
    /**
     * Metoda sprawdza, czy wybrany pionek ma mo¿liwoœæ wykonania skoku w jakimkolwiek kierunku.
     */

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
    
    /**
     * Metoda sprawdza, czy wybrane pole planszy jest wolne (niezajête przez pionek któregoœ z graczy).
     */

    private boolean fieldFree(int diagonal, int row) {
        Field newField = field(diagonal, row);
        return newField.isLegalField() && newField.isFreeField();
    }
    
    /**
     * Metoda zwraca dane pole planszy na podstawie przekazanych danych o po³o¿eniu pola na planszy (przek¹tna, wiersz).
     */

    private Field field(int diagonal, int row) {
        return game.getBoard().getFieldByCoordinates(diagonal, row);
    }
    
    /**
     * Metoda sprawdza, czy dany gracz zakoñczy³ grê, czyli czy wszystkie pionki dla danego gracza znajduj¹ siê na polach startowych przeciwnika.
     */
    
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
