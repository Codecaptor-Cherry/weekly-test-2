package saturday.year25.sat251122;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * N줄에 0 이상 9 이하의 숫자가 3개씩 적혀 있음
 * 맨 처음에 적혀 있는 숫자 중 하나를 골라 시작
 * 다음 줄로 내려갈 때, 바로 아래의 수로 넘어가거나, 아니면 바로 아래의 수와 붙어 있는 수로만 이동 가능
 * 숫자표가 주어져 있을 때 얻을 수 있는 최대, 최소 점수 구하기
 *
 * DP : 이전 위치를 기준으로 가능한 각 단계 별 최대/최소 구하기
 */
public class BJ_2096_내려가기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = stoi(br.readLine());

        int[][] map = new int[N][3];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 3; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }

        // 이전 줄의 결과
        // 2차원 DB 사용하면 100,000이라 메모리 초과
        int[] min = new int[3];
        int[] max = new int[3];

//        for (int i = 0; i < 3; i++) {
//            min[1][i] = map[0][i];
//            max[1][i] = map[0][i];
//        }

        for (int i = 1; i <= N; i++) {
//            System.out.println("min : " + Arrays.toString(min));
//            System.out.println("max : " + Arrays.toString(max));
            // 현재 줄의 결과
            int[] tmpMin = new int[3];
            int[] tmpMax = new int[3];

            // 좌
            tmpMin[0] = map[i - 1][0] + Math.min(min[0], min[1]);
            tmpMax[0] = map[i - 1][0] + Math.max(max[0], max[1]);

            // 중
            tmpMin[1] = map[i - 1][1] + Math.min(min[0], min[1]);
            tmpMin[1] = Math.min(tmpMin[1], map[i - 1][1] + min[2]);
            tmpMax[1] = map[i - 1][1] + Math.max(max[0], max[1]);
            tmpMax[1] = Math.max(tmpMax[1], map[i - 1][1] + max[2]);

            // 우
            tmpMin[2] = map[i - 1][2] + Math.min(min[1], min[2]);
            tmpMax[2] = map[i - 1][2] + Math.max(max[1], max[2]);

            min = tmpMin;
            max = tmpMax;
        }

//        print(min);
//        print(max);

        int maxAns = 0, minAns = Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            maxAns = Math.max(maxAns, max[i]);
            minAns = Math.min(minAns, min[i]);
        }

        System.out.printf("%d %d", maxAns, minAns);
    }

    public static void print(int[][] map) {
        for (int[] arr : map) {
            for (int k : arr) {
                System.out.printf("%d ", k);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
