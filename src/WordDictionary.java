import java.util.List;
import java.util.ArrayList;
import java.io.File;

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

	private List<String> getFileLines(String file) {
		try {
			Path path = FileSystems.getDefault().getPath(file);
			List<String> lines = File.readAllLines(path, StandardCharsets.UTF_8);
			return lines;
		} catch (IOException ex) {
			System.err.println(ex);
			ex.printStackTrace();
		}
		return null;
	}

	private void setAdverbsFromFile(String advFile) {
		List<String> contents = getFileLines(advFile);
	}

	private void setAdjectivesFromFile(String adjFile) {
		List<String> contents = getFileLines(adjFile);
	}

	private void setNounsFromFile(String nounFile) {
		List<String> contents = getFileLines(nounFile);
	}

	private void setVerbsFromFile(String verbFile) {
		List<String> contents = getFileLines(verbFile);
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
