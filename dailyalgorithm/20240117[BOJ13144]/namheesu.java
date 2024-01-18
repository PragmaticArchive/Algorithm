import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] inputs = new int[N];
    boolean[] chk = new boolean[100_001];

    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    for (int i = 0; i < N; i++) {
      inputs[i] = Integer.parseInt(st.nextToken());
    }

    int r = 0;
    long ans = 0;
    for (int l = 0; l < N; l++) {
      while (r < N && !chk[inputs[r]]) {
        chk[inputs[r]] = true;
        r++;
      }
      ans += r - l;
      chk[inputs[l]] = false;
    }
    System.out.println(ans);
  }// end of main
}// end of class
