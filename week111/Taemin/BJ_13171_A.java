import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_13171_A {

    static Long MOD = 1_000_000_007L;
    static Long[] Cache = new Long[64];

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Long a = Long.parseLong(bufferedReader.readLine());
        Long x = Long.parseLong(bufferedReader.readLine());

        // a에 대한 2의 0승 연산 결과 저장
        Cache[0] = a % MOD;
        for (int i = 1; i < 64; i++) {
            // a에 대한 2의 i승 연산 결과 저장
            Cache[i] = (Cache[i-1] * Cache[i-1]) % MOD;
        }

        Long result = 1L;
        for (int i = 63; i >= 0; i--) {
            // 64비트를 사용하기 때문에 쉬프트 연산에서도 Long 타입 지정 요망
            if ((x & (1L << i)) != 0) {
                // a에 대한 2의 i승이 걸리는 경우 계산 누적 진행
                result = (result * Cache[i] % MOD);
            }
        }

        System.out.print(result);
    }
}
