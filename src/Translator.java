import java.net.URL;

public class Translator {

	private static String templateTranslateUrl = "https://translate.yandex.net/api/v1.5/tr.json/" +
		"translate?key=APIKEY&";
	private String translateUrl;

	private enum Language {
		Language_English, Language_French
	};
	private String[] languageStrings = {"en", "fr"};
	
	// lang=en-fr&text=To+be,+or+not+to+be%3F&text=That+is+the+question.";

	public Translator(String key) {
		translateUrl = templateTranslateUrl.replace("APIKEY", key);
	}

	private String buildTranslateQuery(Langauge from, Language to, String phrase) {
		StringBuilder builder = new StringBuilder(translateUrl);
		builder.append("lang=" + languageStrings[from.ordinal()] + "-" + languageStrings[to.ordinal()]);
		builder.append("&text=");

		String tokenizedPhrase = phrase.replace(" ", "+");
		builder.append(tokenizedPhrase);

		return builder.toString()
	}

	public String translate(Langauge from, Language to, String phrase) {
		String query = buildTranslateQuery(from, to, phrase);
		
		// TODO: exceptions
		URL url = new URL(query);
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		String ret = con.getContent();

		return ret;
	}

	public static void main(String[] args) {
		Translator translator 
	}
}