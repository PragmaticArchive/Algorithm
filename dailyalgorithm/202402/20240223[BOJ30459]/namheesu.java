import java.io.*;
import java.util.*;

public class Main {

  static double ans = -1d;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int R = Integer.parseInt(st.nextToken());

    int[] piles = new int[N];
    st = new StringTokenizer(br.readLine(), " ");
    for (int i = 0; i < N; i++) {
      piles[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(piles);

    int[] flagpoles = new int[M];
    st = new StringTokenizer(br.readLine(), " ");
    for (int i = 0; i < M; i++) {
      flagpoles[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(flagpoles);

    for (int i = 0; i < N - 1; i++) {
      for (int j = N - 1; j > i; j--) {
        binarySearch(piles[j] - piles[i], M, flagpoles, R);
      }
    }
    System.out.println(ans == -1d ? "-1" : String.format("%.1f", ans));
  } // end of class

  private static void binarySearch(int len, int M, int[] flagpoles, int R) {
    int left = 0;
    int right = M - 1;
    while (left <= right) {
      int mid = (left + right) / 2;
      double area = (double) (len * flagpoles[mid]) * 0.5d;
      if (area > R) {
        right = mid - 1;
      } else {
        ans = Math.max(ans, area);
        left = mid + 1;
      }
    }
  }
}// end of main
