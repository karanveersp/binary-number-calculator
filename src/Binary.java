import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    private void push(int n) {
        if (binaryNumberAsList.isEmpty()) {
            // Add first element
            binaryNumberAsList.add(n);
        } else {
            // Add new element to 0th place and shift existing element to the right
            // This imitates a stack representation where the most recent element added is the first
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

    public int getConsecutive1s() {
        return consecutive1s();
    }

    private int consecutive1s (){
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
