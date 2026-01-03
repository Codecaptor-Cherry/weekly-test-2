package baekjoon;

import java.io.IOException;
import java.util.Scanner;

public class BJ_13268_셔틀런 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int distance = sc.nextInt();
        int n = distance % 100;

        if (n == 0 || n == 10 || n == 30 || n == 60 || n == 100) {
            System.out.print("0");
        } else if ((0 < n && n < 10) ||
                (10 < n && n <= 15) ||
                (25 <= n && n < 30) ||
                (30 < n && n <= 35) ||
                (55 <= n && n < 60) ||
                (60 < n && n <= 65) ||
                (95 <= n && n < 100)) {
            System.out.print("1");
        } else if ((15 < n && n < 25) ||
                (35 < n && n <= 40) ||
                (50 <= n && n < 55) ||
                (65 < n && n <= 70) ||
                (90 <= n && n < 95)) {
            System.out.print("2");
        } else if ((40 < n && n < 50) ||
                (70 < n && n <= 75) ||
                (85 <= n && n < 90)) {
            System.out.print("3");
        } else if ((75 < n && n < 85)) {
            System.out.print("4");
        } else {
            System.out.print("error");
        }
    }
}
