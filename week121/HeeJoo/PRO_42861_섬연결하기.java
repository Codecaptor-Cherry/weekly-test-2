/*
 * n개의 섬
 * 섬 사이에 다리를 건설하는 비용이 주어질 때, 최소 비용으로 모든 섬이 통행 가능하도록 만들기
 * 
 * 우선순위(1초 이상)보다 유니온 파인드(1초 미만)가 더 빠름
 */ 

import java.util.*;

class Node {
    int num, cost;
    
    Node(int num, int cost) {
        this.num = num;
        this.cost = cost;
    }
}

class Solution {
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);
        
        for (int[] cost : costs) {
            if (find(cost[0], parent) != find(cost[1], parent)) {
                union(cost[0], cost[1], parent);
                answer += cost[2];
            }
        }

//         List<Node>[] list = new ArrayList[n];
//         for (int i = 0; i < n; i++) {
//             list[i] = new ArrayList<>();
//         }
        
//         for (int[] cost : costs) {
//             int a = cost[0];
//             int b = cost[1];
//             int c = cost[2];
            
//             list[a].add(new Node(b, c));
//             list[b].add(new Node(a, c));
//         }
        
//         PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        
//         for (Node node : list[0]) {
//             pq.offer(node);
//         }
        
//         boolean[] visited = new boolean[n];
//         visited[0] = true;
        
//         while (!pq.isEmpty()) {
//             Node node = pq.poll();
            
//             int num = node.num;
//             int cost = node.cost;
            
//             if (visited[num]) {
//                 continue;
//             }
            
//             // System.out.printf("%d(%d)\n", num, cost);
//             visited[num] = true;
//             answer += cost;
            
//             for (Node next : list[num]) {
//                 pq.offer(next);
//             }
//         }
        
        return answer;
    }
    
    public int find(int x, int[] parent) {
        if (x == parent[x]) {
            return parent[x];
        }
        
        return parent[x] = find(parent[x], parent);
    }
    
    public void union(int x, int y, int[] parent) {
        x = find(x, parent);
        y = find(y, parent);
        
        if (x <= y) {
            parent[y] = x;
        } else {
            parent[x] = y;
        }
    }
}
