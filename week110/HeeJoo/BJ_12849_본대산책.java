package saturday.year25.sat250621;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * 본대 : 캠퍼스
 * 정보대 : 과학관
 * 과학관에서 항상 본대를 가고 싶음
 * 인접 건물로 이동하는 데 소요되는 시간은 1분
 * D분 동안 산책할 때, 가능한 경로의 경우의 수 구하기(D분일 때, 과학관에 도착해야 함)
 * 지도는 고정
 *
 * 과학관 출발 - 과학관 도착 ~ D분
 * D - 1분일 때 전산관 또는 미래관
 * A -> B로 가는 데 필요한 최소 시간
 */
public class BJ_12849_본대산책 {
    static final int MOD = (int)1e9 + 7;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int D = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] list = new ArrayList[8]; // 지도 고정
        for (int i = 0; i < 8; i++) {
            list[i] = new ArrayList<>();
        }

        list[0].addAll(Arrays.asList(1, 2));
        list[1].addAll(Arrays.asList(0, 2, 3));
        list[2].addAll(Arrays.asList(0, 1, 3, 5));
        list[3].addAll(Arrays.asList(1, 2, 4, 5));
        list[4].addAll(Arrays.asList(3, 5, 6));
        list[5].addAll(Arrays.asList(2, 3, 4, 7));
        list[6].addAll(Arrays.asList(4, 7));
        list[7].addAll(Arrays.asList(5, 6));

        // -------------------------------------------------------------------

        // n번 건물에 올 수 있는 경우의 수
        // 0번이 과학관
        long[] dp = new long[8];
        dp[0] = 1; // 과학관 출발

        for (int i = 1; i <= D; i++) { // 1초부터 D초까지
            long[] tmp = new long[8]; // i초에 n번 건물에 도착하는 경우의 수

            for (int j = 0; j < 8; j++) {
                for (int k : list[j]) {
                    // 현재 dp는 i-1초에 n번 건물에 도착하는 경우의 수
                    // j번 건물과 인접한 건물들의 경우의 수를 합산하면
                    // i초에 j번 건물에 도착하는 경우의 수가 됨
                    tmp[j] += dp[k];
                }

                tmp[j] %= MOD;
            }

            dp = tmp; // i초에 n번 건물에 도착하는 경우의 수로 dp 갱신
        }

        System.out.println(dp[0]);
    }

}
