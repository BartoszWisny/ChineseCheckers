package games.chinesecheckers.gui;

import games.chinesecheckers.client.Client;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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

public class CreateGameStage extends Stage {
	
	public CreateGameStage(final Client client) {
		this.setTitle("Chinese checkers");
		
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
        AnchorPane.setLeftAnchor(button, 95.0);
        AnchorPane.setTopAnchor(button, 60.0);
        button.setMinHeight(30.0);
        button.setMinWidth(40.0);
        button.setFont(new Font("Times New Roman", 18));
        
        pane.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        pane.getChildren().addAll(playersLabel, choiceBox, button);
        
        Scene scene = new Scene(pane, 260, 110);
		this.setScene(scene);
		setResizable(false);
		
		choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
		    public void changed(ObservableValue<? extends Number> arg, Number number1, Number number2) {
				button.setOnAction(new CreateGameStageEvent(CreateGameStage.this, choiceList[number2.intValue()], client));	
			}
		});
	}
}
