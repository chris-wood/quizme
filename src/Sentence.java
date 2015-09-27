import java.util.List;
import java.util.ArrayList;

public class Sentence {
    private List[] words;

    public Sentence(List<String> words) {
        this.words = new ArrayList<String>(words);
    }
}