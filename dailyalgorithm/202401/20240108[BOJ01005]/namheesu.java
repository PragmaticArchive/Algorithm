import java.io.*;
import java.util.*;

// 위상 정렬
public class Main {
    private static int N, K, W;
    private static int[] D, parent, maxTime;
    private static ArrayList<Integer>[] graph;
    private static ArrayDeque<Integer> queue = new ArrayDeque<>();
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            D = new int[N + 1];
            parent = new int[N + 1];
            graph = new ArrayList[N + 1];
            maxTime = new int[N + 1];

            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 1; i <= N; i++) {
                D[i] = Integer.parseInt(st.nextToken());
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                parent[y]++;
                graph[x].add(y);
            }

            W = Integer.parseInt(br.readLine());
            building();
        }
        System.out.println(sb);
    } // end of main

    private static void building() {
        for (int i = 1; i <= N; i++) {
            if (parent[i] == 0) {
                queue.offer(i);
                maxTime[i] = D[i];
            }
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == W) {
                sb.append(maxTime[W]).append("\n");
                queue.clear();
                return;
            }
            for (int nxt : graph[cur]) {
                maxTime[nxt] = Math.max(maxTime[nxt], maxTime[cur] + D[nxt]);
                if (--parent[nxt] == 0) {
                    queue.offer(nxt);
                }
            }
        }
    }
} // end of class
