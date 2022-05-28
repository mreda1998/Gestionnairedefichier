
import java.io.DataInputStream;
import java.net.Socket;
import java.util.Scanner;
import java.io.*;

public class Client {
	
	/* Application Client */
	
	private static Socket socket;
	
	
	public static void main (String[] args) throws Exception {
		
		// Adresse et port serveur
		String serverAddress = AddressReader.read();
		
		int port = portReader.read();
		// Creation de connexion avec le serveur
		socket = new Socket(serverAddress,port);
		
		System.out.format("The server is running on %s:%d%n", serverAddress,port);
		
		Scanner reader = new Scanner(System.in);
		String[] message = reader.nextLine().split(" ");
		
		switch (message[0]) {
		case "ls":
		case "exit":
		case "download":
		case "mkdir":
		case "upload":
		case "cd":
	
		}
		
		socket.close();
		
	}
	

}
