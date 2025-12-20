package saturday.year25.sat251206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 2 x N 좌표
 * 각 좌표마다 초콜릿 가게가 하나씩 있음
 * 코코의 가게는 (a, b)
 * 한별의 가게는 (c, d)
 * x좌표와 y좌표의 차이가 모두 1 이하인 두 가게는 도로가 놓여 있어 이동 가능 ~ 인접 8방향
 * 1. 코코의 가게에서 시작해 한별의 가게에서 끝난다.
 * 2. 각 초콜릿 가게는 최대 한 번만 방문한다.
 * 모든 초콜릿 가게에서 파는 초콜릿의 맛을 점수로 매겨 놓음
 * 방문한 모든 초콜릿 가게에서 파는 초콜릿 점수 합의 최대값 구하기
 *
 * 왼쪽 <-시작 ~ 중앙 ~ 끝-> 오른쪽
 * 중앙 : 필수로 방문해야 함
 * 왼쪽, 오른쪽 : 선택 방문
 * 선택 방문을 할 때 되돌아오기 위해 필수적으로 방문해야 하는 구간이 존재함을 유의
 * 예시
 * 1  2  3  S  5  6  7  8  9  10
 * 11 12 13 14 15 16 E  18 19 10
 * S에서 시작해 11을 방문하려면, (2, 3, 12 ,13)과 14를 필수 방문해야 함
 * E에서 시작해 19를 방문하려면, (8, 18)과 7을 필수 방문해야 함
 * 7과 14를 방문해야만 왼쪽/오른쪽 확장했다가 중앙으로 돌아올 수 있음 why? 각 지점은 한 번만 방문 가능하기 때문에 S/E로 돌아갈 수 없음
 * 왼쪽 확장은 i열과 S열 사이, 오른쪽 확장은 E열과 j열 사이 구간을 모두 방문해야 함
 *
 * 참고 : https://codingjj.tistory.com/277
 * 실수 : 왼쪽/오른쪽 확장을 할 때, 자꾸 S/E를 끝점으로 생각해서 같은 열을 방문 안해도 된다고 착각
 */
public class BJ_31461_최고의초콜릿성지순례 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = stoi(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int N = stoi(br.readLine());

            int[][] arr = new int[2][N];

            for (int i = 0; i < 2; i++) {
                String[] inputs = br.readLine().split(" ");

                for (int j = 0; j < N; j++) {
                    arr[i][j] = stoi(inputs[j]);
                }
            }

            String[] inputs = br.readLine().split(" ");

            int a = stoi(inputs[0]) - 1;
            int b = stoi(inputs[1]) - 1;
            int c = stoi(inputs[2]) - 1;
            int d = stoi(inputs[3]) - 1;

            if (a > c) { // 항상 왼쪽부터 시작할 수 있도록...
                int tmp = a;
                a = c;
                c = tmp;
                tmp = b;
                b = d;
                d = tmp;
            }

            // 입력 끝
            // 왼쪽
            long max = 0; // 지금까지 방문 최댓값
            long left = 0; // 왼쪽 구간 최댓값
            for (int i = a - 1; i >= 0; i--) { // 왼쪽으로 2칸 이상 떨어진 곳 방문
                max += Math.max(arr[0][i], arr[1][i]); // 큰 값 방문
                left = Math.max(left, max); // i열에서 큰 곳만 방문
                max += Math.min(arr[0][i], arr[1][i]); // 작은 값 방문
                // 왜 큰 값, 작은 값 둘 다?
                // 1. 위, 아래, 모두 중 최댓값을 가지는 경우 방문
                // 2. 다음 이동을 위해 모두 방문해야 함
                left = Math.max(left, max);
            }
            // 오른쪽
            max = 0;
            long right = 0;
            for (int i = c + 1; i < N; i++) {
                max += Math.max(arr[0][i], arr[1][i]);
                right = Math.max(right, max);
                max += Math.min(arr[0][i], arr[1][i]);
                right = Math.max(right, max);
            }

            // 중앙
            long mid = 0;
            long ans = arr[b][a] + arr[d][c]; // 기본적으로 방문
            if (a == c) { // 코코와 한별이의 가게가 같은 열인 경우 중앙(필수 방문 구간) 없음
                ans += Math.max(left, right); // 둘 중 한 방향으로만 확장 가능
            } else {
                // 필수 방문 구간 : 위, 아래, 모두 방문 ~ 3가지 케이스 중 최댓값
                for (int i = a + 1; i < c; i++) {
                    mid += Math.max(arr[0][i] + arr[1][i], Math.max(arr[0][i], arr[1][i]));
                }

                // Math.max(확장 X, 확장 O)
                // 확장 X : arr[x][y]만 방문한 경우... ans 초기 선언에 포함되어 있음
                // 확장 O : 확장 방향의 최댓값 + arr[1 - x][y]... 최댓값이 음수인 경우 left/right = 0이므로 arr[1 - x][y]값만 계산됨
                ans += Math.max(0, left + arr[1 - b][a]);
                ans += Math.max(0, right + arr[1 - d][c]);
                ans += mid;
            }

            sb.append(ans).append(" ");
        }

        System.out.println(sb);
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
