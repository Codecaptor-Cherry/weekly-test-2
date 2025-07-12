import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String x = br.readLine();
        String y = br.readLine();
        String z = br.readLine();
        int k = Integer.parseInt(br.readLine());

        HashMap<String, Integer> combMap = new HashMap<>();
        processCombinations(x, k, combMap);
        processCombinations(y, k, combMap);
        processCombinations(z, k, combMap);

        List<String> result = combMap.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .sorted()
                .collect(Collectors.toList());

        System.out.println(result.isEmpty() ? -1 : String.join("\n", result));
    }

    private static void processCombinations(String s, int k, Map<String, Integer> map) {
        char[] chars = s.toCharArray();
        generateCombinations(chars, k, 0,
                new StringBuilder(), new boolean[chars.length], map);
    }
    private static void generateCombinations(char[] chars, int k, int start,
                                             StringBuilder sb, boolean[] visited, Map<String, Integer> map) {
        if (sb.length() == k) {
            String key = sb.toString();
            map.put(key, map.getOrDefault(key, 0) + 1);
            return;
        }

        for (int i = start; i < chars.length; i++) {
            visited[i] = true;
            sb.append(chars[i]);
            generateCombinations(chars, k, i + 1, sb, visited, map);
            visited[i] = false;
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
