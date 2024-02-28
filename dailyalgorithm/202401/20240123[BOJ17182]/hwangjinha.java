package pragmatic.day240123;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj17182_우주_탐사선 {
    static int n, k;
    static int[][] fw;
    static boolean[] visited;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        fw = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                fw[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int via = 0; via < n; via++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j || i == via || j == via)
                        continue;
                    int originalLen = fw[i][j];
                    int viaLen = fw[i][via] + fw[via][j];
                    if (viaLen < originalLen) {
                        fw[i][j] = viaLen;
                    }
                }
            }
        }

        visited = new boolean[n];
        visited[k] = true;
        dfs(k, 1, 0);

        System.out.println(ans);
    }

    private static void dfs(int node, int depth, int sum) {
        if (depth == n) {
            ans = Math.min(ans, sum);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i])
                continue;
            if (i == node)
                continue;

            visited[i] = true;
            dfs(i, depth+1, sum + fw[node][i]);
            visited[i] = false;
        }
    }
}
