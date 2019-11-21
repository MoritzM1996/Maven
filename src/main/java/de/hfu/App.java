package de.hfu;
import java.util.*;

public class App
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		String test = new String();
		System.out.print("Bitte geben Sie einen Text ein: ");
		test = scan.nextLine();
		int j = 0;
		while(j < test.length())
		{
            if(test.charAt(j) > 96 && test.charAt(j) < 123)
			{
            	System.out.print((char)(test.charAt(j) - 32));
				j++;
			}
            else
			{
				System.out.print(test.charAt(j));
				j++;
			}
		}
		System.out.println();
		scan.close();
	}
}


/**
 * Hello world!
 *
 */
 /*
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
*/
