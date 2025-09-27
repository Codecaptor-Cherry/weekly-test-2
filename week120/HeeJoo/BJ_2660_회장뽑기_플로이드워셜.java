package saturday.year25.sat250927;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_2660_회장뽑기_플로이드워셜 {
    static final int INF = (int)1e9;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = stoi(br.readLine()); // 회원의 수

        int[][] map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(map[i], INF);
            map[i][i] = 0;
        }

        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine());

            int a = stoi(st.nextToken());
            int b = stoi(st.nextToken());

            if (a == -1 || b == -1) { // 입력 종료
                break;
            }

            map[a][b] = 1;
            map[b][a] = 1;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }

        int ansScore = INF;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            int score = 0;
            for (int j = 1; j <= N; j++) {
                score = Math.max(score, map[i][j]);
            }

            if (ansScore > score) {
                ansScore = score;
                list.clear();
                list.add(i);
            } else if (ansScore == score) {
                list.add(i);
            }
        }

        System.out.printf("%d %d\n", ansScore, list.size());
        for (int k : list) {
            System.out.printf("%d ", k);
        }

    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
