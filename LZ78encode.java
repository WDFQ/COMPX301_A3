import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LZ78encode {
    public static void main (String[] args){
        // create root node
        TrieNode root = new TrieNode(0);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String line = reader.readLine();    

            // loop through entire hex line
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);

                // check if root node has char
                int charIndex = Integer.parseInt(String.valueOf(c), 16);

                // if root does not contain current char
                if (root.hexChildren[charIndex] ==  null){
                    

                    
                }
                else {

                    // enter the current char ...
                    while () {
                        
                    }
                }

            }

        } catch (Exception e) {
            // TODO: handle exception
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
