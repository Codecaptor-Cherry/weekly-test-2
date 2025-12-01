package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1402_아무래도이문제는A번난이도인것같다 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            long A = Integer.parseInt(st.nextToken());
            long B = Integer.parseInt(st.nextToken());
            sb.append("yes\n");
        }
        System.out.println(sb.toString());
    }
}
