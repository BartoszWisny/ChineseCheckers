package games.chinesecheckers.board;

import games.chinesecheckers.game.board.field.Field;
import games.chinesecheckers.game.board.field.FieldColor;
import games.chinesecheckers.game.board.pawn.Pawn;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BoardPawn extends Circle implements BoardElement {
	private Pawn pawn;
	
	public BoardPawn(Pawn pawn, BoardCurrent board) {
		this.pawn = pawn;
		this.setRadius(BoardData.fieldSize);
		this.setCoordinates(this.pawn.getField());
		this.setColor();
		this.addEventFilter(MouseEvent.MOUSE_CLICKED, board);
	}

	private void setCoordinates(Field field) {
		this.setCenterX(this.calculateX(field));
		this.setCenterY(this.calculateY(field));
	}
	
	private double calculateX(Field field) {
		double radius = getRadius();
		double gapSize = BoardData.gapSize;
		double diagonal = field.getDiagonal();
		double row = field.getRow();
		return (2 * radius + gapSize) * diagonal + (2 * radius + gapSize) * 0.5 * row;
	}
	
	private double calculateY(Field field) {
		double radius = getRadius();
		double fieldSize = BoardData.fieldSize;
		double gapSize = BoardData.gapSize;
		double row = field.getRow();
		return (2 * radius + gapSize) * Math.sqrt(3) * 0.5 * row + fieldSize;
	}
	
	private void setColor() {
		FieldColor color = this.pawn.getPawnColor();
		
		if(color == FieldColor.BLUE) {
			this.setFill(Color.BLUE);
		} else if(color == FieldColor.WHITE) {
			this.setFill(Color.WHITE);
		} else if(color == FieldColor.RED) {
			this.setFill(Color.RED);
		} else if(color == FieldColor.BLACK) {
			this.setFill(Color.BLACK);
		} else if(color == FieldColor.YELLOW) {
			this.setFill(Color.YELLOW);
		} else if(color == FieldColor.GREEN) {
			this.setFill(Color.GREEN);
		} else if(color == FieldColor.NONE) {
			this.setFill(Color.SEASHELL);
		}
		
		this.setStroke(Color.BLACK);
		this.setStrokeWidth(2.0);		
	}

	public boolean isField() {
		return false;
	}

	public boolean isPawn() {
		return true;
	}

	public FieldColor getColor() {
		return pawn.getPawnColor();
	}
	
	public int getDiagonal() {
		return this.pawn.getField().getDiagonal();
	}
	public int getRow() {
		return this.pawn.getField().getRow();
	}
	
	public Pawn getPawn() {
		return this.pawn;
	}
}