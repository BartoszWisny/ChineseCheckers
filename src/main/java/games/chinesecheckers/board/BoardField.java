package games.chinesecheckers.board;

import games.chinesecheckers.game.board.field.Field;
import games.chinesecheckers.game.board.field.FieldColor;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Klasa implementuje metody dla elementu planszy oraz ustawia wspó³rzêdne pola i jego kolor.
 */

public class BoardField extends Circle implements BoardElement {
	private Field field;
	
	/**
	 * Konstuktor odpowiada za ustawienie odpowiednich danych - promieñ, wspó³rzêdne, kolor - dla pola planszy oraz dodaje opcjê wychwytywania klikniêæ myszy.
	 */
	
	public BoardField(Field field, BoardCurrent board) {
		this.field = field;	
		this.setRadius(BoardData.fieldSize);
		this.setCoordinates();
		this.setColor();
		this.addEventFilter(MouseEvent.MOUSE_CLICKED, board);
	}
	
	/**
	 * Metoda ustawia odpowiednie wspó³rzêdne dla œrodka pola planszy.
	 */
	
	private void setCoordinates() {
		this.setCenterX(this.calculateX());
		this.setCenterY(this.calculateY());
	}
	
	/**
	 * Metoda oblicza wspó³rzêdn¹ X œrodka pola planszy na podstawie danych o po³o¿eniu pola na planszy i danych o rozmiarach dla planszy.
	 */
	
	private double calculateX() {
		double radius = getRadius();
		double gapSize = BoardData.gapSize;
		double diagonal = this.field.getDiagonal();
		double row = this.field.getRow();
		return (2 * radius + gapSize) * diagonal + (2 * radius + gapSize) * 0.5 * row;
	}
	
	/**
	 * Metoda oblicza wspó³rzêdn¹ Y œrodka pola planszy na podstawie danych o po³o¿eniu pola na planszy i danych o rozmiarach dla planszy.
	 */
	
	private double calculateY() {
		double radius = getRadius();
		double fieldSize = BoardData.fieldSize;
		double gapSize = BoardData.gapSize;
		double row = this.field.getRow();
		return (2 * radius + gapSize) * 0.5 * Math.sqrt(3) * row + fieldSize;
	}
	
	/**
	 * Metoda ustawia kolor pola planszy w zale¿noœci od odpowiedniego typu koloru dla pola.
	 */
	
	private void setColor() {
		FieldColor color = this.field.getColor();
		
		if(color == FieldColor.BLUE) {
			this.setFill(Color.LIGHTSKYBLUE);
		} else if(color == FieldColor.WHITE) {
			this.setFill(Color.WHITE);
		} else if(color == FieldColor.RED) {
			this.setFill(Color.MISTYROSE);
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
	
	/**
	 * Metoda sprawdza, czy element planszy to pole - tutaj jest to zawsze prawda.
	 */

	public boolean isField() {
		return true;
	}
	
	/**
	 * Metoda sprawdza, czy element planszy to pionek - tutaj jest to zawsze fa³sz.
	 */

	public boolean isPawn() {
		return false;
	}
	
	/**
	 * Metoda zwraca pole, dla którego generujemy pole planszy.
	 */

	public Field getField() {
		return this.field;
	}
	
	/**
	 * Metoda zwraca kolor pola, dla którego generujemy pole planszy.
	 */

	public FieldColor getColor() {
		return field.getColor();
	}
	
	/**
	 * Metoda sprawdza, czy pole, dla którego generujemy pole planszy, jest polem wolnym (niezajêtym przez pionek).
	 */
	
	public boolean isFreeField() {
		return field.isFreeField();
	}
}
