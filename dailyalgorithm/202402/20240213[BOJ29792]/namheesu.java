import java.io.*;
import java.util.*;

public class Main {

  private static class Monster {

    long p;
    int q;

    public Monster(long p, int q) {
      this.p = p;
      this.q = q;
    }
  }

  private static int N, M, K, ans = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    long[] characters = new long[N];
    for (int i = 0; i < N; i++) {
      characters[i] = Long.parseLong(br.readLine());
    }

    Monster[] monsters = new Monster[K];
    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      monsters[i] = new Monster(Long.parseLong(st.nextToken()), Integer.parseInt(st.nextToken()));
    }

    long[] coins = new long[N];
    for (int i = 0; i < N; i++) {
      coins[i] = attack(monsters, characters[i]);
    }
    Arrays.sort(coins);
    long ans = 0;
    for (int i = 0; i < M; i++) {
      ans += coins[N - 1 - i];
    }
    System.out.println(ans);
  }// end of main

  private static long attack(Monster[] monsters, long damage) {
    long coin = 0;
    long[] dp = new long[901];
    Arrays.fill(dp, -1);
    dp[0] = 0;

    for (Monster monster : monsters) {
      int time = (int) Math.ceil((double) monster.p / damage);
      for (int i = 900; i >= time; i--) {
        if (dp[i - time] != -1) {
          dp[i] = Math.max(dp[i], dp[i - time] + monster.q);
          coin = Math.max(coin, dp[i]);
        }
      }
    }
    return coin;
  }
}// end of class
