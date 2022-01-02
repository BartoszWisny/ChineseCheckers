package chinesecheckers;

import java.io.IOException;

import javax.naming.CommunicationException;

import org.junit.Assert;
import org.junit.Test;

import games.chinesecheckers.server.Server;

public class ServerTests {
	Server server1;
	
	@Test
    public void serverRunTest1() throws InterruptedException, IOException {
        Thread thread = new Thread(new Runnable() {
	       	public void run() {
	       		try {
					server1 = new Server(8080);
					server1.listen();
				} catch (CommunicationException | IOException e) {
					e.printStackTrace();
				}	       		
	        }
	    });
        
        thread.start();
        Thread.sleep(5000);
        server1.close();
    }
	
	@Test
    public void serverRunTest2() throws InterruptedException, IOException {
		Server server2 = new Server(8080);
		Assert.assertFalse(server2.isClosed());
		server2.close();
		Assert.assertTrue(server2.isClosed());
    }
}

