package pragmatic.day231221;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj14003_가장긴증가하는부분수열5 {

    static int n;
    static int[] arr;
    static int[] dp;
    static int[] indexs;
    static int len;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        dp = new int[n];
        indexs = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        dp[0] = arr[0];
        indexs[0] = 0;

        System.out.println(lcs());
        printLisArray();
    }

    private static void printLisArray() {
        int[] ans = new int[len];
        int i = n - 1;
        int idx = len-1;
        for (; i>=0; i--){
            if (idx == indexs[i]) break;
        }
        ans[idx--] = arr[i];

        for (int ith = indexs[i] - 1; ith >= 0; ith--) {
            for (; i>=0; i--){
                if (ith == indexs[i]) break;
            }
            ans[idx--] = arr[i];
        }

        StringBuilder sb = new StringBuilder();
        for (i = 0; i < len; i++) {
            sb.append(ans[i]).append(" ");
        }
        System.out.println(sb);
    }

    private static int lcs() {
        int idx = 0;
        for (int i = 1; i < n; i++) {
            if (dp[idx] < arr[i]) {
                dp[++idx] = arr[i];
                indexs[i] = idx;
            }
            else {
                int index = bs(idx, arr[i]);
                dp[index] = arr[i];
                indexs[i] = index;
            }
        }

        len = idx + 1;
        return len;
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