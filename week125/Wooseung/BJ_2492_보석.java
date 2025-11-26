package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_2492_보석 {

    static int N, M, K, T;
    static List<int[]> list;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   
        M = Integer.parseInt(st.nextToken());  
        T = Integer.parseInt(st.nextToken());  
        K = Integer.parseInt(st.nextToken());  

        list = new ArrayList<>();

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new int[]{x, y});
        }

        int bestX = 0; 
        int bestY = 0; 
        int max = 0;
        
        for (int i = 0; i < T; i++) {
            int baseX = list.get(i)[0]; 
            
            if (baseX + K > N) baseX = N - K;
            if (baseX < 0) baseX = 0;

            for (int j = 0; j < T; j++) {
                int baseY = list.get(j)[1]; 
                
                if (baseY + K > M) baseY = M - K;
                if (baseY < 0) baseY = 0;

                int cnt = 0;
                
                for (int t = 0; t < T; t++) {
                    int x = list.get(t)[0];
                    int y = list.get(t)[1];

                    if (baseX <= x && x <= baseX + K &&
                        baseY <= y && y <= baseY + K) {
                        cnt++;
                    }
                }

                if (cnt > max) {
                    max = cnt;
                    bestX = baseX;
                    bestY = baseY;
                }
            }
        }
        
        System.out.println(bestX + " " + (bestY + K));
        System.out.println(max);
    }
}
