import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_15658_연산자끼워넣기_2 {

    static int N;
    static Long Max, Min;
    static int[] NumOfOperator;
    static long[] Array;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        Array = Arrays.stream(bufferedReader.readLine().split(" "))
                             .mapToLong(Long::parseLong)
                             .toArray();
        NumOfOperator = Arrays.stream(bufferedReader.readLine().split(" "))
                              .mapToInt(Integer::parseInt)
                              .toArray();

        Max = Long.MIN_VALUE;
        Min = Long.MAX_VALUE;

        dfs(1, Array[0]);
        System.out.println(Max);
        System.out.println(Min);
    }

    public static void dfs(int index, Long sum) {
        if (index == N) {
            Max = Math.max(Max, sum);
            Min = Math.min(Min, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (NumOfOperator[i] == 0) continue;
            NumOfOperator[i]--;

            switch(i) {
                case 0:
                    dfs(index + 1, sum + Array[index]);
                    break;
                case 1:
                    dfs(index + 1, sum - Array[index]);
                    break;
                case 2:
                    dfs(index + 1, sum * Array[index]);
                    break;
                case 3:
                    dfs(index + 1, sum / Array[index]);
                    break;
                default:
                    break;
            }

            NumOfOperator[i]++;
        }
    }
}
