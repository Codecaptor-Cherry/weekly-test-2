import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            String line = br.readLine();
            String[] parts = line.split(" ", 3);

            int r = Integer.parseInt(parts[0]);
            int c = Integer.parseInt(parts[1]);
            String msg = parts[2];

            StringBuilder sb = new StringBuilder();
            for (char ch : msg.toCharArray()) {
                sb.append(String.format("%5s", Integer.toBinaryString(ch == ' ' ? 0 : ch - 'A' + 1)).replace(' ', '0'));
            }
            sb.append("0".repeat(Math.max(0, r * c - sb.length())));

            char[][] map = new char[r][c];
            int num = 0;
            int top = 0, bottom = r - 1;
            int left = 0, right = c - 1;

            while (num < r * c) {
                for (int i = left; i <= right; i++) {
                    map[top][i] = sb.charAt(num++);
                }
                top++;

                for (int i = top; i <= bottom; i++) {
                    map[i][right] = sb.charAt(num++);
                }
                right--;

                if (top <= bottom) {
                    for (int i = right; i >= left; i--) {
                        map[bottom][i] = sb.charAt(num++);
                    }
                    bottom--;
                }

                if (left <= right) {
                    for (int i = bottom; i >= top; i--) {
                        map[i][left] = sb.charAt(num++);
                    }
                    left++;
                }
            }

            sb = new StringBuilder();
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    sb.append(map[i][j]);
                }
            }
            System.out.println(sb);
        }
    }
}
