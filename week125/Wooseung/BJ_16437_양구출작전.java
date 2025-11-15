package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_16437_양구출작전 {

    static List<Integer>[] list;
    static long[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        list = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        dp = new long[N + 1];
        for(int i = 2; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            char t = st.nextToken().charAt(0);
            int a = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            list[p].add(i);
            if(t == 'W') {
                a *= -1;
            }
            dp[i] = a;
        }
        dfs(1, -1);
        System.out.println(dp[1]);
    }

    static void dfs(int start, int end) {
        for(int next : list[start]) {
            dfs(next, start);
        }
        if(end != -1) {
            if(dp[start] > 0) {
                dp[end] += dp[start];
            }
        }

    }

}
