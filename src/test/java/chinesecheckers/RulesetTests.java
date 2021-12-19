package chinesecheckers;

import org.junit.Assert;
import org.junit.Test;

import games.chinesecheckers.game.board.field.FieldColor;
import games.chinesecheckers.game.player.NewPlayer;
import games.chinesecheckers.game.player.Player;
import games.chinesecheckers.ruleset.MoveDetails;

public class RulesetTests {
	@Test
	public void moveDetailsTest() {
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
}
