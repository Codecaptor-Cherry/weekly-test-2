import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_17359_전구길만걷자 {

    static int n;
    static int answer = Integer.MAX_VALUE;
    static String[] bulbs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        bulbs = new String[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            bulbs[i] = br.readLine();
            for (int j = 1; j < bulbs[i].length(); j++) {
                if (bulbs[i].charAt(j) != bulbs[i].charAt(j - 1)) {
                    count++;
                }
            }
        }

        permutation(' ', 0, count, 0);
        System.out.print(answer);
    }

    static void permutation(char beforeBulb, int cur, int count, int visited) {
        if (cur == n) {
            answer = Math.min(answer, count);
            return;
        }

        for (int i = 0; i < n; i++) {
            if ((visited & (1 << i)) == 0) {
                char firstBulb = bulbs[i].charAt(0);
                char lastBulb = bulbs[i].charAt(bulbs[i].length() - 1);
                permutation(
                        lastBulb,
                        cur + 1,
                        count + (beforeBulb == ' ' || beforeBulb == firstBulb ? 0 : 1),
                        visited | (1 << i)
                );
            }
        }
    }
}
