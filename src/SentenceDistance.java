public class SentenceDistance {
    public SentenceDistance() {
        // pass
    }

    public int computeDistance(String a, String b) {
        // TODO

        return 0;
    }

    public static void main(String[] args) {
        String word1 = args[0];
        String word2 = args[1];

        SentenceDistance distancer = new SentenceDistance();
        int distance = distancer.computeDistance(word1, word2);
        System.out.println("Distance between " + a + " and " + " b = " + distance);
    }
}
