package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_15828_Router {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Queue<Integer> q = new LinkedList<>();
        while(true) {
            int packet = Integer.parseInt(br.readLine());

            if(packet == -1) {
                break;
            }else if(packet == 0) {
                q.poll();
            }else if(q.size() < N) {
                q.add(packet);
            }
        }
        if(q.isEmpty()) {
            System.out.println("empty");
        }else {
            while(!q.isEmpty()) {
                sb.append(q.poll() + " ");
            }
            System.out.println(sb.toString());
        }
    }
}
