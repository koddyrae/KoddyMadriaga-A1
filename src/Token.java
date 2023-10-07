/**
 * Class to define data structure for the project
 */
public class Token implements Comparable<Token>{

    private int frequency;
    private String word;

    /**
     * Constructor if word's frequency has not been counted
     * @param word the word being stored
     */
    public Token(String word) {
        this.word = word;
        frequency = 1;
    }

    /**
     * Constructor for word and frequency
     * @param word the word
     * @param frequency the frequency of the word in the file
     */
    public Token(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    /**
     * Getter for frequency
     * @return
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * Method to increase the frequency
     */
    public void increaseFrequency() {
        frequency++;
    }

    /**
     * Getter for the current object
     * @return the word contained in the calling object
     */
    public String getWord() {
        return word;
    }

    /**
     * Method to return the object as a string
     * @return object the object in string format
     */
    public String toString() {
        return (this.word + ": " + this.frequency);
    }

    /**
     * Method to compare the object with another object
     * @param o the object to be compared.
     * @return number a negative, positive, or zero depending on its conditions
     */
    @Override
    public int compareTo(Token o) {
        //Comparison of token's frequency (least to most)
        int wordResult = this.word.compareTo(o.getWord());
        if (wordResult != 0) {
            return wordResult;
        }
        return Integer.compare(this.frequency, o.getFrequency());
    }

    /**
     * Method to check if the objects are equal
     * @param o the object it is being compared to
     * @return boolean true if equals, false otherwise
     */
    public boolean equals(Token o) {
        return this.equals(o);
    }
}
