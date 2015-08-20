public class MarkovSentenceGenerator extends SentenceGenerator {

    private WordFrequencyGenerator wfg;

    public MarkovSentenceGenerator(String trainingFile) {
        wfg = new WordFrequencyGenerator(trainingFile);
    }

    public String createRandomNounSentence(double difficulty) {
        return "";
    }

    public String createRandomFullSentence(double difficulty) {
        return "";
    }

    public String createRandomPhrase(double difficulty) {
        return "";
    }
}
