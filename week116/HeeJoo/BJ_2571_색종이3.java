package saturday.year25.sat250823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 100 x 100 정사각형 모양의 흰 도화지
 * 도화지 위에 10 x 10 정사각형 모양의 검은색 종이를 도화지의 변과 평행하도록 붙이기
 * 색종이를 한 장 또는 여러 장 붙인 후 검은색 직사각형 잘라내기
 * 잘라낼 수 있는 검은색 직사각형의 최대 넓이 구하기
 *
 * 1. 검은색 색종이 표시
 * 2. 세로 방향 누적합 : 너비가 1인 직사각형 정리
 * 3. 해당 너비에서 가능한 높이(최솟값) 구하기 -> 넓이 계산
 */

public class BJ_2571_색종이3 {
    static int size = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = stoi(br.readLine());
        int ans = 0;

        // 1. 검은색 종이 붙이기
        int[][] map = new int[100][100]; // 100 x 100
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = stoi(st.nextToken()); // 왼쪽 여백
            int b = stoi(st.nextToken()); // 아래쪽 여백

            size = Math.max(size, Math.max(a + 10, b + 10));
            fillBlack(map, a, b);
        }

        // 2. 세로 방향 누적합
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j] > 0) { // 끊기지 않는 경우에만 누적
                    map[i][j] += map[i - 1][j];
                }
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int height = map[i][j];

                if(map[i][j] == 0) {
                    continue;
                }

                for (int k = j; k < size; k++) {
                    if (map[i][k] == 0) { // 중간에 끊기면 stop
                        break;
                    }

                    height = Math.min(height, map[i][k]); // 가능한 최대 높이 = 누적된 높이의 최솟값
                    ans = Math.max(ans, height * (k - j + 1));
                }
            }
        }

        System.out.println(ans);
    }


    public static void fillBlack(int[][] map, int a, int b) {
        // 10 x 10
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                map[a + i][b + j] = 1;
            }
        }
    }

    public static void print(int[][] map) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("%d ", map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
