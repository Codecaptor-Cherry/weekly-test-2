import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_04384_공평하게팀나누기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] weights = new int[n];
        int totalWeight = 0;
        int minDiff = Integer.MAX_VALUE;
        int targetWeight = 0;

        for (int i = 0; i < n; i++) {
            weights[i] = Integer.parseInt(br.readLine());
            totalWeight += weights[i];
        }

        boolean[][] dp = new boolean[n][totalWeight + 1];
        dp[0][0] = true;

        for (int i = 0; i < n; i++) {
            for (int j = n / 2; j > 0; j--) {
                for (int k = totalWeight; k >= weights[i]; k--) {
                    dp[j][k] = dp[j][k] || dp[j - 1][k - weights[i]];
                }
            }
        }

        for (int k = 0; k <= totalWeight; k++) {
            if (dp[n/2][k]) {
                int localDiff = Math.abs(totalWeight - 2 * k);
                if (minDiff > localDiff) {
                    minDiff = localDiff;
                    targetWeight = k;
                }
            }
        }

        int teamA = Math.min(totalWeight - targetWeight, targetWeight);
        int teamB = Math.max(totalWeight - targetWeight, targetWeight);

        System.out.print(teamA + " " + teamB);
    }
}
