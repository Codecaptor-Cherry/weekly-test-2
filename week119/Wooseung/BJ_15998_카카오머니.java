package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_15998_카카오머니 {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    long a, b;
    long minB = (long) 10e18;
    long balance = 0;
    long min = 0;
    boolean valid = true;

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());

      a = Long.parseLong(st.nextToken());
      b = Long.parseLong(st.nextToken());

      if (balance + a < 0) {

        long temp = b - a - balance;

        if (b != 0 && b < minB) {
          minB = b;
        }
        if (min == 0) {
          min = temp;
        } else {
          min = GCD(min, temp);
          if (min <= minB && minB != (long) 10e18) {
            valid = false;
            break;
          }
        }

      } else {
        if (balance + a != b) {
          valid = false;
          break;
        }
      }
      balance = b;
    }
    if (valid && min != 0) {
      System.out.println(min);
    } else if (valid && min == 0) {
      System.out.println(1);
    } else {
      System.out.println(-1);
    }
  }

  static long GCD(long a, long b) {
    while (b > 0) {
      long temp = a;
      a = b;
      b = temp % b;
    }
    return a;
  }
}
