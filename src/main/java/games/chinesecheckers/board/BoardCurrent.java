package games.chinesecheckers.board;

import java.util.ArrayList;
import java.util.List;

import games.chinesecheckers.client.Client;
import games.chinesecheckers.game.Game;
import games.chinesecheckers.game.board.field.Field;
import games.chinesecheckers.game.board.pawn.Pawn;
import games.chinesecheckers.game.player.Player;
import games.chinesecheckers.gui.SkipTurnEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BoardCurrent extends Stage implements EventHandler<MouseEvent> {
	private Game game;
	private Player player;
	private BoardPawn activePawn;
	private Client client;
	private boolean active;
	private List<BoardPawn> pawns;
	private Label turnLabel;
	private Button skipButton;
	private Label colorLabel;
	private SkipTurnEvent skipEvent;
	private Label emptyLabel1;
	private Label emptyLabel2;
	private Label emptyLabel3;
	private Button forfeitButton;
	
	public BoardCurrent(Game game, int numberOfPlayer, Client client) {
		this.game = game;
		this.setResizable(false);
		this.activePawn = null;
		this.player = game.getPlayerByNumber(numberOfPlayer);
		this.colorLabel = new Label("You are " + player.getColor().toString() + ".");
		this.client = client;
		this.active = false;
		this.pawns = new ArrayList<BoardPawn>();
		this.turnLabel = new Label("Wait for you turn...");
		this.skipButton = new Button("Skip turn");
		this.skipEvent = new SkipTurnEvent(client, this);
		this.skipButton.setOnAction(skipEvent);
		this.emptyLabel1 = new Label("		");
		this.emptyLabel2 = new Label("		");
		this.emptyLabel3 = new Label("		");
		this.forfeitButton = new Button("Forfeit");
		
		drawBoard();
	}
	
	private void drawBoard() {
		Group group = new Group();
		
		for(Field field: this.game.getBoard().getFields()) {
			this.drawField(field, group);
		}
		
		for(Pawn pawn: this.game.getPawns()) {
			this.drawPawn(pawn, group);
		}
		
		GridPane bottomPane = new GridPane();
		bottomPane.setAlignment(Pos.CENTER);
		this.colorLabel.setFont(new Font("Times New Roman", 18));
		this.turnLabel.setFont(new Font("Times New Roman", 18));
        this.skipButton.setMinHeight(30.0);
        this.skipButton.setMinWidth(80.0);
        this.skipButton.setFont(new Font("Times New Roman", 18));
        this.emptyLabel1.setFont(new Font("Times New Roman", 18));
        this.emptyLabel2.setFont(new Font("Times New Roman", 18));
        this.emptyLabel3.setFont(new Font("Times New Roman", 18));
        this.forfeitButton.setMinHeight(30.0);
        this.forfeitButton.setMinWidth(80.0);
        this.forfeitButton.setFont(new Font("Times New Roman", 18));
        bottomPane.add(colorLabel, 0, 0);
        bottomPane.add(emptyLabel1, 1, 0);
        bottomPane.add(turnLabel, 2, 0);
        bottomPane.add(emptyLabel2, 3, 0);
        bottomPane.add(skipButton, 4, 0);
        bottomPane.add(emptyLabel3, 5, 0);
        bottomPane.add(forfeitButton, 6, 0);
		
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setVgap(20.0);
		pane.add(group, 0, 0);
		pane.add(bottomPane, 0, 1);
		pane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		Scene scene = new Scene(pane, pane.prefWidth(0) + 100.0, pane.prefHeight(0) + 30.0);
		this.setScene(scene);
		scene.setFill(Color.TRANSPARENT);
		BoardCurrent.this.initStyle(StageStyle.TRANSPARENT);
	}
	
	private void drawField(Field field, Group group) {
		BoardField boardField = new BoardField(field, this);
		group.getChildren().add(boardField);
	}
	
	private void drawPawn(Pawn pawn, Group group) {
		BoardPawn boardPawn = new BoardPawn(pawn, this);
		this.pawns.add(boardPawn);
		group.getChildren().add(boardPawn);
	}
	
	private boolean isMyElement(BoardElement element) {
		return element.getColor().equals(player.getColor());
	}
	
	private BoardPawn getBoardPawn(Pawn pawn) throws Exception {
		for(BoardPawn boardPawn: this.pawns) {
			if(boardPawn.getPawn() == pawn)
				return boardPawn;
		}
		throw new Exception("Pawn doesn't exist");
	}
	
	public void setLabel(String string) {
		this.turnLabel.setText(string);
	}
	
	public void activate() {
		this.active = true;
		this.skipEvent.activate();
	}
	
	public void setUnactive() {
		this.active = false;
	}
	
	public void makeMove(String moveLine) throws Exception {
		String[] line = moveLine.split(" ");
		int oldDiagonal = Integer.parseInt(line[1]);
		int oldRow = Integer.parseInt(line[2]);
		Pawn pawn = game.getPawnByField(game.getBoard().getFieldByCoordinates(oldDiagonal, oldRow));
		int newDiagonal = Integer.parseInt(line[3]);
		int newRow = Integer.parseInt(line[4]);
		
		Field newField = game.getBoard().getFieldByCoordinates(newDiagonal, newRow);
		this.getBoardPawn(pawn).move(newField, client);
		
		this.activePawn = null;
		this.active = false;
		this.skipEvent.setUnactive();
	}

	public void handle(MouseEvent event) {
		Object source = event.getSource();
		BoardElement element = (BoardElement) source;
		
		if(this.active == true && element.isPawn() && isMyElement(element)) {
			this.activePawn = (BoardPawn) element;
		}
		else if(element.isField() && this.active == true) {
			BoardField field = (BoardField) element;
			Field newField = field.getField();
			if(this.activePawn != null) {
				client.sendOption(activePawn.getPawn().getField().currentFieldToString() + " " + newField.currentFieldToString());
			}
		}
	}
}

