import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dp = new int[M + 1];
        dp[0] = 1;
        for (int i = 1; i <= M; i++) {
            if (!isPrettyNum(i)) continue;
            for (int j = i; j <= M; j++) {
                dp[j] = (dp[j] + dp[j - i]) % K;
            }
        }
        System.out.println(dp[M]);
    } // end of main

    private static boolean isPrettyNum(int N) {
        int S = 0;
        int tmp = N;
        while (tmp != 0) {
            S += tmp % 10;
            tmp /= 10;
        }
        return N % S == 0;
    }
} // end of class
