import java.io.*;
import java.util.*;

public class Main {
    static int T, W;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        tree = new int[T + 1];
        for (int i = 1; i <= T; i++) {
            tree[i] = br.readLine().charAt(0) - '0';
        }
        int[][] dp = new int[T + 1][W + 1];

        for (int i = 1; i <= T; i++) {
            dp[i][0] = dp[i - 1][0];
            if (tree[i] == 1) {
                dp[i][0] += 1;
            }
            for (int j = 1; j <= W; j++) {
                if (tree[i] == 2 && j % 2 == 1) {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + 1;
                } else if (tree[i] == 1 && j % 2 == 0) {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i <= W; i++) {
            ans = Math.max(ans, dp[T][i]);
        }
        System.out.println(ans);
    } // end of main
} // end of class
