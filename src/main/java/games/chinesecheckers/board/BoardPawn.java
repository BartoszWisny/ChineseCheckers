package games.chinesecheckers.board;

import games.chinesecheckers.client.Client;
import games.chinesecheckers.game.board.field.Field;
import games.chinesecheckers.game.board.field.FieldColor;
import games.chinesecheckers.game.board.field.FieldStatus;
import games.chinesecheckers.game.board.pawn.Pawn;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Klasa implementuje metody dla elementu planszy oraz ustawia wsp�rz�dne pionka i jego kolor.
 */

public class BoardPawn extends Circle implements BoardElement {
	private Pawn pawn;
	
	/**
	 * Konstuktor odpowiada za ustawienie odpowiednich danych - promie�, wsp�rz�dne, kolor - dla pionka oraz dodaje opcj� wychwytywania klikni�� myszy.
	 */
	
	public BoardPawn(Pawn pawn, BoardCurrent board) {
		this.pawn = pawn;
		this.setRadius(BoardData.fieldSize);
		this.setCoordinates(this.pawn.getField());
		this.setColor();
		this.addEventFilter(MouseEvent.MOUSE_CLICKED, board);
	}
	
	/**
	 * Metoda w zale�no�ci od ruchu danego gracza ustawia poprzednie pole, na kt�rym by� pionek jako niezaj�te, a nowe pole ustawia jako zaj�te. Metoda ustawia te� nowe pole jako obecne pole pionka.
	 */

	public void move(Field newField, Client client) {
        this.setCoordinates(newField);
        pawn.getField().setStatus(FieldStatus.FREE);
		newField.setStatus(FieldStatus.OCCUPIED);
		this.pawn.setField(newField);
	}
	
	/**
	 * Metoda ustawia odpowiednie wsp�rz�dne dla �rodka pionka.
	 */
	
	private void setCoordinates(Field field) {
		this.setCenterX(this.calculateX(field));
		this.setCenterY(this.calculateY(field));
	}
	
	/**
	 * Metoda oblicza wsp�rz�dn� X �rodka pionka na podstawie danych o po�o�eniu pionka na planszy i danych o rozmiarach dla planszy.
	 */
	
	private double calculateX(Field field) {
		double radius = getRadius();
		double gapSize = BoardData.gapSize;
		double diagonal = field.getDiagonal();
		double row = field.getRow();
		return (2 * radius + gapSize) * diagonal + (2 * radius + gapSize) * 0.5 * row;
	}
	
	/**
	 * Metoda oblicza wsp�rz�dn� Y �rodka pionka na podstawie danych o po�o�eniu pionka na planszy i danych o rozmiarach dla planszy.
	 */
	
	private double calculateY(Field field) {
		double radius = getRadius();
		double fieldSize = BoardData.fieldSize;
		double gapSize = BoardData.gapSize;
		double row = field.getRow();
		return (2 * radius + gapSize) * Math.sqrt(3) * 0.5 * row + fieldSize;
	}
	
	/**
	 * Metoda ustawia kolor pionka w zale�no�ci od odpowiedniego typu koloru dla pionka.
	 */
	
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

	/**
	 * Metoda sprawdza, czy element planszy to pole - tutaj jest to zawsze fa�sz.
	 */
	
	public boolean isField() {
		return false;
	}
	
	/**
	 * Metoda sprawdza, czy element planszy to pionek - tutaj jest to zawsze prawda.
	 */

	public boolean isPawn() {
		return true;
	}
	
	/**
	 * Metoda zwraca kolor pionka, dla kt�rego generujemy pionek na planszy.
	 */

	public FieldColor getColor() {
		return pawn.getPawnColor();
	}
	
	/**
	 * Metoda zwraca numer przek�tnej dla pionka, dla kt�rego generujemy pionek na planszy.
	 */
	
	public int getDiagonal() {
		return this.pawn.getField().getDiagonal();
	}
	
	/**
	 * Metoda zwraca numer wiersza dla pionka, dla kt�rego generujemy pionek na planszy.
	 */
	
	public int getRow() {
		return this.pawn.getField().getRow();
	}
	
	/**
	 * Metoda zwraca pionek, dla kt�rego generujemy pionek na planszy.
	 */
	
	public Pawn getPawn() {
		return this.pawn;
	}
}
