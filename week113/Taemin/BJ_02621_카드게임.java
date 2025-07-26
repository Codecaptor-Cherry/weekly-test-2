import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class BJ_02621_카드게임 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = null;
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            list.add(convertToNumber(stringTokenizer.nextToken(),
                                    Integer.parseInt(stringTokenizer.nextToken())));
        }

        int result = -1;
        int[] colors = findColors(list);
        int[] numbers = findNumbers(list);

        // #1.
        if (IntStream.of(colors).anyMatch(numOfColor -> numOfColor == 5)) {
            if (isConsecutive(list)) {
                result = Math.max(result, findMaxNumber(list) + 900);
            }
        }

        // #2.
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 4) {
                result = Math.max(result, 800 + i);
                break;
            }
        }

        // #3
        int first = findNCountNumber(numbers, 3, -1);
        int second = -1;
        if (first != -1) {
            second = findNCountNumber(numbers, 2, first);
        }
        if (second != -1) {
            result = Math.max(result, first * 10 + second + 700);
        }

        // #4
        if (IntStream.of(colors).anyMatch(numOfColor -> numOfColor == 5)) {
            result = Math.max(result, findMaxNumber(list) + 600);
        }

        // #5
        if (isConsecutive(list)) {
            result = Math.max(result, findMaxNumber(list) + 500);
        }

        // #6
        if (first != -1) {
            result = Math.max(result, first + 400);
        }

        // #7
        first = findNCountNumber(numbers, 2, -1);
        second = -1;
        if (first != -1) {
            second = findNCountNumber(numbers, 2, first);
        }
        if (second != -1) {
            if (first > second) {
                result = Math.max(result, first * 10 + second + 300);
            } else {
                result = Math.max(result, second * 10 + first + 300);
            }
        }

        // #8
        first = findNCountNumber(numbers, 2, -1);
        second = -1;
        if (first != -1) {
            second = findNCountNumber(numbers, 2, first);
        }
        if (first != -1 && second == -1) {
            result = Math.max(result, first + 200);
        }

        // #9
        if (result == -1) {
            result = Math.max(result, findMaxNumber(list) + 100);
        }

        System.out.print(result);
    }

    static int findNCountNumber(int[] numbers, int count, int except) {
        int result = -1;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == count && i != except) {
                result = i;
                break;
            }
        }

        return result;
    }

    static int findMaxNumber(List<Integer> list) {
        int result = 0;
        for (int elem : list) {
            result = Math.max(result, elem % 10);
        }

        return result;
    }

    static int[] findNumbers(List<Integer> list) {
        int[] numbers = new int[10];
        for (int elem : list) {
            int remainder = elem % 10;
            numbers[remainder]++;
        }

        return numbers;
    }

    static int[] findColors(List<Integer> list) {
        int[] colors = new int[4];
        for (int elem : list) {
            int share = elem / 10;
            colors[share]++;
        }

        return colors;
    }

    static boolean isConsecutive(List<Integer> list) {
        List<Integer> checkList = new ArrayList<>();
        for (int elem : list) {
            checkList.add(elem % 10);
        }

        Collections.sort(checkList);
        boolean result = true;
        for (int i = 1; i < checkList.size(); i++) {
            if (checkList.get(i) - 1 != checkList.get(i - 1)) {
                result = false;
            }
        }

        return result;
    }

    static int convertToNumber(String color, int number) {
        int n = 0;
        switch(color) {
            case "R":
                n = 0;
                break;
            case "B":
                n = 1;
                break;
            case "Y":
                n = 2;
                break;
            case "G":
                n = 3;
                break;
            default:
                break;
        }

        return 10 * n + number;
    }
}
