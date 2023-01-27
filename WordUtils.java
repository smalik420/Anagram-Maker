import java.util.Scanner;

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
 
public class WordUtils
{
	private String[] words;		// the dictionary of words
	
	// File containing dictionary of almost 100,000 words.
	private final String WORD_FILE = "wordList.txt";
	
	private Scanner fileReader;
	
	/* Constructor */
	public WordUtils() 
	{ 
		words = new String[90934];
	}
	
	/**	Load all of the dictionary from a file into words array. */
	private void loadWords () 
	{ 
		fileReader = FileUtils.openToRead(WORD_FILE);
		
		String currentLine = "";
		int index = 0;
		while (fileReader.hasNext() )
		{
			currentLine = fileReader.nextLine();
			words[index] = currentLine.toLowerCase();
			index++;
		}
	}
	
	/**	Find all words that can be formed by a list of letters.
	 *  
	 *  @param letters	string containing list of letters
	 *  @return			array of strings with all words found.
	 */
	public String [] findAllWords (String letters)
	{		
		int amtOfValidWords = 0;
		for (int i = 0; i < words.length; i++)
		{		
			if ( isWordMatch(words[i], letters) )
			{
				amtOfValidWords++;
			}
		}
		
		String[] retArr = new String[amtOfValidWords];
		
		int pos = 0;
		for (int i = 0; i < words.length; i++)
		{		
			if ( isWordMatch(words[i], letters) )
			{
				retArr[pos] = words[i];
				pos++;
			}
		}
		
		return retArr;
	}
	
	/**	Find the amt of words that can be formed by a list of letters.
	 * 
	 *  @param letters	string containing list of letters
	 *  @return			int amt of possible words containing those letters.
	 */
	public int getAmtOfValidWords ( String letters)
	{		
		loadWords();
		int amtOfValidWords = 0;
		for (int i = 0; i < words.length; i++)
		{		
			if ( isWordMatch(words[i], letters) )
			{
				amtOfValidWords++;
			}
		}
		
		return amtOfValidWords;
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
		boolean isValid = false;
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
	
	/**	Finds the highest scoring word according to a score table.
	 *
	 *  @param word  		An array of words to check
	 *  @param scoreTable	An array of 26 integer scores in letter order
	 *  @return   			The word with the highest score
	 */
	public String bestWord (String [] wordList, int [] scoreTable)
	{
		String bestWordString = "";
		int currentHighest = 0;
		int currentRunning = 0;
		
		for (int i = 0; i < wordList.length; i++)
		{
			currentRunning = getScore(wordList[i], scoreTable);
			if (currentRunning > currentHighest)
			{
				bestWordString = wordList[i];
				currentHighest = currentRunning;
			}
			
		}
		return bestWordString;
	}
	
	/**	Calculates the score of one word according to a score table.
	 *
	 *  @param word			The word to score
	 *  @param scoreTable	An array of 26 integer scores in letter order
	 *  @return				The integer score of the word
	 */
	public int getScore (String word, int [] scoreTable)
	{
		int score = 0;
		
		for (int i = 0; i < word.length(); i++)
		{
			score += scoreTable[ (int)(word.charAt(i)) - 97  ];
		}
		
		return score;
	}
	
	/**
	 * This is a getter method that loads the word array and then returns 
	 * the entire dictionary. 
	 * 
	 * @return 	words 	array containing the entire dictionary
	 */
	public String[] getWords()
	{
		loadWords();
		return words;
	}
	
	/***************************************************************/
	/************************** Testing ****************************/
	/***************************************************************/
	public static void main (String [] args)
	{
		WordUtils wu = new WordUtils();
		wu.run();
	}
	
	/**
	 * Runs a test for WordUtils
	 */
	public void run() {
		loadWords();
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
}