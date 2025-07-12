package saturday.year25.sat250712;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/*
 * 차는 엘리베이터를 이용해서 층 사이 이동
 * 각 층에는 원형 컨베이어 벨트 존재 ... 벨트 위에 차가 있음
 * 벨트는 시계 방향 또는 반시계 방향으로 움직일 수 있음
 * 사람들은 도착한 순서대로 차를 찾을 수 있음
 * 1. 엘베가 차가 있는 층으로 이동 - 층 * 10초
 * 2. 벨트가 차를 싣고 엘베로 이동 - 회전 수 * 5초
 * 3. 고객에게 차 전달 - 층 * 10초
 * 모든 손님이 차를 찾는데 소요되는 시간 구하기
 */

class Car {
    int num, x, y;

    Car (int num, int x, int y) {
        this.num = num;
        this.x = x;
        this.y = y;
    }
}
public class BJ_3699_주차빌딩 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = stoi(br.readLine()); // 테스트 케이스 개수 ~ 최대 100개

        for (int t = 0; t < T; t++) {
            String[] inputs = br.readLine().split(" ");
            int h = stoi(inputs[0]); // 주차 빌딩 높이
            int l = stoi(inputs[1]); // 컨베이어 벨트의 길이

            int[][] parking = new int[h][l];
            PriorityQueue<Car> pq = new PriorityQueue<>((o1, o2) -> o1.num - o2.num);
            for (int i = 0; i < h; i++) {
                inputs = br.readLine().split(" ");
                for (int j = 0; j < l; j++) {
                    int num = stoi(inputs[j]);
                    parking[i][j] = num;

                    if(num != -1) {
                        pq.offer(new Car(num, i, j));
                    }
                }
            }

        // -------------------------------------------------------
            int sum = 0;

            int[] status = new int[h]; // 각 층의 벨트 위치

            while(!pq.isEmpty()) {
                Car car = pq.poll(); // 차를 찾으러 온 손님

                sum += car.x * 20;

                int gap = Math.abs(car.y - status[car.x]);
                gap = Math.min(gap, l - gap);

                sum += gap * 5;

                status[car.x] = car.y;
            }

            sb.append(sum + "\n");
        }

        System.out.println(sb);

    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
