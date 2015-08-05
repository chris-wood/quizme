import java.net.URL;
import javax.net.ssl.*;
import java.io.*;
import java.util.Scanner;

public class Translator {

	private static String templateTranslateUrl = "https://translate.yandex.net/api/v1.5/tr.json/" +
		"translate?key=APIKEY&";
	private String translateUrl;

	// TODO: pull out to a separate file
	public enum Language {
		LanguageEnglish, LanguageFrench
	};
	private String[] languageStrings = {"en", "fr"};
	
	// lang=en-fr&text=To+be,+or+not+to+be%3F&text=That+is+the+question.";

	public Translator(String key) {
		translateUrl = templateTranslateUrl.replace("APIKEY", key);
	}

	private String buildTranslateQuery(Language from, Language to, String phrase) {
		StringBuilder builder = new StringBuilder(translateUrl);
		builder.append("lang=" + languageStrings[from.ordinal()] + "-" + languageStrings[to.ordinal()]);
		builder.append("&text=");

		String tokenizedPhrase = phrase.replace(" ", "+");
		builder.append(tokenizedPhrase);

		return builder.toString();
	}

	public String translate(Language from, Language to, String phrase) throws Exception {
		String query = buildTranslateQuery(from, to, phrase);
		
		URL url = new URL(query);
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

		// String ret = con.getContent().toString();
		InputStreamReader in = new InputStreamReader((InputStream) con.getContent());
		BufferedReader buff = new BufferedReader(in);
		StringBuilder builder = new StringBuilder();
		String line;
		do {
			line = buff.readLine();
			if (line != null) {
				builder.append(line + "\n");
			}
		} while (line != null);

		return builder.toString();
	}

	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.err.println("usage: java Translator <translator-apikey> <words-apikey>");
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

		Prompt prompt = new Prompt(">", System.in, System.out);
		do {		
			String phrase = prompt.getNextLine();
			if (phrase.length() > 0) {
				String output = translator.translate(Language.LanguageEnglish, Language.LanguageFrench, phrase);
				System.out.println(output);
				System.out.flush();
			}
		} while (!prompt.isDone());
	}
}