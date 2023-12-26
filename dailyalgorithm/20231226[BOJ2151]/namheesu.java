import java.io.*;
import java.util.*;

public class Main {
    static final int[] DY = {-1, 0, 1, 0};
    static final int[] DX = {0, 1, 0, -1};
    static int N;
    static char[][] map;

    static class Point implements Comparable<Point> {
        int y;
        int x;
        int dir;
        int cnt;

        public Point(int y, int x, int dir, int cnt) {
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Point o) {
            return cnt - o.cnt;
        }
    }

    static int[][] doors = new int[2][2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String st;
        N = Integer.parseInt(br.readLine());
        map = new char[N + 2][N + 2];
        int doorInx = 0;
        for (int i = 1; i <= N; i++) {
            st = br.readLine();
            for (int j = 1, inx = 0; j <= N; j++, inx++) {
                map[i][j] = st.charAt(inx);
                if (map[i][j] == '#') {
                    doors[doorInx][0] = i;
                    doors[doorInx++][1] = j;
                }
            }
        }
        bfs();
    } // end of main

    private static void bfs() {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        boolean[][][] visited = new boolean[N + 2][N + 2][4];
        for (int i = 0; i < 4; i++) {
            pq.offer(new Point(doors[0][0], doors[0][1], i, 0));
        }
        while (!pq.isEmpty()) {
            Point cur = pq.poll();
            visited[cur.y][cur.x][cur.dir]=true;
            if (cur.y == doors[1][0] && cur.x == doors[1][1]) {
                System.out.println(cur.cnt);
                return;
            }
            int ny = cur.y + DY[cur.dir];
            int nx = cur.x + DX[cur.dir];
            if(1<=ny&&ny<=N&&1<=nx&&nx<=N&&!visited[ny][nx][cur.dir]&&map[ny][nx]!='*'){
                if(map[ny][nx]=='!'){
                    pq.offer(new Point(ny, nx, (cur.dir+3)%4, cur.cnt + 1));
                    pq.offer(new Point(ny, nx, (cur.dir+1)%4, cur.cnt + 1));
                }
                pq.offer(new Point(ny, nx, cur.dir, cur.cnt));

            }
        }
    }
} // end of class
