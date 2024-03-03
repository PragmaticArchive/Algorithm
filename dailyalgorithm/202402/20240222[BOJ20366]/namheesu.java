import java.io.*;
import java.util.*;

public class Main {

  static int ans = Integer.MAX_VALUE;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] snows = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    for (int i = 0; i < N; i++) {
      snows[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(snows);
    makeElzas(N, snows);
    System.out.println(ans);
  } // end of class

  private static void makeElzas(int n, int[] snows) {
    for (int i = 0; i < n; i++) {
      for (int j = n - 1; j > i; j--) {
        makeAnnas(i, j, snows);
        if (ans == 0) {
          return;
        }
      }
    }
  }

  private static void makeAnnas(int i, int j, int[] snows) {
    int left = i + 1;
    int right = j - 1;
    while (left < right) {
      int elza = snows[i] + snows[j];
      int anna = snows[left] + snows[right];
      int diff = elza - anna;
      ans = Math.min(ans, Math.abs(diff));
      if (diff > 0) {
        left++;
      } else if (diff < 0) {
        right--;
      } else {
        return;
      }
    }
  }
}// end of main
