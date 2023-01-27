package bignumber;

import java.math.BigInteger;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final String[] parts = scanner.nextLine().split("\\s+");
        BigInteger a = new BigInteger(parts[0]);
        BigInteger b = new BigInteger(parts[1]);
        BigInteger c = new BigInteger(parts[2]);
        BigInteger d = new BigInteger(parts[3]);
        BigInteger l = a.negate().multiply(b).add(c).subtract(d);
        System.out.println(l);



    }
}
