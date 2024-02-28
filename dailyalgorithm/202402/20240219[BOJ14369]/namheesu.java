import java.io.*;
import java.util.*;

public class Main {

  private static List<Integer> ans = new ArrayList<>();
  private static final char[][] NUMBER = {
      {'Z', 'E', 'R', 'O'},
      {'O', 'N', 'E'},
      {'T', 'W', 'O'},
      {'T', 'H', 'R', 'E', 'E'},
      {'F', 'O', 'U', 'R'},
      {'F', 'I', 'V', 'E'},
      {'S', 'I', 'X'},
      {'S', 'E', 'V', 'E', 'N'},
      {'E', 'I', 'G', 'H', 'T'},
      {'N', 'I', 'N', 'E'}
  };

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());

    StringBuilder sb = new StringBuilder();
    for (int tc = 1; tc <= T; tc++) {
      char[] S = br.readLine().toCharArray();
      getNumber(S);
      ans.sort((n1, n2) -> n1 - n2);
      sb.append("Case #").append(tc).append(": ");
      for (Integer n : ans) {
        sb.append(n);
      }
      sb.append("\n");
      ans.clear();
    }
    System.out.println(sb.toString());
  }// end of main

  private static void getNumber(char[] s) {
    int[] cnt = countChar(s);
    repeatNum('Z', 0, cnt);
    repeatNum('W', 2, cnt);
    repeatNum('U', 4, cnt);
    repeatNum('X', 6, cnt);
    repeatNum('G', 8, cnt);
    repeatNum('F', 5, cnt);
    repeatNum('H', 3, cnt);
    repeatNum('I', 9, cnt);
    repeatNum('S', 7, cnt);
    repeatNum('O', 1, cnt);
  }

  private static void repeatNum(char c, int num, int[] cnt) {
    while (cnt[c - 'A'] != 0) {
      ans.add(num);
      for (char number : NUMBER[num]) {
        cnt[number - 'A']--;
      }
    }
  }

  private static int[] countChar(char[] s) {
    int[] cnt = new int[26];
    for (char c : s) {
      cnt[c - 'A']++;
    }
    return cnt;
  }
}// end of class
