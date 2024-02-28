package pragmatic.day240125;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj31230_모비스터디 {

    static int N, M, A, B;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        long[] dijk_A = diskstra(A);
        long[] dijk_B = diskstra(B);

        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (dijk_A[B] == dijk_A[i] + dijk_B[i]) {
                cnt++;
                sb.append(i).append(" ");
            }
        }
        System.out.println(cnt);
        System.out.println(sb);

    }

    static long[] diskstra(int start) {
        long[] dist = new long[N + 1];
        boolean[] fixed = new boolean[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE / 2);
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {return Long.compare(a.e, b.e);});
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (fixed[now.v]) continue;
            fixed[now.v] = true;

            for (Node next : graph[now.v]) {
                long originalLen = dist[next.v];
                long viaLen = now.e + next.e;

                if (originalLen > viaLen) {
                    dist[next.v] = viaLen;
                    pq.add(new Node(next.v, viaLen));
                }
            }
        }
        return dist;
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