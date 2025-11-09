package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ_15997_승부예측 {

  static HashMap<String, Integer> nationIndex = new HashMap<>();
  static double ans[] = new double[4];
  static String bracket[][] = new String[6][2];
  static double rate[][] = new double[6][3];

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    for (int i = 0; i < 4; i++) {
      nationIndex.put(st.nextToken(), i);
      ans[i] = 0;
    }

    for(int i = 0; i < 6; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < 2; j++) {
        bracket[i][j] = st.nextToken();
      }
      for(int j = 0; j < 3; j++) {
        rate[i][j] = Double.parseDouble(st.nextToken());
      }
    }

    int score[] = new int[4];
    for(int j = 0; j < 3; j++) {
      dfs(0, j, 1, score.clone());
    }
    for(int i = 0; i < 4; i++) {
      System.out.println(ans[i]);
    }
  }
  static void dfs(int r, int c, double percent, int score[]) {
    if (rate[r][c] == 0) return;

    // 이긴 경우
    if (c == 0) {
      score[nationIndex.get(bracket[r][0])] += 3;
    } else if (c == 1) {
      score[nationIndex.get(bracket[r][0])] += 1;
      score[nationIndex.get(bracket[r][1])] += 1;
    } else {
      score[nationIndex.get(bracket[r][1])] += 3;
    }
    percent = percent * rate[r][c];

    if (r == 5) {
      int sorted[] = score.clone(), first = 0, second = 0;
      Arrays.sort(sorted);
      for (int i = 0; i < 4; i++) {
        if (sorted[3] == sorted[i]) first++;
        if (sorted[2] == sorted[i]) second++;
      }

      double firstRate = 1;
      if (first >= 2) firstRate = 2.0 / first;

      for (int i = 0; i < 4; i++) {
        if (sorted[3] == score[i]) ans[i] = percent * firstRate + ans[i];
        if (first == 1) {
          if (sorted[2] == score[i]) {
            ans[i] = percent / second + ans[i];
          }
        }
      }
      return;
    }
    for (int j = 0; j < 3; j++) {
      dfs(r + 1, j, percent, score.clone());
    }

  }

}
