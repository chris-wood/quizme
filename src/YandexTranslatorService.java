import javax.json.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.URL;
import javax.net.ssl.*;

public class YandexTranslatorService extends TranslatorService {

	private static String templateTranslateUrl = "https://translate.yandex.net/api/v1.5/tr.json/" +
		"translate?key=APIKEY&";
	private String translateUrl;

	private String[] languageStrings = {"en", "fr"};

	// lang=en-fr&text=To+be,+or+not+to+be%3F&text=That+is+the+question.";

	public YandexTranslatorService(String key) {
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

	public String translateToJSON(Language from, Language to, String phrase) throws Exception {
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

	// http://www.oracle.com/technetwork/articles/java/json-1973242.html
	public String translate(Language from, Language to, String phrase) throws Exception {
		String query = buildTranslateQuery(from, to, phrase);

		URL url = new URL(query);
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

		InputStreamReader in = new InputStreamReader((InputStream) con.getContent());
		InputStream ins = (InputStream) con.getContent();
		JsonReader reader = Json.createReader(ins);
		JsonObject root = reader.readObject();
		JsonArray translations = root.getJsonArray("text");
		String translation = translations.getString(0);

		return translation;
	}

	public String languageString(Language lang) {
		return languageStrings[lang.ordinal()];
	}
}
