import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LZ78decode {
    // Create a new dictionary to store all the phrases and store the phrase count
    static Map<Integer, String[]> dictionary = new HashMap<>();
    static int phraseCount = 0;

    public static void main(String[] args) {
        try {
            // Add the first empty phrase into the dictionary
            dictionary.put(phraseCount, new String[2]);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            // Read a pair at a time and deconstruct it
            String line;
            while((line = reader.readLine()) != null){
                // Increment phrase count only when there is a next line
                phraseCount++;

                String[] pair = line.split(",");

                // Add the pair into the dictionary and output the phrase
                dictionary.put(phraseCount, new String[]{pair[0], pair[1]});
                System.out.print(buildPhrase(phraseCount));
            }
        } catch (Exception e) {
            System.err.println("An error has occurred: " + e.getMessage());
        }
    }

    // Helper method that uses a stack to build the phrase
    private static String buildPhrase(int phraseNum){
        // Initialise a stack and a string builder
        Stack<Integer> stack = new Stack<>();
        StringBuilder phrase = new StringBuilder();

        int currentPhraseNum = phraseNum;
        // Keep going until the current phrase is phrase 0, the empty phrase
        while(phraseNum != 0){
            // Add that phrase number to the stack
            stack.push(currentPhraseNum);

            // Check the current phrase num's longest phrase num pattern found in the dictionary
            currentPhraseNum = Integer.parseInt(dictionary.get(currentPhraseNum)[0]);
        }

        // Pop everything from the stack
        while(!stack.isEmpty()){
            String currentChar = dictionary.get(stack.pop())[1];
            phrase.append(currentChar);
        }

        // Return the phrase
        return phrase.toString();
    }
}

