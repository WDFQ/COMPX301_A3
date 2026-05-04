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
            phraseCount++;

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            // Read a pair at a time and deconstruct it
            int code;
            StringBuilder line = new StringBuilder();

            while((code = reader.read()) != -1){
                // Convert the character read into an actual character (number -> char)
                char character = (char) code;

                // Once you reach a comma, that's the end of the pair
                if(character != ','){
                    // Append the character to the line
                    line.append(character);
                }
                else{
                    // // Split the pair by the space 
                    // String pair[] = line.toString().trim().split(" ");

                    // // Add the pair into the dictionary and output the phrase
                    // dictionary.put(phraseCount, new String[]{pair[0], pair[1]});
                    // System.out.print(buildPhrase(phraseCount));

                    // // Increment phrase count only when there is a next line
                    // phraseCount++;

                    String[] pair = line.toString().trim().split(" ");

                    if (pair.length < 2) {
                        // Mid-traversal leftover: just a phrase reference, no new character
                        // Output the phrase it refers to (already in dictionary)
                        System.out.print(buildPhrase(Integer.parseInt(pair[0])));
                    } else {
                        dictionary.put(phraseCount, new String[]{pair[0], pair[1]});
                        System.out.print(buildPhrase(phraseCount));
                        phraseCount++;
                    }

                    line.setLength(0); // don't forget to reset
                    
                }
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
        while(currentPhraseNum != 0){
            // Add that phrase number to the stack
            stack.push(currentPhraseNum);

            // Check the current phrase num's longest phrase num pattern found in the dictionary
            String nextPhraseNum = dictionary.get(currentPhraseNum)[0];
            if (nextPhraseNum == null){
                break;
            }
            
            currentPhraseNum = Integer.parseInt(nextPhraseNum);
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

