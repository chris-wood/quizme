import java.util.Scanner;
import java.io.PrintStream;
import java.io.InputStream;

public class Prompt {

	private String prefix;
	private Scanner input;
	private PrintStream output;
	private boolean done;

	public Prompt(String prompt, InputStream inputStream, PrintStream outputStream) {
		prefix = prompt;
		input = new Scanner(inputStream);
		output = outputStream;
		done = false;
	}

	public boolean isDone() {
		return done;
	}

	public void showPrompt() {
		output.print(prefix + " ");
	}

	public String getNextLine() {
		showPrompt();
		String line = input.nextLine();
		if (line == null || line.length() == 0) {
			done = true;
		}
		return line;
	}
}
