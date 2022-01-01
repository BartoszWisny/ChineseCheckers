package chinesecheckers;

import org.junit.Assert;
import org.junit.Test;

import games.chinesecheckers.game.Game;
import games.chinesecheckers.game.board.Board;
import games.chinesecheckers.game.board.BoardSize;
import games.chinesecheckers.game.board.field.Field;
import games.chinesecheckers.game.board.field.FieldColor;
import games.chinesecheckers.game.board.field.FieldStatus;
import games.chinesecheckers.game.board.field.HomeField;
import games.chinesecheckers.game.board.pawn.Pawn;
import games.chinesecheckers.game.gamesettings.GameSettings;
import games.chinesecheckers.game.player.NewPlayer;
import games.chinesecheckers.game.player.Player;
import games.chinesecheckers.ruleset.GameAnalyzer;
import games.chinesecheckers.ruleset.MoveDetails;

public class RulesetAndSettingsTests {
	@Test
	public void moveDetailsTest() {
		BoardSize boardSize = new BoardSize();
		boardSize.boardSize = 4;
		Board board = new Board();
		FieldColor testColor;
		Player testPlayer;
		String moveLine;
		
		testColor = FieldColor.WHITE;
		testPlayer = new NewPlayer(testColor);
		moveLine = "13 6 12 7";
		MoveDetails details = new MoveDetails(testPlayer, moveLine);
		
		Assert.assertEquals(details.getOldDiagonal(), 13);
		Assert.assertEquals(details.getOldRow(), 6);
		Assert.assertNotEquals(details.getNewDiagonal(), 13);
		Assert.assertNotEquals(details.getNewRow(), 6);
		Assert.assertEquals(details.getNewDiagonal(), 12);
		Assert.assertEquals(details.getNewRow(), 7);
		Assert.assertEquals(details.moveToString(), moveLine);
		Assert.assertEquals(details.getPlayer(), testPlayer);
	}
	
	@Test
	public void gameSettingsTest() {
		GameSettings settings1 = new GameSettings("2o");
		Assert.assertEquals(2, settings1.getNumberOfPlayers());
		int[] playersNumbers1 = {0, 3};
		for (int i = 0; i < 2; i++) {
			Assert.assertEquals(playersNumbers1[i], settings1.getPlayersNumbers()[i]);
		}
		Assert.assertEquals("2o", settings1.toString());
		
		GameSettings settings2 = new GameSettings("3o");
		Assert.assertEquals(2, settings1.getNumberOfPlayers());
		int[] playersNumbers2 = {0, 2, 4};
		for (int i = 0; i < 3; i++) {
			Assert.assertEquals(playersNumbers2[i], settings2.getPlayersNumbers()[i]);
		}
		Assert.assertEquals("3o", settings2.toString());
		
		GameSettings settings3 = new GameSettings("4o");
		Assert.assertEquals(2, settings1.getNumberOfPlayers());
		int[] playersNumbers3 = {1, 2, 4, 5};
		for (int i = 0; i < 4; i++) {
			Assert.assertEquals(playersNumbers3[i], settings3.getPlayersNumbers()[i]);
		}
		Assert.assertEquals("4o", settings3.toString());
		
		GameSettings settings4 = new GameSettings("6o");
		Assert.assertEquals(2, settings1.getNumberOfPlayers());
		int[] playersNumbers4 = {0, 1, 2, 3, 4, 5};
		for (int i = 0; i < 6; i++) {
			Assert.assertEquals(playersNumbers4[i], settings4.getPlayersNumbers()[i]);
		}
		Assert.assertEquals("6o", settings4.toString());
	}
	
	@Test
	public void gameAnalyzerTest() throws Exception {
		GameSettings settings = new GameSettings("4o");
		Game game = new Game(settings);
		BoardSize boardSize = new BoardSize();
		boardSize.boardSize = 4;
		Board board = new Board();
		game.setBoard(board);
		GameAnalyzer validator = new GameAnalyzer(game);
		Player player1 = new NewPlayer(FieldColor.GREEN);
		MoveDetails details1 = new MoveDetails(player1, "5 6 6 6");
		Field testField1 = new HomeField(5, 6, FieldColor.GREEN);
		Pawn[] pawns1 = {new Pawn(game.getBoard().getFieldByCoordinates(5, 6), FieldColor.GREEN)};
		game.setPawns(pawns1);
		
		Assert.assertEquals(true, validator.isValid(details1, false, null));
		Assert.assertEquals(false, validator.isValid(details1, true, null));
		
		Field oldField = game.getBoard().getFieldByCoordinates(8, 6);
		Field newField1 = game.getBoard().getFieldByCoordinates(9, 5);
		Field newField2 = game.getBoard().getFieldByCoordinates(9, 6);
		Field newField3 = game.getBoard().getFieldByCoordinates(8, 7);
		Field newField4 = game.getBoard().getFieldByCoordinates(7, 7);
		Field newField5 = game.getBoard().getFieldByCoordinates(7, 6);
		Field newField6 = game.getBoard().getFieldByCoordinates(8, 5);
		
		Assert.assertEquals(true, validator.moveIsStandard(oldField, newField1));
		Assert.assertEquals(true, validator.moveIsStandard(oldField, newField2));
		Assert.assertEquals(true, validator.moveIsStandard(oldField, newField3));
		Assert.assertEquals(true, validator.moveIsStandard(oldField, newField4));
		Assert.assertEquals(true, validator.moveIsStandard(oldField, newField5));
		Assert.assertEquals(true, validator.moveIsStandard(oldField, newField6));
		Assert.assertEquals(false, validator.moveIsJump(oldField, newField1));
		Assert.assertFalse(validator.hasFinished(player1));
		
		game.getBoard().getFieldByCoordinates(9, 5).setStatus(FieldStatus.OCCUPIED);
		game.getBoard().getFieldByCoordinates(9, 6).setStatus(FieldStatus.OCCUPIED);
		game.getBoard().getFieldByCoordinates(8, 7).setStatus(FieldStatus.OCCUPIED);
		game.getBoard().getFieldByCoordinates(7, 7).setStatus(FieldStatus.OCCUPIED);
		game.getBoard().getFieldByCoordinates(7, 6).setStatus(FieldStatus.OCCUPIED);
		game.getBoard().getFieldByCoordinates(8, 5).setStatus(FieldStatus.OCCUPIED);
		
		Field newField7 = game.getBoard().getFieldByCoordinates(10, 4);
		Field newField8 = game.getBoard().getFieldByCoordinates(10, 6);
		Field newField9 = game.getBoard().getFieldByCoordinates(8, 8);
		Field newField10 = game.getBoard().getFieldByCoordinates(6, 8);
		Field newField11 = game.getBoard().getFieldByCoordinates(6, 6);
		Field newField12 = game.getBoard().getFieldByCoordinates(8, 4);
		
		Assert.assertEquals(true, validator.moveIsJump(oldField, newField7));
		Assert.assertEquals(true, validator.moveIsJump(oldField, newField8));
		Assert.assertEquals(true, validator.moveIsJump(oldField, newField9));
		Assert.assertEquals(true, validator.moveIsJump(oldField, newField10));
		Assert.assertEquals(true, validator.moveIsJump(oldField, newField11));
		Assert.assertEquals(true, validator.moveIsJump(oldField, newField12));
		Assert.assertEquals(false, validator.moveIsStandard(oldField, newField7));
		
		Pawn testPawn1 = new Pawn(oldField, FieldColor.BLUE);
		
		game.getBoard().getFieldByCoordinates(9, 5).setStatus(FieldStatus.OCCUPIED);
		game.getBoard().getFieldByCoordinates(9, 6).setStatus(FieldStatus.FREE);
		game.getBoard().getFieldByCoordinates(8, 7).setStatus(FieldStatus.FREE);
		game.getBoard().getFieldByCoordinates(7, 7).setStatus(FieldStatus.FREE);
		game.getBoard().getFieldByCoordinates(7, 6).setStatus(FieldStatus.FREE);
		game.getBoard().getFieldByCoordinates(8, 5).setStatus(FieldStatus.FREE);
		Assert.assertEquals(true, validator.hasPossibleJumps(testPawn1));
		
		game.getBoard().getFieldByCoordinates(9, 5).setStatus(FieldStatus.FREE);
		game.getBoard().getFieldByCoordinates(9, 6).setStatus(FieldStatus.OCCUPIED);
		game.getBoard().getFieldByCoordinates(8, 7).setStatus(FieldStatus.FREE);
		game.getBoard().getFieldByCoordinates(7, 7).setStatus(FieldStatus.FREE);
		game.getBoard().getFieldByCoordinates(7, 6).setStatus(FieldStatus.FREE);
		game.getBoard().getFieldByCoordinates(8, 5).setStatus(FieldStatus.FREE);
		Assert.assertEquals(true, validator.hasPossibleJumps(testPawn1));
		
		game.getBoard().getFieldByCoordinates(9, 5).setStatus(FieldStatus.FREE);
		game.getBoard().getFieldByCoordinates(9, 6).setStatus(FieldStatus.FREE);
		game.getBoard().getFieldByCoordinates(8, 7).setStatus(FieldStatus.OCCUPIED);
		game.getBoard().getFieldByCoordinates(7, 7).setStatus(FieldStatus.FREE);
		game.getBoard().getFieldByCoordinates(7, 6).setStatus(FieldStatus.FREE);
		game.getBoard().getFieldByCoordinates(8, 5).setStatus(FieldStatus.FREE);
		Assert.assertEquals(true, validator.hasPossibleJumps(testPawn1));
		
		game.getBoard().getFieldByCoordinates(9, 5).setStatus(FieldStatus.FREE);
		game.getBoard().getFieldByCoordinates(9, 6).setStatus(FieldStatus.FREE);
		game.getBoard().getFieldByCoordinates(8, 7).setStatus(FieldStatus.FREE);
		game.getBoard().getFieldByCoordinates(7, 7).setStatus(FieldStatus.OCCUPIED);
		game.getBoard().getFieldByCoordinates(7, 6).setStatus(FieldStatus.FREE);
		game.getBoard().getFieldByCoordinates(8, 5).setStatus(FieldStatus.FREE);
		Assert.assertEquals(true, validator.hasPossibleJumps(testPawn1));
		
		game.getBoard().getFieldByCoordinates(9, 5).setStatus(FieldStatus.FREE);
		game.getBoard().getFieldByCoordinates(9, 6).setStatus(FieldStatus.FREE);
		game.getBoard().getFieldByCoordinates(8, 7).setStatus(FieldStatus.FREE);
		game.getBoard().getFieldByCoordinates(7, 7).setStatus(FieldStatus.FREE);
		game.getBoard().getFieldByCoordinates(7, 6).setStatus(FieldStatus.OCCUPIED);
		game.getBoard().getFieldByCoordinates(8, 5).setStatus(FieldStatus.FREE);
		Assert.assertEquals(true, validator.hasPossibleJumps(testPawn1));
		
		game.getBoard().getFieldByCoordinates(9, 5).setStatus(FieldStatus.FREE);
		game.getBoard().getFieldByCoordinates(9, 6).setStatus(FieldStatus.FREE);
		game.getBoard().getFieldByCoordinates(8, 7).setStatus(FieldStatus.FREE);
		game.getBoard().getFieldByCoordinates(7, 7).setStatus(FieldStatus.FREE);
		game.getBoard().getFieldByCoordinates(7, 6).setStatus(FieldStatus.FREE);
		game.getBoard().getFieldByCoordinates(8, 5).setStatus(FieldStatus.OCCUPIED);
		Assert.assertEquals(true, validator.hasPossibleJumps(testPawn1));
		
		Field pawnField1 = game.getBoard().getFieldByCoordinates(3, 9);
		Field pawnField2 = game.getBoard().getFieldByCoordinates(2, 10);
		Field pawnField3 = game.getBoard().getFieldByCoordinates(3, 10);
		Field pawnField4 = game.getBoard().getFieldByCoordinates(1, 11);
		Field pawnField5 = game.getBoard().getFieldByCoordinates(2, 11);
		Field pawnField6 = game.getBoard().getFieldByCoordinates(3, 11);
		Field pawnField7 = game.getBoard().getFieldByCoordinates(0, 12);
		Field pawnField8 = game.getBoard().getFieldByCoordinates(1, 12);
		Field pawnField9 = game.getBoard().getFieldByCoordinates(2, 12);
		Field pawnField10 = game.getBoard().getFieldByCoordinates(3, 12);
		
		Pawn pawn1 = new Pawn(pawnField1, FieldColor.WHITE);
		Pawn pawn2 = new Pawn(pawnField2, FieldColor.WHITE);
		Pawn pawn3 = new Pawn(pawnField3, FieldColor.WHITE);
		Pawn pawn4 = new Pawn(pawnField4, FieldColor.WHITE);
		Pawn pawn5 = new Pawn(pawnField5, FieldColor.WHITE);
		Pawn pawn6 = new Pawn(pawnField6, FieldColor.WHITE);
		Pawn pawn7 = new Pawn(pawnField7, FieldColor.WHITE);
		Pawn pawn8 = new Pawn(pawnField8, FieldColor.WHITE);
		Pawn pawn9 = new Pawn(pawnField9, FieldColor.WHITE);
		Pawn pawn10 = new Pawn(pawnField10, FieldColor.WHITE);
		
		Pawn[] pawns3 = {pawn1, pawn2, pawn3, pawn4, pawn5, pawn6, pawn7, pawn8, pawn9, pawn10};
		game.setPawns(pawns3);
		
		Player player2 = new NewPlayer(FieldColor.WHITE);
		Assert.assertEquals(true, validator.hasFinished(player2));
		
		Pawn[] pawns2 = {new Pawn(game.getBoard().getFieldByCoordinates(9, 12), FieldColor.GREEN)};
		game.setPawns(pawns2);
		MoveDetails details2 = new MoveDetails(player1, "9 12 9 11");
		Assert.assertEquals(false, validator.isValid(details2, false, null));
		MoveDetails details3 = new MoveDetails(player1, "9 12 10 12");
		Assert.assertEquals(true, validator.isValid(details3, false, null));
		MoveDetails details4 = new MoveDetails(player1, "9 12 9 12");
		Pawn testPawn2 = new Pawn(game.getBoard().getFieldByCoordinates(9, 12), FieldColor.GREEN);
		Assert.assertEquals(false, validator.isValid(details4, false, testPawn2));
		Player player3 = new NewPlayer(FieldColor.BLACK);
		MoveDetails details5 = new MoveDetails(player3, "9 12 10 12");
		Assert.assertEquals(false, validator.isValid(details5, false, null));
	}
}
