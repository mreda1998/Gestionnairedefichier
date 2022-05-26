package server;

public class AdressTester {
	
	public boolean testingAdress(String address)
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
		
		return true;
	}
	public boolean validateSubString(String[] addressSplited)
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
	public boolean validateNumber(String number)
	{
		try
		{
			int num = Integer.parseInt(number);
			
		}
		catch (NumberFormatException nfe)
		{
			return false;
		}
		
		return true;
	
	}

}
