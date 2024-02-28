import java.io.*;
import java.util.*;

public class Main {

  static class Pipe {

    int l, c;

    Pipe(int l, int c) {
      this.l = l;
      this.c = c;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    int D = Integer.parseInt(st.nextToken());
    int P = Integer.parseInt(st.nextToken());

    Pipe[] pipes = new Pipe[P];
    for (int i = 0; i < P; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      int l = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      pipes[i] = new Pipe(l, c);
    }

    int[] dp = new int[D + 1];
    dp[0] = Integer.MAX_VALUE;

    for (Pipe pipe : pipes) {
      for (int i = D; i >= pipe.l; i--) {
        dp[i] = Math.max(dp[i], Math.min(dp[i - pipe.l], pipe.c));
      }
    }
    System.out.println(dp[D]);
  }// end of main
}// end of class
