package saturday.year25.sat250927;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 1일 ~ 365일 달력
 * 다음 규칙에 맞춰 코팅지 붙이기
 * 1. 연속된 두 일자에 각각 일정이 1개 이상 있다면 이를 일정이 연속되었다고 표현
 * 2. 연속된 모든 일정은 하나의 직사각형에 포함
 * 3. 연속된 일정을 모두 감싸는 가장 작은 직사각형의 크기만큼 코팅지를 오려 붙이기
 *
 * 달력은 다음과 같은 규칙을 따른다.
 * 1. 일정은 시작날짜와 종료날짜 포함
 * 2. 시작일이 가장 앞선 일정부터 차례대로 채우기
 * 3. 시작일이 같을 경우 일정의 기간이 긴 것이 먼저 채워짐
 * 4. 일정은 가능한 최상단에 배치
 * 5. 일정 하나의 세로 길이는 1
 * 6. 하루의 폭은 1
 *
 * 필요한 코팅지의 면적 구하기
 *
 * 1. 2차원 배열에 일정 채우기
 * 2. 코팅지 면적 구하기
 */

class Plan implements Comparable<Plan> {
    int start, end;

    Plan(int start, int end) {
        this.start = start;
        this.end = end;
    }


    @Override
    public int compareTo(Plan o) {
        if (this.start == o.start) { // 시작일이 같을 경우 일정이 긴 것을 먼저 채우기
            return (o.end - o.start) - (this.end - this.start);
        }

        return this.start - o.start; // 가장 앞선 일정부터 채우기
    }
}
public class BJ_20207_달력 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = stoi(br.readLine()); // 일정의 개수
        PriorityQueue<Plan> pq = new PriorityQueue<>();

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int start = stoi(st.nextToken());
            int end = stoi(st.nextToken());

            pq.offer(new Plan(start, end));
        }

        int[][] map = new int[N + 1][365 + 1];
        int maxIdx = 0;
        while(!pq.isEmpty()) {
            Plan plan = pq.poll();

            int start = plan.start;
            int end = plan.end;

            int idx = 0;
            while (map[idx][start] != 0) {
                idx++;
            }

            for (int i = start; i <= end; i++) {
                map[idx][i] = 1;
            }

            maxIdx = Math.max(maxIdx, idx);
        }

        for (int i = 1; i <= 365; i++) {
            for (int j = 1; j <= maxIdx; j++) {
                map[j][i] += map[j - 1][i];
            }
        }

        int ans = 0;
        int height = 0, width = 0;
        int day = 1;
        while (day <= 365) {
            if (map[maxIdx][day] != 0) {
                width = 0;
                height = 0;
                while (day <= 365 && map[maxIdx][day] != 0) {
                    height = Math.max(height, map[maxIdx][day]); // 이거 먼저 해야 Exception 발생 xxx day++때문에 범위 벗어날 수 있음
                    day++;
                    width++;
                }

                ans += height * width;
            }

            day++;
        }

        System.out.println(ans);

    }

    public static void print(int[][] map) {
        for (int[] arr : map) {
            for (int k : arr) {
                System.out.printf("%d ", k);
            }
            System.out.println();
        }
        System.out.println();
    }
    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
