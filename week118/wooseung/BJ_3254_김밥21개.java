package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_3254_김밥21개 {

    static final int ROWS = 6, COLS = 7;
    static int[][] map= new int[ROWS][COLS];
    static int[] height= new int[COLS];
    static int[][] dirs = {{0,1},{1,0},{1,1},{1,-1}};


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int round = 1; round <= 21; round++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int j = Integer.parseInt(st.nextToken()) - 1;

            int rs = height[s];
            map[rs][s] = 1;
            height[s]++;
            if (isWin(rs, s, 1)) {
                System.out.println("sk " + round);
                return;
            }

            int rj = height[j];
            map[rj][j] = 2;
            height[j]++;
            if (isWin(rj, j, 2)) {
                System.out.println("ji " + round);
                return;
            }
        }

        System.out.println("ss");
    }

    static boolean isValid(int r, int c) {
        return 0 <= r && r < ROWS && 0 <= c && c < COLS;
    }
    static boolean isWin(int r, int c, int player) {

        for (int[] d : dirs) {
            int cnt = 1;

            int nr = r + d[0], nc = c + d[1];
            while (isValid(nr, nc) && map[nr][nc] == player) {
                cnt++; nr += d[0]; nc += d[1];
            }

            nr = r - d[0]; nc = c - d[1];
            while (isValid(nr, nc) && map[nr][nc] == player) {
                cnt++; nr -= d[0]; nc -= d[1];
            }

            if (cnt >= 4) return true;
        }
        return false;
    }
}
