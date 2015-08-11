import java.util.Scanner;
import java.io.PrintStream;
import java.io.InputStream;

public class Prompt {

	private String query;
	private String prefix;
	private Scanner input;
	private PrintStream output;
	private boolean done;

	public Prompt(String query, String promptPrefix, InputStream inputStream, PrintStream outputStream) {
		this.query = query;
		this.prefix = promptPrefix;
		input = new Scanner(inputStream);
		output = outputStream;
		done = false;
	}

	public boolean isDone() {
		return done;
	}

	public void showPrompt(String text) {
		output.println(query + " " + text);
		output.print(prefix + " ");
	}

	public String getNextLine(String sentence) {
		showPrompt(sentence);
		String line = input.nextLine();
		if (line == null || line.length() == 0) {
			done = true;
		}
		return line;
	}
}
