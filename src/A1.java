import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Main class to run and test the application
 */
public class A1 {
    public static void main(String[] args) {
        ArrayList<String> wordList = new ArrayList<>();
        ArrayList<Token> listOfTokens = new ArrayList<>();
        int totalWordCount = 0;

        try {
            Scanner input = new Scanner(System.in);
            File filePath = new File(input.next());
            Scanner fileReader = new Scanner(filePath);


            // Reads words off file and parses them from digits and symbols
            while (fileReader.hasNext()) {
                String line = fileReader.nextLine();
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (!word.isEmpty() && word.matches(".*[a-zA-Z].*")) {
                        wordList.add(parseWords(word.toLowerCase()));
                        totalWordCount++;
                    }
                }
            }

            int stopWordCount = checkStopWords(wordList);
            listOfTokens = checkDuplicates(wordList);

            System.out.println("Total Words: " + totalWordCount);
            System.out.println("Unique Words: " + listOfTokens.size());
            System.out.println("Stop Words: " + stopWordCount);


            writeMostList(listOfTokens);
            writeLeastList(listOfTokens);
            writeList(listOfTokens);

        } catch (IOException e) {
            System.out.print("An IO Exception has occurred");
        }
    }

    /**
     * Method to parse the words of digits and symbols to just be chars
     *
     * @param word the word currently being parsed
     * @return result the completed parsed word
     */
    private static String parseWords(String word) {
        return word.replaceAll("[\\d\\W]", "");
    }

    /**
     * Method to check for stop words in the list of words from the file
     *
     * @param wordList list of words split from the file
     * @return stopWordCount the count of stop words in the arraylist that were taken out
     */
    private static int checkStopWords(ArrayList<String> wordList) {
        String[] stopWords = {"a", "about", "all", "am", "an", "and", "any", "are", "as", "at", "be", "been", "but",
                "by", "can", "cannot", "could", "did", "do", "does", "else", "for", "from", "get", "got", "had", "has",
                "have", "he", "her", "hers", "him", "how", "i", "if", "in", "into", "is", "it", "its", "like", "more",
                "me", "my", "no", "now", "not", "of", "on", "one", "or", "our", "out", "said", "say", "says", "she",
                "so", "some", "that", "the", "their", "them", "then", "there", "these", "they", "this", "to", "too",
                "us", "upon", "was", "we", "were", "what", "with", "when", "where", "which", "while", "who", "whom",
                "why", "will", "you", "your"
        };
        int stopWordCount = 0;

        for (String stopWord : stopWords) {
            for (int j = 0; j < wordList.size(); j++) {
                if (wordList.get(j).equals(stopWord)) {
                    wordList.remove(j);
                    stopWordCount++;
                }
            }
        }
        return stopWordCount;
    }

    /**
     * Method to check for duplicates in the parsed arraylist and puts them into the final arraylist of tokens
     *
     * @param wordList list of parsed words
     * @return listOfTokens a list of tokens for the final prints of the program
     */
    private static ArrayList<Token> checkDuplicates(ArrayList<String> wordList) {
        HashMap<String, Integer> map = new HashMap<>();
        ArrayList<Token> listOfTokens = new ArrayList<>();

        for (String words : wordList) {
            if (map.containsKey(words)) {
                map.put(words, map.get(words) + 1);
            } else {
                map.put(words, 1);
            }
        }

        for (String dupes : map.keySet()) {
            listOfTokens.add(new Token(dupes, map.get(dupes)));
        }
        return listOfTokens;

    }

    /**
     * Method to write the list in order of most to least frequency with alphabet as secondary ordering
     *
     * @param listOfTokens list of tokens of words and their frequency
     */
    private static void writeMostList(ArrayList<Token> listOfTokens) {
        int loop = Math.min(10, listOfTokens.size());

        System.out.println();
        System.out.println("10 Most Frequent");
        Collections.sort(listOfTokens, new CompMostToken());
        for (int i = 0; i < loop; i++) {
            System.out.println(listOfTokens.get(i).toString());
        }
    }

    /**
     * Method to write the list in order of least to most frequency with alphabet as secondary ordering
     *
     * @param listOfTokens list of tokens of words and their frequency
     */
    private static void writeLeastList(ArrayList<Token> listOfTokens) {
        int loop = Math.min(10, listOfTokens.size());

        System.out.println();
        System.out.println("10 Least Frequent");
        Collections.sort(listOfTokens, new CompLeastToken());
        for (int i = 0; i < loop; i++) {
            System.out.println(listOfTokens.get(i).toString());
        }
    }

    /**
     * Method to write the list in alphabetic order regardless of frequency
     *
     * @param listOfTokens list of tokens of words and their frequency
     */
    private static void writeList(ArrayList<Token> listOfTokens) {
        System.out.println();
        Collections.sort(listOfTokens);
        System.out.println("All");
        for (Token token : listOfTokens) {
            System.out.println(token.toString());
        }
    }

}