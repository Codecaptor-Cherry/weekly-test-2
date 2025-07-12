import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            int[][] container = new int[h][l];

            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < l; j++) {
                    container[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[] beltPos = new int[h];
            int elevFloor = 0;
            int seq = 1;
            int ans = 0;

            int[] carPos; // 0: elev, 1: container
            while ((carPos = findCar(container, seq++)) != null) {
                // 현재 위치 > 차 위치 > 고객 위치(1층)
                int floorMove = (Math.abs(carPos[0] - elevFloor) + carPos[0]) * 10;

                int cw = (carPos[1] - beltPos[carPos[0]] + l) % l;
                int ccw = l - cw;
                int beltMove = Math.min(cw, ccw) * 5;

                ans += floorMove + beltMove;
                
                elevFloor = 0;
                beltPos[carPos[0]] = carPos[1];
            }

            System.out.println(ans);
        }
    }

    private static int[] findCar(int[][] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == num) {
                    return new int[] {i, j};
                }
            }
        }
        return null;
    }
}
