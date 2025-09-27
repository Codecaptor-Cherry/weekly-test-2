package saturday.year25.sat250927;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 각 회원은 다른 회원들과 가까운 정도에 따라 점수를 받게 된다
 * 어느 회원이 다른 모든 회원과 친구이면, 이 회원의 점수는 1점
 * 2점이면, 다른 모든 회원이 친구이거나 친구의 친구임
 * 3점이면, 친구 or 친친 or 친친친
 * 즉 점수는 가까운 정도의 최대치
 * 주의) 어떤 두 회원이 친구사이이면서 동시에 친친이면, 친구사이로 봄 -> 더 가까운 관계를 우선함
 * 회장은 회원들 중 점수가 가장 작은 사람이 된다.
 * 회장의 점수와 회장이 될 수 있는 모든 사람을 찾기
 * 회원의 수는 50명을 넘지 않는다.
 *
 * 각 회원마다 다른 회원과의 거리 구하기 -> 다익스트라
 * 이 중 가장 긴 거리가 해당 회원의 점수
 */

class Node implements Comparable<Node> {
    int index, cost;

    Node(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}
public class BJ_2660_회장뽑기_다익스트라 {
    static int N;
    static int ansScore = (int)1e9;
    static ArrayList<Integer> candidate = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = stoi(br.readLine()); // 회원의 수

        ArrayList<Integer>[] list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine());

            int a = stoi(st.nextToken());
            int b = stoi(st.nextToken());

            if(a == -1 || b == -1) { // 입력 종료
                break;
            }

            list[a].add(b);
            list[b].add(a);
        }

        for (int i = 1; i <= N; i++) {
            int[] dist = new int[N + 1];
            Arrays.fill(dist, (int)1e9);
            dijkstra(i, dist, list);
        }


        System.out.printf("%d %d\n", ansScore, candidate.size());
        for (int k : candidate) {
            System.out.printf("%d ", k);
        }
    }

    public static void dijkstra(int start, int[] dist, ArrayList<Integer>[] list) {
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        boolean[] visited = new boolean[N + 1];
        while(!pq.isEmpty()) {
            Node now = pq.poll();

            if (visited[now.index]) {
                continue;
            }

            visited[now.index] = true;

            for (int next : list[now.index]) {
                if (dist[next] > now.cost + 1) {
                    dist[next] = now.cost + 1;

                    pq.offer(new Node(next, dist[next]));
                }
            }
        }

        int result = 0;
        for (int i = 1; i <= N; i++) {
            result = Math.max(result, dist[i]);
        }

        if (ansScore > result) {
            ansScore = result;
            candidate.clear();
            candidate.add(start);
        } else if (ansScore == result) {
            candidate.add(start);
        }
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
