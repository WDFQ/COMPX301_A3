import java.util.ArrayList;
import java.util.HexFormat;

public class HexToByte {
    public static void main(String[] args) {
        try {
            // Store the input characters in groups of two
            ArrayList<Character> charList = new ArrayList<>();

            // Read the input char by char
            int code;
            while ((code = System.in.read()) != -1) {
                // Convert the code into char
                char character = (char)code;

                charList.add(character);

                // Convert the char into a letter, output to terminal, then reset
                if (charList.size() == 2){
                    StringBuilder builder = new StringBuilder();
                    builder.append(charList.get(0));
                    builder.append(charList.get(1));
                    byte[] bytes = HexFormat.of().parseHex(builder.toString());

                    System.out.write(bytes);
                    System.out.flush();

                    charList.clear();
                }
            }
        } catch (Exception e) {
            System.err.println("An error has occurred: " + e);
        }
    }
}
