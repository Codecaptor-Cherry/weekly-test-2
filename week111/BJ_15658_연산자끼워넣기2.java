
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] oper = new int[4];
    static int[] arr;
    static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++) {
            oper[i] = Integer.parseInt(st.nextToken());
        }
        dfs(1, oper, arr[0]);
        System.out.println(max);
        System.out.println(min);
    }

    static void dfs(int depth, int[] oper, int ans) {
        if(depth == N) {
            max = Math.max(max, ans);
            min = Math.min(min, ans);
            return;
        }
        for(int i = 0; i < 4; i++) {
            if(oper[i] == 0) {
                    continue;
            }
            oper[i]--;
            if(i == 0) {
                dfs(depth + 1, oper, ans + arr[depth]);
            }else if(i == 1) {
                dfs(depth + 1, oper, ans - arr[depth]);
            }else if(i == 2) {
                dfs(depth + 1, oper, ans * arr[depth]);
            }else {
                dfs(depth + 1, oper, ans / arr[depth]);
            }
            oper[i]++;
        }

    }

}
