import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class PackBits {
    final static int BASE = 2;

    // Keep track of the max phrase found in dictionary - tells you max bits we need to encode phrase num
    // This includes Phrase 0 (root) as a phrase
    static int phraseCount = 1;

    // Create a bit buffer and its counter
    static int buffer;
    static int bitsInBuffer = 0;

    // Wrap out to make writing bytes faster
    static OutputStream out = new java.io.BufferedOutputStream(System.out);

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

                // Not end of pair, keep reading and appending
                if(character != ','){
                    // Append the character to the line
                    line.append(character);
                }
                // End of pair
                else{
                    String[] pair = line.toString().trim().split(" ");
                    int pairPhrase = Integer.parseInt(pair[0]);
                    int pairChar = Integer.parseInt(pair[1],  16);

                    // Find the number of bits we use for the phrase number by rounding log base 2 K
                    int phraseBits = (int)Math.ceil(Math.log(phraseCount) / Math.log(BASE));

                    // Output the phrase number, represented by phrase bits and the character, represented by a nibble (4 bits)
                    writeBits(pairPhrase, phraseBits);
                    writeBits(pairChar, 4);

                    // Increment phrase count and reset line stringbuilder
                    phraseCount++;
                    line.setLength(0);
                }
            }

            // Keep track of whether or not there was a trailing character
            boolean lastChar = false;

            // Process the last line
            if(line.length() > 0){
                String[] pair = line.toString().trim().split(" ");

                // Handle all the phrase output
                int pairPhrase = Integer.parseInt(pair[0]);
                int phraseBits = (int)Math.ceil(Math.log(phraseCount) / Math.log(BASE));
                writeBits(pairPhrase, phraseBits);

                // Check if last pair has a trailing character - only output it if so
                if (pair.length > 1 && !pair[1].isEmpty()){
                    int pairChar = Integer.parseInt(pair[1],  16);
                    writeBits(pairChar, 4);
                    lastChar = true;
                }

                // Increment phrase count and reset line stringbuilder
                phraseCount++;
                line.setLength(0);

            }

            int markerByte = 0x00;

            if (bitsInBuffer > 0){
                buffer <<= (8 - bitsInBuffer);
                buffer &= 0xFF;
                out.write(buffer);

                markerByte = 1 << (8 - bitsInBuffer - 1);
            }

            out.write(markerByte);

            // Flush everything
            out.flush();
            
        } 
        catch (Exception e) {
            System.err.println("An error has occurred: " + e.getMessage());
        }
    }

    /**
     * Writes bits into the buffer and flushes if its full
     * @param value Value to the written
     * @param numBits Number of bits to use 
     */
    public static void writeBits(int value, int numBits){
        try{
            // Shift the buffer to the left by numBits times - creates space on the right side of the buffer for how many the value needs with zeroes
            buffer = buffer << numBits;

            // Puts the value onto the right side where space was created - does so by using OR to allow the value to pass through
            buffer = buffer | value;

            // Increment the buffer count
            bitsInBuffer += numBits;

            // Check if there's enough bits to flush out a byte in the buffer - if so, then flush out a byte
            while (bitsInBuffer >= 8){
                // Shift the buffer to the right - this grabs the leftmost 8 bits (might be padded with 0s to the left) and removes the irrelevant bitsInBuffer - 8 bits
                int byteToFlush = buffer >> bitsInBuffer - 8;

                // Removes the leftpadded 0s - makes sure we only get 8 bits
                byteToFlush = byteToFlush & 0xFF;

                // Flush out and decrement counter
                out.write(byteToFlush);
                bitsInBuffer -= 8;

                // Clean up buffer by creating a mask of bitsInBuffer number of 1's
                int bitMask = (1 << bitsInBuffer) -1;

                // Only allows the bits in buffer to pass through 
                buffer = buffer & bitMask;
            }
        }
        catch(Exception e){
            System.err.println("An error has occurred: " + e.getMessage());
        }
    }
}

