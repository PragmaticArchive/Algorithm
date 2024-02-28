package pragmatic.day231211;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj1600_말이되고픈원숭이 {
    static int time;
    static int w, h;
    static int[][] cells;
    static int[][][] counts;
    static boolean[][][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        time = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        cells = new int[h][w];
        counts = new int[time + 1][h][w];
        visited = new boolean[time + 1][h][w];
        for (int i = 0; i < time + 1; i++) {
            for (int j = 0; j < h; j++) {
                Arrays.fill(counts[i][j], Integer.MAX_VALUE);
            }
        }

        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                cells[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < time+1; i++) {
            ans = Math.min(ans, counts[i][h-1][w-1]);
        }
        System.out.println(ans==Integer.MAX_VALUE?-1:ans);
    }

    static int[] dy = {0, 1, 0, -1, -2, -2, -1, -1, 1, 1, 2, 2};
    static int[] dx = {1, 0, -1, 0, -1, 1, -2, 2, -2, 2, -1, 1};
    private static void bfs() {
        Deque<Point> q = new LinkedList<>();
        q.add(new Point(0, 0));
        Deque<Integer> jump = new LinkedList<>();
        Deque<Integer> count = new LinkedList<>();
        count.add(0);
        jump.add(0);
        counts[0][0][0] = 0;

        int y, x, j, c;
        while (!q.isEmpty()) {
            y = q.peek().y;
            x = q.peek().x;
            j = jump.poll();
            c = count.poll();
            q.poll();
            if (c > counts[j][y][x] || visited[j][y][x])
                continue;
            visited[j][y][x] = true;

            for (int i = 0; i < 4; i++) {
                int yy = y + dy[i];
                int xx = x + dx[i];
                if (yy < 0 || h <= yy || xx < 0 || w <= xx)
                    continue;
                if (cells[yy][xx] == 1 || counts[j][yy][xx] < counts[j][y][x] + 1 || visited[j][yy][xx])
                    continue;
                q.add(new Point(xx, yy));
                jump.add(j);
                count.add(c + 1);
                counts[j][yy][xx] = counts[j][y][x] + 1;
            }

            if (j == time)
                continue;
            for (int i = 4; i < dy.length; i++) {
                int yy = y + dy[i];
                int xx = x + dx[i];
                if (yy < 0 || h <= yy || xx < 0 || w <= xx)
                    continue;
                if (cells[yy][xx] == 1 || counts[j+1][yy][xx] < counts[j][y][x] + 1 || visited[j + 1][yy][xx])
                    continue;
                q.add(new Point(xx, yy));
                jump.add(j + 1);
                count.add(c + 1);
                counts[j + 1][yy][xx] = counts[j][y][x] + 1;
            }
        }
    }
}