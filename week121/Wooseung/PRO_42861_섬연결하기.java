import java.util.*;

class Solution {
    
    static int[] parent;
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        parent = new int[n];
        Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);
        
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        for(int[] Node : costs) {
            int from = Node[0];
            int to = Node[1];
            int cost = Node[2];
            
            int parentFrom = find(from);
            int parentTo = find(to);
            
            if(parentFrom == parentTo) continue;
            parent[parentTo] = parentFrom;
            answer += cost;
        }
        return answer;
    }
    
    private int find(int x) {
        if(parent[x] == x) return x;
        return find(parent[x]);
    }
}

