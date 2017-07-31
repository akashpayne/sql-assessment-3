/**
 * As the program was throwing exceptions when constructing the border,
 * a borderException class was made too handle or catch the error.
 * 
 * @author (Akash Payne) ap567
 * @version 31/03/2014
 */
 
 public class BorderException extends Exception
{

	public BorderException()
	{
		super();
	}

	public BorderException(String message)
	{
		super(message);
	}
}