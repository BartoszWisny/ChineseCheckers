package chinesecheckers;

import org.junit.Assert;
import org.junit.Test;

import games.chinesecheckers.game.board.Board;
import games.chinesecheckers.game.board.BoardSize;
import games.chinesecheckers.game.board.field.Field;
import games.chinesecheckers.game.board.field.FieldColor;
import games.chinesecheckers.game.board.field.FieldStatus;
import games.chinesecheckers.game.board.field.HomeField;
import games.chinesecheckers.game.board.field.IllegalField;
import games.chinesecheckers.game.board.field.NeutralField;

public class BoardAndFieldTests {
	@Test
    public void hexagonFieldsTest() {
		BoardSize boardSize = new BoardSize();
		boardSize.boardSize = 4;
		Board board = new Board();
		Field possibleHexagonField1 = board.getFieldByCoordinates(4, 8);
        Field possibleHexagonField2 = board.getFieldByCoordinates(4, 12);
        Field possibleHexagonField3 = board.getFieldByCoordinates(12, 4);
        Assert.assertTrue(possibleHexagonField1.isLegalField() && !possibleHexagonField1.isHomeField());
        Assert.assertTrue(possibleHexagonField2.isLegalField() && !possibleHexagonField1.isHomeField());
        Assert.assertTrue(possibleHexagonField3.isLegalField() && !possibleHexagonField3.isHomeField());
    }

    @Test
	public void homeFieldsTest() {
    	BoardSize boardSize = new BoardSize();
		boardSize.boardSize = 4;
		Board board = new Board();
    	Field possibleBlueField = board.getFieldByCoordinates(11, 1);
        Field possibleWhiteField = board.getFieldByCoordinates(14, 5);
        Field possibleRedField = board.getFieldByCoordinates(10, 12);
        Field possibleBlackField = board.getFieldByCoordinates(5, 13);
        Field possibleYellowField = board.getFieldByCoordinates(0, 12);
        Field possibleGreenField = board.getFieldByCoordinates(5, 5);

        Assert.assertTrue(possibleBlueField.getColor() == FieldColor.BLUE && possibleBlueField.isHomeField());
        Assert.assertTrue(possibleWhiteField.getColor() == FieldColor.WHITE && possibleWhiteField.isHomeField());
        Assert.assertTrue(possibleRedField.getColor() == FieldColor.RED && possibleRedField.isHomeField());
        Assert.assertTrue(possibleBlackField.getColor() == FieldColor.BLACK && possibleBlackField.isHomeField());
        Assert.assertTrue(possibleYellowField.getColor() == FieldColor.YELLOW && possibleYellowField.isHomeField());
        Assert.assertTrue(possibleGreenField.getColor() == FieldColor.GREEN && possibleGreenField.isHomeField());
    }
    
    @Test
    public void getFieldByCoordinatesTest() {
    	BoardSize boardSize = new BoardSize();
		boardSize.boardSize = 4;
		Board board = new Board();
    	Field possibleField = board.getFieldByCoordinates(1, 1);
    	IllegalField illegalField = new IllegalField(0,0);
    	Assert.assertEquals(illegalField.getClass(), possibleField.getClass());
    }
    
    @Test
    public void getFieldsTest() {
    	BoardSize boardSize = new BoardSize();
		boardSize.boardSize = 4;
		Board board = new Board();
    	FieldColor color;
    	Field[] possibleFields;
    	
    	color = FieldColor.YELLOW;
    	possibleFields = board.getFields(color);
    	for (Field field: possibleFields) {
    		Assert.assertEquals(field.getClass(), HomeField.class);
    		Assert.assertEquals(field.getColor(), color);
    	}
    	
    	color = FieldColor.BLACK;
    	possibleFields = board.getFields(color);
    	for (Field field: possibleFields) {
    		Assert.assertEquals(field.getClass(), HomeField.class);
    		Assert.assertEquals(field.getColor(), color);
    	}
    }
    
    @Test
    public void fieldColorTest() {
    	BoardSize boardSize = new BoardSize();
		boardSize.boardSize = 4;
		Board board = new Board();
    	int colorNumber;
    	Field testField;
    	
    	colorNumber = 0;
    	testField = new HomeField(12, 0, FieldColor.setColor(colorNumber));
    	Assert.assertEquals(testField.getColor(), FieldColor.BLUE);
    	Assert.assertEquals(FieldColor.getOpponent(testField.getColor()), FieldColor.BLACK);
    	Assert.assertEquals(testField.getColor().toString(), "Blue");
    	
    	colorNumber = 1;
    	testField = new HomeField(13, 4, FieldColor.setColor(colorNumber));
    	Assert.assertEquals(testField.getColor(), FieldColor.WHITE);
    	Assert.assertEquals(FieldColor.getOpponent(testField.getColor()), FieldColor.YELLOW);
    	Assert.assertEquals(testField.getColor().toString(), "White");
    	
    	colorNumber = 2;
    	testField = new HomeField(11, 10, FieldColor.setColor(colorNumber));
    	Assert.assertEquals(testField.getColor(), FieldColor.RED);
    	Assert.assertEquals(FieldColor.getOpponent(testField.getColor()), FieldColor.GREEN);
    	Assert.assertEquals(testField.getColor().toString(), "Red");
    	
    	colorNumber = 3;
    	testField = new HomeField(4, 14, FieldColor.setColor(colorNumber));
    	Assert.assertEquals(testField.getColor(), FieldColor.BLACK);
    	Assert.assertEquals(FieldColor.getOpponent(testField.getColor()), FieldColor.BLUE);
    	Assert.assertEquals(testField.getColor().toString(), "Black");
    	
    	colorNumber = 4;
    	testField = new HomeField(1, 12, FieldColor.setColor(colorNumber));
    	Assert.assertEquals(testField.getColor(), FieldColor.YELLOW);
    	Assert.assertEquals(FieldColor.getOpponent(testField.getColor()), FieldColor.WHITE);
    	Assert.assertEquals(testField.getColor().toString(), "Yellow");
    	
    	colorNumber = 5;
    	testField = new HomeField(6, 4, FieldColor.setColor(colorNumber));
    	Assert.assertEquals(testField.getColor(), FieldColor.GREEN);
    	Assert.assertEquals(FieldColor.getOpponent(testField.getColor()), FieldColor.RED);
    	Assert.assertEquals(testField.getColor().toString(), "Green");
    	
    	colorNumber = 11;
    	testField = new HomeField(8, 8, FieldColor.setColor(colorNumber));
    	Assert.assertEquals(testField.getColor(), FieldColor.NONE);
    	Assert.assertEquals(FieldColor.getOpponent(testField.getColor()), FieldColor.NONE);
    	Assert.assertEquals(testField.getColor().toString(), "");
    }
    
    @Test
    public void fieldTest() {
    	BoardSize boardSize = new BoardSize();
		boardSize.boardSize = 4;
		Board board = new Board();
    	Field testField;
    	testField = new NeutralField(8, 8);
    	String fieldString = testField.currentFieldToString();
    	Assert.assertEquals(fieldString, "8 8");
    	Assert.assertTrue(testField.isFreeField());
    	testField.setStatus(FieldStatus.OCCUPIED);
    	Assert.assertFalse(testField.isFreeField());     	
    }
}
