package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_31461_최고의초콜릿성지순례 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[2][N];

            for (int r = 0; r < 2; r++) {
                String[] inputs = br.readLine().split(" ");
                for (int c = 0; c < N; c++) {
                    arr[r][c] = Integer.parseInt(inputs[c]);
                }
            }
            String[] inputs = br.readLine().split(" ");
            int a = Integer.parseInt(inputs[0]) - 1; 
            int b = Integer.parseInt(inputs[1]) - 1;
            int c = Integer.parseInt(inputs[2]) - 1;
            int d = Integer.parseInt(inputs[3]) - 1;

            if (a > c) {
                int tmp = a; a = c; c = tmp; 
                tmp = b; b = d; d = tmp;   
            }

            long max = 0;
            long left = 0;
            for (int i = a - 1; i >= 0; i--) {
                max += Math.max(arr[0][i], arr[1][i]);
                left = Math.max(left, max);

                max += Math.min(arr[0][i], arr[1][i]);
                left = Math.max(left, max);
            }
            max = 0;
            long right = 0;
            for (int i = c + 1; i < N; i++) {
                max += Math.max(arr[0][i], arr[1][i]);
                right = Math.max(right, max);

                max += Math.min(arr[0][i], arr[1][i]);
                right = Math.max(right, max);
            }
            long ans = arr[b][a] + arr[d][c];
            if (a == c) {
                ans += Math.max(left, right);
            } else {
                long mid = 0;
                for (int i = a + 1; i < c; i++) {
                    mid += Math.max(
                        arr[0][i] + arr[1][i],          
                        Math.max(arr[0][i], arr[1][i])
                    );
                }
            sb.append(ans).append(" ");
        }
        System.out.println(sb);
    }
}
