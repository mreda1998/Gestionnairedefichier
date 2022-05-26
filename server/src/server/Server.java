package server;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	
	private static ServerSocket listener;
	
	/* 
	 * Application Serveur 
	 */

	public static void main(String[] args) throws Exception 
	{
		//Compteur incrementé à chaque connexion
		
		
		int clientNumber = 0;
		Scanner reader = new Scanner(System.in);
		int serverPort = -10;
		while (serverPort < 5000 || serverPort > 5050)
		{
			System.out.println("Please enter a number between 5000 and 5050");
			while (!reader.hasNextInt()) {
		        System.out.println("This is not a valid number for the port");
		        reader.next(); 
		    }
			serverPort = reader.nextInt();
		}
		
		Scanner readerAddress = new Scanner(System.in);
		
		AdressTester testValid = new AdressTester();
		String serverAddress = "test";
		
		while (!testValid.testingAdress(serverAddress))
		{
			System.out.println("Please enter a valid IP address");
			while (!reader.hasNextInt()) {
		        System.out.println("This is not a valid IP address");
		        reader.next(); 
		    }
		}
		
		
		
		//Creation de la connexion pour communiquer
		listener = new ServerSocket();
		listener.setReuseAddress(true);
		InetAddress serverIP = InetAddress.getByName(serverAddress);
		
		//Association de l'Adresse et du port à la connexion
		
		listener.bind(new InetSocketAddress(serverIP,serverPort));
		
		System.out.format("The server is running %s:%d%n",serverAddress,serverPort);
		
		try 
		{
			
			//A chaque connexion -> run() dans clienthandler
			while (true)
			{
				//attend un nouveau client pour add
				new ClientHandler(listener.accept(),clientNumber++).start();
			}
		}
		finally
		{
			listener.close();
		}
	}

	/*
	 * thread traite la demande de chaque client
	 * */
	private static class ClientHandler extends Thread 
	{
		
		private Socket socket;
		private int clientNumber;
		
		public ClientHandler(Socket socket, int clientNumber)
		{
			this.socket = socket;
			this.clientNumber = clientNumber;
			System.out.println("New connection with clients " + clientNumber + " at " + socket);
			
			
		}
		
		public void run()
		{
			try
			{
				//Création d'un canal sortatnt pour envoyer des messages au cleint
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				
				//Envoie message au client
				out.writeUTF("Hello from server - your are client# " + clientNumber);
				
				
			} catch (IOException e)
			{
				System.out.println("Error handling client# " + clientNumber + ":" + e);
			}
			
			finally
			{
				try
				{
					socket.close();
				}
				catch (IOException e)
				{
					System.out.println("Couldn't close a socket");
					
				}
				System.out.println("Connection with client# " + clientNumber + " closed");
			}
		}
	}
}
