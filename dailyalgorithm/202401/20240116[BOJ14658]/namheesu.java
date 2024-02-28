import java.io.*;
import java.util.*;

public class Main {

  private static int M, N, L, K;

  static class Star {

    int x;
    int y;

    public Star(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    M = Integer.parseInt(st.nextToken());
    N = Integer.parseInt(st.nextToken());
    L = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    Star[] stars = new Star[K];

    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      stars[i] = new Star(x, y);
    }
    if (L >= N && L >= M) {
      System.out.println(0);
    } else {
      install(stars);
    }
  }// end of main

  private static void install(Star[] stars) {
    int cnt = 0, ans = 0;
    for (Star star1 : stars) {
      for (Star star2 : stars) {
        cnt = 0;
        for (Star fall : stars) {
          if (rangeChk(star1.x, star2.y, fall)) {
            cnt++;
          }
          ans = Math.max(ans, cnt);
        }
      }
    }
    System.out.println(K - ans);
  }

  private static boolean rangeChk(int x, int y, Star fall) {
    return x <= fall.x && fall.x <= x + L
        && y <= fall.y && fall.y <= y + L;
  }
}// end of class
