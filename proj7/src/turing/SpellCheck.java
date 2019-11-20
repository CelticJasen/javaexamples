package turing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashSet;
import javax.swing.JFileChooser;
import java.util.TreeSet;

/**
 * This class is a basic spell-checker. It uses words.txt
 * to check if the given words in another document are spelled right.
 */
public class SpellCheck
{
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner words;
		HashSet<String> dict = new HashSet<String>();
		Scanner userFile;
		
		try
		{
			words = new Scanner(new File("resources\\words.txt"));
			
			while(words.hasNext())
			{
				String word = words.next();
				dict.add(word.toLowerCase());
			}
			
			userFile = new Scanner(getInputFileNameFromUser());
			
			// Skip anything that is not a letter
			userFile.useDelimiter("[^a-zA-Z+]");
			
			HashSet<String> badWords = new HashSet<String>();
			
			while(userFile.hasNext())
			{
				String userWord = userFile.next();
				userWord = userWord.toLowerCase();
				
				if(!dict.contains(userWord) && !badWords.contains(userWord))
				{
					badWords.add(userWord);
					TreeSet<String> goodWords = new TreeSet<String>();
					goodWords = corrections(userWord, dict);
					System.out.print(userWord + ": ");
					
					if(goodWords.isEmpty())
					{
						System.out.println("(no suggestions)");
					}
					else
					{
						int count = 0;
						
						for(String goodWord: goodWords)
						{
							System.out.print(goodWord);
							
							if(count < goodWords.size() - 1)
							{
								System.out.print(", ");
							}
							else
							{
								System.out.print("\n");
							}
							
							count++;
						}
					}
				}
			}
		}
		catch(FileNotFoundException e)
		{
			System.exit(0);
		}
	} // end main()
	
	/**
    * Lets the user select an input file using a standard file
    * selection dialog box.  If the user cancels the dialog
    * without selecting a file, the return value is null.
    */
    static File getInputFileNameFromUser()
    {
       JFileChooser fileDialog = new JFileChooser();
       fileDialog.setDialogTitle("Select File for Input");
       int option = fileDialog.showOpenDialog(null);
       if (option != JFileChooser.APPROVE_OPTION)
       {
    	   return null;
       }
       else
       {
    	   return fileDialog.getSelectedFile(); 
       }
    } // end getInputFileNameFromUser()
    
    /**
     * Displays a list of incorrectly spelled words and
     * gives possible correct spellings for them.
     * 
     * @param badWord The word that is misspelled
     * @param dictionary The hash set of words.txt
     * @return A tree set containing a list of possible corrections to the misspelled word.
     */
    static TreeSet<String> corrections(String badWord, HashSet<String> dictionary)
    {
        TreeSet<String> possibleWords =  new TreeSet<String>();
        String subStr1, subStr2, possibility;
        
        for (int i = 0; i < badWord.length(); i++)
        {
            // Remove character i from the word.
            subStr1 = badWord.substring(0, i);
            subStr2 = badWord.substring(i + 1);
            
            // Delete any one of the letters from the misspelled word.
            possibility = subStr1 + subStr2;
            if (dictionary.contains(possibility))
            {
            	possibleWords.add(possibility);
            }
            
            // Change any letter in the misspelled word into
            //any other letter.    
            for (char ch = 'a'; ch <= 'z'; ch++)
            {
                possibility = subStr1 + ch + subStr2;
                if (dictionary.contains(possibility))
                {
                	possibleWords.add(possibility);
                }
            }

            // Divide the word into two substrings.
            subStr1 = badWord.substring(0, i);
            subStr2 = badWord.substring(i);
            
            // Insert any letter at any point in the misspelled word.
            for (char ch = 'a'; ch <= 'z'; ch++)
            {
                possibility = subStr1 + ch + subStr2;
                if (dictionary.contains(possibility))
                    possibleWords.add(possibility);
            }
            
            // Insert a space at any point in the misspelled word and check
            // that both of the words that are produced are in the dictionary.
            char ch = ' ';
            possibility = subStr1 + ch + subStr2;
            if (dictionary.contains(subStr1) && dictionary.contains(subStr2))
                      possibleWords.add(possibility);
        }
        
        // Swap any two neighboring characters in the misspelled word.
        for (int i = 1; i < badWord.length(); i++)
        {
            subStr1 = badWord.substring(0, i - 1);
            char ch1 = badWord.charAt(i - 1);
            char ch2 = badWord.charAt(i);
            subStr2 = badWord.substring(i + 1);
            possibility = subStr1 + ch2 + ch1 + subStr2;
            if (dictionary.contains(possibility))
                possibleWords.add(possibility);
        }
        
        return possibleWords;
    } // end corrections()
} // end class SpellChecker