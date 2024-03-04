import java.io.*;
import java.util.*;

public class Main {

  static int H, W;
  static char[][] map;
  static Plot start, dest;

  static class Plot {

    int y, x, time;

    Plot(int y, int x, int time) {
      this.y = y;
      this.x = x;
      this.time = time;
    }
  }

  static final int[] dy = {-1, 0, 1, 0};
  static final int[] dx = {0, 1, 0, -1};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    H = Integer.parseInt(st.nextToken());
    W = Integer.parseInt(st.nextToken());
    map = new char[H + 2][W + 2];
    for (int i = 1; i <= H; i++) {
      char[] input = br.readLine().toCharArray();
      int inx = 0;
      for (int j = 1; j <= W; j++) {
        map[i][j] = input[inx++];
        if (map[i][j] == 'S') {
          start = new Plot(i, j, 0);
          map[i][j] = '.';
        } else if (map[i][j] == 'E') {
          dest = new Plot(i, j, 0);
          map[i][j] = '.';
        }
      }
    }

    for (int i = 1; i <= H; i++) {
      for (int j = 1; j <= W; j++) {
        if (map[i][j] == '.') {
          aroundWall(i, j);
        }
      }
    }

    System.out.println(bfs());
  } // end of class

  private static int bfs() {
    PriorityQueue<Plot> pq = new PriorityQueue<>((p1, p2) -> p1.time - p2.time);
    int[][] dist = new int[H + 2][W + 2];
    for (int i = 1; i <= H; i++) {
      Arrays.fill(dist[i], Integer.MAX_VALUE);
    }
    pq.offer(start);
    dist[start.y][start.x] = 0;

    while (!pq.isEmpty()) {
      Plot cur = pq.poll();
      if (cur.time > dist[cur.y][cur.x]) {
        continue;
      }
      for (int i = 0; i < 4; i++) {
        int ny = cur.y + dy[i];
        int nx = cur.x + dx[i];
        if (!chkRange(ny, nx) || map[ny][nx] == '#') {
          continue;
        }
        int d = cur.time + 1;
        if (map[cur.y][cur.x] == '*' && map[ny][nx] == '*') {
          d--;
        }
        if (dist[ny][nx] > d) {
          dist[ny][nx] = d;
          pq.offer(new Plot(ny, nx, d));
        }
      }
    }
    return dist[dest.y][dest.x];
  }

  private static void aroundWall(int y, int x) {
    for (int k = 0; k < 4; k++) {
      int ny = y + dy[k];
      int nx = x + dx[k];
      if (chkRange(ny, nx) && map[ny][nx] == '#') {
        map[y][x] = '*';
        return;
      }
    }
  }

  private static boolean chkRange(int ny, int nx) {
    return ny >= 1 && nx >= 1 && ny <= H && nx <= W;
  }
}// end of main
