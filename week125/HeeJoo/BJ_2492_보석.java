package saturday.year25.sat251115;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 굴착 영역은 항상 정사각형
 * 한 변의 길이가 K인 정사각형 영역에 대해 단 한 번만 굴착 가능
 * 최대한 많은 금강석이 포함되도록 굴착하기
 * 변에 놓인 금강석도 정사각형에 포함된 것으로 간주
 */
public class BJ_2492_보석 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");
        int N = stoi(inputs[0]); // 지도 너비
        int M = stoi(inputs[1]); // 지도 높이
        int T = stoi(inputs[2]); // 금강석 개수
        int K = stoi(inputs[3]); // 정사각형 크기

        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < T; i++) {
            inputs = br.readLine().split(" ");
            int x = stoi(inputs[0]);
            int y = stoi(inputs[1]);

            list.add(new int[]{x, y});
        }

        Collections.sort(list, (o1, o2) -> { // 행 내림차순, 열 오름차순
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }

            return o2[1] - o1[1];
        });

        int x = 0, y = 0, max = 0;


        for (int i = 0; i < list.size(); i++) {
            // 기준 좌표 1 - 상단
            int a1 = list.get(i)[0];
            int b1 = list.get(i)[1];

            for (int j = 0; j < list.size(); j++) {
                int cnt = 0;

                // 기준 좌표 2 - 좌측
                int a2 = list.get(j)[0];
                int b2 = list.get(j)[1];

                // 두 좌표가 상단 변과 좌측 변에 걸치도록 시작 좌표 설정
                int a = Math.min(a1, a2);
                int b = Math.max(b1, b2);

                // 범위를 벗어나거나 동일한 경우에도 설정되도록 ...
                if (a + K > N) {
                    a = N - K;
                }

                if (b - K < 0) {
                    b = K;
                }

//                System.out.printf("%d, %d | %d, %d -> %d, %d\n", a1, b1, a2, b2, a, b);
                for (int[] point : list) {
                    if (check(point[0], point[1], a, b, K)) {
                        cnt++;
                    }
                }

                if (max < cnt) {
                    max = cnt;
                    x = a;
                    y = b;
                }
            }
        }

        System.out.printf("%d %d\n%d", x, y, max);
    }

    public static boolean check(int x, int y, int a, int b, int K) {
        if (x < a || x > a + K || y > b || y < b - K) return false;

        return true;
    }

    public static int stoi (String str){
        return Integer.parseInt(str);
    }
}
