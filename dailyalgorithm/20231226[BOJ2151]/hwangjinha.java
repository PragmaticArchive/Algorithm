package pragmatic.day231226;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj2151_거울_설치 {

    static int n;
    static char[][] cells;
    static Point[] doors;
    static int di = 0;

    static ArrayList<Point> placeable = new ArrayList<>();
    static int[] dijk;
    static boolean[] fixed;
    static PriorityQueue<int[]> shortest = new PriorityQueue<>((a, b) -> {
        return a[0] - b[0];
    });

    static final int INF = Integer.MAX_VALUE / 2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        doors = new Point[2];
        for (int i = 0; i < 2; i++) doors[i] = new Point(0,0);

        n = Integer.parseInt(br.readLine());
        cells = new char[n][];
        for (int i = 0; i < n; i++) {
            cells[i] = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                if (cells[i][j] == '#') {
                    if (di == 0) cells[i][j] = ',';
                    doors[di].y = i;
                    doors[di++].x = j;
                } else if (cells[i][j] == '!') {
                    cells[i][j] = (char) ('0' + placeable.size());
                    placeable.add(new Point(j, i));
                }
            }
        }

        dijk = new int[placeable.size() + 2];
        fixed = new boolean[placeable.size() + 2];
        Arrays.fill(dijk, INF);

        dijk[placeable.size()] = 0;
        fixed[placeable.size()] = true;
        for (int i = 0; i < 4; i++) {
            for (int node : getNodeDir(doors[0], i)) {
                shortest.add(new int[] {0, node, (i + 2) % 4});
                dijk[node] = 0;
            }
        }



        while (!shortest.isEmpty()) {
            int[] node = shortest.poll();
            if (node[1] == placeable.size() + 1) {
                System.out.println(node[0]);
                break;
            }
            if (fixed[node[1]]) continue;
            fixed[node[1]] = true;

            for (int d : new int[] {(node[2] + 1) % 4, (node[2] + 3) % 4}) {
                for (int v : getNodeDir(placeable.get(node[1]), d)) {
                    shortest.add(new int[] {node[0] + 1, v, d});
                }
            }
        }

    }

    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static ArrayList<Integer> getNodeDir(Point node, int dir) {
        ArrayList<Integer> ret = new ArrayList<>();

        int y = node.y + dy[dir];
        int x = node.x + dx[dir];
        while (0 <= y && y < n && 0 <= x && x < n) {
            if (cells[y][x] == '*') break;
            else if (cells[y][x] == '#') ret.add(placeable.size() + 1);
            else if (cells[y][x] != '.') {
                ret.add(cells[y][x] - '0');
            }
            y += dy[dir];
            x += dx[dir];
        }
        return ret;
    }
}