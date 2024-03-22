Homework 8 Breakdown:

Goals:
Practice with implementing and navigating binary search trees.
We will implement a binary search tree to count the number of times each word appears in a given text to make a word cloud.

Building a Word Cloud
A word cloud takes the most frequent words in a document and displays them in a jumbled arrangment.
The size of each word is proportional to the number of times that words is used.
"Stop words" are exclused from the word cloud. Stop words are words such as "the", "and", "a", etc.

Files & Classes:
Text files with words

WordCountMap.java
    Each instance/object of WordCountMap will represent/is a binary search tree. It maintains a record of the words & their counts.

    The tree should be organized according to the alphabetical orderering of the words.

    It will contain the following classes:
        Node
            Word;       // Why can't we just use the class
            Count;      // WordCount in our node instead?
            LeftNode;
            RightNode;
        
        WordCount
            Word;
            Count;

WordCounter.java
    This will contain our main program and any supporting methods it needs.
    This will open a text file.
    This will count all the words in the text file.
    This will produce one of three types of outputs... (elaborate?)
        Specifically, your main method here will parse the command-line arguments to support the following three ways of running the program:
            java WordCounter alphabetical [textFileName]
                This will print out a list of words alongside their occurrence count.
                Each line will consist of a word, a colon, and its count, sorted alphabetically.

                Example:
                    java WordCounter alphabetical FederalistPapers.txt

                    abandon: 5
                    abandoned: 3
                    abandoning: 1
                    abate: 1
                    abatements: 1
                    abbe: 4
                    abetted: 2
                    abhorrence: 1
                    abide: 1
            java WordCounter frequency [textFileName]
                This will write HTML to a file containng a word cloud based on the code given (read throught the code & comments in WordCloudMaker.java)

                Example:
                    java WordCounter cloud FederalistPapers.txt 40

                    This will generate a word cloud based on the 40 most common non-stopwords in FederalistPapers.txt. (If FederalistPapers.txt contains fewer than 40 non-stopwords, then the cloud will just use all the words.)

                    Create a reasonable filename to contain the HTML.
                    Make sure it has the extension .html.
                    Ideally you would take the name of the text file being passed in and use that to name the HTML file.

                    java WordCounter cloud FederalistPapers.txt 40

                    would create a file called FederalistPapers.html, which, when opened in an internet browser, might look like the following:

            java WordCounter cloud [textFileName] [numberOfWordsToInclude]


WordCloudMaker.java
    This transforms a list of word counts into HTML that shows a simple word cloud.

We must use these exact instance variables and make them public, as the WordCloudMaker class will depend on accessing them directly.

We'll need to implement the compareTo method which is used by the WordCount, since it implements the interface Comparable<WordCount>

We should use the instance variable count to do this comparison.

Methods that need to be implemented
    * If the specified word is already in this WordCountMap, then its
    * count is increased by one. Otherwise, the word is added to this map
    * with a count of 1.
    public void incrementCount(String word)

    * Returns an array list of WordCount objects, one per word stored in this
    * WordCountMap, sorted in decreasing order by count.
    public ArrayList<WordCount> getWordCountsByCount()

    * Returns a list of WordCount objects, one per word stored in this
    * WordCountMap, sorted alphabetically by word.
    public ArrayList<WordCount> getWordCountsByWord()

Note that ArrayList should be the built-in Java implementation of a List that uses an array to store the data.

Additional Notes
    WordCountMap must implement a binary search tree based on the alphabetical ordering of the words.
        We must implement this tree structure our selves, and are not allowed to use a built in Java data structure.
    
    None of the words in StopWords.txt should appear on the wordcloud/list/result when StopWords.txt is used. We can implement this in whatever manner, but we should take into account how efficiently we do this.

    At least one of our getWordCountsBy methods in the WordCountMap should use a tree traversal to get the list of words and counts in the correct order.
    For the other method, we can use the built in java methos (such as collections.sort()) if we would like.

    We can look back at the code given in HW1 (LineReader.java) or in section A.10 of your book for more information on command line arguments.

    When reading from a text file, we should strip punctuations from words. One way to do this is to use the String method replaceAll(). This can use a regular expression to and identify and remove all those characters.

    If you want to find and remove any character in a String string that is NOT a letter, you could use the following line of code:

    string = string.replaceAll("[^a-zA-Z]","");

    No changes need to be made to WordCloudMaker.java nor StopWords.txt.

    We can try our word cloud on other documents! Many can be found at [Project Gutenberg](http://www.gutenberg.org/)

    Keep modularity and encapsulation in mind as you design your code. Make sure your comments indicate what the instance variables you choose represent.