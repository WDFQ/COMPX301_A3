import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LZ78encode {
    public static void main (String[] args){
        // create root node
        int currentPhraseNum = 1;
        boolean isFirstPair = true;
        TrieNode root = new TrieNode(currentPhraseNum);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String line = reader.readLine();    
            TrieNode currentNode = root;

            // loop through entire hex line
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);

                // get the index of the current char in respect to the children list  
                int charIndex = Integer.parseInt(String.valueOf(c), 16);

                // if current does not contain current char
                if (currentNode.hexChildren[charIndex] ==  null){
 
                    // ensure no comma in front of first pair
                    if (!isFirstPair) {
                        System.out.print(",");   
                    }
                    isFirstPair = false; 

                    // output the pair
                    System.out.print(currentNode.phraseNum + " " + c);

                    // create and put node in its spot in the current node's list
                    TrieNode newNode = new TrieNode(currentPhraseNum);
                    currentNode.hexChildren[charIndex] = newNode;
                    currentPhraseNum++;

                    // reset back to root
                    currentNode = root;
                }
                else {
                    // if it exists move into it
                    currentNode = currentNode.hexChildren[charIndex];
                }
            }

            // check if we were mid traversal when the loop ends
            if (currentNode != root) {
                // just output the phrase number that it ends on
                System.out.print(currentNode.phraseNum + " ");
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    static class TrieNode {
        int phraseNum;
        TrieNode[] hexChildren = new TrieNode[16];

        TrieNode(int phraseNum){
            this.phraseNum = phraseNum;
        }
    }
}
