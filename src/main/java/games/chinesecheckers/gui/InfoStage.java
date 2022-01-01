package games.chinesecheckers.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InfoStage extends Stage {
	public InfoStage(String string) {
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		Label label = new Label(string);
		label.setFont(new Font("Times New Roman", 18));
        pane.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setStyle("-fx-border-color: black");
        pane.getChildren().addAll(label);
        
        Scene scene = new Scene(pane, 240.0, 60.0);
        this.setScene(scene);
        setResizable(false);
        initStyle(StageStyle.UNDECORATED);
	}
}
