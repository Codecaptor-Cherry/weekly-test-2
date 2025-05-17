package saturday.year25.sat250517;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 최대한 많은 곡을 연주하려고 할 때, 필요한 기타의 최소 개수 구하기
 * 기타마다 연주할 수 있는 곡이 다름
 *
 * N이 10 이하니까 조합으로 풀이
 */
public class BJ_1497_기타콘서트 {
    static int N, M;
    static int ans = (int)1e9;
    static int songCnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = stoi(st.nextToken()); // 기타의 개수. 10 이하 자연수
        M = stoi(st.nextToken()); // 곡의 개수. 50 이하 자연수

        List<Integer>[] list = new ArrayList[N]; // i번째 기타의 연주 가능 곡
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String guitar = st.nextToken(); // 기타 이름
            String songs = st.nextToken(); // 곡 연주 가능 여부
            list[i] = new ArrayList<>();
            for (int j = 0; j < M; j++) {
                if(songs.charAt(j) == 'Y') {
                    list[i].add(j);
                }
            }
        }

        // --------------------------------------------------------------

        subSet(0, new int[N], list);

        System.out.println(songCnt == 0 ? -1 : ans);
    }

    public static void compute(int[] guitars, List<Integer>[] list) {
        int guitarCnt = 0; // 사용한 기타 개수

        int[] arr = new int[M];
        for (int i = 0; i < N; i++) {
            if(guitars[i] == 0) {
                continue;
            }
            guitarCnt++;
            for (int k : list[i]) {
                arr[k] = 1;
            }
        }

        int sum = 0; // 연주 가능한 곡의 개수
        for (int k : arr) {
            sum += k;
        }

        if(sum >= songCnt) {
            ans = Math.min(ans, guitarCnt);
            songCnt = sum;
        }
    }
    public static void subSet(int idx, int[] guitars, List<Integer>[] list) {
        if(idx == N) {
            compute(guitars, list);
            return;
        }

        guitars[idx] = 1; // idx번째 기타 사용
        subSet(idx + 1, guitars, list);
        guitars[idx] = 0; // idx번째 기타 미사용
        subSet(idx + 1, guitars, list);
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
