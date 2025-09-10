package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2571_색종이3 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int ans = -1;
        int[][] arr = new int[100][100];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            for(int r = x; r < x + 10; r++) {
                for(int c = y; c < y + 10; c++) {
                    arr[r][c] = 1;
                }
            }
        }
        for(int i = 0 ; i < 99; i++) {
            for(int j = 0; j < 100; j++) {
                if(arr[i][j] != 0 && arr[i + 1][j] != 0) {
                    arr[i + 1][j] = arr[i][j] + 1;
                }
            }
        }
        for(int i = 0; i < 100; i++) {
            for(int j = 0; j < 100; j++) {
                int h = 100;

                for(int k = j; k < 100; k++) {
                    h = Math.min(arr[i][k], h);
                    if(h == 0) break;
                    ans = Math.max(ans, h * (k - j + 1));
                }
            }
        }
        System.out.println(ans);
    }

}
