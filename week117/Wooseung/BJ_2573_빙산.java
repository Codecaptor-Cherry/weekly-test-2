package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class IceBerg {
    int r, c;
    IceBerg(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class BJ_2573_빙산 {

    static int[][] map;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int N, M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        int cnt = 0;
        while ((cnt = SplitCheck()) < 2) {
            if (cnt == 0) {
                ans = 0;
                break;
            }
            Melt();
            ans++;
        }
        System.out.println(ans);
    }
    static int SplitCheck() {
        boolean[][] visited = new boolean[N][M];

        int cnt = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] != 0 && !visited[i][j]) {
                    IcebergCheck(i, j, visited);
                    cnt++;
                }
            }
        }
        return cnt;
    }
    static void IcebergCheck(int r, int c, boolean[][] visited) {
        visited[r][c] = true;

        int nr, nc;
        for(int d = 0; d < 4; d++) {
            nr = r + dr[d];
            nc = c + dc[d];

            if(nr < 0 || nc < 0 || nr >= N || nc >= M) {
                continue;
            }
            if(map[nr][nc] != 0 && !visited[nr][nc]) {
                IcebergCheck(nr, nc, visited);
            }
        }
    }

    static void Melt() {
        Queue<IceBerg> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] != 0) {
                    q.add(new IceBerg(i, j));
                    visited[i][j] = true;
                }
            }
        }
        int nr, nc;
        while(!q.isEmpty()) {
            IceBerg cur = q.poll();

            int cnt = 0;

            for(int d = 0; d < 4; d++) {
                nr = cur.r + dr[d];
                nc = cur.c + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
                    continue;
                }

                if (!visited[nr][nc] && map[nr][nc] == 0) {
                    cnt++;
                }
            }
            if(map[cur.r][cur.c] - cnt < 0) {
                map[cur.r][cur.c] = 0;
            }else {
                map[cur.r][cur.c] -= cnt;
            }
        }
    }
}
