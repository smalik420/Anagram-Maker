import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
/**
 *	Provides utilities for word games:
 *	1. finds all words in the dictionary that match a list of letters
 *	2. prints an array of words to the screen in tabular format
 *	3. finds the word from an array of words with the highest score
 *	4. calculates the score of a word according to a table
 *
 *	Uses the FileUtils and Prompt classes.
 *	
 *	@author Sidhant Malik
 *	@since	10/20/22
 */
 
public class WordUtilities
{
	private List<String> words;				// list of words
	
	
	private Scanner fileReader;
	
	/* Constructor */
	public WordUtilities() 
	{ 
		words = new ArrayList<String>();
	}
	
	/**	Load all of the dictionary from a file into words array. */
	public void readWordsFromFile (String fileNameIn) 
	{ 
		fileReader = FileUtils.openToRead(fileNameIn);
		while (fileReader.hasNext())
		{
			words.add(fileReader.next());
		}
		
		fileReader.close();	
	}
	
	
	/**
	 *  Decides if a word matches a group of letters.
	 *
	 *  @param word  The word to test.
	 *  @param letters  A string of letters to compare
	 *  @return  true if the word matches the letters, false otherwise
	 */
	public boolean isWordMatch (String word, String letters) 
	{
		for (int i = 0; i < word.length(); i++)
		{
			if ( getFrequency(word.charAt(i), letters) < getFrequency(word.charAt(i), word ) )
				return false;
		}
		
		return true;
		
	}
	
	/**
	 *  Counts the amount of a certain letter in the word. 
	 *
	 *  @param currentLetter  the letter to get the frequency of
	 *  @param currentWord  the currentWord to get use w the currentChar
	 *  @return the frequency of the letter in the word
	 */
	 
	
	private int getFrequency (char currentLetter, String currentWord)
	{
		int count = 0;
		for (int i = 0; i < currentWord.length(); i++)
		{
			if (currentWord.charAt(i) == currentLetter)
				count++;
		}
		
		return count;
	}
	
	/**
	 *	Determines if a word's characters match a group of letters
	 *	@param word		the word to check
	 *	@param letters	the letters
	 *	@return			true if the word's chars match; false otherwise
	 */
	private boolean wordMatch(String word, String letters) {
		// if the word is longer than letters return false
		if (word.length() > letters.length()) return false;
		
		// while there are still characters in word, check each word character
		// with letters
		while (word.length() > 0) {
			// using the first character in word, find the character's index inside letters
			// and ignore the case
			int index = letters.toLowerCase().indexOf(Character.toLowerCase(word.charAt(0)));
			// if the word character is not in letters, then return false
			if (index < 0) return false;
			
			// remove character from word and letters
			word = word.substring(1);
			letters = letters.substring(0, index) + letters.substring(index + 1);
		}
		// all word letters were found in letters
		return true;
	}
	
	/**
	 *	finds all words that match some or all of a group of alphabetic characters
	 *	Precondition: letters can only contain alphabetic characters a-z and A-Z
	 *	@param letters		group of alphabetic characters
	 *	@return				an ArrayList of all the words that match some or all
	 *						of the characters in letters
	 */
	public ArrayList<String> allWords(String letters) {
		ArrayList<String> wordsFound = new ArrayList<String>();
		// check each word in the database with the letters
		for (String word: words)
			if (wordMatch(word, letters))
				wordsFound.add(word);
		return wordsFound;
	}
	

	/**	Print the words found to the screen.
	 *  
	 *  @param words	array containing the words to be printed
	 */
	public void printWords (String [] wordList) 
	{ 
		for (int i = 0; i < wordList.length; i++)
		{
			if (i % 5 != 0)
			{
				System.out.printf("%10s", wordList[i]);
			}
			else
			{
				System.out.printf("\n%10s", wordList[i]);
			}
		}
	}
	
	/**
	 *	Sort the words in the database
	 */
	public void sortWords() {
		SortMethods sm = new SortMethods();
		sm.mergeSort(words);
	}

	
	/***************************************************************/
	/************************** Testing ****************************/
	/***************************************************************/
//	public static void main (String [] args)
//	{
//		WordUtilities wu = new WordUtilities();
//		wu.run();
//	}
	
	/**
	 * Runs a test for WordUtils
	 
	public void run() {
		readWordsFromFile();
		String letters = Prompt.getString("Please enter a list of letters, from 3 to 12 letters long, without spaces");
		String [] word = findAllWords(letters);
		System.out.println();
		printWords(word);
		
		// Score table in alphabetic order according to Scrabble
		int [] scoreTable = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
		String best = bestWord(word,scoreTable);
		System.out.println("\n\nHighest scoring word: " + best + "\nScore = " 
							+ getScore(best, scoreTable) + "\n");
	}
	*/
}
