import java.io.*;
import java.util.*;

public class Main {

  private static int N;
  private static long X;
  private static long[] inputs;
  private static PriorityQueue<Line> pq = new PriorityQueue<>((l1, l2) -> Long.compare(l1.time, l2.time));

  private static class Line {

    int inx;
    long time;

    public Line(int inx, long time) {
      this.inx = inx;
      this.time = time;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    N = Integer.parseInt(st.nextToken());
    X = Long.parseLong(st.nextToken());

    st = new StringTokenizer(br.readLine(), " ");
    inputs = new long[N];
    long total = 0L;
    for (int i = 0; i < N; i++) {
      inputs[i] = Long.parseLong(st.nextToken());
      total += inputs[i];
    }
    if (total <= X) {
      System.out.println(1);
    } else {
      System.out.println(binarySearch());
    }
  }// end of main

  private static int binarySearch() {
    int left = 1;
    int right = N;
    while (left < right) {
      int mid = (left + right) / 2;
      if (chk(mid) <= X) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    return right;
  }

  private static long chk(int mid) {
    for (int i = 0; i < mid; i++) {
      pq.offer(new Line(i, inputs[i]));
    }
    long max = 0;
    for (int i = mid; i < N; i++) {
      Line cur = pq.poll();
      cur.time += inputs[i];
      max = Math.max(cur.time, max);
      if (max > X) {
        break;
      }
      pq.offer(cur);
    }
    pq.clear();
    return max;
  }
}// end of class
