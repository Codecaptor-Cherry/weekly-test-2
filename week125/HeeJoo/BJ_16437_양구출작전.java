package saturday.year25.sat251115;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * N개의 섬(1 ~ N)
 * 1번 섬에는 구명보트만 있음
 * 2 ~ N번 섬에는 양 or 늑대가 살고 있음
 * 각 섬에서 1번 섬으로 가는 경로는 유일하며, i번 섬에는 p_i번 섬으로 가는 다리가 있음
 * 양들은 1번 섬으로 가는 경로로 이동
 * 늑대들은 원래 섬에서 움직이지 않고 섬에 들어온 양들을 잡아먹음
 * 한 늑대당 양 한마리만 먹음 ~ 평생동안 한마리
 * 얼마나 많은 양이 1번 섬에 도달 가능 ?
 */

public class BJ_16437_양구출작전 {
    static long ans = 0;
    static int[][] islands;
    static List<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = stoi(br.readLine());
        islands = new int[N + 1][2]; // 타입(0), 수(1)

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 2; i <= N; i++) {
            String[] inputs = br.readLine().split(" ");
            int type = 0;
            int num = stoi(inputs[1]);
            int parent = stoi(inputs[2]);

            if (inputs[0].equals("S")) { // S : 양
                type = 1;
            } else { // W : 늑대
                type = -1;
            }

            islands[i][0] = type;
            islands[i][1] = num;
            graph[parent].add(i);
        }

        ans = dfs(1);

        System.out.println(ans);
    }

    public static long dfs(int now) {
        long sheep = 0;

        for (int next : graph[now]) {
           sheep += dfs(next);
        }

        if (islands[now][0] == -1) { // 늑대 섬
            long gap = sheep - islands[now][1];
//            System.out.printf("%d번 섬 : %d마리 양과 %d마리 늑대\n", now, sheep, islands[now][1]);
            if (gap > 0) {
                islands[now][1] = 0;
                return gap;
            } else {
                islands[now][1] -= sheep;
                return 0;
            }
        } else if (islands[now][0] == 1){ // 양 섬
//            System.out.printf("%d번 섬 : %d마리 양\n", now, islands[now][1] + sheep);
            return islands[now][1] + sheep;
        } else {
            return sheep;
        }
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
