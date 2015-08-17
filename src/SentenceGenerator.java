import simplenlg.framework.*;
import simplenlg.lexicon.*;
import simplenlg.realiser.english.*;
import simplenlg.phrasespec.*;
import simplenlg.features.*;

public abstract class SentenceGenerator {

	public abstract String createRandomNounSentence(double difficulty);
	public abstract String createRandomFullSentence(double difficulty);
	public abstract String createRandomPhrase(double difficulty);

}
