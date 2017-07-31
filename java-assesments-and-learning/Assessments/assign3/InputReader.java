import java.util.HashSet;
import java.util.Scanner;

/**
 * InputReader reads typed text input from the standard text terminal. 
 * The text typed by a user is then chopped into words.
 * 
 * @author     Michael Kölling and David J. Barnes
 * @version    1.5 (2012.11.29)
 */
public class InputReader
{
    private Scanner reader;

    /**
     * Create a new InputReader that reads text from the text terminal.
     */
    public InputReader()
    {
        reader = new Scanner(System.in);
    }

    /**
     * Read a line of text from standard input (the text terminal),
     * and return it as an array of words.
     *
     * @return  A set of Strings, where each String is one of the 
     *          words typed by the user
     */
    public String[] getInput() 
    {
        System.out.print("> ");                // print prompt
        String inputLine = reader.nextLine().trim().toLowerCase();

        String[] words = inputLine.split(" ");  // split at spaces

        return words;
    }
    
    /**
     * Return true if the given word is an integer, false otherwise.
     * @param word A possible integer string.
     * @return true if word is an int.
     */
    public boolean isAnInt(String word)
    {
        try {
            // Try to parse the word as an integer.
            Integer.parseInt(word.trim());
            return true;
        }
        catch(NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Convert the given word to an integer.
     * Assumes the word really contains just integer characters.
     * @param word The word to be converted.
     * @return The integer represented by word.
     * @throws NumberFormatException if word is not an integer.
     */
    public int convertToInt(String word)
    {
        return Integer.parseInt(word);
    }
}
