package saturday.year25.sat251122;

/*
 * N x M 보드와 4개의 버튼
 * 각 칸은 빈 공간(.), 벽(#)
 * 두 개의 빈 칸에는 동전이 하나씩 놓여있고, 두 동전의 위치는 다름
 * 버튼을 누르면 두 동전이 버튼 방향으로 동시에 한 칸씩 움직임
 * 동전이 이동하려는 칸이 벽이면, 이동하지 않음
 * 동전이 이동하려는 방향에 칸이 없으면, 동전은 보드 바깥으로 추락
 * 이동하려는 칸에 동전이 있어도 이동 가능
 * 두 동전 중 하나만 보드에서 떨어뜨리기 위해서 버튼을 최소 몇 번 눌러야하는지 구하기
 * 두 동전을 떨어뜨릴 수 없거나, 10번보다 많이 눌러야 한다면 -1
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_16197_두동전_DFS {
    static int[] dirX = new int[]{-1, 1, 0, 0};
    static int[] dirY = new int[]{0, 0, -1, 1};
    static int N, M;
    static int ans = 11;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());

        char[][] map = new char[N][M];
        int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);

                if (map[i][j] == 'o') {
                    if (cnt++ == 0) {
                        x1 = i;
                        y1 = j;
                    } else {
                        x2 = i;
                        y2 = j;
                    }
                }
            }
        }

        dfs(x1, y1, x2, y2, 0, 0, map);
        System.out.println(ans == 11 ? -1 : ans);

    }

    public static void dfs(int x1, int y1, int x2, int y2, int cnt, int drop, char[][] map) {
        if (cnt >= ans) {
            return; // 10번 넘음
        }

        if (drop == 2) {
            return; // 둘다 떨어짐
        }

        if (drop == 1) { // 하나만 떨어짐
            ans = Math.min(ans, cnt);
            return;
        }


        for (int i = 0; i < 4; i++) {
            // 동전 1 이동
            int dx1 = x1 + dirX[i];
            int dy1 = y1 + dirY[i];
            boolean flag1 = false;
            boolean flag2 = false;

            if (!oor(dx1, dy1)) flag1 = true;
            if (!flag1 && map[dx1][dy1] == '#') {
                dx1 = x1;
                dy1 = y1;
            }

            // 동전 2 이동
            int dx2 = x2 + dirX[i];
            int dy2 = y2 + dirY[i];

            if (!oor(dx2, dy2)) flag2 = true;
            if (!flag2 && map[dx2][dy2] == '#') {
                dx2 = x2;
                dy2 = y2;
            }

            // 재귀
            if (flag1 && flag2) { // 둘떨
                dfs(dx1, dy1, dx2, dy2, cnt + 1, 2, map);
            } else if (!flag1 && !flag2) { // 영떨
                dfs(dx1, dy1, dx2, dy2, cnt + 1, 0, map);
            } else { // 일떨
                dfs(dx1, dy1, dx2, dy2, cnt + 1, 1, map);
            }
        }
    }

    public static boolean oor(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= M) return false;
        return true;
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
