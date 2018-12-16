import java.util.Scanner;

public class HexadecimalTest {
    public static void main(String[] args) {
        String a = "0111000";
        String b = "0111001";
        String c = "0100101000000001";
        String d = "01001110";

        Hexadecimal hexA = new Hexadecimal(a);
        System.out.printf("%s : %s%n", a, hexA.getHexString());

        Hexadecimal hexB = new Hexadecimal(b);
        System.out.printf("%s : %s%n", b, hexB.getHexString());

        Hexadecimal hexC = new Hexadecimal(c);
        System.out.printf("%s : %s%n", c, hexC.getHexString());

        Hexadecimal hexD = new Hexadecimal(d);
        System.out.printf("%s : %s%n", d, hexD.getHexString());


        // Create binary from 13, then get that binary's hexadecimal value
        System.out.println("Int 13 : " + Hexadecimal.getHexadecimalValue(new Binary(13).getBinaryString()));

        Scanner sc = new Scanner(System.in);
        String binary;

        do {
            System.out.print("Enter a binary string to convert to Hex (empty string to exit): ");
            binary = sc.nextLine();
            if (binary.isEmpty())
                continue;

            Hexadecimal num = new Hexadecimal(binary);

            System.out.printf("%s as a hexadecimal number is: %s%n", binary, num.getHexString());
        } while (!binary.isEmpty());




    }
}
