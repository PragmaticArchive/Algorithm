package pragmatic.day231221;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj12015_가장긴증가하는부분수열2 {

    static int n;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        dp = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        dp[0] = arr[0];

        System.out.println(lcs() + 1);
    }

    private static int lcs() {
        int idx = 0;
        for (int i = 1; i < n; i++) {
            if (dp[idx] < arr[i])
                dp[++idx] = arr[i];
            else {
                dp[bs(idx, arr[i])] = arr[i];
            }
        }

        return idx;
    }

    private static int bs(int idx, int num) {
        int l = 0;
        int r = idx;
        int m;
        while (l < r) {
            m = (l + r) / 2;
            if (dp[m] < num) l = m + 1;
            else r = m;
        }
        return l;
    }
}