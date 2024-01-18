import java.io.*;
import java.util.*;

public class Main {

  static class Problem {

    int num;
    int difficulty;
    boolean solved;

    public Problem(int num, int difficulty) {
      this.num = num;
      this.difficulty = difficulty;
    }
  }

  static PriorityQueue<Problem> maxPQ, minPQ;
  static Problem[] problems;
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    problems = new Problem[100_001];

    maxPQ = new PriorityQueue<>((p1, p2) ->
        p2.difficulty == p1.difficulty ? Integer.compare(p1.num, p2.num) * -1
            : Integer.compare(p1.difficulty, p2.difficulty) * -1
    );
    minPQ = new PriorityQueue<>((p1, p2) ->
        p2.difficulty == p1.difficulty ? Integer.compare(p1.num, p2.num)
            : Integer.compare(p1.difficulty, p2.difficulty)
    );

    StringTokenizer st;
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      int P = Integer.parseInt(st.nextToken());
      int L = Integer.parseInt(st.nextToken());
      problems[P] = new Problem(P, L);
      maxPQ.offer(problems[P]);
      minPQ.offer(problems[P]);
    }

    int M = Integer.parseInt(br.readLine());
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      int x, P, L;
      switch (st.nextToken()) {
        case "recommend":
          x = Integer.parseInt(st.nextToken());
          recommend(x == 1 ? maxPQ : minPQ);
          break;
        case "solved":
          P = Integer.parseInt(st.nextToken());
          problems[P].solved = true;
          break;
        case "add":
          P = Integer.parseInt(st.nextToken());
          L = Integer.parseInt(st.nextToken());
          problems[P] = new Problem(P, L);
          maxPQ.offer(problems[P]);
          minPQ.offer(problems[P]);
      }
    }
    System.out.println(sb);
  }// end of main

  private static void recommend(PriorityQueue<Problem> pq) {
    while (!pq.isEmpty() & pq.peek().solved) {
      pq.poll();
    }
    sb.append(pq.peek().num).append("\n");
  }
}// end of class
