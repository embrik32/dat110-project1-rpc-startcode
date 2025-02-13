package no.hvl.dat110.messaging;

//import java.security.SecureRandom;
//import java.util.Arrays;
import java.util.Random;

//import no.hvl.dat110.TODO;

public class MessageUtils {

	public static final int SEGMENTSIZE = 128;

	public static int MESSAGINGPORT = 8080;
	public static String MESSAGINGHOST = "localhost";

	public static byte[] encapsulate(Message message) {
		
		byte[] segment = null;
		byte[] data;
		
		// TODO - START
		data = message.getData();
		if (data.length > 127) {
			throw new IllegalArgumentException("wrong length.");
		}
		segment = new byte[SEGMENTSIZE];
		segment[0] = (byte) data.length;

		System.arraycopy(data, 0, segment, 1, data.length);
		
		// TODO - END
		return segment;
		
	}

	public static Message decapsulate(byte[] segment) {

		Message message = null;
		
		// TODO - START
		if (segment.length != SEGMENTSIZE) {
			throw new IllegalArgumentException("wrong length.");
		}
		int dataLength = segment[0] & 0xFF;
		byte[] data = new byte[dataLength];
		System.arraycopy(segment, 1, data, 0, dataLength);
		message = new Message(data);
		

		// TODO - END
		
		return message;
		
	}
	
}
