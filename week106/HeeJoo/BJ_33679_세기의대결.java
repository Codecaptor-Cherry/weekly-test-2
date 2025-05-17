package saturday.year25.sat250517;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 총알 N발 장전
 * 각 총알에는 위력을 나타내는 수가 적혀 있음
 * 사격을 시작할 총알의 위치를 자유롭게 선택 가능
 * 매 턴 총알 한 발로 보스 몬스터를 사격하거나 허공 사격 가능
 * 사격 후 약실이 시계방향으로 한 칸 회전
 * 보스 몬스터의 초기 방어력은 0. 피격될 때마다 총알의 위력과 동일한 수치의 방어력으로 설정
 * 보스 몬스터의 방어력 이하의 위력을 가진 총알을 사격하게 되면 플레이어 사망
 * 보스 몬스터에게 총알 한 발을 사격할 때마다 1점 획득. 사망하는 경우 그 즉시 0점이 됨
 * 총알을 모두 사격하거나 사망한 경우 게임 종료
 * YJ와 HG 중 누가 더 높은 점수를 얻는지 구하기
 *
 * 1. 시작지점 설정
 * 2. 사격 여부 결정 ~ 점수 획득
 *
 * 시간이 좀 많이 걸림... 이분탐색 적용해서 어케 푸는거지 ㅇㅅㅇ...
 */
public class BJ_33679_세기의대결 {
    static int N;
    static long YJScore = 0, HGScore = 0;
    static int[] YJs, HGs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = stoi(br.readLine()); // 총알의 개수

        // YJ와 HG의 약실 상태
        YJs = new int[N];
        HGs = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            YJs[i] = stoi(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            HGs[i] = stoi(st.nextToken());
        }

        // -----------------------------------------------------

        long[] YJdp = new long[N + 1];
        YJdp[1] = 1;
        long[] HGdp = new long[N + 1];
        HGdp[1] = 1;

        for (int i = 2; i <= N; i++) {
            if(YJs[i - 1] > YJs[i - 2]) {
                YJdp[i] = YJdp[i - 1] + 1;
            } else {
                YJdp[i] = YJdp[i - 1];
            }

            if(HGs[i - 1] > HGs[i - 2]) {
                HGdp[i] = HGdp[i - 1] + 1;
            } else {
                HGdp[i] = HGdp[i - 1];
            }
        }

        for (int i = 0; i < N; i++) {
            YJScore = Math.max(YJScore, simul(i, YJs, new long[N + 1]));
            HGScore = Math.max(HGScore, simul(i, HGs, new long[N + 1]));
//            System.out.printf("%d | %d\n",YJScore, HGScore);
        }


        if (YJScore > HGScore) {
            System.out.println("YJ Win!");
        } else if(YJScore < HGScore) {
            System.out.println("HG Win!");
        } else {
            System.out.println("Both Win!");
        }
    }

    public static long simul(int idx, int[] arr, long[] dp) {
        dp = new long[N];
        Arrays.fill(dp, 1);

        long max = 1;
        for (int i = idx + 1; i < idx + N; i++) {
            for(int j = i - 1; j >= idx; j--) {
                if(arr[i % N] > arr[j % N]) {
                    dp[i % N] = Math.max(dp[i % N], dp[j % N] + 1);
                }
            }

            max = Math.max(max, dp[i % N]);
        }

//        System.out.println(Arrays.toString(dp));
        return max;

    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
