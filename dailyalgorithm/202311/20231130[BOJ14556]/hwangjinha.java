package pragmatic.day231130;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj14556_Balance {
    static int n;
    static long[] arr;
    static final long lim = 1000000009L;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new long[n+1];
        arr[1] = 1L;

        for (int i = 2; i <= n; i++) {
            arr[i] = (2 * i - 1) * arr[i-1] % lim;
        }
        System.out.println(arr[n]);
    }
}