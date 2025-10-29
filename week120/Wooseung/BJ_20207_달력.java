package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_20207_달력 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] dayCnt = new int[366];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            for(int j = S; j <= E; j++) {
                dayCnt[j]++;
            }
        }

        int ans = 0;
        int max = 0;
        int cnt = 0;
        for(int i = 1; i <= 365; i++) {
            if(dayCnt[i] == 0) {
               ans += max * cnt;
               max = cnt = 0;
               continue;
            }

            cnt++;
            max = Math.max(max, dayCnt[i]);
        }
        ans += max * cnt;
        System.out.println(ans);
    }

}
