package games.chinesecheckers.gui;

import games.chinesecheckers.client.ConnectionData;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ServerInfoStage extends Stage {
    ConnectionData data;
    
    public ServerInfoStage(final ConnectionData data) {
        this.data = data;
        
        AnchorPane pane = new AnchorPane();       
        Label ipLabel = new Label("IP Address: ");
        AnchorPane.setLeftAnchor(ipLabel, 20.0);
        AnchorPane.setTopAnchor(ipLabel, 20.0);
        ipLabel.setFont(new Font("Times New Roman", 18));
        final TextField ipField = new TextField();
        ipField.setMaxHeight(30.0);
        ipField.setMaxWidth(200.0);
        AnchorPane.setLeftAnchor(ipField, 120.0);
        AnchorPane.setTopAnchor(ipField, 15.0);
        ipField.setFont(new Font("Times New Roman", 18));
        Label portLabel = new Label("Port: ");
        AnchorPane.setLeftAnchor(portLabel, 70.0);
        AnchorPane.setTopAnchor(portLabel, 70.0);
        portLabel.setFont(new Font("Times New Roman", 18));
        final TextField portField = new TextField();
        AnchorPane.setLeftAnchor(portField, 120.0);
        AnchorPane.setTopAnchor(portField, 65.0);
        portField.setMaxHeight(30.0);
        portField.setMaxWidth(200.0);
        portField.setFont(new Font("Times New Roman", 18));
        Button button = new Button("Connect");
        AnchorPane.setLeftAnchor(button, 120.0);
        AnchorPane.setTopAnchor(button, 120.0);
        button.setMinHeight(30.0);
        button.setMinWidth(100.0);
        button.setFont(new Font("Times New Roman", 18));
        
        pane.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setStyle("-fx-border-color: black");
        pane.getChildren().addAll(ipLabel, ipField, portLabel, portField, button);
        
        Scene scene = new Scene(pane, 340.0, 180.0);

        button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
			    data.address = ipField.getText();
			    data.port = Integer.parseInt(portField.getText());
			    ServerInfoStage.this.close();
			}
		});
        
        setScene(scene);
        setResizable(false);
        initStyle(StageStyle.UNDECORATED);
    }
}
