public class SentenceDistance {
    public SentenceDistance() {
        // pass
    }

    private int levenshteinDistanceDP(String a, String b) {
        int[][] m = new int[a.length() + 1][b.length() + 1];
    
        m[0][0] = 0;
        for (int i = 0; i < a.length() + 1; i++) {
            m[i][0] = i;
        }
        for (int j = 0; j < b.length() + 1; j++) {
            m[0][j] = j;
        }

        for (int i = 1; i < a.length() + 1; i++) {
            for (int j = 1; j < b.length() + 1; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    m[i][j] = m[i - 1][j - 1];
                } else { 
                    int d1 = m[i - 1][j] + 1; // insert
                    int d2 = m[i][j - 1] + 1; // delete
                    int d3 = m[i - 1][j - 1] + 1; // sub.
                    int min = d1 < d2 ? d1 : d2;
                    min = min < d3 ? min : d3;
                    m[i][j] = min;
                }
            }
        }
    
        return m[a.length()][b.length()];
    }

    //// The Levenshtein distance recurrence relation
    // d('', '') = 0               -- '' = empty string
    // d(s, '')  = d('', s) = |s|  -- i.e. length of s
    //d(s1+ch1, s2+ch2)
    //   = min( d(s1, s2) + if ch1=ch2 then 0 else 1 fi,
    //          d(s1+ch1, s2) + 1,
    //          d(s1, s2+ch2) + 1 )

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
        // return levenshteinDistance(a, b, b.length() - 1, a.length() - 1);
        return levenshteinDistanceDP(a, b);
    }

    public static void main(String[] args) {
        String word1 = args[0];
        String word2 = args[1];

        SentenceDistance distancer = new SentenceDistance();
        int distance = distancer.computeDistance(word1, word2);

        System.out.println("Distance between '" + word1 + "' and '" + word2 + "' = " + distance);
    }
}

