import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_02713_규현이의사랑을담은문자메세지 {

    static int[][] directions = {
            { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }
    };

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();

        int numOfTest = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < numOfTest; i++) {
            String line = bufferedReader.readLine();
            String[] section = line.split(" ", 3);
            int row = Integer.parseInt(section[0]);
            int col = Integer.parseInt(section[1]);
            String decimal = convertToDecimal(row, col, section[2]);
            String message = convertToMessage(row, col, decimal);
            stringBuilder.append(message).append('\n');
        }

        System.out.print(stringBuilder.toString());
    }

    static String convertToMessage(int row, int col, String decimal) {
        StringBuilder stringBuilder = new StringBuilder();
        char[][] map = new char[row][col];
        boolean[][] visited = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            Arrays.fill(map[i], '0');
        }

        int r = 0, c = 0, d = 0;
        for (int i = 0; i < decimal.length(); i++) {
            char number = decimal.charAt(i);
            map[r][c] = number;
            visited[r][c] = true;

            int nextR = r + directions[d][0];
            int nextC = c + directions[d][1];
            if ((0 <= nextR && nextR < row && 0 <= nextC && nextC < col)
                && !visited[nextR][nextC]) {
                r = nextR;
                c = nextC;
            } else {
                d = (d + 1) % 4;
                r += directions[d][0];
                c += directions[d][1];
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                stringBuilder.append(map[i][j]);
            }
        }

        return stringBuilder.toString();
    }

    static String convertToDecimal(int row, int col, String section) {
        StringBuilder builder = new StringBuilder();
        for (char character : section.toCharArray()) {
            int number = (character == ' ') ? 0 : character - 64;
            String decimal = Integer.toBinaryString(number);

            int numOfZero = 5 - decimal.length();
            for (int j = 0; j < numOfZero; j++) {
                builder.append(0);
            }
            builder.append(decimal);
        }

        return builder.toString();
    }
}
