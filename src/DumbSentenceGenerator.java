public class DumbSentenceGenerator extends SentenceGenerator {
    private WordDictionary hardDictionary;
    private WordDictionary easyDictionary;

    public DumbSentenceGenerator() {
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
        p.setSubject("the " + hardDictionary.getRandomNoun());
        p.setVerb(hardDictionary.getRandomVerb());
        p.setObject("the " + hardDictionary.getRandomNoun());

        String sentence = realiser.realiseSentence(p);
        return sentence;
	}

    public String createRandomEasySentence() {
        Lexicon lexicon = Lexicon.getDefaultLexicon();
        NLGFactory nlgFactory = new NLGFactory(lexicon);
        Realiser realiser = new Realiser(lexicon);

        SPhraseSpec p = nlgFactory.createClause();
        p.setSubject("the " + easyDictionary.getRandomNoun());
        p.setVerb(easyDictionary.getRandomVerb());
        p.setObject("the " + easyDictionary.getRandomNoun());

        String sentence = realiser.realiseSentence(p);
        return sentence;
    }

    public String createRandomFullSentence(double difficulty) {
        if (difficulty < 0.5) {
            return createRandomEasySentence();
        } else {
            return createRandomHardSentence();
        }
    }

	public String createRandomPhrase(double difficulty) {
        return createRandomFullSentence(difficulity);
    }

    public String createRandomNounSentence(double difficulity) {
        Lexicon lexicon = Lexicon.getDefaultLexicon();
        NLGFactory nlgFactory = new NLGFactory(lexicon);
        Realiser realiser = new Realiser(lexicon);
        SPhraseSpec p = nlgFactory.createClause();

        if (difficulity < 0.5) {
            p.setObject("the " + easyDictionary.getRandomNoun());
        } else {
            p.setObject("the " + hardDictionary.getRandomNoun());
        }

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
