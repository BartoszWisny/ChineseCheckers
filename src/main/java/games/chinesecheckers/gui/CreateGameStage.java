package games.chinesecheckers.gui;

import games.chinesecheckers.client.Client;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
 * Klasa odpowiada za wygenerowanie okna z mo�liwo�ci� wyboru liczby graczy dla danej rozgrywki.
 */

public class CreateGameStage extends Stage {
	
	/**
	 * Konstruktor generuje okno z mo�liwo�ci� wyboru liczby graczy dla danej rozgrywki i odpowiada za zarz�dzanie obs�ug� okna.
	 */
	
	public CreateGameStage(final Client client) {
		AnchorPane pane = new AnchorPane();       
        Label playersLabel = new Label("Number of players: ");
        AnchorPane.setLeftAnchor(playersLabel, 20.0);
        AnchorPane.setTopAnchor(playersLabel, 20.0);
        playersLabel.setFont(new Font("Times New Roman", 18));
        final String[] choiceList = {"2", "3", "4", "6"};
        ComboBox<String> choiceBox = new ComboBox<String>(FXCollections.observableArrayList(choiceList));
        AnchorPane.setLeftAnchor(choiceBox, 170.0);
        AnchorPane.setTopAnchor(choiceBox, 15.0);
        choiceBox.setMinHeight(30.0);
        choiceBox.setMinWidth(70.0);
        choiceBox.setStyle("-fx-font: 18px \"Serif\";");
        final Button button = new Button("OK");
        AnchorPane.setLeftAnchor(button, 110.0);
        AnchorPane.setTopAnchor(button, 65.0);
        button.setMinHeight(30.0);
        button.setMinWidth(40.0);
        button.setFont(new Font("Times New Roman", 18));
        
        pane.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setStyle("-fx-border-color: black");
        pane.getChildren().addAll(playersLabel, choiceBox, button);
        
        Scene scene = new Scene(pane, 260.0, 115.0);
		this.setScene(scene);
		setResizable(false);
		initStyle(StageStyle.UNDECORATED);		
		
		choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
		    public void changed(ObservableValue<? extends Number> arg, Number number1, Number number2) {
				button.setOnAction(new CreateGameStageEvent(CreateGameStage.this, choiceList[number2.intValue()], client));	
			}
		});
		
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
}
