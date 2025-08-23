import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_02571_색종이3 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numOfPaper = Integer.parseInt(br.readLine());
        int[][] map = new int[100][100];
        int result = 0;

        for (int i = 0; i < numOfPaper; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            for (int j = x; j < x + 10; j++) {
                for (int k = y; k < y + 10; k++) {
                    map[j][k] = 1;
                }
            }
        }

        for (int i = 0; i < 100; i++) {
            for (int j = 1; j < 100; j++) {
                if (map[j-1][i] > 0 && map[j][i] > 0) {
                    map[j][i] = map[j-1][i] + 1;
                }
            }
        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                int h = map[i][j];

                for (int k = j; k < 100; k++) {
                    h = Math.min(h, map[i][k]);
                    result = Math.max(result, h * (k - j + 1));
                    if (h == 0) break;
                }
            }
        }

        System.out.print(result);
    }
}
