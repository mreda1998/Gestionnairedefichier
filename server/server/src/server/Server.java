package server;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
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
		int serverPort = portReader.read();
		String serverAddress = AddressReader.read();
		
		
		
		
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
			Boolean connected = false;
			
			
			try
			{
				String[] input = new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine().split(" ");
				switch (input[0]) {
					case("ls"):
					case("exit"):
					case("mkdir"):
					case("upload"):
					case("download"):
					case("cd"):					
				}
				
				
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
