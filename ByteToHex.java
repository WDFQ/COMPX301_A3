/**
 * Converts byte into hex.
 *
 * @author Jeff Jia 1641408
 * @lastModified 16/05/2026
 */

public class ByteToHex {
    public static void main(String[] args) {
        try {
            int b;

            // check if input file is finished
            while ((b = System.in.read()) != -1) {
                // print result
                System.out.printf("%02X", b);
            }
        } catch (Exception e) {
            System.err.println("An exception occurred: " + e);
        }
    }
}
