package games.chinesecheckers.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.stage.Stage;

public class Client {
	
	private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
	
	private Stage stage;
	
	public Client(String address, int port) throws IOException {
		socket = new Socket(address, port);
		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        
        ListenerThread listener = new ListenerThread(in, this);
        listener.start();
	}
	
	public void sendOption(String option) {
		out.println(option);
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void closePreviousStage() {
		if(this.stage != null)
			stage.close();
	}
}