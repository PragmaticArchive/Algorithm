package pragmatic.day231227;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj22853_도도의_음식_준비 {

    static long n, k, c;
    static long[] arr;

    static long ans = 1000000L * 1000000 * 10 + 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Long.parseLong(st.nextToken());
        k = Long.parseLong(st.nextToken());
        c = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new long[(int)n];

        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        dfs(0, 0);
        System.out.println(ans);
    }

    static void dfs(int depth, int start) {
        binSearch();

        if (depth == c) {
            return;
        }

        for (int i = start; i < n; i++) {
            if (arr[i] <= 1) continue;
            arr[i] -= 1;
            dfs(depth+1, i);
            arr[i] += 1;
        }
    }

    private static void binSearch() {
        long l = -1;
        long r = ans;
        long m;
        while (l + 1 < r) {
            m = (l + r) / 2;
            if (check(m)) {
                r = m;
            } else {
                l = m;
            }
        }
        ans = Math.min(ans, r);
    }

    private static boolean check(long time) {
        long cnt = 0;
        for (int i = 0;i < n; i++) {
            cnt += time / arr[i];
        }
        return cnt >= k;
    }
}