import java.io.IOException;
import java.util.*;

public class Main {

    private static int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long x = sc.nextLong();

        long s = a;
        long ans = 1;

        char[] binaryChar = Long.toBinaryString(x).toCharArray();
        for (int i = binaryChar.length - 1; i >= 0; i--) {
            s %= MOD;
            if (binaryChar[i] == '1') {
                ans *= s;
                ans %= MOD;
            }
            s *= s;
        }

        System.out.println(ans);
    }
}
A
