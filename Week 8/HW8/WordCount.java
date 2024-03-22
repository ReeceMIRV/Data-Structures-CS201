public class WordCount implements Comparable<WordCount>{
    public String word;
    public int count;

    // Constructor method
    public WordCount(String word, int count) {
        this.word = word;
        this.count = count;
    }

    /**
     * @description Implements compareTo using the wordCounts object count rather than alphabetical
     * Comparison is done in descending order. So... 3, 2, 1
     * Compare currentWord count to compareeWord count
     */
    @Override
    public int compareTo(WordCount comparee) {
        if (this.count > comparee.count) {
            return -1;
        } else if (this.count < comparee.count) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return("  {\n  " + "  Word: " + word + "\n  Count: " + count + " \n  }\n");
    }
}

