package pragmatic.day231129;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj1939_중량제한 {
    static int n, m;
    static List<int[]>[] graph;
    static int A, B;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<int[]> ();
        }

        int a, b, c;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            graph[a].add(new int[] {b, c});
            graph[b].add(new int[] {a, c});
        }
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        int l = 0;
        int r = 1000000000 + 1;
        int m;
        while (l + 1 < r) {
            m = (l + r) / 2;
            if (check(m)) {
                l = m;
            } else {
                r = m;
            }
        }
        System.out.println(l);
    }

    static boolean[] visited;
    private static boolean check(int m) {
        visited = new boolean[n+1];
        Queue<Integer> q = new LinkedList<>();
        q.add(A);
        visited[A] = true;
        while (!q.isEmpty()) {
            int now = q.poll();
            for (int[] next : graph[now]) {
                if (visited[next[0]] || next[1] < m) continue;
                if (next[0] == B) {
                    return true;
                }
                q.add(next[0]);
                visited[next[0]] = true;
            }
        }
        return false;
    }
}