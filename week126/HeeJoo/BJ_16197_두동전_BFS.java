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
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16197_두동전_BFS {
    static int[] dirX = new int[]{-1, 1, 0, 0};
    static int[] dirY = new int[]{0, 0, -1, 1};
    static int N, M;

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

//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.printf("%c", map[i][j]);
//            }
//            System.out.println();
//        }

//        System.out.printf("시작 : (%d, %d) | (%d, %d)\n", x1,y1,x2,y2);
        int ans = bfs(x1, y1, x2, y2, map);
        System.out.println(ans == -1 ? -1 : ans);

    }

    public static int bfs(int x1, int y1, int x2, int y2, char[][] map) {
        // 시작점
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, x1, y1, x2, y2});

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int a1 = now[1];
            int b1 = now[2];
            int a2 = now[3];
            int b2 = now[4];

            if (now[0] == 10) return -1;

            for (int i = 0; i < 4; i++) {
                int da1 = a1 + dirX[i];
                int db1 = b1 + dirY[i];
                int da2 = a2 + dirX[i];
                int db2 = b2 + dirY[i];

                boolean flag1 = oor(da1, db1);
                boolean flag2 = oor(da2, db2);

                if (!flag1 && !flag2) continue; // 둘 떨
                else if (flag1 && flag2) { // 영 떨
                    if (map[da1][db1] == '#') {
                        da1 = a1;
                        db1 = b1;
                    }

                    if (map[da2][db2] == '#') {
                        da2 = a2;
                        db2 = b2;
                    }

                    queue.offer(new int[]{now[0] + 1, da1, db1, da2, db2});
                }
                else { // 일 떨
                    return now[0] + 1;
                }
            }
        }

        return -1;
    }

    public static boolean oor(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= M) return false;
        return true;
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
