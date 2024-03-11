import java.io.*;
import java.util.*;

public class Main {

  static int N, M, K;
  static final int[] DY = {-1, 0, 1, 0};
  static final int[] DX = {0, 1, 0, -1};
  static int[][] map;

  static class Plot {

    int y, x, health;

    public Plot(int y, int x) {
      this.y = y;
      this.x = x;
    }

    public Plot(int y, int x, int health) {
      this(y, x);
      this.health = health;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    map = new int[N + 2][M + 2];
    Plot start = null;
    ArrayDeque<Plot> mannequins = new ArrayDeque<>();
    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= M; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
        if (map[i][j] == 4) {
          start = new Plot(i, j);
        } else if (map[i][j] == 3) {
          mannequins.offer(new Plot(i, j));
          map[i][j] = 1;
        }
      }
    }

    makeWall(mannequins);
    System.out.println(bfs(start));
  } // end of class

  private static void makeWall(ArrayDeque<Plot> mannequins) {
    boolean[][] visited = new boolean[N + 2][M + 2];
    while (!mannequins.isEmpty()) {
      Plot cur = mannequins.poll();
      if (cur.health == K) {
        continue;
      }
      for (int k = 0; k < 4; k++) {
        int ny = cur.y + DY[k];
        int nx = cur.x + DX[k];
        if (!chkRange(ny, nx) || visited[ny][nx]) {
          continue;
        }
        visited[ny][nx] = true;
        map[ny][nx] = 1;
        mannequins.offer(new Plot(ny, nx, cur.health + 1));
      }
    }
  }

  private static int bfs(Plot start) {
    ArrayDeque<Plot> queue = new ArrayDeque<>();
    boolean[][] visited = new boolean[N + 2][M + 2];
    queue.add(start);
    visited[start.y][start.x] = true;
    while (!queue.isEmpty()) {
      Plot cur = queue.poll();
      if (map[cur.y][cur.x] == 2) {
        return cur.health;
      }
      for (int i = 0; i < 4; i++) {
        int ny = cur.y + DY[i];
        int nx = cur.x + DX[i];
        if (!chkRange(ny, nx) || visited[ny][nx] || map[ny][nx] == 1) {
          continue;
        }
        visited[ny][nx] = true;
        queue.add(new Plot(ny, nx, cur.health + 1));
      }
    }
    return -1;
  }

  private static boolean chkRange(int y, int x) {
    return 1 <= y && y <= N && 1 <= x && x <= M;
  }
}// end of main
