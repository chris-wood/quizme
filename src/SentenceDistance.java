public class SentenceDistance {
    public SentenceDistance() {
        // pass
    }

    private int levenshteinDistance(String a, String b, int i, int j) {
        if (j == -1) {
            return i;
        } else if (i == -1) {
            return j;
        } else {
            if (a[j] == b[i]) {
                return levenschteinDistance(a, b, i - 1, j - 1);
            } else {
                int delDistance = levenschteinDistance(a, b, i - 1, j) + 1; // delete
                int insDistance = levenschteinDistance(a, b, i, j - 1) + 1; // insert
                int swapDistance = levenschteinDistance(a, b, i - 1, j - 1) + 1; // substitute
                
                int min = delDistance < insDistance ? delDistance : insDistance;
                min = min < swapDistance ? min : swapDistance;
                return min;
            }
        }
    }    

    public int computeDistance(String a, String b) {
        return levenschteinDistance(a, b, b.length(), a.length());
    }

    public static void main(String[] args) {
        String word1 = args[0];
        String word2 = args[1];

        SentenceDistance distancer = new SentenceDistance();
        int distance = distancer.computeDistance(word1, word2);
        System.out.println("Distance between " + a + " and " + " b = " + distance);
    }
}

