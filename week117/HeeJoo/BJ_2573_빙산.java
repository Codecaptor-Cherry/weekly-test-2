package saturday.year25.sat250906;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 2차원 배열에 표시된 빙산의 높이... 각 칸에 양의 정수로 저장... 빈 칸은 0
 * 빙산은 해당 칸의 동서남북 네 방향으로 붙어있는 0이 저장된 칸(빈 칸)의 개수만큼 높이가 감소한다.
 * 단, 각 칸에 저장된 높이는 0보다 더 줄어들지 않는다.
 * 동서남북 방향으로 붙어있는 칸들은 서로 연결된 상태이다.
 * 한 덩어리의 빙산이 주어질 때, 두 덩어리 이상으로 분리되는 최초의 시간 구하기
 * 전부 녹을 때까지 두 덩어리 이상으로 분리되지 않으면 0 출력
 *
 * 1. 빙산 녹이기 -> 순차적으로 녹이는 게 아니라 한 타이밍에 녹여야 함... 같은 시간에 녹는 빙산에 영향받지 않도록 마지막에 녹이기
 * 2. 덩어리 구하기
 */
public class BJ_2573_빙산 {
    static int N, M;
    static int[] dirX = new int[]{0, 0, 1, -1}; // 동서남북
    static int[] dirY = new int[]{1, -1, 0, 0}; // 동서남북
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());

        int[][] map = new int[N][M];
        int sum = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = stoi(st.nextToken());
                sum += map[i][j];
            }
        }

        int ans = 0;
        int piece = 0;

//        print(map);
        while(piece < 2 && (sum -= simul(map)) > 0) { // 한 덩어리 && 더 녹을 빙산 존재
//            print(map);

            piece = checkPiece(sum, map, new boolean[N][M]); // 덩어리 개수 확인
            ans++; // 시간 경과
        }

        if (piece < 2) { // 주의 !! 마지막에 두 덩어리 이상이 안되면 0 출력
            System.out.println(0);
        } else {
            System.out.println(ans);
        }
    }

    public static void makePiece(int x, int y, int[][] map, boolean[][] visited) {
//        System.out.printf("덩어리 ... (%d, %d)\n", x, y);
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while(!queue.isEmpty()) {
            int a = queue.peek()[0];
            int b = queue.poll()[1];

            for (int i = 0; i < 4; i++) {
                int da = a + dirX[i];
                int db = b + dirY[i];

                if(!checkRange(da, db) || map[da][db] == 0 || visited[da][db]) {
                    continue;
                }

                visited[da][db] = true;
                queue.offer(new int[]{da, db});
            }
        }
    }

    public static int checkPiece(int sum, int[][] map, boolean[][] visited) {
        int piece = 0; // 덩어리 개수
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 || visited[i][j]) { // 빈 칸 or 방문 완료
                    continue;
                }

                makePiece(i, j, map, visited); // (i, j)를 시작으로 하는 덩어리 체크
                piece++;
            }
        }

        return piece;
    }

    public static int melt(int x, int y, int[][] map) {
        int sum = 0;

        for (int i = 0; i < 4; i++) { // 동서남북
            int dx = x + dirX[i];
            int dy = y + dirY[i];

            if (!checkRange(x, y)) { // 범위 내
                continue;
            }

            if (map[dx][dy] == 0) { // 빈 칸인 경우 녹임
                sum++;
            }

            if (map[x][y] == sum) { // 좌표값은 0 미만이 될 수 없음
                break;
            }
        }

        return sum;
    }

    public static int simul(int[][] map) {
        int sum = 0;

        Queue<int[]> queue = new ArrayDeque<>(); // 이번 타이밍에 녹을 빙산의 좌표 정보와 녹는 정도 저장

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) { // 빙산이 아닌 경우 pass
                    continue;
                }

                int cnt = melt(i, j, map);
                sum += cnt; // 동서남북 빈 칸 계산

                queue.offer(new int[]{i, j, cnt}); // (i, j)의 빙산은 주변 빈 칸만큼 녹음

            }
        }

        // 다른 빙산에 영향이 가지 않도록 마지막에 다 같이 녹임
        while(!queue.isEmpty()) {
            int x = queue.peek()[0];
            int y = queue.peek()[1];
            int cnt = queue.poll()[2];

            map[x][y] -= cnt;
        }

        return sum; // 총 녹인 빙산의 높이
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

    public static boolean checkRange(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= M) {
            return false;
        }

        return true;
    }
    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
