package converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Do you want to convert /from decimal or /to decimal? (To quit type /exit)");
            String input = scanner.next();
            if (input.equals("/from")) {
                System.out.print("Enter a number in decimal system:");
                String input2 = scanner.next();
                System.out.print("Enter target base:");
                int targetBase = scanner.nextInt();
                Decimal number = new Decimal(input2, targetBase);
                if (targetBase == 2 || targetBase == 8 || targetBase == 16) {
                    System.out.printf("%nConversion result: %s%n%n", number.ConvertFromDecimal());
                }
            } else if (input.equals("/to")) {
                System.out.print("Enter source number:");
                String input2 = scanner.next();
                System.out.print("Enter source base:");
                int targetBase = scanner.nextInt();
                Decimal number = new Decimal(input2, targetBase);
                if (targetBase == 2 || targetBase == 8 || targetBase == 16) {
                    System.out.printf("%nConversion to decimal result: %s%n%n", number.ConverterToDecimal());
                }
            } else if (input.equals("/exit")) {
                return;
            }
        }
    }

     static class Decimal {
        private String num;
        private int targetBase;

        public void setNum(String num) {
            this.num = num;
        }

        public void setTargetBase(int targetBase) {
            this.targetBase = targetBase;
        }

        public Decimal(String num, int targetBase) {
            this.num = num;
            this.targetBase = targetBase;
        }

        public String HexaWriter(String str1, int number) {
            if (number <= 9) {
                str1 += number;
            } else {
                char var = (char) (number + 55);
                str1 += var;
            }
            return str1;
        }

        public String ConvertFromDecimal() {
            int number = Integer.parseInt(num);
            String str1 = "";
            while (number / targetBase >= targetBase - 1) {
                str1 = HexaWriter(str1, number % targetBase);
                number = number / targetBase;
            }
            str1 = HexaWriter(str1, number % targetBase);
            if (number / targetBase != 0) {
                str1 = HexaWriter(str1, number / targetBase);
            }

            return Reverser(str1);
        }
        public String Reverser(String str) {
            char[] listOfChar = str.toCharArray();
            List<Character> array = new ArrayList<>();
            for (int i = 0; i < listOfChar.length; i++) {
                array.add(listOfChar[listOfChar.length - 1 - i]);
            }
            for (int i = 0; i < listOfChar.length; i++) {
                listOfChar[i] = array.get(i);
            }
            return String.valueOf(listOfChar);
        }
        public int ConverterToDecimal() {
            int sum = 0;
            String regex = "[0-9]";
            String str = "";
            String str2 = "";
            for (int i = num.length() - 1; i >= 0; i--) {
                str += num.charAt(i);
                if (str.matches(regex)) {
                    int var = num.charAt(i) - 48;
                    sum += var * Math.pow(targetBase, num.length() - 1 - i);
                    str = "";
                } else {
                    str2 = str.toUpperCase();
                    for (int j = 0; j < str.length(); j++) {
                        int var2 = str2.charAt(j) - 55;
                        sum += var2 * Math.pow(targetBase, num.length() - 1 - i);
                        str = "";
                    }
                }
            }
            return sum;
        }
    }
}