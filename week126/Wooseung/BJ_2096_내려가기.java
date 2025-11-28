package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2096_내려가기 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] maxArr = new int[N + 1][3];
        int[][] minArr = new int[N + 1][3];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            maxArr[i][0] = Math.max(maxArr[i - 1][0] + a, maxArr[i - 1][1] + a);
            maxArr[i][1] = Math.max(maxArr[i - 1][0] + b, Math.max(maxArr[i - 1][1] + b, maxArr[i - 1][2] + b));
            maxArr[i][2] = Math.max(maxArr[i - 1][1] + c, maxArr[i - 1][2] + c);

            minArr[i][0] = Math.min(minArr[i - 1][0] + a, minArr[i - 1][1] + a);
            minArr[i][1] = Math.min(minArr[i - 1][0] + b, Math.min(minArr[i - 1][1] + b, minArr[i - 1][2] + b));
            minArr[i][2] = Math.min(minArr[i - 1][1] + c, minArr[i - 1][2] + c);
        }
        int max = Math.max(maxArr[N][0], Math.max(maxArr[N][1], maxArr[N][2]));
        int min = Math.min(minArr[N][0], Math.min(minArr[N][1], minArr[N][2]));

        System.out.println(max + " " + min);

    }

}
