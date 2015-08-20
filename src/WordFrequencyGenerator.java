import java.util.Map;
import java.util.HashMap;
import java.util.stream.Stream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Arrays;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class WordFrequencyGenerator {

    private Map<String, Long> countMap;

    public FrequencyGenerator(String fileName) {
        try {
            Stream<String> lineWords = Files.lines(Paths.get(fileName))
                    .map(line -> line.split("\\s+"))
                    .flatMap(Arrays::stream);
            computeWordCount(lineWords);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void computeWordCount(Stream<String> words) {
        countMap = words.collect(groupingBy(word -> word, counting()));
    }

    public void displayWordCount() {
        for (String key : countMap.keySet()) {
            System.out.println(key + " -> " + countMap.get(key));
        }
    }   

    public static void main(String[] args) {
        FrequencyGenerator gen = new FrequencyGenerator(args[0]);
        gen.displayWordCount();
    }
}
