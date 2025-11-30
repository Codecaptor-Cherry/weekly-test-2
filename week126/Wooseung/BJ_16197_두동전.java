package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_16197_두동전 {

    static int N, M;
    static char[][] map;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    static class Coins {
        int r1, c1, r2, c2, cnt;

        Coins(int r1, int c1, int r2, int c2, int cnt) {
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        List<int[]> coins = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'o') {
                    coins.add(new int[]{i, j});
                }
            }
        }

        int result = bfs(coins.get(0), coins.get(1));
        System.out.println(result);
    }

    static int bfs(int[] coin1, int[] coin2) {

        Queue<Coins> q = new ArrayDeque<>();
        boolean[][][][] visited = new boolean[N][M][N][M];

        q.add(new Coins(coin1[0], coin1[1], coin2[0], coin2[1], 0));
        visited[coin1[0]][coin1[1]][coin2[0]][coin2[1]] = true;

        while (!q.isEmpty()) {

            Coins cur = q.poll();

            if (cur.cnt >= 10) return -1;

            for (int d = 0; d < 4; d++) {

                int nr1 = cur.r1 + dr[d];
                int nc1 = cur.c1 + dc[d];
                int nr2 = cur.r2 + dr[d];
                int nc2 = cur.c2 + dc[d];

                boolean fall1 = isOut(nr1, nc1);
                boolean fall2 = isOut(nr2, nc2);


                if (fall1 ^ fall2) return cur.cnt + 1;

                if (fall1 && fall2) continue;

                if (!fall1 && map[nr1][nc1] == '#') {
                    nr1 = cur.r1;
                    nc1 = cur.c1;
                }
                if (!fall2 && map[nr2][nc2] == '#') {
                    nr2 = cur.r2;
                    nc2 = cur.c2;
                }

                if (!visited[nr1][nc1][nr2][nc2]) {
                    visited[nr1][nc1][nr2][nc2] = true;
                    q.add(new Coins(nr1, nc1, nr2, nc2, cur.cnt + 1));
                }
            }
        }

        return -1;
    }

    static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= M;
    }
}
