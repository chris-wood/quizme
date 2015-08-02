import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.FileSystems;
import java.nio.charset.StandardCharsets;

public class WordDictionary {

	private List<String> adjectives;
	private List<String> adverbs;
	private List<String> singularNouns;
	private List<String> pluralNouns;
	private List<String> verbs;

	private WordDictionary() {
		adjectives = new ArrayList<String>();
		adverbs = new ArrayList<String>();
		singularNouns = new ArrayList<String>();
		pluralNouns = new ArrayList<String>();
		verbs = new ArrayList<String>();
	}

	private List<String> getFileLines(String file) {
		try {
			Path path = FileSystems.getDefault().getPath(file);
			List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
			return lines;
		} catch (IOException ex) {
			System.err.println(ex);
			ex.printStackTrace();
		}
		return null;
	}

	private void setAdverbsFromFile(String advFile) {
		List<String> contents = getFileLines(advFile);
		for (String s : contents) {
			System.out.println(s);
		}
	}

	private void setAdjectivesFromFile(String adjFile) {
		List<String> contents = getFileLines(adjFile);
		for (String s : contents) {
			System.out.println(s);
		}
	}

	private void setNounsFromFile(String nounFile) {
		List<String> contents = getFileLines(nounFile);
		for (String line : contents) {
			String[] nouns = line.split(" ");
			pluralNouns.append(nouns[0]);
			singularNouns.append(nouns[1]);
		}
	}

	private void setVerbsFromFile(String verbFile) {
		List<String> contents = getFileLines(verbFile);
		for (String s : contents) {
			System.out.println(s);
		}
	}

	public static WordDictionary createFromFiles(String adjFile, String advFile, 
		String nounFile, String verbFile) {
		WordDictionary dictionary = new WordDictionary();
		dictionary.setAdverbsFromFile(advFile);
		dictionary.setAdjectivesFromFile(adjFile);
		dictionary.setNounsFromFile(nounFile);
		dictionary.setVerbsFromFile(verbFile);
		return dictionary;
	}

	public static void main(String[] args) {
		WordDictionary dictionary = WordDictionary.createFromFiles("../db/adj.exc", "../db/adv.exc", "../db/noun.exc", "../db/verb.exc");
		// TOOD
	}
}