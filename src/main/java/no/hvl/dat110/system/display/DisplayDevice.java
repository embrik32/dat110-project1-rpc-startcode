package no.hvl.dat110.system.display;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.RPCServer;
import no.hvl.dat110.system.controller.Common;


public class DisplayDevice {
	
	public static void main(String[] args) {
		
		System.out.println("Display server starting ...");
		
		System.out.println("Display server starting ...");

		// Start RPC-serveren på Display-porten
		RPCServer displayServer = new RPCServer(Common.DISPLAYPORT);

		// Registrer Display-implementasjonen på serveren
		DisplayImpl display = new DisplayImpl((byte)Common.WRITE_RPCID, displayServer);

		// Kjør serveren slik at den kan motta RPC-kall
		displayServer.run();

		// Stopp serveren når den er ferdig
		displayServer.stop();
		
		System.out.println("Display server stopping ...");
		
	}
}
