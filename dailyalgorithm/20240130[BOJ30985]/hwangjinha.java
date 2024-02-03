package pragmatic.day240130;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj30985_직장인_파댕이의_사회생활 {
    static int n, m, k;
    static ArrayList<Node>[] graph;
    static long INF = Long.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int u, v, c;
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v, c));
            graph[v].add(new Node(u, c));
        }

        long[] dijkFrom = dijkstra(1);
        long[] dijkTo = dijkstra(n);
        long ans = INF;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            long elev = Integer.parseInt(st.nextToken());
            if (dijkTo[i] == INF || dijkFrom[i] == INF) continue;
            if (elev == -1)
                continue;
            elev *= (k-1);
            long len = dijkFrom[i] + dijkTo[i] + elev;
            if (ans > len)
                ans = len;
        }

        System.out.println(ans == INF ? -1 : ans);
    }

    private static long[] dijkstra(int i) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Long.compare(a.e, b.e));
        boolean[] fixed = new boolean[n + 1];
        long[] dists = new long[n + 1];
        Arrays.fill(dists, INF);
        dists[i] = 0;

        pq.add(new Node(i, 0));
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (fixed[now.v]) continue;
            fixed[now.v] = true;

            for (Node next : graph[now.v]) {
                if (fixed[next.v])
                    continue;
                if (dists[next.v] > now.e + next.e) {
                    dists[next.v] = now.e + next.e;
                    pq.add(new Node(next.v, dists[next.v]));
                }
            }
        }
        return dists;
    }

    static class Node {
        int v;
        long e;

        public Node(int v, long e) {
            this.v = v;
            this.e = e;
        }
    }
}
