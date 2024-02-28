package pragmatic.day240105;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj18223_민준이와_마산_그리고_건우 {

    static int v, e, p;
    static ArrayList<Next> graph[];
    static boolean[] fixed;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        graph = new ArrayList[v + 1];
        for (int i = 0; i <= v; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Next(b, c));
            graph[b].add(new Next(a, c));
        }

        int[] dijk = dijkstra(1);
        int comp = dijk[v];

        dijk = dijkstra(p);
        System.out.println(comp == dijk[1] + dijk[v]? "SAVE HIM" : "GOOD BYE");

    }

    private static int[] dijkstra(int startP) {
        PriorityQueue<Next> pq = new PriorityQueue<>((a, b) -> {
            return a.w - b.w;
        });
        int[] dijk = new int[v + 1];
        fixed = new boolean[v + 1];
        Arrays.fill(dijk, Integer.MAX_VALUE);

        pq.add(new Next(startP, 0));
        dijk[startP] = 0;

        while (!pq.isEmpty()) {
            Next now = pq.poll();
            if (fixed[now.v]) continue;

            for (Next next : graph[now.v]) {
                if (fixed[next.v]) continue;
                if (dijk[next.v] > dijk[now.v] + next.w) {
                    dijk[next.v] = dijk[now.v] + next.w;
                    pq.add(new Next(next.v, dijk[next.v]));
                }
            }
        }
        return dijk;
    }

    static class Next {
        int v;
        int w;

        public Next(int v, int w) {
            this.v = v;
            this.w = w;
        }

    }
}