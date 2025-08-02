package saturday.year25.sat250802;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 양의 정수 n
 * nx^2 + (n + 1)x - (n + 2)를 정수 범위에서 인수분해 하는 프로그램
 *
 * (ax + b)(cx + d) = nx^2 + (n + 1)x - (n + 2) ... 인수분해 가능
 * ac = n
 * ad + bc = n + 1
 * bd = n + 2
 */
public class BJ_28242_수학선생님의고민_Hard {
    static int N, A, B, C, D;
    static boolean flag = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        // ac = n
        for (int i = 1; i < (int)Math.sqrt(N) + 1; i++) {
            if (N % i == 0) {
                int a = i;
                int c = N / a;

                // bd = -(n + 2) = 양수 * 음수
                for (int j = 1; j < (int)Math.sqrt(N + 2) + 1; j++) {
                    if((N + 2) % j == 0) {
                        int b = j;
                        int d = (N + 2) / j;

                        // ad + bc = n + 1
                        calc(a, -b, c, d);
                        calc(a, -d, c, b);
                        calc(a, b, c, -d);
                        calc(a, d, c, -b);
                    }

                    if (flag) {
                        System.out.printf("%d %d %d %d\n", A, B, C, D);
                        return;
                    }
                }
            }
        }

        System.out.println(-1);

    }

    public static void calc(int a, int b, int c, int d) {
        if(a * d + b * c == N + 1) {
            flag = true;
            A = a; B = b; C = c; D = d;
        }

        return;
    }
}
