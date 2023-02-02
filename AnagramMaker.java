import java.util.ArrayList;

/**
 *	AnagramMaker - This program gets a certain amount of anagrams from an input
 *				   string and number using recursive topics. 
 *
 *	Requires the WordUtilities, SortMethods, Prompt, and FileUtils classes
 *
 *	@author	Sidhant Malik
 *	@since	Jan 31, 2023
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
		wu = new WordUtilities();
		wu.readWordsFromFile(FILE_NAME);
		wu.sortWords();
	}
	
	public static void main(String[] args) {
		AnagramMaker am = new AnagramMaker();
		am.run();
	}
	
	/**	
	 * ERuns the entire program
	 */
	public void run() {
		printIntroduction();
		getUserInput();
		System.out.println("\nThanks for using AnagramMaker!\n");
	}
	
	
	/**
	 * This method runs a while loop to continually get the num of words, the max
	 * num of anagrams to print, and then the input string itself to make anagrams
	 * from before calling the recursive solution. 
	 */
	public void getUserInput()
	{
		System.out.println();
		String input = "";
		while (!input.equals("q"))
		{
			input = Prompt.getString("Word(s), name or phrase (q to quit)");
		
			if (!input.equals("q")) 
			{
				numWords = Prompt.getInt("Number of words in anagram");
				maxPhrases = Prompt.getInt("Maximum number of anagrams to print");
				System.out.println();
				
				ArrayList<String> anagrams = new ArrayList<>();
				input = removeNonAlphabetic(input);
				runRecursive(input, anagrams);
				System.out.println("\nStopped at " + numPhrases	+ " anagrams\n");
				numPhrases = 0;
			}
		}
		
		
	}
	
	/**
	 * This simple helper method takes in whatever the user entered and removes the
	 * non letter characters from it
	 * 
	 * @param String inputIn is the string that the user entered
	 * @return String retString is the string the user entered without non-alpha cars
	 */
	public String removeNonAlphabetic(String inputIn)
	{
		String retString = "";
		for (int i = 0; i < inputIn.length(); i++)
		{
			char current = Character.toLowerCase(inputIn.charAt(i));

			if (Character.isLetter(current) )
			{
				retString += current;
			}
			
		}
		return retString;
	}
	
	/**
	 *	This is the main recursive solution that finds the anagrams. 
	 *	The base case for this is when the length of the inputted
	 *	string is 0, or there are no words to work with from the 
	 *	previous iterations anagrams and so no new recrusive calls 
	 *	are necessary. Either way, first all the possible words
	 *	posisble are made for the first anagram, and then these are 
	 *	stored in an array list for each of these a new recurive 
	 *	call is made in which all of the possible anagrams for those
	 *	are found as well. 
	 *
	 *	@param input this is the string that the user starts 
	 *		   with that will start to be broken down
	 *  @param  ArrayList<String> anagrams is a list of anagrams
	 * 		   possible from the previous word
	 * 
	 * @return void but return can be used in order to end method 
	 * 		   and recursive calls after the base case
	 * 
	 */
	public void runRecursive(String input, ArrayList<String> anagrams) 
	{
		if (anagrams.size() == numWords && input.length() == 0)
		{
			for (int i = 0; i < anagrams.size(); i++)
			{
				System.out.print(anagrams.get(i) + " ");
			}
			System.out.println();
			
			numPhrases++;
			
			return;
		}
		
		if (anagrams.size() == numWords) 
		{ 
			return; 
		}
		else
		{
			ArrayList<String> allPossibleWords = wu.allWords(input);
			for (int i = 0; i < allPossibleWords.size(); i++)
			{
				anagrams.add(allPossibleWords.get(i)); 
				
				String newPhrase = removeOld(allPossibleWords.get(i), input);
				
				runRecursive(newPhrase, anagrams);
				anagrams.remove(anagrams.size() - 1);
				
				//puts a restriction on how many anagrams can be made
				if(numPhrases >= maxPhrases) return;
			}
		}

	}
	
	/**
	 * This takes in a target and continually removes each part of a phrase 
	 * sent in as well. 
	 * 
	 * @param	newPhraseIn  to remove
	 * @param	target to be removed from
	 * @return 	String target after individual elemnets of newPhraseIn are removed
	 */
	public String removeOld(String newPhraseIn, String target)
	{
		for (int i = 0; i < newPhraseIn.length(); i++)
		{
			int pos = target.indexOf(newPhraseIn.charAt(i));
			target = target.substring(0, pos) + target.substring(pos + 1); 
		}
		
		return target;
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
	
}
