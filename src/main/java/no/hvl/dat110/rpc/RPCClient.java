package no.hvl.dat110.rpc;

import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.*;

public class RPCClient {

	// underlying messaging client used for RPC communication
	private MessagingClient msgclient;

	// underlying messaging connection used for RPC communication
	private MessageConnection connection;
	
	public RPCClient(String server, int port) {
	
		msgclient = new MessagingClient(server,port);
	}
	
	public void connect() {
		
		
				connection = msgclient.connect();
			
		
	}
	
	public void disconnect() {
		
		// TODO - START
		// disconnect by closing the underlying messaging connection
		
		
			if (connection != null) {
				connection.close();
				connection = null;
			}
	
	}

	/*
	 Make a remote call om the method on the RPC server by sending an RPC request message and receive an RPC reply message

	 rpcid is the identifier on the server side of the method to be called
	 param is the marshalled parameter of the method to be called
	 */

	public byte[] call(byte rpcid, byte[] param) {
		
		byte[] returnval = null;
		byte[] requestPayload = RPCUtils.encapsulate(rpcid, param);
		Message requestMessage = new Message(requestPayload);
		connection.send(requestMessage);
		Message responMessage = connection.receive();
		byte[] responsePayLoad = RPCUtils.decapsulate(responMessage.getData());
		returnval = responsePayLoad;
		return returnval;
		
	}

}
