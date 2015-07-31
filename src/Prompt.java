import java.util.Scanner;
import java.io.PrintStream;
import java.io.InputStream;

public class Prompt {

	private String prefix;
	private Scanner input;
	private PrintStream output;

	public Prompt(String prompt, InputStream inputStream, PrintStream outputStream) {
		prefix = prompt;
		input = new Scanner(inputStream);
		output = outputStream;
	}

	public boolean isDone() {
		return input.hasNextLine();
	}

	public String getNextLine() {
		output.print(prefix + " ");
		return input.nextLine();
	}
}