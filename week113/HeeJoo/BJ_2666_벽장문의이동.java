package saturday.year25.sat250719;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * n개의 같은 크기의 벽장들이 일렬로 붙어있고, 벽장의 문은 n - 2개만 존재
 * 한 벽장 앞에 있는 문은 이웃 벽장 앞에 문이 없다면(즉, 벽장이 열려있다면) 그 벽장 앞으로 움직일 수 있다 ... 만화책방 ?
 * 사용할 벽장의 순서에 따라서 벽장문을 이동하는 순서 찾기
 */
public class BJ_2666_벽장문의이동 {
    static int ans = (int)1e9;
    static int m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = stoi(br.readLine()); // 벽장의 개수 ... 3 초과 20 이하의 정수


        String[] inputs = br.readLine().split(" ");
        // 열려있는 벽장 번호
        int a = stoi(inputs[0]);
        int b = stoi(inputs[1]);

        m = stoi(br.readLine()); // 사용할 벽장들의 순서의 길이 ... 최대 20
        int[] open = new int[m]; // 사용할 벽장 순서
        for (int i = 0; i < m; i++) {
            int k = stoi(br.readLine()); // 사용할 벽장의 번호
            open[i] = k;
        }

        dfs(0, a, b, 0, open);

        System.out.println(ans);
    }

    public static void dfs(int idx, int a, int b, int sum, int[] open) {
        if(sum > ans) {
            return;
        }

        if(idx == m) {
            ans = Math.min(ans, sum);
            return;
        }

        dfs(idx + 1, open[idx], b, sum + Math.abs(open[idx] - a), open); // a문 이동
        dfs(idx + 1, a, open[idx], sum + Math.abs(open[idx] - b), open); // b문 이동

    }
    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
