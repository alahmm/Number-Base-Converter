package converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter two numbers in format: {source base} {target base} (To quit type /exit) >");
            String baseSource = scanner.next();
            if (baseSource.equals("/exit")) {
                return;
            } else {
                String baseTarget = scanner.next();
                while (true) {
                    System.out.printf("Enter number in base %s to convert to base %s (To go back type /back) >", baseSource, baseTarget);
                    String input2 = scanner.next();
                    if (!input2.equals("/back")) {
                        if (input2.contains(".")) {
                            String[] list = input2.split("\\.");
                            Decimal number = new Decimal(list[0],Integer.parseInt(baseSource));
                            Decimal number2 = new Decimal(number.ConverterToDecimal().toString(), Integer.parseInt(baseTarget));
                            String resultInt = number2.ConvertFromDecimal();
                            /**converting the fractional part
                             *
                             */
                            Decimal fractionalNumber = new Decimal(list[1], Integer.parseInt(baseSource));
                            Decimal fractionalNumber2 = new Decimal(fractionalNumber.FractionalConverterToDecimal().toString(), Integer.parseInt(baseTarget));
                            String resultFraction = fractionalNumber2.ConvertFractionFromDecimal();
                            System.out.printf("Conversion result: %s%n%n", resultInt + resultFraction);
                        } else {
                            Decimal number = new Decimal(input2, Integer.parseInt(baseSource));
                            Decimal number2 = new Decimal(number.ConverterToDecimal().toString(), Integer.parseInt(baseTarget));
                            System.out.printf("Conversion result: %s%n%n", number2.ConvertFromDecimal());
                        }

                    } else {
                        break;
                    }
                }
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

        public String HexaWriter(String str1, BigInteger number) {
            if (number.compareTo(BigInteger.TEN.subtract(BigInteger.ONE)) <= 0) {
                str1 += number;
            } else {
                char var = (char) (number.intValueExact() + 55);
                str1 += var;
            }
            return str1;
        }

        public String ConvertFromDecimal() {
            BigInteger number = new BigInteger(num);
            String str1 = "";
            while (number.divide(BigInteger.valueOf(targetBase)).compareTo(BigInteger.valueOf(targetBase).subtract(BigInteger.valueOf(1))) >= 0) {
                str1 = HexaWriter(str1, number.remainder(BigInteger.valueOf(targetBase)));
                number = number.divide(BigInteger.valueOf(targetBase));
            }
            str1 = HexaWriter(str1, number.remainder(BigInteger.valueOf(targetBase)));
            if (!number.divide(BigInteger.valueOf(targetBase)).equals(BigInteger.ZERO)) {
                str1 = HexaWriter(str1, number.divide(BigInteger.valueOf(targetBase)));
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
        public BigInteger ConverterToDecimal() {
            BigInteger sum = new BigInteger("0");
            String regex = "[0-9]";
            String str = "";
            String str2 = "";
            for (int i = num.length() - 1; i >= 0; i--) {
                str += num.charAt(i);
                if (str.matches(regex)) {
                    int var = num.charAt(i) - 48;
                    long v = (long) (var * Math.pow(targetBase, num.length() - 1 - i));
                    sum = sum.add(BigInteger.valueOf(v));
                    str = "";
                } else {
                    str2 = str.toUpperCase();
                    for (int j = 0; j < str.length(); j++) {
                        int var2 = str2.charAt(j) - 55;
                        long v = (long) (var2 * Math.pow(targetBase, num.length() - 1 - i));
                        sum = sum.add(BigInteger.valueOf(v));
                        str = "";
                    }
                }
            }
            return sum;
        }
         /**
          * fractional part
          */
         public String ConvertFractionFromDecimal() {
             BigDecimal number = new BigDecimal(num);
             String str1 = "";
             while (!number.equals(BigDecimal.ZERO) && str1.length() < 5) {
                 number = number.multiply(BigDecimal.valueOf(targetBase)).setScale(5, RoundingMode.CEILING);
                 if (number.compareTo(BigDecimal.ONE) >= 0) {
                     str1 = HexaWriter(str1, number.toBigInteger());
                     number = number.subtract(new BigDecimal(number.toBigInteger()));
                 } else {
                     str1 = HexaWriter(str1, number.toBigInteger());
                 }
             }
             return "." + str1;
         }
         public BigDecimal FractionalConverterToDecimal() {
             BigDecimal sum = new BigDecimal("0");
             String regex = "[0-9]";
             String str = "";
             String str2 = "";
             for (int i = 0; i < num.length() ; i++) {
                 str += num.charAt(i);
                 if (str.matches(regex)) {
                     int var = num.charAt(i) - 48;
                     double v = var * Math.pow(targetBase, - i - 1);
                     sum = sum.add(BigDecimal.valueOf(v));
                     str = "";
                 } else {
                     str2 = str.toUpperCase();
                     for (int j = 0; j < str.length(); j++) {
                         int var2 = str2.charAt(j) - 55;
                         double v = var2 * Math.pow(targetBase, - i - 1);
                         sum = sum.add(BigDecimal.valueOf(v));
                         str = "";
                     }
                 }
             }
             return sum;
         }
    }
}