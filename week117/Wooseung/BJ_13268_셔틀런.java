package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_13268_셔틀런 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()) % 100;
        int[] starts = {0, 10, 30, 60};

        int minDist = 100;
        for (int s : starts) {
            int d = Math.abs(n - s); 
            int circular = 100 - d;    
            int bestHere = Math.min(d, circular);
            if (bestHere < minDist) {
                minDist = bestHere;
            }
        }

        int result;
        if (minDist == 0) {
            result = 0;
        } else if (minDist <= 5) {  
            result = 1;
        } else if (minDist <= 10) {   
            result = 2;
        } else if (minDist <= 15) {     
            result = 3;
        } else {                     
            result = 4;
        }

        System.out.print(result);
    }
}
