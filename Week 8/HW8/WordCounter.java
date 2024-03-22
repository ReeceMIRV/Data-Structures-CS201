/*
 * This is our main class
 * @Authors Grace Hanson, Mauricio I. Reyes Villanueva
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordCounter {
    // Scan the file and return it as a wordCountMap
    public static WordCountMap mapFile(String fileName) throws IOException {
        // Run the WordCounter method for the file (argument 1) in the input
        // Create my objects
        File file = new File(fileName); 
        Scanner scanner = new Scanner(file);
        WordCountMap wordCountMap = new WordCountMap();

        // Get the stopWords file as a string
        String stopWords = Files.readString(Path.of("StopWords.txt"));
        stopWords = stopWords.toLowerCase(); // Change to lowercase to ignore case sensitivity
        
        // Read through the file one word at a time
        while (scanner.hasNext()) {
            String nextWord = scanner.next();

            // Format the word into something we can cleanly compare and use
            nextWord = nextWord.toLowerCase();
            nextWord = nextWord.replaceAll("[^a-zA-Z]","");
            
            // Increment the count / add the word to the tree, if the word is not a stop word
            // It's also nice to note that if nextWord is empty, then it won't add an empty string to the cloud
            // Because technically stopWords doesn't contain ""
            if (!stopWords.contains(nextWord)) {
                wordCountMap.incrementCount(nextWord);
            }
        }
        
        scanner.close();

        return wordCountMap;
    }
    public static void main(String[] args) throws IOException {
        // Throw an exception if the file (when using the scanner) is not found or if the path is not found        
        String fileName = args[1];
        WordCountMap wordCountMap = mapFile(fileName);

        // If the first argument asks for alphabetical order then
        if (args[0].equalsIgnoreCase("alphabetical")) {
            wordCountMap.printBinaryTree();
        }

        // If the first argument asks for frequency order (order by word count) then
        // the frequency is displayed in decreasing order
        else if (args[0].equalsIgnoreCase("frequency")) {
            wordCountMap.printWordsByCount();
        }

        // If the first argument asks for a word cloud then
        else if (args[0].equalsIgnoreCase("cloud")) {
            // Format Names for the WordCloud
            String wordCloudName = fileName + " Word Cloud";
            String htmlFileName = fileName.replace(".txt", ".html");

            // Create WordCloud based on inputs
            WordCloudMaker wordCloud = new WordCloudMaker();
            ArrayList<WordCount> wordCounts = wordCountMap.getWordCountsByCount();

            int maxNumOfWords = Integer.parseInt(args[2]);
            if (maxNumOfWords > wordCounts.size()) maxNumOfWords = wordCounts.size();
            List<WordCount> subList = wordCounts.subList(0, maxNumOfWords);

            wordCloud.createWordCloudHTML(wordCloudName, subList, htmlFileName);
        }

    }
}