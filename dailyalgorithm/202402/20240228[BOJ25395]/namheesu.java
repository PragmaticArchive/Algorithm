import java.io.*;
import java.util.*;

public class Main {

  static int N, S;

  static class Car {

    int x, h;

    public Car() {
    }

    public Car(int x, int h) {
      this.x = x;
      this.h = h;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    S = Integer.parseInt(st.nextToken()) - 1;

    Car[] cars = new Car[N];
    st = new StringTokenizer(br.readLine(), " ");
    for (int i = 0; i < N; i++) {
      cars[i] = new Car();
      cars[i].x = Integer.parseInt(st.nextToken());
    }
    st = new StringTokenizer(br.readLine(), " ");
    for (int i = 0; i < N; i++) {
      cars[i].h = Integer.parseInt(st.nextToken());
    }

    bfs(cars);
  } // end of class

  private static void bfs(Car[] cars) {
    ArrayDeque<Car> queue = new ArrayDeque<>();
    boolean[] visited = new boolean[N];

    queue.offer(cars[S]);
    visited[S] = true;

    int left = S, right = S;
    int min = cars[S].x, max = cars[S].x;
    while (!queue.isEmpty()) {
      Car cur = queue.poll();
      min = Math.min(min, cur.x - cur.h);
      max = Math.max(max, cur.x + cur.h);

      for (int i = left - 1; i >= 0; i--) {
        if (cars[i].x < min)break;
        if(visited[i])continue;
        left = Math.min(left,i);
        visited[i]=true;
        queue.offer(cars[i]);
      }

      for (int i = right+1; i <N ; i++) {
        if (cars[i].x > max)break;
        if(visited[i])continue;
        right = Math.max(right,i);
        visited[i]=true;
        queue.offer(cars[i]);
      }
    }
    StringBuilder sb = new StringBuilder();
    for (int i = left; i <=right ; i++) {
      sb.append(i+1).append(" ");
    }
    System.out.println(sb);
  }

}// end of main
