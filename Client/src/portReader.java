import java.util.Scanner;

public class portReader {
	public static int read() {
		
		Scanner reader = new Scanner(System.in);
		int nbPort = 0;
		Boolean validPort = false;
		
		while (!validPort) {
			System.out.println("Please enter a Port number between 5000 and 5050");
			if (reader.hasNextInt() && reader.nextInt() > 5000 && reader.nextInt() < 5000) {
				nbPort = reader.nextInt();
				validPort = true;
				reader.nextLine();
			}
			else {
				System.out.println("Invalid Port");
				reader.nextLine();
			}
		}
		return nbPort;
	}

}
