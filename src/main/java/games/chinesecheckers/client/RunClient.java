package games.chinesecheckers.client;

import java.io.IOException;

import games.chinesecheckers.gui.CreateButtonEvent;
import games.chinesecheckers.gui.InfoStage;
import games.chinesecheckers.gui.JoinButtonEvent;
import games.chinesecheckers.gui.ServerInfoStage;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 * Klasa uruchamia dzia³anie aplikacji dla gracza i generuje odpowiednie okna dla gracza podczas rozpoczêcia pracy aplikacji.
 */

public class RunClient extends Application {
	private Client client;
	
	/**
	 * Metoda uruchamia dzia³anie aplikacji dla gracza.
	 */
	
    public static void main(String[] args) {
    	launch(args);
    }
    
    /**
     * Metoda wyœwietla okno dla nowego klienta, który ³¹czy siê z serwerem gry, a po pod³¹czeniu klienta do serwera wyœwietla okno umo¿liwiaj¹ce wybór utworzenia gry lub do³¹czenia do gry i odpowiednio zarz¹dza wyborami gracza.
     */
    
    @Override
	public void start(Stage startStage) throws IOException {
		try {
			ConnectionData data = new ConnectionData();
			ServerInfoStage infoStage = new ServerInfoStage(data);
			infoStage.showAndWait();

	    	this.client = new Client(data.address, data.port);
			
			AnchorPane pane = new AnchorPane();       
	        Button buttonCreate = new Button("Create");
	        AnchorPane.setLeftAnchor(buttonCreate, 20.0);
	        AnchorPane.setTopAnchor(buttonCreate, 20.0);
	        buttonCreate.setMinHeight(30.0);
	        buttonCreate.setMinWidth(100.0);
	        buttonCreate.setFont(new Font("Times New Roman", 18));
	        Button buttonJoin = new Button("Join");
	        AnchorPane.setLeftAnchor(buttonJoin, 20.0);
	        AnchorPane.setTopAnchor(buttonJoin, 70.0);
	        buttonJoin.setMinHeight(30.0);
	        buttonJoin.setMinWidth(100.0);
	        buttonJoin.setFont(new Font("Times New Roman", 18));
	        
	        pane.getChildren().addAll(buttonCreate, buttonJoin);
	        pane.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
	        pane.setStyle("-fx-border-color: black");
	        
	        buttonCreate.setOnAction(new CreateButtonEvent(startStage, client));
			buttonJoin.setOnAction(new JoinButtonEvent(startStage, client));
					
			Scene scene = new Scene(pane, 140.0, 120.0);
			startStage.setScene(scene);
			startStage.setResizable(false);
			startStage.initStyle(StageStyle.UNDECORATED);
			startStage.show();
			
			Platform.setImplicitExit(false);
			startStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
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
		} catch (IOException e) {
			Platform.runLater(new Runnable() {
				public void run() {
					final InfoStage newStage = new InfoStage("Server is not running!");
					newStage.show();
					PauseTransition delay = new PauseTransition(Duration.seconds(10));
					delay.setOnFinished(new EventHandler<ActionEvent>() {
						public void handle(ActionEvent event) {
							newStage.close();
							System.exit(0);
						}
					});
					delay.play();
				}
			});
		}   	
	}
}
