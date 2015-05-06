package wiki.media.plane.task;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientHandler  {
	
	//static RequestHandler handler = null;
    static private ExecutorService executor = null;
    public static ExecutorService getExecutor() {
		if (executor == null) {
			executor = Executors.newFixedThreadPool(5);
		 
		} 
		return executor;
	}

	public static void setExecutor(ExecutorService executor) {
		ClientHandler.executor = executor;
	}

	static ClientHandler handler = new ClientHandler();
	public static ClientHandler getInstance() {
		getExecutor();
		return handler;
	}

	public void handleConnection(SocketChannel socketChannel) throws IOException {
		//todo : here to make simple templte method like pattern wherr we deine here handle request and handle response which are working speartly 
		 
		//WikiHttpRequest request = this.readRequestType(socketChannel);//to move this to excuter 

		 Runnable requestWorker = new RequestHandler(socketChannel);
		 executor.execute(requestWorker);

		
		

	}

	private WikiHttpRequest readRequestType(SocketChannel socketChannel) throws IOException {
		ByteBuffer readBuffer = ByteBuffer.allocate(1024);

		// Clear out our read buffer so it's ready for new data
		readBuffer.clear();
		// Attempt to read off the channel
		int numRead;
		try {
			numRead = socketChannel.read(readBuffer);
			System.out.println("readRequestType2"+numRead);

		} catch (IOException e) {
			e.printStackTrace();
			socketChannel.close();
 		}


		String requestString = new String(readBuffer.array());	
		readBuffer.clear();

		return new WikiHttpRequest(requestString);
	}

}
