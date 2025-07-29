package saturday.year25.sat250726;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * N명의 학생들 ... 1 <= N <= 1,000
 * 가급적 실력 차이가 많이 나도록 조를 편성하는 것이 유리
 * 나이 차이가 많이 날 경우에는 부정적인 효과
 * 1. 나이 순서대로 정렬
 * 2. 적당히 학생들 나누기
 * 조의 개수는 상관 없음
 * 각각의 조가 잘 짜여진 정도 = 조에서 가장 높은 점수 - 가장 낮은 점수
 * 전체적으로 조가 잘 짜여진 정도 = 각각의 조가 잘 짜여진 정도의 합
 * 한 명으로 조가 구성되는 경우 정도는 0
 * 조가 잘 짜여진 정도의 최댓값을 구하기
 */
public class BJ_2229_조짜기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = stoi(br.readLine());

        int[] arr = new int[N]; // 나이 순서대로 정렬된 상태로 입력이 주어짐
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = stoi(st.nextToken());
        }

        int[] dp = new int[N + 1]; // dp[i] : i번째 학생까지 포함했을 때 조가 잘 짜여진 정도의 최댓값

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) { // 1번부터 i번까지 팀에 존재할 때, 1 ~ j - 1, j ~ i번으로 팀 나누기
                dp[i] = Math.max(dp[i], dp[j - 1] + groupScore(j, i, arr));
            }
        }

        System.out.println(dp[N]);
    }

    // 해당 조의 짜여짐 정도 ... 최고점 - 최하점
    public static int groupScore(int start, int end, int[] arr) {
        int maxVal = Integer.MIN_VALUE;
        int minVal = Integer.MAX_VALUE;

        for (int i = start - 1; i <= end - 1; i++) {
            maxVal = Math.max(arr[i], maxVal);
            minVal = Math.min(arr[i], minVal);
        }

        return maxVal - minVal;
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
