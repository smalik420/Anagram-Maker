/**
 *	AnagramMaker - <description goes here>
 *
 *	Requires the WordUtilities, SortMethods, Prompt, and FileUtils classes
 *
 *	@author	Sidhant Malik
 *	@since	1/28/23
 */
public class AnagramMaker {
								
	private final String FILE_NAME = "randomWords.txt";	// file containing all words
	
	private WordUtilities wu;	// the word utilities for building the word
								// database, sorting the database,
								// and finding all words that match
								// a string of characters
	
	// variables for constraining the print output of AnagramMaker
	private int numWords;		// the number of words in a phrase to print
	private int maxPhrases;		// the maximum number of phrases to print
	private int numPhrases;		// the number of phrases that have been printed
			
	/*	Initialize the database inside WordUtilities
	 *	The database of words does NOT have to be sorted for AnagramMaker to work,
	 *	but the output will appear in order if you DO sort.
	 */
	public AnagramMaker() {
		numWords = -1;
		maxPhrases = -1;
		numPhrases = 0;
		
		wu = new WordUtilities();
		wu.readWordsFromFile(FILE_NAME);
		wu.sortWords();
	}
	
	public static void main(String[] args) {
		AnagramMaker am = new AnagramMaker();
		am.run();
	}
	
	/**	The top routine that prints the introduction and runs the anagram-maker.
	 */
	public void run() {
		printIntroduction();
		runAnagramMaker();
		System.out.println("\nThanks for using AnagramMaker!\n");
	}
	
	/**
	 *	Print the introduction to AnagramMaker
	 */
	public void printIntroduction() {
		System.out.println("\nWelcome to ANAGRAM MAKER");
		System.out.println("\nProvide a word, name, or phrase and out comes their anagrams.");
		System.out.println("You can choose the number of words in the anagram.");
		System.out.println("You can choose the number of anagrams shown.");
		System.out.println("\nLet's get started!");
	}
	
	/**
	 *	Prompt the user for a phrase of characters, then create anagrams from those
	 *	characters.
	 */
	public void runAnagramMaker() 
	{
		String userInput = getUserInput();
		getAnagramsRecursively(userInput);
		
	}
	
	/**
	 * Gets the original phrase, removes non letter characters from it,
	 * along with the maximum number of words in the anagram and the 
	 * maximum amount of anagrams to print using the prompt class.
	 * 
	 * @return str the original phrase to make anagrams from that the user 
	 * 			   entered
	 */
	public String getUserInput() 
	{
		originalPhrase = Prompt.getString("Word(s), name or phrase (q to quit)");
		Str userPhrase = new String("");
		for (int i = 0; i < originalPhrase.length(); i++)
		{
			if ( Character.isLetter(originalPhrase.getChar(i)) == true )
			{
				userPhrase += originalPhrase.getChar(i);
			}
			
		}
		return userPhrase;
		
		numWords = Prompt.getInt("Number of words in anagram");
		maxPhrases = Prompt.getInt("Maximum number of anagrams to print");
	}
	
	getAnagramsRecursively(String phrase)
	{
		if (phrase.length == 0) //base case
		{
			
		}
		else
		{
				
			
			
		}
		
	}
}
