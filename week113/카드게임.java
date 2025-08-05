import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] cards = new String[5];
        for (int i = 0; i < 5; i++) {
            cards[i] = sc.nextLine();
        }

        System.out.println(getScore(cards));
    }

    private static int getScore(String[] cards) {
        Map<Character, Integer> colorCount = new HashMap<>();
        Map<Integer, Integer> numberCount = new TreeMap<>();

        for (String card : cards) {
            char color = card.charAt(0);
            int num = Integer.parseInt(card.substring(2));

            colorCount.put(color, colorCount.getOrDefault(color, 0) + 1);
            numberCount.put(num, numberCount.getOrDefault(num, 0) + 1);
        }

        List<Integer> numbers = new ArrayList<>(numberCount.keySet());
        Collections.sort(numbers);
        int maxNum = numbers.get(numbers.size() - 1);

        boolean isFlush = colorCount.size() == 1;
        boolean isStraight = isStraight(numbers);

        Integer quad = null, triple = null;
        List<Integer> pairs = new ArrayList<>();
        for (Map.Entry<Integer, Integer> e : numberCount.entrySet()) {
            if (e.getValue() == 4) quad = e.getKey();
            else if (e.getValue() == 3) triple = e.getKey();
            else if (e.getValue() == 2) pairs.add(e.getKey());
        }
        Collections.sort(pairs);

        // 카드 5장이 모두 같은 색이면서 숫자가 연속적일 때, 점수는 가장 높은 숫자에 900을 더한다.
        if (isFlush && isStraight) return maxNum + 900;

        // 카드 5장 중 4장의 숫자가 같을 때 점수는 같은 숫자에 800을 더한다.
        if (quad != null) return quad + 800;

        // 카드 5장 중 3장의 숫자가 같고 나머지 2장도 숫자가 같을 때 점수는 3장이 같은 숫자에 10을 곱하고 2장이 같은 숫자를 더한 다음 700을 더한다.
        if (triple != null && pairs.size() == 1) return triple * 10 + pairs.get(0) + 700;

        // 5장의 카드 색깔이 모두 같을 때 점수는 가장 높은 숫자에 600을 더한다.
        if (isFlush) return maxNum + 600;

        // 카드 5장의 숫자가 연속적일 때 점수는 가장 높은 숫자에 500을 더한다.
        if (isStraight) return maxNum + 500;

        // 카드 5장 중 3장의 숫자가 같을 때 점수는 같은 숫자에 400을 더한다.
        if (triple != null) return triple + 400;

        // 카드 5장 중 2장의 숫자가 같고 또 다른 2장의 숫자가 같을 때 점수는 같은 숫자 중 큰 숫자에 10을 곱하고 같은 숫자 중 작은 숫자를 더한 다음 300을 더한다.
        if (pairs.size() == 2) return pairs.get(1) * 10 + pairs.get(0) + 300;

        // 카드 5장 중 2장의 숫자가 같을 때 점수는 같은 숫자에 200을 더한다.
        if (pairs.size() == 1) return pairs.get(0) + 200;

        // 위의 어떤 경우에도 해당하지 않을 때 점수는 가장 큰 숫자에 100을 더한다.
        return maxNum + 100;
    }

    private static boolean isStraight(List<Integer> numbers) {
        if (numbers.size() != 5) return false;
        for (int i = 0; i < 4; i++) {
            if (numbers.get(i + 1) != numbers.get(i) + 1) {
                return false;
            }
        }
        return true;
    }
}
