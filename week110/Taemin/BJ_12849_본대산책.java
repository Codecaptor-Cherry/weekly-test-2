import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_12849_본대산책 {

    static final long mod = 1_000_000_007L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int d = Integer.parseInt(br.readLine());

        long[][] dp =  new long[d + 1][8];
        dp[0][0] = 1;

        for (int time = 0; time < d; time++) {
            dp[time + 1][0] = (dp[time][1] + dp[time][2]) % mod;
            dp[time + 1][1] = (dp[time][0] + dp[time][2] + dp[time][3]) % mod;
            dp[time + 1][2] = (dp[time][0] + dp[time][1] + dp[time][3] + dp[time][4]) % mod;
            dp[time + 1][3] = (dp[time][1] + dp[time][2] + dp[time][4] + dp[time][5]) % mod;
            dp[time + 1][4] = (dp[time][2] + dp[time][3] + dp[time][5] + dp[time][6]) % mod;
            dp[time + 1][5] = (dp[time][3] + dp[time][4] + dp[time][7]) % mod;
            dp[time + 1][6] = (dp[time][4] + dp[time][7]) % mod;
            dp[time + 1][7] = (dp[time][5] + dp[time][6]) % mod;
        }

        System.out.print(dp[d][0]);
    }

}
