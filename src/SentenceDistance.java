public class SentenceDistance {
    public SentenceDistance() {
        // pass
    }

    private int levenshteinDistance(String a, String b, int i, int j) {
        if (j == -1) {
            return i + 1;
        } else if (i == -1) {
            return j + 1;
        } else {
            if (a.charAt(j) == b.charAt(i)) {
                return levenshteinDistance(a, b, i - 1, j - 1);
            } else {
                int delDistance = levenshteinDistance(a, b, i - 1, j) + 1; // delete
                int insDistance = levenshteinDistance(a, b, i, j - 1) + 1; // insert
                int swapDistance = levenshteinDistance(a, b, i - 1, j - 1) + 1; // substitute
                
                int min = delDistance < insDistance ? delDistance : insDistance;
                min = min < swapDistance ? min : swapDistance;
                return min;
            }
        }
    }    

    public int computeDistance(String a, String b) {
        return levenshteinDistance(a, b, b.length() - 1, a.length() - 1);
    }

    public static void main(String[] args) {
        String word1 = args[0];
        String word2 = args[1];

        SentenceDistance distancer = new SentenceDistance();
        int distance = distancer.computeDistance(word1, word2);

        System.out.println("Distance between '" + word1 + "' and '" + word2 + "' = " + distance);
    }
}

