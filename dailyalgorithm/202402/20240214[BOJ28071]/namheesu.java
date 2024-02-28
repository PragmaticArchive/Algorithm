import java.io.*;
import java.util.*;

public class Main {

  static int N, M, K;
  static int[][] dp;
  static int[] boxes;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    boxes = new int[N];
    dp = new int[M + 1][K];
    st = new StringTokenizer(br.readLine(), " ");
    for (int i = 0; i < N; i++) {
      boxes[i] = Integer.parseInt(st.nextToken());
      dp[1][boxes[i] % K] = Math.max(dp[1][boxes[i] % K], boxes[i]);
    }

    for (int i = 2; i < M + 1; i++) {
      for (int j = 0; j < N; j++) {
        for (int k = 0; k < K; k++) {
          if (dp[i - 1][Math.floorMod(k - boxes[j], K)] == 0) {
            continue;
          }
          dp[i][k] = Math.max(dp[i][k], dp[i - 1][Math.floorMod(k - boxes[j], K)] + boxes[j]);
        }
      }
    }
    int ans = 0;
    for (int i = 0; i < M + 1; i++) {
      ans = Math.max(ans, dp[i][0]);
    }
    System.out.println(ans);
  }// end of main

}// end of class
