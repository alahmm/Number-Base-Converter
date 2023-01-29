package decimalnumber;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

class Main {
/*    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input1 = scanner.next();
        int input2 = scanner.nextInt();
        BigDecimal decimal = new BigDecimal(input1);
        System.out.println(decimal.setScale(input2, RoundingMode.CEILING));    }
}*/
/*public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String input1 = scanner.next();
    String input2 = scanner.next();
    String input3 = scanner.next();

    BigDecimal decimal = new BigDecimal(input1);
    BigDecimal decimal2 = new BigDecimal(input2);
    BigDecimal decimal3 = new BigDecimal(input3);

    System.out.println(decimal.add(decimal2).add(decimal3));    }
}*/
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int power = scanner.nextInt();
    int scale = scanner.nextInt();
    String input3 = scanner.next();
    BigDecimal decimal3 = new BigDecimal(input3);
    System.out.println(decimal3.setScale(scale, RoundingMode.FLOOR).pow(power));
  }
}
