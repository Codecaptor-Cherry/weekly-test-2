package saturday.year25.sat250719;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 빨, 파, 노, 초 4가지 색의 카드 ... R, B, Y, G
 * 색깔별로 1 ~ 9 존재 ... 총 36장
 * 36장에서 5장을 뽑고, 다음과 같은 규칙으로 점수 계산
 * 1. 스트플 : 가장 높은 숫자 + 900
 * 2. 포카드 : 같은 숫자 + 800
 * 3. 풀하우스 : 3장 숫자 * 10 + 2장 숫자 + 700
 * 4. 플러시 : 가장 높은 숫자 + 600
 * 5. 스트레이트 : 가장 높은 숫자 + 500
 * 6. 트리플 : 같은 숫자 + 400
 * 7. 투페어 : 큰 숫자 * 10 + 작은 숫자 + 300
 * 8. 원페어 : 같은 숫자 + 200
 * 9. 탑 : 가장 큰 숫자 + 100
 * 두 가지 이상의 규칙을 적용할 수 있는 경우에는 가장 높은 점수가 최종 점수
 */

class Card {
    char color;
    int num;

    Card(char color, int num) {
        this.color = color;
        this.num = num;
    }
}
public class BJ_2621_카드게임 {
    static final int N = 5;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // triple : 3장의 수
        // first : 2장의 수 ... 페어 첫 번째
        // second : 2장의 수 ... 페어 두 번째
        int triple = 0, first = 0, second = 0;

        Card[] cards = new Card[N];
        for (int i = 0; i < N; i++) {
            String[] inputs = br.readLine().split(" ");

            cards[i] = new Card(inputs[0].charAt(0), stoi(inputs[1]));
        }

        // 숫자 오름차순
        Arrays.sort(cards, (o1, o2) -> o1.num - o2.num);

        // 1. 스티플
        boolean flush = true;
        char preColor = cards[0].color;
        for (int i = 1; i < N; i++) {
            if (cards[i].color != preColor) {
                flush = false;
                break;
            }
        }

        boolean straight = true;
        int preNum = cards[0].num;
        for (int i = 1; i < N; i++) {
            if (cards[i].num != preNum + 1) {
                straight = false;
                break;
            }

            preNum++;
        }

        if(straight && flush) {
            System.out.println(900 + cards[N - 1].num);
            return;
        }

        // 2. 포카드
        boolean fourCard = false;
        int[] cnt = new int[10];
        for (int i = 0; i < N; i++) {
            cnt[cards[i].num]++;
        }

        for (int i = 1; i < 10; i++) {
            if(cnt[i] == 4) {
                fourCard = true;
                first = i; // 포카드 숫자 임시 저장
                break;
            } else if(cnt[i] == 3) {
                triple = i;
            } else if (cnt[i] == 2) {
                if (first == 0) {
                    first = i;
                } else {
                    second = i;
                }
            }
        }

        if (fourCard) {
            System.out.println(800 + first);
            return;
        }

        // 3. 풀하우스
        if(triple != 0 && first != 0) {
            System.out.println(700 + (triple * 10) + first);
            return;
        }

        // 4. 플러시
        if (flush) {
            System.out.println(600 + cards[N - 1].num);
            return;
        }

        // 5. 스트레이트
        if (straight) {
            System.out.println(500 + cards[N - 1].num);
            return;
        }

        // 6. 트리플
        if (triple != 0) {
            System.out.println(400 + triple);
            return;
        }

        // 7. 투페어
        if (first != 0 && second != 0) {
            System.out.println(300 + (second * 10) + first); // second > first 주의 !
            return;
        }

        // 8. 원페어
        if (first != 0) {
            System.out.println(200 + first);
            return;
        }

        // 9. 탑
        System.out.println(100 + cards[N - 1].num);
        return;
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
