import java.net.URL;
import javax.net.ssl.*;
import java.io.*;
import java.util.Scanner;

public class Translator {

	private TranslatorService translator;

	public Translator(String key) {
		translator = new YandexTranslatorService(key);
	}

	public String translate(Language from, Language to, String phrase) throws Exception {
		return translator.translate(from, to, phrase);
	}

	public static void main(String[] args) throws Exception {
		if (args.length != 3) {
			System.err.println("usage: java Translator <translator-apikey> <words-apikey> <history-file>");
			System.exit(-1);
		}

		String fileName = args[0];
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String translatorApiKey = reader.readLine();
		reader.close();
		Translator translator = new Translator(translatorApiKey);

		String wordsApiFileName = args[1];
		BufferedReader wordsApiReader = new BufferedReader(new FileReader(wordsApiFileName));
		String wordsApiKey = wordsApiReader.readLine();
		SentenceGenerator sentenceGenerator = new SentenceGenerator(wordsApiKey);

		SentenceDistance distancer = new SentenceDistance();

		String historyFile = args[2];
		QuizHistory history = new QuizHistory(historyFile);

		String sentence = sentenceGenerator.createRandomSentence();
		Prompt prompt = new Prompt("Translate the following: " + sentence + "\n>", System.in, System.out);
		do {
			String phrase = prompt.getNextLine();
			if (phrase.length() > 0) {
				String translatedPhrase = translator.translate(Language.LanguageEnglish, Language.LanguageFrench, phrase);
				
				System.out.println("Translation: " + translatedPhrase);

				int editDistance = distancer.computeDistance(phrase, translatedPhrase);
				System.out.println("Edit distance: " + editDistance);

				System.out.flush();
			}
		} while (!prompt.isDone());
	}
}
