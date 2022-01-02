package games.chinesecheckers.board;

import java.util.ArrayList;
import java.util.List;

import games.chinesecheckers.client.Client;
import games.chinesecheckers.game.Game;
import games.chinesecheckers.game.board.field.Field;
import games.chinesecheckers.game.board.pawn.Pawn;
import games.chinesecheckers.game.player.Player;
import games.chinesecheckers.gui.InfoStage;
import games.chinesecheckers.gui.SkipTurnEvent;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 * Klasa odpowiada za wygenerowanie bie¿¹cego widoku planszy gry. Generuje pola planszy, pionki oraz reaguje na ruchy gracza. 
 */

public class BoardCurrent extends Stage implements EventHandler<MouseEvent> {
	private Game game;
	private Player player;
	private BoardPawn activePawn;
	private Client client;
	private boolean active;
	private List<BoardPawn> pawns;
	private Label turnLabel;
	public Button skipButton;
	private Label colorLabel;
	private SkipTurnEvent skipEvent;
	private Label emptyLabel1;
	private Label emptyLabel2;
	
	/**
	 * Konstruktor tworzy i ustawia parametry odpowiednich elementów generowanego okna dla planszy gry.
	 */
	
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
		
		drawBoard();
	}
	
	/**
	 * Metoda odpowiada za wygenerowanie widoku planszy (pola i pionki) oraz za odpowiednie wyœwietlanie okna gracza i zarz¹dzanie obs³ug¹ okna.
	 */
	
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
        this.skipButton.setDisable(true);
        this.emptyLabel1.setFont(new Font("Times New Roman", 18));
        this.emptyLabel2.setFont(new Font("Times New Roman", 18));
        bottomPane.add(colorLabel, 0, 0);
        bottomPane.add(emptyLabel1, 1, 0);
        bottomPane.add(turnLabel, 2, 0);
        bottomPane.add(emptyLabel2, 3, 0);
        bottomPane.add(skipButton, 4, 0);
        
		AnchorPane pane = new AnchorPane();
		AnchorPane.setLeftAnchor(group, 20.0);
		AnchorPane.setTopAnchor(group, 20.0);
		AnchorPane.setLeftAnchor(bottomPane, 20.0);
		AnchorPane.setTopAnchor(bottomPane, 700.0);
		pane.getChildren().addAll(group, bottomPane);
        
		pane.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setStyle("-fx-border-color: black");
		Scene scene = new Scene(pane, pane.prefWidth(0) + 20.0, pane.prefHeight(0) + 20.0);
		this.setScene(scene);
		setResizable(false);
		initStyle(StageStyle.UNDECORATED);
		
		Platform.setImplicitExit(false);
		this.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent event) {
		        event.consume();
		        final InfoStage newStage = new InfoStage("You can't close this game!");
			    newStage.show();
			    PauseTransition delay = new PauseTransition(Duration.seconds(10));
				delay.setOnFinished( new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						newStage.close();
					}
				});
				delay.play();
		    }
		});
	}
	
	/**
	 * Metoda generuje pole planszy i dodaje je do zbioru wszystkich elementów planszy.
	 */
	
	private void drawField(Field field, Group group) {
		BoardField boardField = new BoardField(field, this);
		group.getChildren().add(boardField);
	}
	
	/**
	 * Metoda generuje pionek dla gracza, dodaje pionek do zbioru pionków i zbioru elementów planszy.
	 */
	
	private void drawPawn(Pawn pawn, Group group) {
		BoardPawn boardPawn = new BoardPawn(pawn, this);
		this.pawns.add(boardPawn);
		group.getChildren().add(boardPawn);
	}
	
	/**
	 * Metoda sprawdza, czy wybrany element planszy nale¿y do gracza, dla którego ta plansza jest generowana.
	 */
	
	private boolean isMyElement(BoardElement element) {
		return element.getColor().equals(player.getColor());
	}
	
	/**
	 * Metoda pobiera odpowiedni pionek ze zbioru pionków, jeœli pionek nie istnieje, zwracany jest wyj¹tek i komunikat.
	 */
	
	private BoardPawn getBoardPawn(Pawn pawn) throws Exception {
		for(BoardPawn boardPawn: this.pawns) {
			if(boardPawn.getPawn() == pawn)
				return boardPawn;
		}
		throw new Exception("Pawn doesn't exist");
	}
	
	/**
	 * Metoda ustawia odpowiedni napis na oknie w zale¿noœci od tego, czy dany gracz ma obecnie ruch.
	 */
	
	public void setLabel(String string) {
		this.turnLabel.setText(string);
	}
	
	/**
	 * Metoda aktywuje okno, by gracz móg³ wykonaæ ruch.
	 */
	
	public void activate() {
		this.active = true;
		this.skipEvent.activate();
		skipButton.setDisable(false);
	}
	
	/**
	 * Metoda dezaktywuje okno, jeœli dany gracz nie ma obecnie swojej kolejki na wykonanie ruchu.
	 */
	
	public void setUnactive() {
		this.active = false;
		skipButton.setDisable(true);
	}
	
	/**
	 * Metoda pobiera dane o ruchu gracza, wyszukuje odpowiedni pionek i wywo³uje metody odpowiedzialne za wykonanie ruchu pionka. Po ruchu okno jest dezaktywowane.
	 */
	
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
		this.skipButton.setDisable(true);
	}
	
	/**
	 * Metoda przechwytuje klikniêcia myszy gracza. Jeœli jego okno jest aktywne, to ustawia bie¿¹cy pionek na ten wybrany przez gracza lub wysy³a komunikat, jeœli klikniête zosta³o pole planszy.
	 */

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
