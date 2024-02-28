import java.io.*;
import java.util.*;

public class Main {

  private static long[][] dist;
  private static int N, M, K;
  private static long[] elevator;
  private static ArrayList<Room>[] hallway;

  private static class Room {

    int num;
    long time;

    public Room(int num, long time) {
      this.num = num;
      this.time = time;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    hallway = new ArrayList[N + 1];
    for (int i = 1; i <= N; i++) {
      hallway[i] = new ArrayList<>();
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      int u = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());
      long c = Long.parseLong(st.nextToken());
      hallway[u].add(new Room(v, c));
      hallway[v].add(new Room(u, c));
    }

    elevator = new long[N + 1];
    st = new StringTokenizer(br.readLine(), " ");
    for (int i = 1; i <= N; i++) {
      elevator[i] = Long.parseLong(st.nextToken());
    }

    dist = new long[2][N + 1]; // 1층, K층
    for (int i = 0; i < 2; i++) {
      Arrays.fill(dist[i], Long.MAX_VALUE);
    }

    dijkstra(0, 1);
    dijkstra(1, N);

    long ans = Long.MAX_VALUE;
    for (int i = 1; i <= N; i++) {
      if (elevator[i] == -1 || dist[0][i] == Long.MAX_VALUE || dist[1][i] == Long.MAX_VALUE) {
        continue;
      }
      ans = Math.min(ans, dist[0][i] + elevator[i] * (K - 1) + dist[1][i]);
    }
    System.out.println(ans == Long.MAX_VALUE ? -1 : ans);
  }// end of main

  private static void dijkstra(int floor, int start) {
    PriorityQueue<Room> pq = new PriorityQueue<>((r1, r2) -> Long.compare(r1.time, r2.time));
    dist[floor][start] = 0L;
    pq.offer(new Room(start, 0L));

    while (!pq.isEmpty()) {
      Room cur = pq.poll();

      if (cur.time > dist[floor][cur.num]) {
        continue;
      }
      for (Room nxt : hallway[cur.num]) {
        long d = dist[floor][cur.num] + nxt.time;
        if (dist[floor][nxt.num] > d) {
          dist[floor][nxt.num] = d;
          pq.offer(new Room(nxt.num, d));
        }
      }
    }
  }
}// end of class
