package games.chinesecheckers.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InfoStage extends Stage {
	public InfoStage(String string) {
		AnchorPane pane = new AnchorPane();
		Label label = new Label(string);
		AnchorPane.setLeftAnchor(label, 20.0);
        AnchorPane.setTopAnchor(label, 20.0);
        label.setFont(new Font("Times New Roman", 18));
        Button button = new Button("Close");
        button.setMinHeight(30.0);
        button.setMinWidth(80.0);
        AnchorPane.setLeftAnchor(button, 80.0);
        AnchorPane.setTopAnchor(button, 60.0);
        button.setFont(new Font("Times New Roman", 18));
        pane.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setStyle("-fx-border-color: black");
        pane.getChildren().addAll(label, button);
        
        Scene scene = new Scene(pane, 240.0, 110.0);
        this.setScene(scene);
        setResizable(false);
        initStyle(StageStyle.TRANSPARENT);
        
        button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Platform.exit();
                System.exit(0);
			}
		});
	}
}
