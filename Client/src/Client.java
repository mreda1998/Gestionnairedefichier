
import java.io.DataInputStream;
import java.net.Socket;

public class Client {
	
	/* Application Client */
	
	private static Socket socket;
	
	public static void main (String[] args) throws Exception {
		
		// Adresse et port serveur
		String serverAddress = "127.0.0.1";
		int port = 5000;
		
		// Creation de connexion avec le serveur
		socket = new Socket(serverAddress,port);
		
		System.out.format("The server is running on %s:%d%n", serverAddress,port);
		
		//Creation cana entrant pour reception message envoye par le seveur
		
		DataInputStream in = new DataInputStream(socket.getInputStream());
		
		//Attente reception message envoyé par serveur 
		
		String helloMessageFromServer = in.readUTF();
		System.out.println(helloMessageFromServer);
		
		socket.close();
		
	}

}
