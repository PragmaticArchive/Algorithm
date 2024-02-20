import java.io.*;
import java.util.*;

public class Main {


  private static int N, M, ans = 0;
  private static int[][] dist;
  private static char[][] map;
  private static ArrayList<Person> exits = new ArrayList<>();

  private static class Person {

    int y, x;

    public Person(int y, int x) {
      this.y = y;
      this.x = x;
    }
  }

  private static final int[] DY = {-1, 0, 1, 0};
  private static final int[] DX = {0, 1, 0, -1};
  private static ArrayList<Person> people = new ArrayList<>();
  private static Queue<Person> queue = new ArrayDeque<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    map = new char[N + 2][M + 2];

    Person hanbyeol = null;
    for (int i = 1; i <= N; i++) {
      char[] input = br.readLine().toCharArray();
      for (int j = 1; j <= M; j++) {
        map[i][j] = input[j - 1];
        if (map[i][j] == '#') {
          exits.add(new Person(i, j));
        } else if (map[i][j] == 'H') {
          hanbyeol = new Person(i, j);
        } else if (map[i][j] == 'P') {
          people.add(new Person(i, j));
        }
      }
    }
    for (Person exit : exits) {
      dist = new int[N + 2][M + 2];
      for (int i = 0; i < N + 2; i++) {
        Arrays.fill(dist[i], Integer.MAX_VALUE);
      }
      bfs(exit);
      if (dist[hanbyeol.y][hanbyeol.x] == Integer.MAX_VALUE) {
        continue;
      }
      int cnt = 0;
      for (Person person : people) {
        if (dist[person.y][person.x] <= dist[hanbyeol.y][hanbyeol.x]) {
          cnt++;
        }
      }
      ans = Math.max(ans, cnt);
    }
    System.out.println(ans);
  }// end of main

  private static void bfs(Person exit) {
    queue.clear();
    queue.offer(exit);
    dist[exit.y][exit.x] = 0;

    while (!queue.isEmpty()) {
      Person cur = queue.poll();
      for (int i = 0; i < 4; i++) {
        int ny = cur.y + DY[i];
        int nx = cur.x + DX[i];
        if (ny < 1 || nx < 1 || ny > N || nx > M || map[ny][nx] == 'X') {
          continue;
        }
        if (dist[ny][nx] > dist[cur.y][cur.x] + 1) {
          dist[ny][nx] = dist[cur.y][cur.x] + 1;
          queue.offer(new Person(ny, nx));
        }
      }
    }
  }
}// end of class
