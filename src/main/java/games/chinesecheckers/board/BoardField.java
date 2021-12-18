package games.chinesecheckers.board;

import games.chinesecheckers.game.board.field.Field;
import games.chinesecheckers.game.board.field.FieldColor;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BoardField extends Circle implements BoardElement {
	private Field field;
	
	public BoardField(Field field, BoardCurrent board) {
		this.field = field;	
		this.setRadius(BoardData.fieldSize);
		this.setCoordinates();
		this.setColor();
		this.addEventFilter(MouseEvent.MOUSE_CLICKED, board);
	}
	
	private void setCoordinates() {
		this.setCenterX(this.calculateX());
		this.setCenterY(this.calculateY());
	}
	
	private double calculateX() {
		double radius = getRadius();
		double gapSize = BoardData.gapSize;
		double diagonal = this.field.getDiagonal();
		double row = this.field.getRow();
		return (2 * radius + gapSize) * diagonal + (2 * radius + gapSize) * 0.5 * row;
	}
	
	private double calculateY() {
		double radius = getRadius();
		double fieldSize = BoardData.fieldSize;
		double gapSize = BoardData.gapSize;
		double row = this.field.getRow();
		return (2 * radius + gapSize) * 0.5 * Math.sqrt(3) * row + fieldSize;
	}
	
	private void setColor() {
		FieldColor color = this.field.getColor();
		
		if(color == FieldColor.BLUE) {
			this.setFill(Color.LIGHTSKYBLUE);
		} else if(color == FieldColor.WHITE) {
			this.setFill(Color.GHOSTWHITE);
		} else if(color == FieldColor.RED) {
			this.setFill(Color.LIGHTCORAL);
		} else if(color == FieldColor.BLACK) {
			this.setFill(Color.LIGHTGRAY);
		} else if(color == FieldColor.YELLOW) {
			this.setFill(Color.LEMONCHIFFON);
		} else if(color == FieldColor.GREEN) {
			this.setFill(Color.PALEGREEN);
		} else if(color == FieldColor.NONE) {
			this.setFill(Color.DIMGRAY);
		}		
	}

	public boolean isField() {
		return true;
	}

	public boolean isPawn() {
		return false;
	}

	public Field getField() {
		return this.field;
	}

	public FieldColor getColor() {
		return field.getColor();
	}
	
	public boolean isFreeField() {
		return field.isFreeField();
	}
}
