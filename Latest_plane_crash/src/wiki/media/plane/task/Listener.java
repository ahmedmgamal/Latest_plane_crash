package wiki.media.plane.task;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Listener {
	public static void main (String[] args){
		new Listener().start(999);

	}

	private void start(int port) {
		try {
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			System.out.println("Starting ..");
			serverSocketChannel.socket().bind(new InetSocketAddress(port));
			serverSocketChannel.configureBlocking(false);

			while(true){
			    SocketChannel socketChannel =
			            serverSocketChannel.accept();
			    if(socketChannel != null){
			    	System.out.println("Got New Request ");
				    ClientHandler.getInstance().handleConnection(socketChannel);
			        }else{
			        	//TO DO: we can add here any other check / monitors that could be done when server is not processing request 
 			        }
			    
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
