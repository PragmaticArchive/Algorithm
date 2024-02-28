package pragmatic.day231218_감시;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class boj15683_감시 {
    static int n, m;
    static int[][] cells;

    static List<Point> locs = new ArrayList<>();
    static int[] dirs;
    static final int[] up = {-1, 0};
    static final int[] down = {1, 0};
    static final int[] right = {0, 1};
    static final int[] left = {0, -1};
    static int[][][][] directions = { // [카메라종류][포지션모양][포지션화살표방향][y/x]
            {},
        {
            {up}, {down}, {right}, {left}
        }, {
            {up, down}, {left, right}
        }, {
            {up, right}, {right, down}, {down, left}, {left, up}
        }, {
            {up, right, down}, {right, down, left}, {down, left, up}, {left, up, right}
        }, {
            {up, right, down, left}
        }
    };

    static int ans;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        ans = n * m + 1;
        cells = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                cells[i][j] = Integer.parseInt(st.nextToken());
                if (cells[i][j] != 0 && cells[i][j] != 6) {
                    locs.add(new Point(j, i));
                }
            }
        }
        dirs = new int[locs.size()];

        dfs(0);
        System.out.println(ans);
    }

    private static void dfs(int depth) {
        if (depth == dirs.length) {
            // 끝까지 채움
            simul();
            return;
        }
        Point loc = locs.get(depth);
        int camNum = cells[loc.y][loc.x];
        for (int i = 0; i < directions[camNum].length; i++) {
            dirs[depth] = i;
            dfs(depth + 1);
        }
    }

    private static void simul() {
        int[][] cellsCopy = new int[n][];
        for (int i = 0; i < n; i++) {
            cellsCopy[i] = Arrays.copyOf(cells[i], m);
        }
        // 각 모양에 맞게 색칠
        for (int i = 0; i < dirs.length; i++) {
            int dir = dirs[i];
            Point loc = locs.get(i);
            int y = loc.y;
            int x = loc.x;
            int camNum = cells[y][x];
            for (int[] d : directions[camNum][dir]) {
                int yy = y;
                int xx = x;
                yy += d[0];
                xx += d[1];
                while (0 <= yy && yy < n && 0 <= xx && xx < m) {
                    if (cellsCopy[yy][xx] == 6)
                        break;
                    else if (cellsCopy[yy][xx] == 0) {
                        cellsCopy[yy][xx] = 7;
                    }
                    yy += d[0];
                    xx += d[1];
                }
            }
        }
        int count0 = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                count0 += (cellsCopy[i][j]==0)? 1 : 0;
            }
        }
        ans = Math.min(ans, count0);
    }
}