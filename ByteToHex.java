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
