package pragmatic.day231213;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj18324_Race {
    static int k, n;
    static long x;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        while (n-- > 0) {
            x = Long.parseLong(br.readLine());

            int l = 1;
            int r = 100000;
            while (l + 1 != r) {
                int m = (l + r) / 2;
                if (check(m)) l = m;
                else r = m;
            }
            sb.append(getTime(l)).append("\n");
        }
        System.out.println(sb);
    }

    private static long getTime(long maxS) {
        long front = maxS * (maxS + 1) / 2;
        long back = maxS <= x ? 0 : (maxS - x) * (maxS + x - 1) / 2;
        long remain = (long)k - front - back;
        return maxS + Math.max(0, maxS - x) + remain / maxS + ((remain % maxS == 0)? 0 : 1) ;
    }

    private static boolean check(long maxS) {
        long front = maxS * (maxS + 1) / 2;
        long back = maxS <= x ? 0 : (maxS - x) * (maxS + x - 1) / 2;
        return k >= front + back;
    }
}