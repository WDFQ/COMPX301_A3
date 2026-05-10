import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PackBits {
    final static int BASE = 2;

    // Keep track of the max phrase found in dictionary - tells you max bits we need to encode phrase num
    // This includes Phrase 0 (root) as a phrase
    int phraseCount = 1;

    // Create a bit buffer and its counter
    int buffer;
    int bitsInBuffer = 0;

    public static void main(String[] args) {
        try {
            // Read from the input
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
                    String[] pair = line.toString().trim().split(" ");

                    // Find the number of bits we use for the phrase number by rounding log base 2 K
                    int phraseBits = (int)Math.ceil(Math.log(Integer.parseInt(pair[0])) / Math.log(BASE));

                }
            }   
            
        } catch (Exception e) {
            System.err.println("An error has occurred: " + e.getMessage());
        }
    }

    /**
     * Writes bits into the buffer and flushes if its full
     * @param value Value to the written
     * @param numBits Number of bits to use 
     */
    public void writeBits(int value, int numBits){
        // Shift the buffer to the left by numBits times - creates space on the right side of the buffer for how many the value needs with zeroes
        buffer = buffer << numBits;

        // Puts the value onto the right side where space was created - does so by using OR to allow the value to pass through
        buffer = buffer | value;

        // Increment the buffer count
        bitsInBuffer += numBits;

        // Check if there's enough bits to flush out a byte - if so, then flush out a byte
        while (numBits >= 8){
            // Shift the buffer to the right - this grabs the leftmost 8 bits (might be padded with 0s to the left) and removes the irrelevant bitsInBuffer - 8 bits
            int byteToFlush = buffer >> bitsInBuffer - 8;

            // Removes the leftpadded 0s - makes sure we only get 8 bits
            byteToFlush = byteToFlush & 0xFF;

            // Flush out and decrement counter
            System.out.print(byteToFlush);
            bitsInBuffer -= 8;

            // Clean up buffer
            //buffer = buffer 
        }

    }
}

