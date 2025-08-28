package saturday.year25.sat250823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 두 명의 플레이어가 차례대로 돌아가며 진행
 * 홀수 선, 짝수 후
 * 0 ~ n - 1까지 고유한 번호가 부여된 점 n개
 * 어느 세 점도 일직선 위에 놓이지 않음
 * 매 차례 마다 두 점을 선택해서 이를 연결하는 선분을 긋는데,
 * 이전에 그린 선분을 다시 그을 수 는 없지만 이미 그린 다른 선분과 교차하는 것은 가능
 * 처음으로 사이클을 완성하는 순간 게임 종료
 * 사이클 C는 플레이어가 그린 선분들의 부분 집합으로 다음 조건을 만족한다.
 * 조건 : C에 속한 임의의 선분의 한 끝점에서 출발하여 모든 선분을 한 번씩만 지나서 출발점으로 되돌아올 수 있다.
 * 게임의 진행 상황이 주어지면 몇 번째 차례에서 사이클이 완성되었는지, 혹은 아직 게임이 진행 중인지 판단하는 프로그램
 /
 */
public class BJ_20040_사이클게임 {
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = stoi(st.nextToken()); // 점의 개수
        int m = stoi(st.nextToken()); // 진행된 차례의 수

        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = stoi(st.nextToken());
            int b = stoi(st.nextToken());

            if (findParent(a) == findParent(b)) { // 두 점의 부모가 같다면 사이클 완성
                System.out.println(i + 1);
                return;
            } else { // 선분 긋기
                unionParent(a, b);
            }
        }

        System.out.println(0);
    }

    public static int findParent(int a) {
        if(a != parents[a]) {
            return parents[a] = findParent(parents[a]); // 경로 압축
        }

        return a;
    }

    public  static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        if (a <= b) {
            parents[b] = a;
        } else {
            parents[a] = b;
        }
    }



    public static int stoi(String str) {
        return Integer.parseInt(str);
    }

}
