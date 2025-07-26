import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_01497_기타콘서트 {

    static int result = -1;
    static int count = Integer.MIN_VALUE;
    static int numOfGuitar = 0;
    static int numOfSong = 0;
    static long[] guitars = null;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        numOfGuitar = Integer.parseInt(st.nextToken());
        numOfSong = Integer.parseInt(st.nextToken());

        guitars = new long[numOfGuitar];
        for (int i = 0; i < numOfGuitar; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            st.nextToken();
            guitars[i] = convertToLong(st.nextToken());
        }

        combination(0, 0L, 0);

        result = (count < 1) ? -1 : result;
        System.out.print(result);
    }

    static void combination(int idx, long curSong, int countGuitar) {
        if (idx == numOfGuitar) {
            int countSong = Long.bitCount(curSong);
            if (countSong > count) {
                result = countGuitar;
                count = countSong;
            } else if (countSong == count && result > countGuitar) {
                result = countGuitar;
            }

            return;
        }

        combination(idx + 1, curSong | guitars[idx], countGuitar + 1);
        combination(idx + 1, curSong, countGuitar);
    }

    static long convertToLong(String input) {
        long result = 0L;
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'Y') {
                result |= (1L << (chars.length - i - 1));
            }
        }

        return result;
    }
}
