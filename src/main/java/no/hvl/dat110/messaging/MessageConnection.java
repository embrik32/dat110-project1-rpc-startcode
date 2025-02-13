package no.hvl.dat110.messaging;


import java.io.DataInputStream;
import java.io.DataOutputStream;
// import java.io.FileInputStream;
// import java.io.FileOutputStream;
import java.io.IOException;
// import java.io.InputStream;
// import java.io.OutputStream;
import java.net.Socket;
//import java.util.Random;
//import java.util.Arrays;

//import no.hvl.dat110.TODO;


public class MessageConnection {

	private DataOutputStream outStream; // for writing bytes to the underlying TCP connection
	private DataInputStream inStream; // for reading bytes from the underlying TCP connection
	private Socket socket; // socket for the underlying TCP connection
	
	public MessageConnection(Socket socket) {

		try {

			this.socket = socket;

			outStream = new DataOutputStream(socket.getOutputStream());

			inStream = new DataInputStream (socket.getInputStream());

		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void send(Message message) {

		byte[] data;
		data = message.getData();
		try {
			outStream.write(MessageUtils.encapsulate(message));
			outStream.flush();
		} catch (Exception e) {
			System.out.println("Error occurred: " + e);
		}
		// TODO - START
		// encapsulate the data contained in the Message and write to the output stream
		

	}

	public Message receive() {

		Message message = null;
		byte[] data = new byte[128];
		try {
			inStream.read(data);
			message = MessageUtils.decapsulate(data);

		} catch (IOException e) {
			System.out.println("Connection: " + e.getMessage());
			e.printStackTrace();
		}

		
		// TODO - END
		
		return message;
		
	}

	// close the connection by closing streams and the underlying socket	
	public void close() {

		try {
			
			outStream.close();
			inStream.close();

			socket.close();
			
		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}