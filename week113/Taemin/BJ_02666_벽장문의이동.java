import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_02666_벽장문의이동 {

    static int answer = Integer.MAX_VALUE;
    static int numOfWall, first, second, third;
    static int[] sequence;

    public static void main(String[] args) throws IOException {
        getInputs();
        count(0, 0, first, second);
        System.out.print(answer);
    }

    static void count(int cur, int count, int left, int right) {
        if (count > answer) return;

        if (cur == third) {
            answer = Math.min(answer, count);
            return;
        }

        int wall = sequence[cur];
        count(cur + 1, count + Math.abs(left - wall), wall, right);
        count(cur + 1, count + Math.abs(right - wall), left, wall);
    }

    static void getInputs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        numOfWall = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        first = Integer.parseInt(st.nextToken());
        second = Integer.parseInt(st.nextToken());
        third = Integer.parseInt(br.readLine());

        sequence = new int[third];
        for (int i = 0; i < third; i++) {
            sequence[i] = Integer.parseInt(br.readLine());
        }
    }
}
