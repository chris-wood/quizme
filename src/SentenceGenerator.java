import simplenlg.framework.*;
import simplenlg.lexicon.*;
import simplenlg.realiser.english.*;
import simplenlg.phrasespec.*;
import simplenlg.features.*;

public class SentenceGenerator {

	private WordDictionary dictionary;

	public SentenceGenerator(String key) {
		// TODO: create the word dictionary from the files, or using the remote service
		dictionary = WordDictionary.createFromFiles("../db/adj.exc", "../db/adv.exc", 
            "../db/noun.exc", "../db/verb.exc");
	}

	public String createRandomSentence() {
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = nlgFactory.createClause();
		p.setSubject("the " + dictionary.getRandomNoun());
		p.setVerb(dictionary.getRandomVerb());
		p.setObject("the " + dictionary.getRandomNoun());

		String sentence = realiser.realiseSentence(p);
		return sentence;
	}

	public static void main(String[] args) {
        WordDictionary dictionary = WordDictionary.createFromFiles("../db/adj.exc", "../db/adv.exc", 
            "../db/noun.exc", "../db/verb.exc");

		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = nlgFactory.createClause();
		p.setSubject("the " + dictionary.getRandomNoun());
		p.setVerb(dictionary.getRandomVerb());
		p.setObject("the " + dictionary.getRandomNoun());

		String output2 = realiser.realiseSentence(p);
		System.out.println(output2);
	}
}

