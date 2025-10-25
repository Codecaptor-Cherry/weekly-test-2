package saturday.year25.sat251025;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 가로 두칸, 세로 N칸 우리
 * 사자를 가두는 데, 가로로도 세로로도 붙어 있게 배치할 수 없음
 * 2 x N 배열에 사자를 배치하는 경우의 수 구하기
 *
 * dp
 * 왼쪽 or 오른쪽 or 배치x
 * N = ?) 왼 + 오 + x
 * N = 1) 1 + 1 + 1 = 3
 * N = 2) 2 + 2 + 3 = 7
 * N = 3) 5 + 5 + 7 = 17
 * N = 4) 12 + 12 + 17 = 41
 * N = ?) (이전 오른 + 배치x) + (이전 왼 + 배치x) + 이전 경우의 수
 * dp[i] = dp[i - 1] * 2 + dp[i - 2]
 */
public class BJ_1309_동물원 {
    static final int MOD = 9901;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        if (N == 1) {
            System.out.println(3);
            return;
        }

        int[] dp = new int[N + 1];
        dp[1] = 3;
        dp[2] = 7;

        for (int i = 3; i <= N; i++) {
            dp[i] = (dp[i - 1] * 2 + dp[i - 2]) % MOD;
        }


        System.out.println(dp[N]);
    }
}
