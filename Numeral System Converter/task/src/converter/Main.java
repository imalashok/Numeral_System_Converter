package converter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sourceRadix;
        int targetRadix;

        if (scanner.hasNextInt()) {
             sourceRadix = Integer.parseInt(scanner.nextLine());
        } else {
            System.out.println("Error: Source and Target Radix should be in the range [1-36].");
            return;
        }

        if (sourceRadix < 1 || sourceRadix > 36) {
            System.out.println("Error: Source and Target Radix should be in the range [1-36].");
            return;
        }


        String srcNumber = scanner.nextLine().toLowerCase();
        String impossibleRadixChars = "1023456789abcdefghijklmnopqrstuvwxyz".substring(sourceRadix); //should start with '1' to properly handle sourceRadix = 1;
        for (int i = 0, skipDotOnce = 0; i < srcNumber.length(); i++) {
            if (srcNumber.charAt(i) == '.') {
                skipDotOnce++;
            }
            if (impossibleRadixChars.contains(String.valueOf(srcNumber.charAt(i))) || skipDotOnce > 1) {
                System.out.println("Error: Incorrect number format in current source radix");
                return;
            }
        }

        if (scanner.hasNextInt()) {
            targetRadix = scanner.nextInt();
        } else {
            System.out.println("Error: Source and Target Radix should be in the range [1-36].");
            return;
        }

        if (targetRadix < 1 || targetRadix > 36) {
            System.out.println("Error: Source and Target Radix should be in the range [1-36].");
            return;
        }

        String[] sourceNumber = srcNumber.split("\\.");
        convertNumber(sourceRadix, sourceNumber, targetRadix);

    }

    public static void convertNumber (int sourceRadix, String[] sourceNumber, int targetRadix) {
        int decimalInteger;
        double decimalFractional = 0.0;

        if (sourceRadix == 1) {
            decimalInteger = sourceNumber[0].length();
            if (sourceNumber.length > 1) {
                decimalFractional = sourceNumber[1].length();
            }
        } else {
            decimalInteger = Integer.parseInt(sourceNumber[0], sourceRadix);
            if (sourceNumber.length > 1) {
                for (int i = 0, num; i < sourceNumber[1].length(); i++) {
                    if (sourceNumber[1].charAt(i) >= 'a' && sourceNumber[1].charAt(i) <= 'z') {
                        num = sourceNumber[1].charAt(i) - 87;
                    } else {
                        num = sourceNumber[1].charAt(i) - '0';
                    }
                    decimalFractional += (double) num / Math.pow(sourceRadix, i + 1);
                }
            }
        }

        if (targetRadix == 1) {
            for (int i = 0; i < decimalInteger; i++) {
                System.out.print(1);
            }
        } else {
            System.out.print(Long.toString(decimalInteger, targetRadix));
            if (decimalFractional > 0) {
                System.out.print(".");
                for (int i = 0; i < 5; i++) {
                    decimalFractional *= targetRadix;
                    int iPart = (int) Math.floor(decimalFractional);
                    decimalFractional -= iPart;
                    if (iPart > 9) {
                        System.out.print((char) (iPart + 87));
                    } else {
                        System.out.print(iPart);
                    }
                }
            }
        }
    }
}