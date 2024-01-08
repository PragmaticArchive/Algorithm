package pragmatic.day240108;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj1005_ACM_Craft {
    static int n, k;
    static int[] arr; // 걸리는 시간
    static int[] time; // 일을 할 수 있는 시간
    static ArrayList<Integer>[] graph;
    static int[] entries;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            arr = new int[n + 1];
            time = new int[n + 1];
            entries = new int[n + 1];
            graph = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            int a, b;
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                entries[b]++;
            }
            Queue<Integer> q = new ArrayDeque<>();
            for (int i = 1; i <= n; i++) {
                if (entries[i] == 0)
                    q.add(i);
            }

            int w = Integer.parseInt(br.readLine());

            int ans = 0;
            while (!q.isEmpty()) {
                int node = q.poll();
                if (node == w) {
                    ans = time[node] + arr[node];
                    break;
                }
                for (int next : graph[node]) {
                    time[next] = Math.max(time[node] + arr[node], time[next]);
                    entries[next]--;
                    if (entries[next] == 0) {
                        q.add(next);
                    }
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}
