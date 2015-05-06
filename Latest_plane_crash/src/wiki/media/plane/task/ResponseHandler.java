package wiki.media.plane.task;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class ResponseHandler implements Runnable {
	private SocketChannel socketChannel = null;

	public ResponseHandler(SocketChannel socketChannel) throws IOException {
		this.socketChannel = socketChannel;

	}

	@Override
	public void run() {
		try {
			Helper.fibonacci(40);
			System.out.println("about to send ");
			String header = "HTTP/1.1 200 OK\n\n";
			ByteBuffer writeBuffer = ByteBuffer.allocate(header.length());
			writeBuffer.put(header.getBytes());

			this.socketChannel.write(writeBuffer);

			RandomAccessFile fromFile = new RandomAccessFile(
					"latest_plan_crash.html", "rw");
			FileChannel fromChannel = fromFile.getChannel();
			fromChannel.transferTo(0, fromChannel.size(), socketChannel);
			fromChannel.close();
			socketChannel.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
