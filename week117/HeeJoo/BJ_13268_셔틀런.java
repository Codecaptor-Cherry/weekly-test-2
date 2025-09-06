package saturday.year25.sat250906;

import java.util.Scanner;

/*
 * 시작지점부터 약 5m의 간격을 두고 직선 상으로 총 4개의 연습용 콘을 세워둔다.
 * 0(시작), 5, 10, 15, 20
 * 0 - 구간1 - 구간2 - 구간3 - 구간4
 * 셔틀런의 순서는 다음과 같다. (한 세트)
 * 1. 시작 - 5 - 시작
 * 2. 시작 - 10 - 시작
 * 3. 시작 - 15 - 시작
 * 4. 시작 - 20 - 시작
 * 쉬는 시간 없이 위 과정을 반복한다.
 * 달릴 수 있는 거리가 미터로 주어졌을 때, 어느 구간에서 멈추는지 구하기
 *
 *
 * 5+5 + 10+10 + 15+15 + 20+20 = 100
 */
public class BJ_13268_셔틀런 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt() % 100; // 총 이동 거리
        int ans = 0;

        for (int i = 1; i < 5 && n > 0; i++) { // 1 ~ 4구간
            for (int j = 0; j < i && n > 0; j++) { // 1 -> 4구간 ... 한 구간씩 이동
                n -= 5;
                ans++;
            }

            if (n <= 0) { // 이동거리가 부족하면 stop
                break;
            }

            for (int j = 0; j < i && n > 0; j++) { // 4 -> 1구간 ... 한 구간씩 이동
                n -= 5;
                if (n >= 0) { // 이동거리가 부족하면 stop
                    ans--;
                }
            }
        }

        System.out.println(ans);
    }
}
