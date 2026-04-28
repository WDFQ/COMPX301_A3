import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HexFormat;

public class HexToByte {
    public static void main(String[] args) {
        try {
            // Read from the input
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            // Store the input characters in groups of two
            ArrayList<Character> charList = new ArrayList<>();

            // Read the input char by char
            int code;
            while ((code = reader.read()) != -1) {
                // Convert the code into char
                char character = (char)code;

                charList.add(character);

                // Convert the char into a letter, output to terminal, then reset
                if (charList.size() == 2){
                    StringBuilder builder = new StringBuilder();
                    builder.append(charList.get(0));
                    builder.append(charList.get(1));
                    byte[] bytes = HexFormat.of().parseHex(builder.toString());

                    String asciiValue = new String(bytes, StandardCharsets.US_ASCII);
                    System.out.print(asciiValue);

                    charList.clear();
                }
            }
        } catch (Exception e) {
            System.err.println("An error has occurred: " + e);
        }
    }
}
