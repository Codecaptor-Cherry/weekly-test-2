import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 1 ~ N번의 사과나무가 일렬로 존재
 * 초기 높이는 모두 0
 * 2개의 물뿌리개 > 1개는 나무 하나를 1만큼 성장, 다른 하나는 나무 하나를 2만큼 성장 시킴
 * 두 물뿌리개는 동시에 사용해야 하며, 나무가 없는 곳에는 사용할 수 없음
 * 두 물뿌리개를 한 나무에 사용하여 3만큼 키울 수도 있음
 * 두 물뿌리개를 이용해 주어진 배치를 만들 수 있는지 여부를 판단하기
 *
 * 홀짝 or 홀
 * 홀짝 + 홀짝 = 홀짝
 * 홀짝 + 홀 = 짝짝 or 홀홀
 * 1. 총 합을 3으로 나눌 수 없는 경우 불가능
 * 2. 홀수 개수가 더 많은 경우 불가능
 * 3. 홀짝 개수를 맞춘 뒤, 짝 % 3 != 0이면 불가능
 */
public class Main {
    static final String YES = "YES";
    static final String NO = "NO";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = stoi(br.readLine()); // 사과나무 개수

        int[] trees = new int[N];
        String[] inputs = br.readLine().split(" ");
        int sum = 0;
        int odd = 0;
        for (int i = 0; i < N; i++) {
            trees[i] = stoi(inputs[i]);
            sum += trees[i];
            odd += trees[i] % 2;
        }

        // 1번 + 2번 통합
        // 1번과 3번은 사실상 같은 의미로 총 합이 3의 배수가 아니면 홀짝 개수를 맞춘 뒤 짝 % 3 == 0 성립 불가능
        // sum / 3 = 홀짝 페어 개수로 홀수 개수보다 많아야 함
        if (sum % 3 == 0 && odd <= sum / 3) {
            System.out.println(YES);
            return;
        }
        
        System.out.println(NO);
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
