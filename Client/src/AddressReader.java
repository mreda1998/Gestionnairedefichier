import java.util.Scanner;

public class AddressReader {
	public static String read() {
		
		Scanner reader = new Scanner(System.in);
		String address = "";
		Boolean validAddress = false;
		
		while (!validAddress) {
			System.out.println("Please enter a valid Address number");
			String line = reader.nextLine();
			if (testingAddress(line)) {
				address = line;
				validAddress = true;
				reader.nextLine();
			}
			else {
				System.out.println("Invalid Address Number");
				reader.nextLine();
			}
		}
		return address;
		
		
	}
	
	public static boolean testingAddress(String address)
	{
		
		int count = 0;
		for (char c : address.toCharArray())
		{
			if (c == ".".toCharArray()[0])
			{
				count += 1;
			}
		}

		if (count != 3)
		{
			return false;
		}
		String[] addressSplited = address.split("\\.");	
		if (!validateSubString(addressSplited)) {
			return false;
		}
		return true;
	}
	public static boolean validateSubString(String[] addressSplited)
	{
		for (String element : addressSplited)
		{
			if (!validateNumber(element))
			{
				return false;
			}

		}
		return true;
	}
	public static boolean validateNumber(String number)
	{
		try
		{
			int num = Integer.parseInt(number);
			if ( num < 0 || num > 255 ) {
				return false;
			}
			return true;
		}
		catch (NumberFormatException nfe)
		{
			return false;
		}
		
	
	}

}
