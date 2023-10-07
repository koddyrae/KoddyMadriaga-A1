import java.util.Comparator;

/**
 * Comparator class to compare tokens based on greatest to least frequency and alphabetically
 */
public class CompMostToken implements Comparator<Token> {
    /**
     * Method to compare two objects based on frequency and its word (alphabetically)
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return result the result would be positive, negative, or zero depending on conditions set in the method
     */
    @Override
    public int compare(Token o1, Token o2) {

        int frequencyResult = (o2.getFrequency() - o1.getFrequency());
        if (frequencyResult != 0) {
            return frequencyResult;
        }

        return (o1.getWord().compareTo(o2.getWord()));

    }

}