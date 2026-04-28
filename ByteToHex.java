public class ByteToHex {
    public static void main(String[] args) {
        try {
            byte[] byteArray = System.in.readAllBytes();   
            
            String hex = "";

            // Iterating through each byte in the array
            for (byte i : byteArray) {
                hex += String.format("%02X", i);
            }

            System.out.print(hex);
        } catch (Exception e) {
            System.err.println("An exception occured: " + e);
        }
    }
}