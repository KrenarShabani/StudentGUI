import javax.swing.JOptionPane;

public class Utilities {

	
	public static boolean CheckStudent(String[] check)
	{
		boolean flag = true;
		if(CheckString(check[0]) | CheckString(check[1]))
		{
			JOptionPane.showMessageDialog(null, "Cant have integers in a name !");
			flag = false;
		}
		if(check[0].length() < 2 | check[1].length() < 2)
		{
			JOptionPane.showMessageDialog(null, "First and Last Name need to be more than two Characters!");
			flag = false;
		}
		if(check[2].length() != 8 | !CheckInteger(check[2]))
		{
			JOptionPane.showMessageDialog(null, "CUNY ID needs to a number and has to be 8 digits long!");
			flag = false;
		}
		if(!CheckGPA(check[3]))
		{
			JOptionPane.showMessageDialog(null, "GPA Is Invalid!");
			flag = false;
		}	
		return flag;
	}
	
	
	public static boolean CheckInteger(String check)
	{
		if(check.isEmpty()) return false;
		boolean flag = true; 
		for(int i = 0; i< check.length(); i++) //this is incrementing each char
		{
			flag = false;
			for(int j = 0; j < 10; j++) // this is checking if the char is an int
				if(Character.forDigit(j,10) != (check.charAt(i)))
				{
					flag = true;
					break;
				}
			if(!flag) return false;
		}
		return true;
	}
	public static boolean CheckString(String check)
	{
		if(check.isEmpty()) return false;
		boolean flag = false; 
		for(int i = 0; i< check.length(); i++) //this is incrementing each char
		{
			flag = false;
			for(int j = 0; j < 10; j++) // this is checking if the char is an int
				if(!(Character.forDigit(j,10) == (check.charAt(i))))
				{
					flag = true;
					break;
				}
			if(flag) return false;
		}
		return true;
	}
	
	public static boolean CheckGPA(String check)
	{
		if(check.length() == 1)
		{
			if(!CheckInteger(check.substring(0,1)) | Integer.parseInt(check.substring(0,1)) > 4 | Integer.parseInt(check.substring(0,1)) < 0 )
				return false;
			else
				return true;
		}
		if(check.length() <= 0 | check.length() > 4)
			return false;
		if(!CheckInteger(check.substring(0,1)) | Integer.parseInt(check.substring(0,1)) >= 4 | Integer.parseInt(check.substring(0,1)) < 0 )
			return false;
		//System.out.print(check.substring(0,1));
		if(check.charAt(1) != '.')
				return false;
		if(!CheckInteger(check.substring(2)))
			return false;
		
		return	true;
	}
	public static String BuildString(char[] raw)
	{
		StringBuilder venus = new StringBuilder();
		for(int i = 0; i < raw.length; i++)
		{
			if(raw[i] !=' ')
				venus.append(raw[i]);
		}
		return venus.toString();
	}
}
