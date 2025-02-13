package no.hvl.dat110.system.controller;

import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.Message;
import no.hvl.dat110.rpc.RPCClient;
import no.hvl.dat110.rpc.RPCClientStopStub;

public class Controller  {
	
	private static int N = 5;
	
	public static void main (String[] args) {
		
		DisplayStub display;
		SensorStub sensor;
		
		RPCClient displayclient,sensorclient;
		
		System.out.println("Controller starting ...");

		// Opprett RPC-klienter for systemet
		displayclient = new RPCClient(Common.DISPLAYHOST, Common.DISPLAYPORT);
		sensorclient = new RPCClient(Common.SENSORHOST, Common.SENSORPORT);

		// Opprett stopp-stubs for RPC-mellomvare
		RPCClientStopStub stopdisplay = new RPCClientStopStub(displayclient);
		RPCClientStopStub stopsensor = new RPCClientStopStub(sensorclient);
				
		try {
			
	
			// Opprett lokale stub-objekter og koble til RPC-tjenester
			display = new DisplayStub(displayclient);
			sensor = new SensorStub(sensorclient);
	
			displayclient.connect();
			sensorclient.connect();
	
			// Les verdi fra sensor og skriv til display
			for (int i = 0; i < N; i++) {
			int sensorValue = sensor.read(); // Anta at sensoren har en read()-metode
			String str = Integer.toString(sensorValue);
			display.write(str + "  grader");     // Anta at displayet har en write()-metode
			}
		} catch (Exception e) {
			System.err.println("An error occurred: " + e.getMessage());
		} finally {
			// Stopp tjenester og frakoble klienter
			stopdisplay.stop();
			stopsensor.stop();
			displayclient.disconnect();
			sensorclient.disconnect();
			System.out.println("Controller stopping ...");
		}
	}
}
