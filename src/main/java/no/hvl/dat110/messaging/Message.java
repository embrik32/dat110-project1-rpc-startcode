package no.hvl.dat110.messaging;

import no.hvl.dat110.TODO;

public class Message {

	// the up to 127 bytes of data (payload) that a message can hold
	private byte[] data;

	// construction a Message with the data provided
	public Message(byte[] data1) {
		
		// TODO - START

		if (data1 != null & data1.length < 128) {
			
			this.data = data1.clone();

			
		} else {
			throw new IllegalArgumentException("wrong input.");
		}
		 
			
		// TODO - END
	}

	public byte[] getData() {
		return this.data; 
	}

}
