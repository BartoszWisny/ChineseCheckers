package chinesecheckers;

import org.junit.Assert;
import org.junit.Test;

import games.chinesecheckers.game.Game;
import games.chinesecheckers.game.board.field.Field;
import games.chinesecheckers.game.board.field.FieldColor;
import games.chinesecheckers.game.board.field.HomeField;
import games.chinesecheckers.game.board.field.NeutralField;
import games.chinesecheckers.game.board.pawn.Pawn;
import games.chinesecheckers.game.gamesettings.GameSettings;
import games.chinesecheckers.game.player.NewPlayer;
import games.chinesecheckers.game.player.Player;

public class GameTests {
	
	private Game createTestGame(int numberOfPlayers) {
		String settingsLine = Integer.toString(numberOfPlayers) + "o";
		Game game = new Game(new GameSettings(settingsLine));
		game.setUp();
		return game;
	}
	
	@Test
	public void numberOfPlayersTest() {
		int numberOfPlayers = 3;
		Game game = this.createTestGame(numberOfPlayers);
		Assert.assertTrue(game.getNumberOfPlayers() == numberOfPlayers);
	}
	
	@Test
	public void fieldCoordinatesTest() {
		int numberOfPlayers = 6;
		Field testField;
		Game game = this.createTestGame(numberOfPlayers);
		
		testField = game.getBoard().getFieldByCoordinates(7, 5);
		Assert.assertTrue(testField.getDiagonal() == 7);
		Assert.assertTrue(testField.getRow() == 5);
		Assert.assertTrue(testField.getColor().equals(FieldColor.NONE));
		Assert.assertTrue(testField.isLegalField());
		
		testField = game.getBoard().getFieldByCoordinates(11, 11);
		Assert.assertTrue(testField.getDiagonal() == 11 && testField.getRow() == 11);
		Assert.assertTrue(testField.getColor().equals(FieldColor.RED));
		Assert.assertTrue(testField.isHomeField());
		
		testField = game.getBoard().getFieldByCoordinates(8, 4);
		Assert.assertFalse(testField.getDiagonal() == 4 && testField.getRow() == 8);		
		
		testField = game.getBoard().getFieldByCoordinates(1, 10);
		Assert.assertTrue(testField.getDiagonal() == 0);
		Assert.assertTrue(testField.getRow() == 0);
		Assert.assertTrue(testField.getColor().equals(FieldColor.NONE));
		Assert.assertFalse(testField.isLegalField());		
	}
	
	@Test
	public void pawnFieldTest() {
		int numberOfPlayers;
		Field testField;
		Game game;
		
		numberOfPlayers = 4;
		game = this.createTestGame(numberOfPlayers);		
		testField = game.getBoard().getFieldByCoordinates(4, 4);
		try {
			Pawn pawn = game.getPawnByField(testField);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		numberOfPlayers = 2;
		game = this.createTestGame(numberOfPlayers);		
		testField = game.getBoard().getFieldByCoordinates(11, 1);
		try {
			Pawn pawn = game.getPawnByField(testField);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test (expected = Exception.class)
	public void exceptionGetPawnByFieldTest() throws Exception {
		int numberOfPlayers = 2;
		Game game = this.createTestGame(numberOfPlayers);
		Field field = game.getBoard().getFieldByCoordinates(14, 14);
		Pawn pawn = game.getPawnByField(field);
	}
	
	@Test
	public void pawnTest() {
		Pawn pawn;
		Field testField;
		FieldColor testColor;
		
		testField = new NeutralField(7, 7);
		testColor = FieldColor.BLACK;
		pawn = new Pawn(testField, testColor);
		testField = new NeutralField(8, 6);
		pawn.setField(testField);
		Assert.assertEquals(pawn.currentFieldToString(), "8 6");
	}
	
	@Test
	public void gameSettingsTest() {
		int numberOfPlayers = 4;
		Game game = this.createTestGame(numberOfPlayers);
		Assert.assertEquals(game.getSettings().toString(), "4o");
		
	}
	
	@Test
	public void playerTest() {
		FieldColor testColor;
		
		testColor = FieldColor.GREEN;
		Player testPlayer = new NewPlayer(testColor);
		Assert.assertEquals(testPlayer.getColor(), FieldColor.GREEN);
		Assert.assertEquals(testPlayer.getOpponentColor(), FieldColor.RED);
		Assert.assertEquals(testPlayer.toString(), "FieldColor: Green opponentColor: Red");
	}
	
	@Test
	public void gameTest() {
		String line = "3o";
		GameSettings settings = new GameSettings(line);
		Game testGame = new Game(settings);
		FieldColor[] playersColors = {FieldColor.BLUE, FieldColor.RED, FieldColor.YELLOW};
		Player[] players = new Player[settings.getNumberOfPlayers()];
		for (int i = 0; i < settings.getNumberOfPlayers(); i++) {
			players[i] = new NewPlayer(playersColors[i]);
		}
		testGame.setPlayers(players);
		for (int i = 0; i < settings.getNumberOfPlayers(); i++) {
			Assert.assertEquals(testGame.getPlayerByNumber(i), players[i]);
		}
		
		Field testField1 = new HomeField(11, 1, FieldColor.BLUE);
		Field testField2 = new HomeField(12, 1, FieldColor.BLUE);
		Pawn[] pawns = {new Pawn(testField1, testField1.getColor()), new Pawn(testField2, testField2.getColor())};
		testGame.setPawns(pawns);
		for (int i = 0; i < pawns.length; i++) {
			Assert.assertEquals(testGame.getPawns()[i], pawns[i]);
		}
		
		Player testPlayer1 = new NewPlayer(FieldColor.RED);
		Player testPlayer2 = new NewPlayer(FieldColor.WHITE);
		testGame.addWinner(testPlayer1);
		Assert.assertEquals(testGame.getNumberOfWinners(), 1);
		Assert.assertFalse(testGame.isWinner(testPlayer2));
	}
}
