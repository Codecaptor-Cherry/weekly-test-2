package saturday.year25.sat250712;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * R x C
 * 모든 글자는 알파벳 대문자와 공백으로 이루어져 있음
 * 글자는 다음과 같이 숫자로 바뀜 ... 공백 = 0, A = 1, ..., Z = 26
 * 1. 문자를 위 규칙에 따라 숫자로 바꿈
 * 2. 5자리 이진수로 변환
 * 3. R x C 행렬에 소용돌이 패턴으로 채우기... 모든 칸을 채우지 못할 경우, 0으로 계속 채우기
 * 4. 행 우선으로 읽기
 * 주어진 문장을 비밀 메시지로 변환하기
 */
public class BJ_2713_규현이의사랑을담은문자메시지 {
    static int R, C;
    static int[] dirX = {0, 1, 0, -1}; // 우하좌상
    static int[] dirY = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = stoi(br.readLine()); // 테스트 케이스 수

        for (int t = 0; t < T; t++) {
            String[] inputs = br.readLine().split(" ");

            R = stoi(inputs[0]); // 행의 수
            C = stoi(inputs[1]); // 열의 수
            String message = ""; // 보낼 메시지
            for (int i = 2; i < inputs.length; i++) { // 2번째 원소부터 마지막 원소까지 문자 내용
                if(i == inputs.length - 1) {
                    message += inputs[i];
                } else {
                    message += inputs[i] + " "; // split하며 사라진 공백 추가
                }
            }

            int[][] arr = new int[R][C];

            // 1. 문자를 숫자로 변환 + 2. 5자리 2진수로 변환
            int d = 0;
            int x = 0, y = 0;
            for (int i = 0; i < message.length(); i++) {
                String result = changeMessage(message.charAt(i));

                // 3. 행렬 채우기 ~ 나선 방향
                for (int j = 0; j < result.length(); j++) {
                    int ch = result.charAt(j) == '0' ? -1 : 1; // 자동으로 채워지는 0과 구분하기 위해 -1로 임시 표시
                    arr[x][y] = ch;

                    int dx = x + dirX[d];
                    int dy = y + dirY[d];

                    if(!checkRange(dx, dy) || arr[dx][dy] != 0) { // 범위를 벗어나거나 이미 채워진 경우 방향 변경
                        d = (d + 1) % 4;
                    }

                    x += dirX[d];
                    y += dirY[d];
                }
            }

            // 4. 행 우선 읽기
            for (int[] array : arr) {
                for (int k : array) {
                    sb.append(k != -1 ? k : 0); // 임시 표시한 -1을 0으로 바꿔서 출력
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static boolean checkRange(int x, int y) {
        if(x < 0 || x >= R || y < 0 || y >= C) {
            return false;
        }

        return true;
    }

    public static String changeMessage(char ch) {
        int num = ctoi(ch); // 문자에 대응하는 숫자로 변환

        // 5자리 2진수 표현
        String binary = String.format("%5s", Integer.toBinaryString(num)).replace(' ', '0');

        return binary;
    }

    public static int ctoi(char ch) {
        if(ch == ' ') {
            return 0;
        }

        return (int)(ch - 'A') + 1;
    }
    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
