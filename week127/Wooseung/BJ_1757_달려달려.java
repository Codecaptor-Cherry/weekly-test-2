package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1757_달려달려 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] dp = new int[N + 1][M + 1];

        for(int i = 1; i <= N; i++) {
            int m = Integer.parseInt(br.readLine());
            dp[i][0] = dp[i - 1][0];

            for(int j = 1; j <= M; j++) {
                dp[i][j] = dp[i - 1][j - 1] + m;
            }

            for(int j = 1; j <= M; j++) {
                if(i < j)  break;
                dp[i][0] = Math.max(dp[i][0], dp[i - j][j]);
            }
        }
        System.out.println(dp[N][0]);
    }
}
