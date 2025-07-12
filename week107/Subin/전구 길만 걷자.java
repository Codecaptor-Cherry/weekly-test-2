import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int n, ans = Integer.MAX_VALUE;
    private static String[] bulb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        bulb = new String[n];

        int chg = 0;
        for (int i = 0; i < n; i++) {
            bulb[i] = br.readLine();
            for (int j = 1; j < bulb[i].length(); j++) {
                if (bulb[i].charAt(j) != bulb[i].charAt(j - 1)) ++chg;
            }
        }

        perm(' ', chg, 0, new boolean[n]);
        System.out.println(ans);
    }

    private static void perm(char beforeBulb, int chg, int cnt, boolean[] selected) {
        if (cnt == n) {
            ans = Math.min(ans, chg);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!selected[i]) {
                selected[i] = true;
                perm(bulb[i].charAt(bulb[i].length() - 1),
                        chg + (beforeBulb == ' ' || beforeBulb == bulb[i].charAt(0) ? 0 : 1),
                        cnt + 1, selected);
                selected[i] = false;
            }
        }
    }
}
