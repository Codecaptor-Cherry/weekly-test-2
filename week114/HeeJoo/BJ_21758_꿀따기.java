package saturday.year25.sat250726;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 좌우 N개의 장소
 * 서로 다른 두 곳을 골라서 벌을 한 마리씩 둠
 * 또, 다른 한 장소를 골라서 벌통을 둠
 * 두 마리 벌은 벌통으로 똑바로 날아가면서 지나가는 모든 칸에서 꿀을 땀
 * 벌이 시작한 장소에서는 어떤 벌도 꿀 채집 불가
 * 벌통은 꿀 채집 가능
 * 벌들이 딸 수 있는 최대 꿀 양 구하기
 *
 * 1. 벌1, 벌2, 꿀통 위치 정하기
 * -> 조합으로 위치를 구하면 시간 초과
 * -> 양 끝을 고정해야 꿀을 최대로 채집 가능 why ? 낭비하는 칸이 없음
 * 
 * 2. 벌들과 꿀통의 위치 관계에 따라 채집량 계산
 * 꿀통, 벌1, 벌2
 * 벌1, 꿀통, 벌2
 * 벌1, 벌2, 꿀통
 */
public class BJ_21758_꿀따기 {
    static int N, ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = stoi(br.readLine());

        int[] arr = new int[N];
        String[] inputs = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = stoi(inputs[i]);
        }

        // --------------------------------------------------------------------

        int[] left = new int[N]; // 좌 -> 우
        int[] right = new int[N]; // 좌 <- 우

        left[0] = arr[0];
        right[N - 1] = arr[N - 1];

        for (int i = 1; i < N; i++) {
            left[i] = left[i - 1] + arr[i];
            right[N - 1 - i] = right[N - i] + arr[N - 1 - i];
        }

        for (int i = 1; i < N - 1; i++) {
            int a = 0, b = i, c = N - 1; // a < b < c
            ans = Math.max(ans, gathering(a, b, c, arr, left, right));
        }

        System.out.println(ans);
    }
    
    public static int gathering(int a, int b, int c, int[] arr, int[] left, int[] right) {
        int result = 0;

        // 꿀통, 벌1, 벌2
        result += right[a] - right[b];
        result += right[a] - right[c] - arr[b]; // 벌1이 시작한 곳은 채집 불가능

        // 벌1, 꿀통, 벌2
        result = Math.max(result, left[b] - left[a] + right[b] - right[c]);

        // 벌1, 벌2, 꿀통
        result = Math.max(result, left[c] - left[a] - arr[b] + left[c] - left[b]); // 벌2가 시작한 곳은 채집 불가능

        return result;
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
