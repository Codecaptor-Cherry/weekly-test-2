import java.util.*;

class Solution {
    
    static class Node {
        private int length;
        private Map<Character, Node> child;
        
        Node() {
            length = 0;
            child = new HashMap<>();
        }
        
        Node add(char c) {
            length++;
            
            Node ret = child.get(c);
            if(ret == null) {
                child.put(c, new Node());
                ret = child.get(c);
            }
            return ret;
        }
        
        void end() {
            length++;
        }
        int size() {
            return length;
        }
        Node get(char c) {
            return child.get(c);
        }
    }
    
    public int solution(String[] words) {
        int answer = 0;
        
        Node root = new Node();
        
        char[] arr;
        Node now;
        for(int i = 0; i < words.length; i++) {
            arr = words[i].toCharArray();
            now = root;
            
            for(int j = 0; j < arr.length; j++) {
                now = now.add(arr[j]);
            }
            now.end();
        }
        
        int length;
        for(int i = 0; i < words.length; i++) {
            arr = words[i].toCharArray();
            now = root; 
            
            answer += arr.length;
            
            for(int j = 0; j < arr.length; j++) {
                now = now.get(arr[j]);
                length = now.size();
                
                if(length == 1) {
                    answer -= (arr.length - 1) -j;
                    break;
                }
            }
        }
        
        return answer;
    }
}
