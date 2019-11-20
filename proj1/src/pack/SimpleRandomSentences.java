package pack;

/**
  This class is meant to create a random sentence.
  The rule set for the sentence is as follows...
  
	<sentence> ::= <simple_sentence> [ <conjunction> <sentence> ]

	<simple_sentence> ::= <noun_phrase> <verb_phrase>
	
	<noun_phrase> ::= <proper_noun> |
	<determiner> [ <adjective> ]. <common_noun> [ who <verb_phrase> ]
	
	<verb_phrase> ::= <intransitive_verb> |
	<transitive_verb> <noun_phrase> |
	is <adjective> |
	believes that <simple_sentence>
	
	<conjunction> ::= and | or | but | because
	
	<proper_noun> ::= Fred | Jane | Richard Nixon | Miss America
	
	<common_noun> ::= man | woman | fish | elephant | unicorn
	
	<determiner> ::= a | the | every | some
	
	<adjective> ::= big | tiny | pretty | bald
	
	<intransitive_verb> ::= runs | jumps | talks | sleeps
	
	<transitive_verb> ::= loves | hates | sees | knows | looks for | finds
  
  There will be no period at the end of the sentence.
  The first word of the sentence may or may not be capitalized.
*/

public class SimpleRandomSentences {

	//All of these string arrays contain the words that will be used to
	//create an intelligible sentence
   static final String[] conjunction = { "and", "or", "but", "because" };
                                   
   static final String[] properNoun = { "Fred", "Jane", "Richard Nixon", "Miss America" };

   static final String[] commonNoun = { "man", "woman", "fish", "elephant", "unicorn" };
   
   static final String[] determiner = { "a", "the", "every", "some" };
   
   static final String[] adjective = { "big", "tiny", "pretty", "bald" };
   
   static final String[] intransitiveVerb = { "runs", "jumps", "talks", "sleeps" };
   
   static final String[] transitiveVerb = { "loves", "hates", "sees", "knows", "looks for", "finds" };

   public static void main(String[] args)
   {
	   sentence();
   }
   
   /**
    * This has a chance of calling back to itself
    * after inserting a conjunction into the sentence.
    * Otherwise, it will call to another method
    * to take control.
    */
   static void sentence()
   {
	   simpleSentence();
	   if(Math.random() > 0.2)
	   {
		   System.out.print(randomItem(conjunction) + " ");
		   sentence();
	   }
   }
   
   static void simpleSentence()
   {
	   nounPhrase();
	   verbPhrase();
   }
   
   /**
    * This handles the logic of choosing certain nouns
    * to insert into the sentence. It will have a chance
    * of inserting an adjective or a determiner as well.
    */
   static void nounPhrase()
   {
		if (Math.random() > 0.2)
		{
			System.out.print(randomItem(properNoun) + " ");
		}
		else
		{
			System.out.print(randomItem(determiner) + " ");
			if (Math.random() > 0.2)
			{
				System.out.print(randomItem(adjective) + " ");
			}
		
			System.out.print(randomItem(commonNoun) + " ");
		
			if (Math.random() > 0.2)
			{
				System.out.print("who ");
				verbPhrase();
			}
		}
   }
   
   /**
    * This handles the logic of choosing certain verbs
    * to insert into the sentence. It will have a chance
    * of inserting an adjective or the words "believes that "
    * as well.
    */
   static void verbPhrase()
   {
	   int rNumber = (int)(Math.random()*4) + 1;
	   
	   switch(rNumber)
	   {
		   case 1:
			   System.out.print(randomItem(intransitiveVerb) + " ");
			   break;
			   
		   case 2:
			   System.out.print(randomItem(transitiveVerb) + " ");
			   nounPhrase();
			   break;
			   
		   case 3:
			   System.out.print("is " + randomItem(adjective) + " ");
			   break;
			   
		   case 4:
			   System.out.print("believes that ");
			   simpleSentence();
			   break;
	   }
   }
   
   /**
    * This will choose a word at random from given array
    * @param listOfStrings An array with string values
    * @return listOfStrings 
    */
   static String randomItem(String[] listOfStrings)
   {
	   int pick = (int)(Math.random()*listOfStrings.length);
	   return listOfStrings[pick];
   }
}