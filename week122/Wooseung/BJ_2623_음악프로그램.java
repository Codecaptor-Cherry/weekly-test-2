package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_2623_음악프로그램 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<List<Integer>> edges = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            edges.add(new ArrayList<>());
        }

        int[] indegree = new int[N + 1];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());

            for(int j = 1; j < num; j++) {
                int to = Integer.parseInt(st.nextToken());
                edges.get(from).add(to);
                indegree[to]++;

                from = to;
            }
        }
        System.out.println(topologicalSort(edges, indegree, N));
    }

    static String topologicalSort(List<List<Integer>> edges, int[] indegree, int N) {

        Queue<Integer> q = new ArrayDeque<>();
        ArrayList<Integer> result = new ArrayList<>();

        for(int i = 1; i <= N; i++) {
            if(indegree[i] == 0) {
                q.add(i);
            }
        }

        while(!q.isEmpty()) {
            int cur = q.poll();
            result.add(cur);

            for(int next : edges.get(cur)) {
                indegree[next]--;

                if(indegree[next] == 0) {
                    q.add(next);
                }
            }
        }

        if(result.size() != N) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < result.size(); i++) {
            sb.append(result.get(i) + "\n");
        }

        return sb.toString();
    }

}
