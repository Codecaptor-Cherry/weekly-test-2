/*
 * 참고 : https://ampersandor.tistory.com/21
 * 가로 n, 세로 3인 판을 타일링하는 의뢰
 * 2가지 종류의 타일 존재(그림 참고)
 * 각 타일은 90도씩 회전할 수 있으며, 타일의 개수는 제한이 없음
 * n이 주어졌을 때, 주어진 두 가지 종류의 타일로 n x 3 크기의 판을 타일링하는 방법의 수 구하기
 *
 * DP 점화식 이용 ... n = i일 때만 만들 수 있는 새로운 조합 + .... + n = 1일 때만 만들 수 있는 새로운 조합 * 남은 칸에 가능한 경우의 수
 * n = 1) 1 
 * n = 2) 3 = 2 + 1 * 1 ... 새로 등장하는 타일은 ㄱ자 2개를 조합한 모양
 * n = 3) 10 = 5 + 2 * 1 + 1 * 3 ... 새로 등장하는 타일은 III, ㄴㄱI를 회전한 4개 ~ 총 5개
 * n = 4) 23 = 2 + 5 * 1 + 2 * 3 + 1 * 10 ... 새로 등장하는 타일의 개수는 2개 ... ㅡㄴㄱㅡ와 뒤집은 것
 * n = 5) 62 = 2 + 2 * 1 + 5 * 3 + 2 * 10 + 1 * 23 = 2 2 15 20 23 ... 새로 등장하는 타일의 개수는 2개
 * n = 6) 170 = 4 + 2 * 1 + 2 * 3 + 5 * 10 + 2 * 23 + 1 * 62 ... 새로 등장하는 타일의 개수는 4개
 * 이후 2, 2, 4 패턴으로 새로운 타일 등장
 * dp[x] = dp[x - 1] + 2 * dp[x - 2] + 5 * dp[x - 3] + 2 * dp[x - 4] + 2 * dp[x - 5] + 4 * dp[x - 6] 
 + 2 * dp(x - 7) + 2 * dp(x - 8) + 4 * dp(x - 9) + ... ~ 점화식과 계산식 순서 반대
 * dp[x + 3] - dp[x]로 점화식을 정리하면 dp(x) = dp(x - 1) + 2 * dp(x - 2) + 6 * dp(x - 3) + dp(x - 4) - dp(x - 6) 
 */

class Solution {
    static final int MOD = (int)1e9 + 7;
    static final int size = 100000;
    public int solution(int n) {
        switch (n) {
            case 1:
                return 1;
            case 2:
                return 3;
            case 3:
                return 10;
            case 4:
                return 23;
            case 5:
                return 62;
            case 6:
                return 170;
        }

        int[] dp = new int[n + 1];
        
        dp[1] = 1;
        dp[2] = 3;
        dp[3] = 10;
        dp[4] = 23;
        dp[5] = 62;
        dp[6] = 170;

        for (int i = 7; i <= n; i++) {
            dp[i] = (dp[i - 1] + 2 * dp[i - 2] + 6 * dp[i - 3] + dp[i - 4] - dp[i - 6] + MOD) % MOD;
        }

        // for(int k : dp) {
        //     System.out.printf("%d ", k);
        // }
        return dp[n] % MOD;
    }
}
