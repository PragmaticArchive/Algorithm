import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int d = Integer.parseInt(st.nextToken());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    int[] stones = new int[n];
    for (int i = 0; i < n; i++) {
      stones[i] = Integer.parseInt(br.readLine());
    }
    Arrays.sort(stones);
    System.out.println(binarySearch(stones, d, m));
  } // end of class

  private static int binarySearch(int[] stones, int d, int m) {
    int left = 0, right = d;
    int ans = 0;
    while (left <= right) {
      int mid = (left + right) / 2;
      int prev = 0, count = 0;
      for (int i = 0; i < stones.length; i++) {
        if (stones[i] - prev < mid) {
          count++;
        } else {
          prev = stones[i];
        }
      }
      if (count > m) {
        right = mid - 1;
      } else {
        ans = mid;
        left = mid + 1;
      }
    }
    return ans;
  }
}// end of main
