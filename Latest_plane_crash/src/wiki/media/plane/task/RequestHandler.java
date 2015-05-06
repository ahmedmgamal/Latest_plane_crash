package wiki.media.plane.task;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class RequestHandler implements Runnable {
	SocketChannel socketChannel = null;

	public RequestHandler(SocketChannel socketChannel) {
		this.socketChannel = socketChannel;
	}

	@Override
	public void run() {
		System.out.println("RequestHandler.run()");
		try {
			String reqString = readRequestAsString();
			WikiHttpRequest request = new WikiHttpRequest(reqString);
			if (request.getMethod() == "GET") {
				Runnable responseWorker;
				responseWorker = new ResponseHandler(this.socketChannel);
				responseWorker .run();
				//ClientHandler.getExecutor().execute(responseWorker);
			} else {
				// TODO: o define the action for update , this will be based on
				// PUT with payload in message
				// user (or applciation on client ), should uplaod the checksum of the old file 
				// when user upload fiel for edit , we will check if the old check sum like the currnt one 
				// if yes , will replace , if know will get the diff between the new and currnt file , 
				//		if the diff is only new lines added ,we add them in the right place 
				//	else
				//		reject and retuen to user
				//  more imporved logic could be added for smarter mege 
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String readRequestAsString() throws IOException {
		ByteBuffer readBuffer = ByteBuffer.allocate(8192);

		// Clear out our read buffer so it's ready for new data
		readBuffer.clear();

		// Attempt to read off the channel
		int numRead = 0;
		try {
			numRead = socketChannel.read(readBuffer);
		} catch (IOException e) {
			// The remote forcibly closed the connection, cancel
			// the selection key and close the channel.

			this.socketChannel.close();
		}

		if (numRead == -1) {
			socketChannel.close();
		}
		String requestString = new String(readBuffer.array());
		return requestString;
	}

}
