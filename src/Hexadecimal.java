import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Karanveer Plaha
 * @version 1.0 12/16/2018
 */

public class Hexadecimal {
    private List<String> hexNumber;

    public Hexadecimal(String binary) {
        hexNumber = new ArrayList<>();
        initialize(binary);
    }

    /**
     * Static method that returns a string
     * representation of a given binary number
     * string.
     * @param binary String
     * @return String
     */
    public static String getHexadecimalValue(String binary) {
        return new Hexadecimal(binary).getHexString();
    }

    private void initialize(String binary) {
        binary = reversed(binary);
        int n = binary.length();
        List<String> segmentsOf4 = new ArrayList<>();
        for (int i = 0; i < n; i += 4) {
            if (i + 4 < n) {
                String fourBits = binary.substring(i, i+4);
                // have to reverse it to get the actual binary
                segmentsOf4.add(reversed(fourBits));
            }
            else {
                String someBits = binary.substring(i); // i to end
                segmentsOf4.add(reversed(someBits));
            }
        }

        for (String segment: segmentsOf4) {
            int bValue = Binary.toDecimal(segment);

            if (bValue >= 10) {
                // its a letter value
                switch(bValue) {
                    case 10:
                        push("A");
                        break;
                    case 11:
                        push("B");
                        break;
                    case 12:
                        push("C");
                        break;
                    case 13:
                        push("D");
                        break;
                    case 14:
                        push("E");
                        break;
                    case 15:
                        push("F");
                        break;
                }
            }
            else
                push(String.valueOf(bValue));
        }
    }

    /**
     * Method that returns the reverse of a given string
     * @param s String to reverse
     * @return String
     */
    private String reversed(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    private void push(String n) {
        if (hexNumber.isEmpty()) {
            hexNumber.add(n);
        }
        else {
            hexNumber.add(0, String.valueOf(n));
        }
    }

    /**
     * Get the string representation of the hexadecimal number
     * @return String
     */
    public String getHexString() {
        return hexNumber.stream().collect(Collectors.joining());
    }

}
