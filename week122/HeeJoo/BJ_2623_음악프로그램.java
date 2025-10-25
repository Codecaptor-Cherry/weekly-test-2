package saturday.year25.sat251025;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 가수(1 ~ N)의 출연 순서 정하기
 * 일부 가수들의 순서 정보가 주어질 때 최종 결과 구하기
 * 불가능한 경우 0
 *
 * 어떤 가수 앞에 선행 가수가 존재함 ~ 위상 정렬
 */
public class BJ_2623_음악프로그램 {
    static int N;
    static Set<Integer>[] graph;
    static Queue<Integer> ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        N = stoi(split[0]); // 가수의 수
        int M = stoi(split[1]); // 보조PD의 수

        int[] inDegree = new int[N + 1];
        graph = new HashSet[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new HashSet<>();
        }

        for (int i = 0; i < M; i++) {
            split = br.readLine().split(" ");

            int cnt = stoi(split[0]); // 담당 가수의 수

            int now = stoi(split[1]); // 직전 출연 가수
            for (int j = 2; j <= cnt; j++) {
                int next = stoi(split[j]);

                if (!graph[now].contains(next)) {
                    graph[now].add(next);
                    inDegree[next]++;
                }

                now = next;
            }
        }

//        for (int i = 1; i <= N; i++) {
//            System.out.print(i + " : ");
//            for (int k : graph[i]) {
//                System.out.print(k + " ");
//            }
//            System.out.println();
//        }
//        System.out.println(Arrays.toString(inDegree));

        bfs(inDegree);

        StringBuilder sb = new StringBuilder();

        if (ans.size() == N) {
            while (!ans.isEmpty()) {
                sb.append(ans.poll() + "\n");
            }
        } else {
            sb.append(0);
        }

        System.out.println(sb.toString());
    }

    public static void bfs(int[] inDegree) {
        ans = new ArrayDeque<>(); // 최종 출연 순서

        Queue<Integer> queue = new ArrayDeque<>(); // 위상 정렬 큐
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int idx = queue.poll();
            ans.offer(idx);

            for (int next : graph[idx]) {
                inDegree[next]--;

                if (inDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
