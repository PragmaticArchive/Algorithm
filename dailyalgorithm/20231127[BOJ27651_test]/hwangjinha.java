import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static long[] arr;
    static long[] sums;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new long[n];
        sums = new long[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        int idx = 0;
        while (st.hasMoreTokens()) {
            arr[idx++] = Integer.parseInt(st.nextToken());
            sums[idx] = sums[idx - 1] + arr[idx - 1];
        }

        long ans = 0L;
        for (int i = 1; i < n - 1; i++) {
            int m = lowerBound(1 + sums[i] + (sums[n] - sums[i])/2);
            int r = lowerBound(sums[n] - sums[i]);
            ans += Math.max(r - m, 0);
        }
        System.out.println(ans);
    }

    static int lowerBound(long target) {
        int l = 0;
        int r = n;
        while (l < r) {
            int m = (l + r) / 2;
            if (sums[m] >= target) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return r;
    }
}