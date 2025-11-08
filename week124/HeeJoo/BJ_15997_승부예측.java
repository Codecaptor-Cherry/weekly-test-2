package saturday.year25.sat251108;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 4개의 팀이 조별리그 진행
 * 자신을 제외한 모든 상대방과 한 번씩, 총 3번의 경기 수행
 * 승자는 3점, 비기는 경우 서로 1점
 * 조별리그를 모두 치른 후 승점 순으로 순위 정하기
 * 점수가 같은 경우 추첨으로 순위를 정하며, 추첨은 공평하게 진행됨
 * 상위 2팀은 다음 라운드로 진출
 * 각 팀이 조별리그를 통과하여 다음 라운드로 진출할 확률 구하기
 * A B W D L : A팀, B팀, A 승리 확률, 비길 확률, B 승리 확률
 *
 * 모든 경기 종료 후 획득한 승점으로 순위 구하기
 */

class Game {
    int a, b;
    double w, d, l;

    Game(int a, int b, double w, double d, double l) {
        this.a = a;
        this.b = b;
        this.w = w;
        this.d = d;
        this.l = l;
    }
}
public class BJ_15997_승부예측 {
    static final int SIZE = 4;
    static Game[] games;
    static double[] ans = new double[SIZE];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] names;
        Map<String, Integer> hm = new HashMap<>();
        names = br.readLine().split(" ");

        for (int i = 0; i < SIZE; i++) {
            hm.put(names[i], i); // 국가명 : 인덱스
        }

        games = new Game[6];
        for (int i = 0; i < 6; i++) {
            String[] input = br.readLine().split(" ");

            int a = hm.get(input[0]);
            int b = hm.get(input[1]);
            double w = stod(input[2]);
            double d = stod(input[3]);
            double l = stod(input[4]);

            games[i] = new Game(a, b, w, d, l);
        }

        simul(0, 1, new int[4]);

        for (String name : names) {
            int idx = hm.get(name);
            System.out.println(ans[idx]);
        }

    }

    public static void getRank(double probability, int[] score) {
        int[][] result = new int[4][2]; // 국가 인덱스, 점수
        for (int i = 0; i < 4; i++) {
            result[i][0] = i;
            result[i][1] = score[i];
        }

        Arrays.sort(result, (o1, o2) -> o2[1] - o1[1]); // 점수 내림차순

        // 점수 내림차순 국가 인덱스
        int ai = result[0][0];
        int bi = result[1][0];
        int ci = result[2][0];
        int di = result[3][0];

        // 점수 내림차순
        int as = result[0][1];
        int bs = result[1][1];
        int cs = result[2][1];
        int ds = result[3][1];

        if (bs != cs) { // 상위 2개국 진출
            ans[ai] += probability;
            ans[bi] += probability;
        } else if (as == ds) { // 4개국 점수 동일
            ans[ai] += probability / 2;
            ans[bi] += probability / 2;
            ans[ci] += probability / 2;
            ans[di] += probability / 2;
        } else if (as == cs && cs != ds) { // 상위 3개국 점수 동일
            ans[ai] += probability * 2 / 3;
            ans[bi] += probability * 2 / 3;
            ans[ci] += probability * 2 / 3;
        } else if (as != bs && bs == ds) { // 하위 3개국 점수 동일
            ans[ai] += probability;
            ans[bi] += probability / 3;
            ans[ci] += probability / 3;
            ans[di] += probability / 3;
        } else if (as != bs && bs == cs && cs != ds) { // 2등, 3등 점수 동일
            ans[ai] += probability;
            ans[bi] += probability / 2;
            ans[ci] += probability / 2;
        }
    }

    public static void simul(int idx, double probability, int[] score) {
        if (idx == 6) { // 조별 리그 완료
            if (probability == 0) return;

            // 상위 2팀 구하기
            getRank(probability, score);

            return;
        }

        // 1. A 승리
        score[games[idx].a] += 3;
        simul(idx + 1, probability * games[idx].w, score);
        score[games[idx].a] -= 3;

        // 2. 비김
        score[games[idx].a] += 1;
        score[games[idx].b] += 1;
        simul(idx + 1, probability * games[idx].d, score);
        score[games[idx].a] -= 1;
        score[games[idx].b] -= 1;

        // 3. B 승리
        score[games[idx].b] += 3;
        simul(idx + 1, probability * games[idx].l, score);
        score[games[idx].b] -= 3;
    }

    public static double stod(String str) {
        return Double.parseDouble(str);
    }
}
