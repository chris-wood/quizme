import simplenlg.framework.*;
import simplenlg.lexicon.*;
import simplenlg.realiser.english.*;
import simplenlg.phrasespec.*;
import simplenlg.features.*;

public class SentenceGenerator {

	private WordDictionary hardDictionary;
    private WordDictionary easyDictionary;

	public SentenceGenerator(String key) {
		// TODO: create the word dictionary from the files, or using the remote service
		hardDictionary = WordDictionary.createFromFiles("../db/adj.exc", "../db/adv.exc", 
            "../db/noun.exc", "../db/verb.exc");
        easyDictionary = WordDictionary.createFromFiles("../db/adj.exc", "../db/adv.exc",
            "../db/easynoun.exc", "../db/easyverb.exc");
	}

	public String createRandomHardSentence() {
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

	public static void test(String[] args) {
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

