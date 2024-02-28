import java.io.*;
import java.util.*;

public class Main {

  private static int N, M, A, B;
  private static ArrayList<City>[] cities;
  private static PriorityQueue<City> pq = new PriorityQueue<>((c1, c2) -> Long.compare(c1.time, c2.time));

  static class City {

    int num;
    long time;

    public City(int num, long time) {
      this.num = num;
      this.time = time;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    A = Integer.parseInt(st.nextToken());
    B = Integer.parseInt(st.nextToken());

    cities = new ArrayList[N + 1];
    for (int i = 1; i <= N; i++) {
      cities[i] = new ArrayList<>();
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      long t = Long.parseLong(st.nextToken());
      cities[a].add(new City(b, t));
      cities[b].add(new City(a, t));
    }

    long[] distA = dijkstra(A);
    long[] distB = dijkstra(B);
    findCity(distA, distB);
  }// end of main

  private static void findCity(long[] distA, long[] distB) {
    StringBuilder sb = new StringBuilder();
    int cnt = 0;
    for (int i = 1; i <= N; i++) {
      if (distA[B] == distA[i] + distB[i]) {
        cnt++;
        sb.append(i).append(" ");
      }
    }
    System.out.println(cnt);
    System.out.println(sb);
  }

  private static long[] dijkstra(int start) {
    pq.offer(new City(start, 0));
    long[] dist = new long[N + 1];
    Arrays.fill(dist, Long.MAX_VALUE);
    dist[start] = 0L;

    while (!pq.isEmpty()) {
      City cur = pq.poll();
      if (dist[cur.num] < cur.time) {
        continue;
      }
      for (City next : cities[cur.num]) {
        if (dist[next.num] > dist[cur.num] + next.time) {
          dist[next.num] = dist[cur.num] + next.time;
          pq.offer(new City(next.num, dist[next.num]));
        }
      }
    }
    return dist;
  }
}// end of class
