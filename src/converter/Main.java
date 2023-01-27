package converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter number in decimal system:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        System.out.println("Enter target base:");
        int targetBase = scanner.nextInt();
        Decimal number = new Decimal(input, targetBase);
        System.out.print("Conversion result: ");
        if (targetBase == 2 || targetBase == 8 || targetBase == 16) {
            System.out.print(number.Convert());
        }
    }

    static class Decimal {
        private String num;

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        private int targetBase;

        public int getTargetBase() {
            return targetBase;
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

        public String Convert() {
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
    }
}