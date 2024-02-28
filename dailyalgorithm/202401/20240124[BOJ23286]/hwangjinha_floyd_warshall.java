package pragmatic.day240124;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj23286_허들_넘기_플로이드워셜 {

    static int n, m, t;
    static ArrayList<Node>[] graph;
    static int[][] dists;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        dists = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) Arrays.fill(dists[i], Integer.MAX_VALUE);

        int u, v, h;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            dists[u][v] = h;
        }

        // 플로이드 워셜
        for (int via = 1; via <= n; via++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dists[i][j] = Math.min(dists[i][j],
                        Math.max(dists[i][via], dists[via][j]));
                }
            }
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