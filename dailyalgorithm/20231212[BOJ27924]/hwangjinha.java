package pragmatic.day231212;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj27924_윤이는엄청난것을훔쳐갔습니다 {
    static int n;
    static List<Integer>[] graph;
    static int yun, p1, p2;
    static int[] cells;
    static Set<Integer> leaves = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n];
        cells = new int[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            graph[a].add(b);
            graph[b].add(a);
        }
        st = new StringTokenizer(br.readLine());
        yun = Integer.parseInt(st.nextToken()) - 1;
        p1 = Integer.parseInt(st.nextToken()) - 1;
        p2 = Integer.parseInt(st.nextToken()) - 1;
        cells[yun] = 1;
        cells[p1] = 2;
        cells[p2] = 2;

        for (int i = 0; i < n; i++ ) {
            if (graph[i].size() == 1)
                leaves.add(i);
        }

        System.out.println(bfs()?"YES":"NO");
    }

    private static boolean bfs() {
        Queue<Integer> yq = new LinkedList<>();
        Queue<Integer> pq = new LinkedList<>();
        yq.add(yun);
        pq.add(p1);
        pq.add(p2);
        while (!yq.isEmpty()) {
            int size = yq.size();
            for (int i = 0; i < size; i++) {
                int y = yq.poll();
                if (leaves.contains(y))
                    return true;
                if (cells[y] == 2)
                    continue;
                for (int nexty : graph[y]) {
                    if (cells[nexty] == 0) {
                        yq.add(nexty);
                        cells[nexty] = 1;
                    }
                }
            }

            size = pq.size();
            for (int i = 0; i < size; i++) {
                int p = pq.poll();
                for (int nextp : graph[p]) {
                    if (cells[nextp] <= 1) {
                        pq.add(nextp);
                        cells[nextp] = 2;
                    }
                }
            }
        }
        return false;
    }
}