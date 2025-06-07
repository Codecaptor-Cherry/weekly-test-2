package saturday.year25.sat250524;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 일렬로 이어진 전구 묶음 N개
 * 묶음에는 불이 들어오지 않는 전구 존재
 * 전구 상태가 바뀌는 횟수가 최소일 때를 원함 ~ 즉, 01 or 10이 최소로 나타나야 함
 * 전구 묶음을 가장 예쁘게 배치했을 때, 전구의 상태가 바뀌는 횟수 출력
 *
 * 1 <= N <= 10 ~ 순열 가능
 */
public class BJ_17359_전구길만걷자 {
    static int ans = (int)1e9;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = stoi(br.readLine());

        String[] inputs = new String[N];
        for (int i = 0; i < N; i++) {
            inputs[i] = br.readLine();
        }

        boolean[] visited = new boolean[N];
        perm(0, N, new int[N], inputs, visited);

        for (int i = 0; i < N; i++) {
            char ch = inputs[i].charAt(0);
            for (int j = 1; j < inputs[i].length(); j++) {
                if(ch != inputs[i].charAt(j)) {
                    ans++;
                }

                ch = inputs[i].charAt(j);
            }
        }

        System.out.println(ans);
    }

    public static void perm(int idx, int N, int[] selected, String[] inputs, boolean[] visited) {
        if(idx == N) {
//            System.out.println(Arrays.toString(selected));
            compute(N, selected, inputs);
            return;
        }

        for (int i = 0; i < N; i++) {
            if(visited[i]) {
                continue;
            }

            visited[i] = true;
            selected[idx] = i;
            perm(idx + 1, N, selected, inputs, visited);
            visited[i] = false;
        }
    }

    public static void compute(int N, int[] selected, String[] inputs) {
        int sum = 0;

        String first = inputs[selected[0]];
        char lastWord = first.charAt(first.length() - 1);
        for (int i = 1; i < N; i++) {
            char firstWord = inputs[selected[i]].charAt(0);

            if(lastWord != firstWord) {
                sum++;
            }

            lastWord = inputs[selected[i]].charAt(inputs[selected[i]].length() - 1);
        }

        ans = Math.min(ans, sum);
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
