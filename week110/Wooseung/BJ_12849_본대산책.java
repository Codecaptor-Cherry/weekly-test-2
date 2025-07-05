import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final long div = 1_000_000_007;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int D = Integer.parseInt(br.readLine());

        long[][] dp = new long[D + 1][8];
        dp[0][0] = 1;

        for(int i = 0; i < D; i++) {
            dp[i + 1][0] = (dp[i][1] + dp[i][2]) % div;
            dp[i + 1][1] = (dp[i][0] + dp[i][2] + dp[i][3]) % div;
            dp[i + 1][2] = (dp[i][0] + dp[i][1] + dp[i][3] + dp[i][5]) % div;
            dp[i + 1][3] = (dp[i][1] + dp[i][2] + dp[i][4] + dp[i][5]) % div;
            dp[i + 1][4] = (dp[i][3] + dp[i][5] + dp[i][6]) % div;
            dp[i + 1][5] = (dp[i][2] + dp[i][3] + dp[i][4] + dp[i][7]) % div;
            dp[i + 1][6] = (dp[i][4] + dp[i][7]) % div;
            dp[i + 1][7] = (dp[i][5] + dp[i][6]) % div;
        }
        System.out.println(dp[D][0]);
    }

}
