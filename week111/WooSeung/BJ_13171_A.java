
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final long MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long A = Long.parseLong(br.readLine());
        long X = Long.parseLong(br.readLine());


        long ans = pow(A, X);
        System.out.println(ans);
    }

    static long pow(long A, long X) {
        long ans = 1;
        A %= MOD;
        while(X > 0) {
            if(X % 2 == 1) {
                ans = (ans * A) % MOD;
            }
            A = (A * A) % MOD;
            X /= 2;
        }
        return ans;
    }

}
