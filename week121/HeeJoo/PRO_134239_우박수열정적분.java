/*
 * 자연수 k
 * 1-1. 입력된 수가 짝수라면 2로 나누기
 * 1-2. 입력된 수가 홀수라면 3을 곱하고 1 더하기
 * 2. 결과가 1보다 크면 1번 작업 반복
 * [a, -b] : 그래프, x1 = a, x2 = n - b, y = 0으로 둘러 쌓인 공간의 면적
 * n = k가 초항인 우박수열이 1이 될 때까지의 횟수
 * 정적분 결과 목록 구하기
 * 유효한 구간이 주어진 경우 -1
 
 * 1. 우박수열
 * 2. 구간별 정적분 -> 사다리꼴 ~ (왼쪽 높이 + 오른쪽 높이) / 2
 */

import java.util.*;

class Solution {
    public double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length];
        
        List<Integer> list = new ArrayList<>();
        collatz(k, list);
        
        // 넓이 미리 구하기
        double[] arr = new double[list.size() - 1];
        for (int i = 0; i < list.size() - 1; i++) {            
            arr[i] = (list.get(i) + list.get(i + 1)) / 2.0;
        }
        
        int n = list.size() - 1;
        for (int i = 0; i < ranges.length; i++) {
            int start = ranges[i][0];
            int end = n + ranges[i][1];
            
            if (start > end) {
                answer[i] = -1;
                continue;
            }
            
            if (start == end) {
                answer[i] = 0;
                continue;
            }
            
            for (int j = start; j < end; j++) {
                answer[i] += arr[j];
            }            
        }
        
        return answer;
    }
    
    public void collatz(int k, List<Integer> list) {
        list.add(k);
        
        while (k != 1) {
            if (k % 2 == 0) { // 짝수
                k /= 2;
            } else { // 홀수
                k = k * 3 + 1;
            }
            
            list.add(k);
        }
    }
}
