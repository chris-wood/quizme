import java.net.URL;
import javax.net.ssl.*;
import java.io.*;

public class WordsAPI {

	private static String baseUrl = "https://wordsapiv1.p.mashape.com/words/";
	private String key;

	public WordsAPI(String key) {
		this.key = key;
	}

	private HttpsURLConnection createConnection(String word, String method) {
		String query = "https://wordsapiv1.p.mashape.com/words/" + word + "/" + method;
		URL url = new URL(query);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestProperty("X-Mashape-Key", key);
		conn.setRequestProperty("Accept", "application/json");
		return conn;
	}

	public String getAlso(String word) {
		HttpsURLConnection conn = createConnection(word, "also");

		InputStreamReader in = new InputStreamReader((InputStream) conn.getContent());
		BufferedReader buff = new BufferedReader(in);
		StringBuilder builder = new StringBuilder();
		String line;
		do {
			line = buff.readLine();
			if (line != null) {
				builder.append(line + "\n");
			}
		} while (line != null);
	}
}
