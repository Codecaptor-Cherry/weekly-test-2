package baekjoon;

import java.util.Scanner;

public class BJ_28242_수학선생님의고민Hard {

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            int target = -(n + 2);

            for (int a = 1; a * a <= n; a++) {
                if (n % a != 0) continue;
                int c = n / a;

                for (int k = 1; k * k <= Math.abs(target); k++) {
                    if (target % k != 0) continue;
                    int[] bList = {k, -k};
                    for (int b : bList) {
                        if (b == 0) continue;
                        int d = target / b;
                        if (a * d + b * c == n + 1) {
                            System.out.println(a + " " + b + " " + c + " " + d);
                            return;
                        }
                    }
                }
            }
            System.out.println(-1);
        }
}
