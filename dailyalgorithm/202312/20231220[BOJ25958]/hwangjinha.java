package pragmatic.day231220;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj25958_예쁜수 {

    static int m, k;
    static List<Integer> prettyNums = new ArrayList<>();

    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= m; i++) {
            if (isPretty(i))
                prettyNums.add(i);
        }

        dp = new int[m + 1];
        dp[0] = 1;
        for (int pn : prettyNums) {
            for (int num = m; num > 0; num--) {
                int minus = 0;
                for (int i = 1; num - (minus = pn * i) >= 0; i++ ) {
                    dp[num] += dp[num - minus];
                    dp[num] %= k;
                }
            }
//            System.out.println();
//            System.out.println(pn);
//            System.out.println(Arrays.toString(dp));
        }
        System.out.println(dp[m]);
    }

    private static boolean isPretty(int i) {
        int original = i;
        int sum = 0;
        while (i != 0) {
            sum += i % 10;
            i /= 10;
        }
        return original % sum == 0;
    }
}
