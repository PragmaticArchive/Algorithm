package pragmatic.day240202;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj27314_러키_한별 {

    static int n, m;
    static char[][] cells;
    static int[] hanbyul;
    static ArrayList<int[]> ppl = new ArrayList<>();
    static ArrayList<int[]> exits = new ArrayList<>();
    static int[][] dists;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        cells = new char[n][];
        for (int i = 0; i < n; i++) {
            cells[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (cells[i][j] == '#') {
                    exits.add(new int[] {i, j});
                } else if (cells[i][j] == 'P') {
                    ppl.add(new int[] {i, j});
                } else if (cells[i][j] == 'H') {
                    hanbyul = new int[] {i, j};
                }
            }
        }

        int ans = 0;
        for (int[] exit : exits) {
            bfs(exit);
            int cnt = 0;
            int len = dists[hanbyul[0]][hanbyul[1]];
            if (len == Integer.MAX_VALUE) continue;
            for (int[] person : ppl) {
                if (dists[person[0]][person[1]] <= len) {
                    cnt++;
                }
            }
            ans = Math.max(ans, cnt);
        }
        System.out.println(ans);
    }

    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    private static void bfs(int[] exit) {
        dists = new int[n][m];
        for (int i = 0; i < dists.length; i++)
            Arrays.fill(dists[i], Integer.MAX_VALUE);
        dists[exit[0]][exit[1]] = 0;

        Queue<int[]> q = new LinkedList<>();
        q.add(exit);
        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int i = 0; i < 4; i++) {
                int r = now[0] + dy[i];
                int c = now[1] + dx[i];
                if (r < 0 || c < 0 || n <= r || m <= c || cells[r][c] == 'X')
                    continue;
                if (dists[r][c] > dists[now[0]][now[1]] + 1) {
                    q.add(new int[] {r, c});
                    dists[r][c] = dists[now[0]][now[1]] + 1;
                }
            }
        }
    }
}