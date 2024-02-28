import java.io.*;
import java.util.*;

public class Main {

  private static ArrayList<Integer>[] alphabetDict;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    StringBuilder sb = new StringBuilder();
    alphabetDict = new ArrayList['z' - 'a' + 1];
    for (int i = 0; i < T; i++) {
      for (int j = 0; j < 'z' - 'a' + 1; j++) {
        alphabetDict[j] = new ArrayList<>();
      }
      char[] inputs = br.readLine().toCharArray();
      int K = Integer.parseInt(br.readLine());

      for (int j = 0; j < inputs.length; j++) {
        alphabetDict[inputs[j] - 'a'].add(j);
      }
      int max = -1, min = Integer.MAX_VALUE;
      for (int j = 0; j < alphabetDict.length; j++) {
        if (alphabetDict[j].size() >= K) {
          for (int k = 0; k <= alphabetDict[j].size() - K; k++) {
            int endInx = alphabetDict[j].get(k + K - 1);
            int startInx = alphabetDict[j].get(k);
            max = Math.max(max, endInx - startInx + 1);
            min = Math.min(min, endInx - startInx + 1);
          }
        }
      }
      if (max == -1) {
        sb.append(-1).append("\n");
      } else {
        sb.append(min).append(" ").append(max).append("\n");
      }
    }
    System.out.println(sb);
  }// end of main
}// end of class
