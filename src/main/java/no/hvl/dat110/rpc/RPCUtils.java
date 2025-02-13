package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.util.Arrays;
//import no.hvl.dat110.TODO;

public class RPCUtils {
	
	public static byte[] encapsulate(byte rpcid, byte[] payload) {
		// Create a new array with space for rpcid + payload
		byte[] encoded = new byte[1 + payload.length];
		
		// Set the rpcid as the first byte
		encoded[0] = rpcid;
		
		// Copy the payload into the encoded array, starting from index 1
		System.arraycopy(payload, 0, encoded, 1, payload.length);
		
		return encoded;
	}
	
	public static byte[] decapsulate(byte[] rpcmsg) {
		// The first byte of the rpcmsg is the rpcid, which we don't need to keep.
		// The rest is the payload, which starts from index 1.
		byte[] payload = new byte[rpcmsg.length - 1];
		
		// Copy the payload into the new array
		System.arraycopy(rpcmsg, 1, payload, 0, payload.length);
		
		return payload;
	}
	

	// convert String to byte array
	public static byte[] marshallString(String str) {
		
		byte[] encoded = new byte[str.getBytes().length];
		
		encoded = str.getBytes();
		
		return encoded;
	}

	// convert byte array to a String
	public static String unmarshallString(byte[] data) {
		
		String decoded = new String(Arrays.copyOfRange(data, 0, data.length));
		
		return decoded;
		
	}
	
	public static byte[] marshallVoid() {
		
		byte[] encoded = new byte[0];

		return encoded;

		
	}
	
	public static void unmarshallVoid(byte[] data) {
		if (data.length !=0) {
			throw new IllegalArgumentException("Data for unmarshallVoid is not empty.");
		}
		return;
		
	}

	// convert boolean to a byte array representation
	public static byte[] marshallBoolean(boolean b) {
		
		byte[] encoded = new byte[1];
				
		if (b) {
			encoded[0] = 1;
		} else
		{
			encoded[0] = 0;
		}
		
		return encoded;
	}

	// convert byte array to a boolean representation
	public static boolean unmarshallBoolean(byte[] data) {
		
		return (data[0] > 0);
		
	}

	// integer to byte array representation
	public static byte[] marshallInteger(int x) {
		
		byte[] encoded = new byte[4];
		
		
		encoded = ByteBuffer.allocate(4).putInt(x).array();

		return encoded;
	}
	
	// byte array representation to integer
	public static int unmarshallInteger(byte[] data) {
		
		int decoded = ByteBuffer.wrap(Arrays.copyOfRange(data, 0, data.length)).getInt();

		return decoded;
		
	}
}
