import javax.json.*;

public class YandexTranslatorService extends TranslatorService {

	private String[] languageStrings = {"en", "fr"};

	public String translate(Language from, Language to, String phrase) throws Exception {
		String query = buildTranslateQuery(from, to, phrase);

		URL url = new URL(query);
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

		InputStreamReader in = new InputStreamReader((InputStream) con.getContent());
		InputStream ins = con.getContent();
		JsonReader reader = Json.createReader(ins);
		JsonObject root = rdr.readObject();
		String translation = root.getString("text");

		return translation;
	}

	public abstract String languageString(Language lang) {
		return languageStrings[lang.ordinal()];
	}
}

// http://www.oracle.com/technetwork/articles/java/json-1973242.html
