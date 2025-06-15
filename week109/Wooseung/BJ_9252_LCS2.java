package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ_9252_LCS2 {
    static int[][] dp;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();

        int len1 = str1.length();
        int len2 = str2.length();

        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();

        dp = new int[len1 + 1][len2 + 1];

        for(int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if(arr1[i - 1] == arr2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[str1.length()][str2.length()]);
        toString(arr1, len1, len2);
        System.out.println(sb.toString());

    }

    static void toString(char[] str, int i, int j) {
        Stack<Character> st = new Stack<>();

        while(i > 0 && j > 0) {

            if(dp[i][j] == dp[i - 1][j]) {
                i--;
            }else if(dp[i][j] == dp[i][j - 1]) {
                j--;
            }else {
                st.push(str[i - 1]);
                i--;
                j--;
            }
        }
        while(!st.isEmpty()) {
            sb.append(st.pop());
        }
    }

}
