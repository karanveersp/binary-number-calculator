import java.util.Scanner;

public class BinaryTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;

        do {
            System.out.print("Enter a number to convert to binary (-1 to exit): ");
            n = scanner.nextInt();
            if (n < 0)
                continue;

            Binary binary = new Binary(n);

            System.out.printf("%d as a binary number is: %s%n", n, binary.getBinaryString());
            System.out.printf("Consecutive 1s: %d%n", binary.getConsecutive1s());
            System.out.printf("%s%n%n", binary.getExpression());

        } while (n >= 0);
    }
}
