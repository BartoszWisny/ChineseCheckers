package games.chinesecheckers.client;

import java.io.IOException;

import games.chinesecheckers.gui.CreateButtonEvent;
import games.chinesecheckers.gui.JoinButtonEvent;
import games.chinesecheckers.gui.ServerInfoStage;
import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.stage.WindowEvent;

public class RunClient extends Application {
	private Client client;
	
    public static void main(String[] args) {
    	launch(args);
    }
    
    @Override
	public void start(Stage startStage) throws IOException {
		ConnectionData data = new ConnectionData();
		ServerInfoStage infoStage = new ServerInfoStage(data);
		infoStage.showAndWait();

    	this.client = new Client(data.address, data.port);
		startStage.setTitle("Chinese checkers");
		
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
        
        buttonCreate.setOnAction(new CreateButtonEvent(startStage, client));
		buttonJoin.setOnAction(new JoinButtonEvent(startStage, client));
				
		Scene scene = new Scene(pane, 140, 120);
		startStage.setScene(scene);
		startStage.setResizable(false);
		startStage.show();
		
		startStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	        public void handle(WindowEvent t) {
	        	Platform.exit();
	            System.exit(0);
	        }
	    });
	}   
}
