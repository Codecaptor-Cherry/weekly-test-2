package saturday.year25.sat250802;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 두 팀간의 사람 수 차이를 1 이하로 하며,
 * 두 팀간의 몸무게 차이가 최소화되도록 팀 나누기
 *
 * i번째 학생을 A그룹에 넣을까? 말까?
 * N/2명 또는 N/2 - 1명을 A그룹에 넣어서 B그룹과 몸무게 차이 최소화 ~ 전제 무게의 절반에 가장 가까운 부분 집합
 */
public class BJ_4384_공평하게팀나누기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = stoi(br.readLine());

        int[] arr = new int[N];
        int sum = 0; // sum - A그룹 = B그룹
        for (int i = 0; i < N; i++) {
            int input = stoi(br.readLine());
            arr[i] = input;
            sum += input;
        }

//        int select = N / 2; // N / 2명을 A그룹으로 넣기
        int select = N / 2;
        boolean[][] dp = new boolean[select + 1][sum + 1]; // i명을 선택했을 때, 몸무게 합이 j가 가능한지?
        dp[0][0] = true; // 0명을 골라 0kg

        // j, k 순서 반대로하면 틀림
        // 반대로하면 i번 학생을 중복으로 뽑아 true가 되는 경우 존재
        for (int i = 0; i < N; i++) { // i번 학생 포함
            for (int j = select; j > 0; j--) { // j명 선택
                for (int k = sum; k >= arr[i]; k--) { // 가능한 몸무게
                    dp[j][k] |= dp[j - 1][k - arr[i]];
                }
            }
        }

//        for (int i = 0; i < select + 1; i++) {
//            for (int j = 0; j < sum + 1; j++) {
//                if(dp[i][j]) {
//                    System.out.printf("%d, %d\n",i,j);
//                }
//            }
//            System.out.println();
//        }
//        System.out.println();

        int minDif = (int)1e9;
        int ans = 0;
        for (int i = 0; i <= sum; i++) {
            if(dp[select][i]) {
                int a = i;
                int b = sum - a;
                int curDif = Math.abs(a - b);
                if(minDif > curDif) {
                    minDif = curDif;
                    ans = i;
                }
            }
        }

        System.out.println(ans < sum - ans ? ans + " " + (sum - ans) : (sum - ans) + " " + ans);
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
