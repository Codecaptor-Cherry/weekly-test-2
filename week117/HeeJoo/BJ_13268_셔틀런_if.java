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
public class BJ_13268_셔틀런_if {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt(); // 총 이동 거리

        while (n / 100 > 0) {
            n %= 100;
        }

        if (n == 0) {
            System.out.println(0);
            return;
        }

        // 1런
        if (n < 10) {
            System.out.println(1);
        } else if (n == 10) {
            System.out.println(0);
        }
        // 2런
        else if (n <= 15) {
            System.out.println(1);
        } else if (n <= 20) {
            System.out.println(2);
        } else if (n < 25) {
            System.out.println(2);
        } else if (n < 30) {
            System.out.println(1);
        } else if (n == 30) {
            System.out.println(0);
        }
        // 3런
        else if (n <= 35) {
            System.out.println(1);
        } else if (n <= 40) {
            System.out.println(2);
        } else if (n <= 45) {
            System.out.println(3);
        } else if (n < 50) {
            System.out.println(3);
        } else if (n < 55) {
            System.out.println(2);
        } else if (n < 60) {
            System.out.println(1);
        } else if (n == 60) {
            System.out.println(0);
        }
        // 4런
        else if (n <= 65) {
            System.out.println(1);
        } else if (n <= 70) {
            System.out.println(2);
        } else if (n <= 75) {
            System.out.println(3);
        } else if (n <= 80) {
            System.out.println(4);
        } else if (n < 85) {
            System.out.println(4);
        } else if (n < 90) {
            System.out.println(3);
        } else if (n < 95) {
            System.out.println(2);
        } else if (n < 100) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}
