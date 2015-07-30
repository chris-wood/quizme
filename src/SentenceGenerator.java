import simplenlg.framework.*;
import simplenlg.lexicon.*;
import simplenlg.realiser.english.*;
import simplenlg.phrasespec.*;
import simplenlg.features.*;

public class SentenceGenerator {
	public static void main(String[] args) {
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = nlgFactory.createClause();
		p.setSubject("Mary");
		p.setVerb("chase");
		p.setObject("the monkey");

		String output2 = realiser.realiseSentence(p);
		System.out.println(output2);
	}
}

