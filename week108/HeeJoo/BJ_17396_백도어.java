package saturday.year25.sat250607;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * N개의 분기점
 * 0번째 분기점이 시작 위치
 * N-1번째 분기점이 넥서스
 * 적 와드, 미니언, 포탑 등 상대의 시야에 걸리는 곳은 지나칠 수 없음
 * N-1번째 분기점은 보여도 상관없음
 * 현재 위치에서 넥서스까지 갈 수 있는 최소 시간 구하기
 */

class Node {
    int to;
    long cost;

    Node(int to, long cost) {
        this.to = to;
        this.cost = cost;
    }
}
public class BJ_17396_백도어 {
    static int N, M;
    static long INF = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = stoi(st.nextToken()); // 분기점의 수
        M = stoi(st.nextToken()); // 길의 수

        String[] status = br.readLine().split(" "); // 분기점 상태 ... 보이지 않음(0), 보임(1)
        status[N - 1] = "0"; // 보여도 가야하므로 변경

        ArrayList<Node>[] list = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = stoi(st.nextToken());
            int b = stoi(st.nextToken());
            int cost = stoi(st.nextToken());

            if(status[a].equals("1") || status[b].equals("1")) {
                continue;
            }

            // 양방향 연결
            list[a].add(new Node(b, cost));
            list[b].add(new Node(a, cost));
        }

        long[] dist = new long[N]; // 0번 노드에서 i번째까지 가는데 필요한 최소 거리
        Arrays.fill(dist, INF);

        dijkstra(list, dist, new boolean[N]);

        System.out.println(dist[N - 1] != INF ? dist[N - 1] : -1);
    }

    public static void dijkstra(ArrayList<Node>[] list, long[] dist, boolean[] visited) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Math.toIntExact(o1.cost - o2.cost));

        // 시작 노드 초기화
        pq.add(new Node(0, 0));
        dist[0] = 0;

        while(!pq.isEmpty()) {
            // 현재 최단 거리가 가장 짧은 노드를 꺼내서 방문 처리
            Node start = pq.poll();
            int next = start.to;

            // 다음 노드 방문 가능 ?
            if(visited[next]) {
                continue;
            }

            // 다음 노드 방문 처리
            visited[next] = true;

            // start에서 next를 거쳐 end로 가는 게 더 짧은 경우 갱신
            for (Node end : list[next]) {
                // start.cost + end.cost = start에서 next까지 + next에서 end까지
                if(!visited[end.to] && dist[end.to] > start.cost + end.cost) {
                    dist[end.to] = start.cost + end.cost;
                    pq.offer(new Node(end.to, dist[end.to]));
                }
            }
        }
    }

    public static void print(int[][] map) {
        for (int[] arr : map) {
            for (int k : arr) {
                System.out.printf("%d ", k);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
