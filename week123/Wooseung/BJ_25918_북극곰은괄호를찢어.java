package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ_25918_북극곰은괄호를찢어 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N =Integer.parseInt(br.readLine());
        String str = br.readLine();
        Stack<Character> st = new Stack<>();
        int ans = -1;

        if(N % 2 == 0) {
            for(char ch : str.toCharArray()) {
                if(st.isEmpty() || st.peek().equals(ch)) {
                    st.push(ch);
                }else {
                    st.pop();
                }
                ans = Math.max(ans, st.size());
            }
            if(!st.isEmpty()) {
                ans = -1;
            }
        }
        System.out.println(ans);
    }

}
