import java.util.List;
import java.util.ArrayList;

public class WordDictionary {

	private List<String> adjectives;
	private List<String> adverbs;
	private List<String> nouns;
	private List<String> verbs;

	private WordDictionary() {
		adjectives = new ArrayList<String>();
		adverbs = new ArrayList<String>();
		nouns = new ArrayList<String>();
		verbs = new ArrayList<String>();
	}

	private void setAdverbsFromFile(String advFile) {

	}

	private void setAdjectivesFromFile(String adjFile) {

	}

	private void setNounsFromFile(String nounFile) {

	}

	private void setVerbsFromFile(String verbFile) {

	}

	public static createFromDictionary(String adjFile, String advFile, 
		String nounFile, String verbFile) {
		WordDictionary dictionary = new WordDictionary();
		dictionary.setAdverbsFromFile(advFile);
		dictionary.setAdjectivesFromFile(adjFile);
		dictionary.setNounsFromFile(nounFile);
		dictionary.setVerbsFromFile(verbFile);
		return dictionary;
	}
}
