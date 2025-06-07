package saturday.year25.sat250607;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/*
 * 알파벳 소문자로 구성된 문자열 X, Y, Z
 * 각각의 문자열에는 중복된 문자 존재 xxx
 * 문자열 S에 있는 문자 중 임의로 k개를 선택하여 문자열 S에서의 순서를 유지하여 만든
 * 모든 부분 문자열을 모아 놓은 집합을 문자열 S에 대한 조합 C(S, k)라고 함
 * ex) S = "abc"
 * ex) C(S, 2) = {"ab", "ab", "bc"}
 * X, Y, Z와 k가 주어질 때, C(X, k), C(Y, k), C(Z, k)에 두 번 이상 나타나는 부분 문자열을 오름차순으로 구하기
 *
 * 1. 부분 문자열 구하기
 * 2. 출현 횟수 기록 ~ 해시맵
 * 3. 오름차순 출력
 */
public class BJ_25328_문자열집합조합하기 {
    static HashMap<String, Integer> hashMap = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String X = br.readLine();
        String Y = br.readLine();
        String Z = br.readLine();

        int k = Integer.parseInt(br.readLine());

        subSet(0, 0, "", X, X.length(), k);
        subSet(0, 0, "", Y, Y.length(), k);
        subSet(0, 0, "", Z, Z.length(), k);

        List<String> list = new ArrayList<>();
        for (String key : hashMap.keySet()) {
            if(hashMap.get(key) > 1) {
                list.add(key);
            }
        }

        if(list.size() == 0) {
            System.out.println(-1);
            return;
        }

        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append(str + "\n");
        }

        System.out.println(sb);
    }

    public static void subSet(int idx, int cnt, String subString, String str, int length, int k) {
        // 부분 문자열 완성
        if(cnt == k) {
            if(hashMap.containsKey(subString)) {
                hashMap.put(subString, hashMap.get(subString) + 1);
            } else {
                hashMap.put(subString, 1);
            }

            return;
        }

        // 범위 넘어섬
        if(idx == length) {
            return;
        }

        subSet(idx + 1, cnt, subString, str, length, k);
        subSet(idx + 1, cnt + 1, subString + str.charAt(idx), str, length, k);
    }
}
