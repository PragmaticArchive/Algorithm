import java.io.*;
import java.util.*;

public class Main {

  static class Student {

    int inx, danger;

    public Student(int inx, int danger) {
      this.inx = inx;
      this.danger = danger;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[][] dp = new int[51][51];
    for (int i = 0; i < 51; i++) {
      Arrays.fill(dp[i], -1);
    }
    dp[0][0] = 0;
    // dp
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      int red = Integer.parseInt(st.nextToken());
      int blue = Integer.parseInt(st.nextToken());
      int danger = Integer.parseInt(st.nextToken());
      for (int j = 50; j >= red; j--) {
        for (int k = 50; k >= blue; k--) {
          if (dp[j - red][k - blue] == -1) {
            continue;
          }
          dp[j][k] = Math.max(dp[j][k], dp[j - red][k - blue] + danger);
        }
      }
    }
    // 순서 리스트 (저위험군, 번호순)
    Student[] students = new Student[N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      int red = Integer.parseInt(st.nextToken());
      int blue = Integer.parseInt(st.nextToken());
      students[i] = new Student(i + 1, dp[red][blue] == -1 ? 0 : dp[red][blue]);
    }
    Arrays.sort(students, Comparator.comparing((Student s) -> s.danger).thenComparing(s -> s.inx));
    StringBuilder sb = new StringBuilder();
    for (Student student : students) {
      sb.append(student.inx).append(" ").append(student.danger).append("\n");
    }
    System.out.println(sb);
  }// end of main
}// end of class
