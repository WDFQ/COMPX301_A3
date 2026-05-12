public class UnpackBits {

    static int bitPosition = 0;

    public static void main(String[] args) {
        boolean isFirstPair = true;

        try {
            byte[] bytes = System.in.readAllBytes();
            int phraseCount = 1;
            

            while (true) { 

                // get the number of bits are needed for the phrase number for the output pair  
                int phraseBits = (int) Math.ceil(Math.log(phraseCount) / Math.log(2));

                // check if theres anymore valid bits to make a pair
                if ((bitPosition + phraseBits + 4) > (bytes.length * 8)){
                    break;
                }
                

                int phraseNum  = readBits(bytes, phraseBits);  
                int charNibble = readBits(bytes, 4);  
                String hexChar = Integer.toHexString(charNibble).toUpperCase();  

                // ensure no comma in front of first pair
                if (!isFirstPair) {
                    System.out.print(",");   
                }
                isFirstPair = false; 

                System.out.print(phraseNum + " " + hexChar);

                phraseCount++;
            }
            
        } catch (Exception e) {
            System.err.println("Error has occurred: " + e.getMessage());
        }

        
    }

    /**
     * reads the specified n bits from byte array and returns them as integer
     * @param bytes the byte array containing bits
     * @param n the number of bits to be read
     * @return bits as an int
     */
    public static int readBits(byte[] bytes, int n) {
        int result = 0;
        for (int i = 0; i < n; i++) {
            int bit = getBit(bytes, bitPosition);
            result = (result << 1) | bit;
            bitPosition++;
        }
        return result;
    }

    /**
     * Returns the bit at the given bit index across the byte array
     * @param bytes     The byte array to read from
     * @param bitPos    The bit index across the entire byte array (0 = leftmost bit of bytes[0])
     * @return          The bit value (0 or 1)
     */
    public static int getBit(byte[] bytes, int bitPos) {    
        // Check for null or empty array
        if (bytes == null || bytes.length == 0) {
            throw new IllegalArgumentException("Byte array is null or empty");
        }

        // Check bitPos is within bounds
        if (bitPos < 0 || bitPos >= bytes.length * 8) {
            throw new IndexOutOfBoundsException("bitPos " + bitPos + " is out of range for array of " + bytes.length + " bytes (" + bytes.length * 8 + " bits)");
        }

        // Which byte in the array
        int byteIndex = bitPos / 8;

        // Which bit within that byte (7 = leftmost, 0 = rightmost)
        int bitIndex = 7 - (bitPos % 8);

        // Shift that bit to position 0 and mask everything else out
        return (bytes[byteIndex] >> bitIndex) & 1;
    }
}
