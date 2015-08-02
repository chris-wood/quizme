import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.FileSystems;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class WordDictionary {

	private List<String> adjectives;
	private List<String> adverbs;
	private List<String> singularNouns;
	private List<String> pluralNouns;
	private List<String> verbs;
	private SecureRandom random;

	private WordDictionary() {
		adjectives = new ArrayList<String>();
		adverbs = new ArrayList<String>();
		singularNouns = new ArrayList<String>();
		pluralNouns = new ArrayList<String>();
		verbs = new ArrayList<String>();
		random = new SecureRandom();
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
		for (String line : contents) {
			String[] adjLine = line.split(" ");
			if (!adjectives.contains(adjLine[0])) {	
				adjectives.add(adjLine[0]);
			}
			if (!adjectives.contains(adjLine[1])) {	
				adjectives.add(adjLine[1]);
			}
		}
	}

	private void setNounsFromFile(String nounFile) {
		List<String> contents = getFileLines(nounFile);
		for (String line : contents) {
			String[] nouns = line.split(" ");
			pluralNouns.add(nouns[0]);
			if (!singularNouns.contains(nouns[1])) {
				singularNouns.add(nouns[1]);
			}
		}
	}

	private void setVerbsFromFile(String verbFile) {
		List<String> contents = getFileLines(verbFile);
		for (String line : contents) {
			String[] verbLine = line.split(" ");
			if (!verbs.contains(verbLine[1])) {	
				verbs.add(verbLine[1]);
			}
		}
	}

	public static WordDictionary createFromFiles(String adjFile, String advFile, 
		String nounFile, String verbFile) {
		WordDictionary dictionary = new WordDictionary();
		// dictionary.setAdverbsFromFile(advFile);
		dictionary.setAdjectivesFromFile(adjFile);
		dictionary.setNounsFromFile(nounFile);
		dictionary.setVerbsFromFile(verbFile);
		return dictionary;
	}

	public String getRandomNoun() {
		if (random.nextBoolean()) {
			return singularNouns.get(random.nextInt(singularNouns.size()));
		} else {
			return pluralNouns.get(random.nextInt(pluralNouns.size()));
		}
	}

	public String getRandomVerb() {
		return verbs.get(random.nextInt(verbs.size()));
	}

	public String getRandomAdjective() {
		return adjectives.get(random.nextInt(adjectives.size()));
	}

	public static void main(String[] args) {
		WordDictionary dictionary = WordDictionary.createFromFiles("../db/adj.exc", "../db/adv.exc", "../db/noun.exc", "../db/verb.exc");
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter any key to get more...");
		while (scanner.hasNext()) {
			scanner.nextLine(); // discard
			System.out.println("Noun: " + dictionary.getRandomNoun());
			System.out.println("Verb: " + dictionary.getRandomVerb());
			System.out.println("Adj.: " + dictionary.getRandomAdjective());
			System.out.println("Enter any key to get more...");
		}
	}
}