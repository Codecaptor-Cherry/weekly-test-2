package saturday.year25.sat251129;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 운동장을 도는데 정확히 N분에 완주 가능
 * N분동안 달릴지, 쉴지 결정해야 함
 * 달리기 1분마다 지침 지수 1 증가
 * 쉬기 1분마다 지침 지수 1 감소
 * 지침 지수가 M보다 커지면 더 이상 달릴 수 없음
 * 학생마다 단위 시간 당 달릴 수 있는 거리가 다름
 * 한 번 쉬면 지침 지수가 0이 될 때까지 다시 달릴 수 없음
 * 달리기가 끝나면 지침 지수는 0이 되어야 함
 * 최대한 멀리 갈 수 있는 거리 구하기
 */
public class BJ_1757_달려달려 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");
        int N = stoi(inputs[0]); // 제한 시간
        int M = stoi(inputs[1]); // 한계 지침지수

        int[] arr = new int[N]; // i분에 달릴 수 있는 거리 D_i
        for (int i = 0; i < N; i++) {
            arr[i] = stoi(br.readLine());
        }

        int[][] dp = new int[N + 1][M + 1]; // i분에 지침 지수가 j일 때 이동거리

        for (int i = 1; i <= N; i++) {
            // 휴식
            dp[i][0] = dp[i - 1][0];

            // 달림
            for (int j = 1; j <= M; j++) {
                dp[i][j] = dp[i - 1][j - 1] + arr[i - 1];
            }

            // dp[i][0]을 채울 수 있는 최댓값 찾기
            // i와 j의 거리 차이만큼 휴식
            for (int j = 1; j <= M && i > j; j++) {
                dp[i][0] = Math.max(dp[i][0], dp[i - j][j]);
            }
        }

        System.out.println(dp[N][0]);
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
