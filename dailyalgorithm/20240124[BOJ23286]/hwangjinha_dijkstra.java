package pragmatic.day240124;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj23286_허들_넘기_dijkstra {

    static int n, m, t;
    static ArrayList<Node>[] graph;
    static int[][] dists;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        int u, v, h;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v, h));
        }
        dists = new int[n + 1][];
        for (int i = 1; i <= n; i++) {
            dists[i] = dijk(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int result = dists[s][e];
            sb.append(result == Integer.MAX_VALUE? -1 : result).append("\n");
        }

        System.out.println(sb);
    }

    private static int[] dijk(int start) {
        int[] dist = new int[n + 1];
        boolean[] fixed = new boolean[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.e - b.e);
        pq.add(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (fixed[now.v]) continue;
            fixed[now.v] = true;

            for (Node next : graph[now.v]) {
                int hurdle = Math.max(next.e, now.e);
                if (dist[next.v] > hurdle) {
                    pq.add(new Node(next.v, hurdle));
                    dist[next.v] = hurdle;
                }
            }
        }
        return dist;
    }

    static class Node {
        int v, e;

        public Node(int v, int e) {
            this.v = v;
            this.e = e;
        }

        @Override
        public String toString() {
            return "Node{" +
                "v=" + v +
                ", e=" + e +
                '}';
        }
    }
}