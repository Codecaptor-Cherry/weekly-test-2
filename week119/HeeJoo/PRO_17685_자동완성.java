/*
 * 문자열을 학습시킨 후, 학습된 단어들을 순서대로 찾을 때 몇 개의 문자를 입력하면 되는지 계산하는 프로그램
 *
 * 방법 1. Trie
 * 방법 2. 사전순 배열
 * 2번으로 풀이
 *
 * 1. 단어를 사전순으로 배열
 * 2. 인접한 단어들만 비교
 * 주의) 길이 i까지의 단어가 고유한 접두사인지 체크 !!!
 */

import java.util.*;

class Solution {
    public int solution(String[] words) {
        int answer = 0;
        
        // 단어를 사전순으로 정렬하면 인접 단어들만 비교하면 됨
        Arrays.sort(words);
        int length = words.length;
        
        // for(String s : words) {
        //     System.out.print(s + " " );
        // }
        // System.out.println();
        
        // 첫 단어는 다음 단어와 비교
        int result = getGap(words[0], words[1]);
        answer += result == words[0].length() ? result : result + 1; // 결과값과 words[0]의 길이가 같으면 고유한 접두사
        
        // 중간 단어는 이전, 이후 단어와 비교
        for (int i = 1; i < length - 1; i++) {
            int max = 0;
            
            // 이전 단어와 비교
            max = Math.max(max, getGap(words[i], words[i - 1]));
            
            // 이후 단어와 비교
            max = Math.max(max, getGap(words[i], words[i + 1]));            
            
            if (max == words[i].length()) { // 고유한 접두사
                answer += max;
            } else { // 한 글자 더 필요
                answer += max + 1;
            }

        }
        
        // 마지막 단어는 이전 단어와 비교
        result = getGap(words[length - 1], words[length - 2]);
        answer += result == words[length - 1].length() ? result : result + 1; // 결과값과 words[length - 1]의 길이가 같으면 고유한 접두사

        return answer;
    }
    
    public static int getGap(String s1, String s2) {
        int length = Math.min(s1.length(), s2.length());
        
        for (int i = 0; i < length; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return i;
            }
        }
        
        return length;
    }
}
