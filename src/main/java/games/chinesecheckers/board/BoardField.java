package games.chinesecheckers.board;

import games.chinesecheckers.game.board.field.Field;
import games.chinesecheckers.game.board.field.FieldColor;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Klasa implementuje metody dla elementu planszy oraz ustawia wsp�rz�dne pola i jego kolor.
 */

public class BoardField extends Circle implements BoardElement {
	private Field field;
	
	/**
	 * Konstuktor odpowiada za ustawienie odpowiednich danych - promie�, wsp�rz�dne, kolor - dla pola planszy oraz dodaje opcj� wychwytywania klikni�� myszy.
	 */
	
	public BoardField(Field field, BoardCurrent board) {
		this.field = field;	
		this.setRadius(BoardData.fieldSize);
		this.setCoordinates();
		this.setColor();
		this.addEventFilter(MouseEvent.MOUSE_CLICKED, board);
	}
	
	/**
	 * Metoda ustawia odpowiednie wsp�rz�dne dla �rodka pola planszy.
	 */
	
	private void setCoordinates() {
		this.setCenterX(this.calculateX());
		this.setCenterY(this.calculateY());
	}
	
	/**
	 * Metoda oblicza wsp�rz�dn� X �rodka pola planszy na podstawie danych o po�o�eniu pola na planszy i danych o rozmiarach dla planszy.
	 */
	
	private double calculateX() {
		double radius = getRadius();
		double gapSize = BoardData.gapSize;
		double diagonal = this.field.getDiagonal();
		double row = this.field.getRow();
		return (2 * radius + gapSize) * diagonal + (2 * radius + gapSize) * 0.5 * row;
	}
	
	/**
	 * Metoda oblicza wsp�rz�dn� Y �rodka pola planszy na podstawie danych o po�o�eniu pola na planszy i danych o rozmiarach dla planszy.
	 */
	
	private double calculateY() {
		double radius = getRadius();
		double fieldSize = BoardData.fieldSize;
		double gapSize = BoardData.gapSize;
		double row = this.field.getRow();
		return (2 * radius + gapSize) * 0.5 * Math.sqrt(3) * row + fieldSize;
	}
	
	/**
	 * Metoda ustawia kolor pola planszy w zale�no�ci od odpowiedniego typu koloru dla pola.
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
	 * Metoda sprawdza, czy element planszy to pionek - tutaj jest to zawsze fa�sz.
	 */

	public boolean isPawn() {
		return false;
	}
	
	/**
	 * Metoda zwraca pole, dla kt�rego generujemy pole planszy.
	 */

	public Field getField() {
		return this.field;
	}
	
	/**
	 * Metoda zwraca kolor pola, dla kt�rego generujemy pole planszy.
	 */

	public FieldColor getColor() {
		return field.getColor();
	}
	
	/**
	 * Metoda sprawdza, czy pole, dla kt�rego generujemy pole planszy, jest polem wolnym (niezaj�tym przez pionek).
	 */
	
	public boolean isFreeField() {
		return field.isFreeField();
	}
}
