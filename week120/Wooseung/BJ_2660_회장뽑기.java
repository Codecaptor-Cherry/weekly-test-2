package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2660_회장뽑기 {

    static final int INF = 100_000_000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] dist = new int[N + 1][N + 1];

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(i != j) {
                    dist[i][j] = INF;
                }
            }
        }

        while(true) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(x == -1 && y == -1) break;

            dist[x][y] = dist[y][x] = 1;
        }

        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    if(dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        int maxScore = INF;
        int[] scoreArr = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            int score = 0;
            for(int j = 1; j <= N; j++) {
                if(dist[i][j] != INF) {
                    score = Math.max(score, dist[i][j]);
                }
            }

            scoreArr[i] = score;
            maxScore = Math.min(maxScore, score);
        }

        int cnt = 0;

        for(int i = 1; i <= N; i++) {
            if(maxScore == scoreArr[i]) {
                cnt++;
                sb.append(i + " ");
            }
        }
        System.out.println(maxScore + " " + cnt);
        System.out.println(sb.toString());
    }

}
