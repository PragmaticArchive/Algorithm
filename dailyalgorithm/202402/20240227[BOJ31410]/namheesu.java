import java.io.*;
import java.util.*;

public class Main {

  static class Pollution {

    int x;
    long y;

    public Pollution(int x, long y) {
      this.x = x;
      this.y = y;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    Pollution[] pollutions = new Pollution[N];
    StringTokenizer st;
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      int x = Integer.parseInt(st.nextToken());
      long p = Long.parseLong(st.nextToken());
      pollutions[i] = new Pollution(x, p);
    }
    Arrays.sort(pollutions, (a, b) -> a.x - b.x);

    long leftSum = 0, rightSum = 0;
    int cnt = 1;
    leftSum = pollutions[0].y;
    for (int i = 1; i < N; i++) {
      leftSum += pollutions[i].y + (long) (pollutions[i].x - pollutions[i - 1].x) * cnt++;
    }
    cnt = 1;
    rightSum = pollutions[N - 1].y;
    for (int i = N - 2; i >= 0; i--) {
      rightSum += pollutions[i].y + (long) (pollutions[i + 1].x - pollutions[i].x) * cnt++;
    }
    // 오른쪽, 왼쪽 구간합

    long ans = Long.MAX_VALUE;
    long left = 0, right = 0;
    for (int i = 1; i < N - 1; i++) {
      left = leftSum - pollutions[i].y - (pollutions[N - 1].x - pollutions[i].x);
      right = rightSum - pollutions[i].y - (pollutions[i].x - pollutions[0].x);
      ans = Math.min(ans, Math.min(left, right));
    }

    left = leftSum - pollutions[0].y - (pollutions[N - 1].x - pollutions[0].x);
    right = rightSum - pollutions[0].y - (long) (N - 1) * (pollutions[1].x - pollutions[0].x);
    ans = Math.min(ans, Math.min(left, right));

    left = leftSum - pollutions[N - 1].y - (long) (N - 1) * (pollutions[N - 1].x - pollutions[N - 2].x);
    right = rightSum - pollutions[N - 1].y - (pollutions[N - 1].x - pollutions[0].x);
    ans = Math.min(ans, Math.min(left, right));
    // 각 지점을 분석하는곳 이라 가정

    System.out.println(ans);
  } // end of class
}// end of main
