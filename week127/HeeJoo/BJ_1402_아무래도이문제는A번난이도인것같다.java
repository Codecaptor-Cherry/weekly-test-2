package saturday.year25.sat251129;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 정수 A = a1 * a2 * a3 * ... * an 일 때,
 * 정수 B = a1 + a2 + a3 + ... + an 이 성립하면
 * A는 B로 변할 수 있다고 한다.
 * 만약 B가 C로 변할 수 있으면, A는 C로 변할 수 있다.
 * A와 B가 주어지면 A는 B로 변할 수 있는지 판별
 *
 * A = A * 1 or A * -1 * -1
 * ai가 1 또는 -1이 될 수 있으니, A, B 조건이 성립할 때 까지 연산하면 되므로 모든 케이스 성공
 * ex) A = 6 = 6 * -1 * -1 * 1
 * ex) B = 5 = 6 + -1 + -1 + 1
 */
public class BJ_1402_아무래도이문제는A번난이도인것같다 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = stoi(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int t = 0; t < T; t++) {
            sb.append("yes\n");
        }

        System.out.println(sb);
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
