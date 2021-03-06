import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Karanveer Plaha
 * @version 2.0 11/16/2018
 * @version 1.0 11/26/2017
 */

public class Binary {

    private List<Integer> binaryNumberAsList;

    public Binary(int n) {
        if (n < 0)
            throw new IllegalArgumentException("Integer must be >= 0");
        binaryNumberAsList = new ArrayList<>();
        initialize(n);
    }

    /**
     * This static method can be used get the decimal
     * value of a binary string.
     * @param binaryString String
     * @return integer
     */
    public static int toDecimal(String binaryString) {
        if (binaryString == null || binaryString.isEmpty())
            return 0;

        // reverse the binary string
        binaryString = new StringBuilder(binaryString).reverse().toString();

        // convert binary string to int array
        int[] binaryArray = Stream.of(binaryString.split(""))
                                  .mapToInt(Integer::valueOf)
                                  .toArray();

        int sum = 0;
        for (int i = 0; i < binaryArray.length; i++) {
            sum += binaryArray[i] * ((int) Math.pow(2, i));
        }
        return sum;
    }

    /**
     * This static method can be used to get the decimal
     * value of a Binary object.
     * @param binaryNumber Binary object
     * @return integer
     */
    public static int toDecimal(Binary binaryNumber) {
        // call the string parameter version
        return toDecimal(binaryNumber != null ? binaryNumber.getBinaryString() : "");
    }

    /**
     * This method initializes the binary number list from
     * the given integer n.
     * @param n int
     */
    private void initialize(int n) {
        if (n == 0) {
            push(n);
        }
        else {
            while (n > 0) {
                int remainder = n % 2;
                n = n / 2;
                push(remainder);
            }
        }
    }

    /**
     * This custom push method makes the
     * list field work like a stack.
     * If the list is empty, we add the element.
     * Else we use the parameterized add method with the arguments (0, n)
     * to say "add n at index 0". The list will automatically shift its existing content
     * one index to the right and n will be inserted to the "front"/"top" of the list.
     * @param n int
     */
    private void push(int n) {
        if (binaryNumberAsList.isEmpty()) {
            // Add first element
            binaryNumberAsList.add(n);
        } else {
            // Add n to 0th place and shift existing element to the right
            // This imitates a stack representation where the most recently added element is the first element
            binaryNumberAsList.add(0, n);
        }
    }

    /**
     * Returns the string representation of the binary number which is
     * stored as a list imitating a stack.
     * The method uses a stream where the integer values are mapped to strings, collected and returned.
     * @return binary number as string
     */
    public String getBinaryString() {
        return binaryNumberAsList.stream().map(String::valueOf).collect(Collectors.joining());
    }

    public String getExpression() {
        // create array of [0, 1, 2...n] where n is the highest power of 2 in the binary number
        int[] powerOfTwo = IntStream.range(0, binaryNumberAsList.size()).toArray();

        String[] binaryString = new String[powerOfTwo.length];

        int j = 0;
        for (int i = binaryString.length-1; i >= 0; i--) {
            if (i == binaryString.length-1) {
                binaryString[i] = String.format("%d X 2^%d = %s", binaryNumberAsList.get(i), powerOfTwo[j], getBinaryString());
            } else
                binaryString[i] = String.format("%d X 2^%d", binaryNumberAsList.get(i), powerOfTwo[j]);
            j++;
        }

        return String.join(" + ",binaryString);
    }

    public int getConsecutive1s (){
        int count = 0;
        int maxCount = 0;

        for (Integer i : binaryNumberAsList) {
            if (count == 0 && i == 1) {
                count++;
                if (maxCount == 0)
                    maxCount = 1;
            } else if (count > 0 && i == 1) {
                count++;
                if (count > maxCount)
                    maxCount = count;
            } else if (i == 0) {
                count = 0;
            }
        }
        return maxCount;
    }
}
