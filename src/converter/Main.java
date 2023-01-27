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
        if (targetBase == 2) {
            System.out.print(number.ConvertToBinary());
        } else if (targetBase == 16) {
            System.out.print(number.ConvertToHexadecimal());
        } else if (targetBase == 8) {
            System.out.print(number.ConvertToOctal());
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

        public String ConvertToHexadecimal() {
            int number = Integer.parseInt(num);
            String str1 = "";
            while (number / 16 >= 15) {
                str1 += HexaWriter(str1, number % 16);
                number = number / 16;
            }
            str1 = HexaWriter(str1, number % 16);
            if (number / 16 != 0) {
                str1 = HexaWriter(str1, number / 16);
            }

            return Reverser(str1);
        }

        public String ConvertToOctal() {
            int number = Integer.parseInt(num);
            String str = "";
            while (number / 2 >= 7) {
                str += number % 8;
                number = number / 8;
            }
            str += number % 8;
            if (number / 8 != 0) {
                str += number / 8;
            }
            return Reverser(str);
        }

        public String ConvertToBinary() {
            int number = Integer.parseInt(num);
            List<Integer> listOfCharacters = new ArrayList<>();
            while (number / 2 >= 1) {
                listOfCharacters.add(number % 2);
                number = number / 2;
            }
            listOfCharacters.add(1);
            String str = "";
            for (Integer variable : listOfCharacters
            ) {
                str += variable;
            }
            return Reverser(str);
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