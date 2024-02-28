package pragmatic.day240131;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj22254_공정_컨설턴트_호석 {
    static int n, x;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int l = 0;
        int r = n;
        while (l + 1 < r) {
            int m = (l + r) / 2;
            if (check(m))
                r = m;
            else
                l = m;
        }
        System.out.println(r);
    }

    private static boolean check(int num) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < num; i++)
            pq.add(0);

        int max = 0;
        for (int i = 0; i < n; i++) {
            int time = pq.poll();
            int newTime = time + arr[i];
            max = Math.max(max, newTime);
            pq.add(newTime);
        }
        return x >= max;
    }
}