import java.io.*;
import java.util.*;

public class Main {

  private static int N, M, T;
  private static int[][] hurdles;
  private static final int MAX_HEIGHT = 1_000_001;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    T = Integer.parseInt(st.nextToken());

    hurdles = new int[N + 1][N + 1];
    for (int i = 1; i <= N; i++) {
      Arrays.fill(hurdles[i], MAX_HEIGHT);
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      int u = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());
      int h = Integer.parseInt(st.nextToken());
      hurdles[u][v] = h;
    }
    jumpHurdles();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < T; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      int s = Integer.parseInt(st.nextToken());
      int e = Integer.parseInt(st.nextToken());
      sb.append(hurdles[s][e] == MAX_HEIGHT ? -1 : hurdles[s][e]).append("\n");
    }
    System.out.println(sb);
  }// end of main

  private static void jumpHurdles() {
    for (int k = 1; k <= N; k++) {
      for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
          if (i == j || hurdles[i][k] == MAX_HEIGHT || hurdles[k][j] == MAX_HEIGHT) {
            continue;
          }
          hurdles[i][j] = Math.min(hurdles[i][j], Math.max(hurdles[i][k], hurdles[k][j]));
        }
      }
    }
  }
}// end of class
