import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Arrays;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class WordFrequencyGenerator {

    private Map<String, Long> countMap;
    private Map<String, List<String>> kgramTieMap; // the -> [fox, cat, dog, ...]

    public WordFrequencyGenerator(String fileName) {
        try {
            Stream<String> lineWords = Files.lines(Paths.get(fileName))
                    .map(line -> line.split("\\s+"))
                    .flatMap(Arrays::stream);
            computeWordCount(lineWords);
            computeKgramTies(lineWords);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void addPairToKgramMap(String key, String value) {
        if (!kgramTieMap.containsKey(key)) {
            kgramTieMap.put(key, new ArrayList<String>());
        }
        kgramTieMap.get(key).add(value);
    }

    private void computeKgramTies(Stream<String> words) {
        kgramTieMap = new HashMap<String, List<String>>();
        String prev = null;
        words.forEach((word) -> {
            if (prev != null) {
                addPairToKgramMap(prev, word);
            }
            prev = word;
        });
    }

    private void computeWordCount(Stream<String> words) {
        countMap = words.collect(groupingBy(word -> word, counting()));
    }

    public List<String> getKgrams(String root, int k) {
        List<String> kgrams = new ArrayList<String>();
        List<String> queue = new ArrayList<String>();
        List<Integer> depths = new ArrayList<Integer>();
        List<String> visited = new ArrayList<String>();

        // depth-first search using the edges in the kgram map

        queue.add(root);
        depths.add(1);
        while (queue.size() > 0) {
            String curr = queue.get(0);
            int depth = depths.get(0);
            queue.removeAt(0); // drop first element
            depths.removeAt(0);
            if (!visited.contains(curr)) {
                
                if (kgramTieMap.contains(curr)) {
                    
                }
            }
        }
        
        return kgrams;
    }

    public void displayWordCount() {
        for (String key : countMap.keySet()) {
            System.out.println(key + " -> " + countMap.get(key));
        }
    }   

    public static void main(String[] args) {
        WordFrequencyGenerator gen = new WordFrequencyGenerator(args[0]);
        gen.displayWordCount();
    }
}

