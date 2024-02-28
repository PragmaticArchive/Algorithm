import java.io.*;
import java.util.*;

public class Main {

  private static int N, ans = Integer.MAX_VALUE;
  private static int[][] T;
  private static boolean[] visited;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    T = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      for (int j = 0; j < N; j++) {
        T[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    for (int k = 0; k < N; k++) {
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          T[i][j] = Math.min(T[i][j], T[i][k] + T[k][j]);
        }
      }
    }
    visited = new boolean[N];
    visited[K] = true;
    findShortRoute(1, K, 0);
    System.out.println(ans);
  }// end of main

  private static void findShortRoute(int cnt, int node, int total) {
    if (cnt == N) {
      ans = Math.min(ans, total);
    }
    for (int i = 0; i < N; i++) {
      if (node == i || visited[i]) {
        continue;
      }
      visited[i] = true;
      findShortRoute(cnt + 1, i, total + T[node][i]);
      visited[i] = false;
    }
  }
}// end of class
