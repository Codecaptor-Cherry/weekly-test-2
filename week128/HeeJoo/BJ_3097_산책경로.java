package saturday.year25.sat251206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 경로는 여러 개의 선분으로 이루어져 있음
 * 각 선분은 두 정수 (X, Y)로 나타낼 수 있으며, 이동하는 방향과 거리를 의미
 * 경로에 있는 선분 중 하나를 제거해 시작 위치와 마지막 위치 사이의 거리를 최소로 만들려고 함
 * 1. 초기 경로일 때, 시작 위치와 마지막 위치의 거리 차이 구하기
 * 2. 선분 하나를 제거했을 때, 시작 위치와 마지막 위치의 거리 차이 최솟값 구하기
 *
 * 1. 경로 완성 - 위치 차이
 * 2. 선분 하나를 선택 후 제거 - 위치 차이
 */
public class BJ_3097_산책경로 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = stoi(br.readLine());
        int[] arrX = new int[N];
        int[] arrY = new int[N];

        int x = 0, y = 0;
        for (int i = 0; i < N; i++) {
            String[] inputs = br.readLine().split(" ");

            int dx = stoi(inputs[0]);
            int dy = stoi(inputs[1]);

            arrX[i] = dx;
            arrY[i] = dy;

            x += dx;
            y += dy;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(x).append(" ").append(y).append("\n"); // 산책을 마치는 위치

        double ans = Double.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            // i번째 선분 제거
            x -= arrX[i];
            y -= arrY[i];

            // 거리 계산
            ans = Math.min(ans, Math.sqrt(manhattan(0, 0, x, y)));

            // 선분 복구
            x += arrX[i];
            y += arrY[i];
        }

        sb.append(String.format("%.2f", ans));

        System.out.println(sb);

    }

    public static int manhattan(int a, int b, int x, int y) {
        return ((a - x) * (a - x)) + ((b - y) * (b - y));
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
