package games.chinesecheckers.gui;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class InfoStage extends Stage {
	public InfoStage(String string) {
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
        Label label = new Label(string);
        this.setTitle("Chinese checkers");
        label.setFont(new Font("Times New Roman", 18));
        pane.getChildren().addAll(label);
        
        Scene scene = new Scene(pane, 240, 50);
        this.setScene(scene);
        setResizable(false);
        
        InfoStage.this.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent t) {
            	Platform.exit();
                System.exit(0);
            }
        }); 
	}
}
