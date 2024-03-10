import java.io.*;
import java.util.*;

public class Main {

  static class Star {

    int num, height;
    ArrayList<Star> children;

    public Star(int num) {
      this.num = num;
      this.children = new ArrayList<>();
    }
  }

  static PriorityQueue<Integer> pq = new PriorityQueue<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    pq.offer(0);

    StringTokenizer st;
    for (int i = 0; i < N; i++) {
      int starNum = Integer.parseInt(br.readLine());
      Star[] stars = new Star[starNum + 1];
      for (int j = 1; j <= starNum; j++) {
        stars[j] = new Star(j);
      }
      for (int j = 0; j < starNum - 1; j++) {
        st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        stars[v].children.add(stars[w]);
        stars[w].children.add(stars[v]);
      }
      setStar(stars);
      setHeight(stars, starNum);
    }
    int ans = 0;
    while (!pq.isEmpty()) {
      ans = pq.poll();
    }
    System.out.println(ans);
  } // end of class

  private static void setStar(Star[] stars) {
    int leaf = pq.poll();
    stars[1].children.add(new Star(0));
    stars[1].height = leaf + 1;
  }

  private static void setHeight(Star[] star, int starNum) {
    ArrayDeque<Star> queue = new ArrayDeque<>();
    boolean[] visited = new boolean[starNum + 1];
    visited[1] = true;
    queue.offer(star[1]);
    while (!queue.isEmpty()) {
      Star cur = queue.poll();
      if (cur.children.size() == 1) {
        pq.offer(cur.height);
        continue;
      }
      for (Star child : cur.children) {
        if (visited[child.num] || child.num == 0) {
          continue;
        }
        visited[child.num] = true;
        child.height = cur.height + 1;
        queue.offer(child);
      }
    }
  }
}// end of main
