package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_2628_종이자르기 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int width = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(br.readLine());

        List<Integer> horizontal = new ArrayList<>();
        List<Integer> vertical = new ArrayList<>();
        horizontal.add(0);
        vertical.add(0);

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int dir = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            if(dir == 0) {
                horizontal.add(num);
            }else {
                vertical.add(num);
            }
        }

        horizontal.add(height);
        vertical.add(width);

        Collections.sort(horizontal);
        Collections.sort(vertical);

        int horizontalMax = 0;
        int verticalMax = 0;
        for(int i = 1; i < horizontal.size(); i++) {
            horizontalMax = Math.max(horizontalMax, horizontal.get(i) - horizontal.get(i - 1));
        }

        for(int i = 1; i < vertical.size(); i++) {
            verticalMax = Math.max(verticalMax, vertical.get(i) - vertical.get(i - 1));
        }
        System.out.println(horizontalMax * verticalMax);
    }

}
