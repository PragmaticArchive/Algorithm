import java.io.*;
import java.util.*;

public class Main {
	static final int[] HY = { -2, -2, -1, 1, 2, 2, 1, -1 };
	static final int[] HX = { -1, 1, 2, 2, 1, -1, -2, -2 };
	static final int[] DY = { -1, 0, 1, 0 };
	static final int[] DX = { 0, 1, 0, -1 };
	static int W, H, K;
	static int[][] map;
	static boolean[][][] visited;

	static class Monkey {
		int y;
		int x;
		int k;
		int cnt;

		public Monkey(int y, int x, int k, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.k = k;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H + 2][W + 2];
		visited = new boolean[H + 2][W + 2][K+1];
		for (int i = 1; i <= H; i++) {
			String str = br.readLine();
			for (int j = 1, inx = 0; j <= W; j++, inx += 2) {
				map[i][j] = str.charAt(inx) - '0';
			}
		}
		bfs();
	} // end of main

	public static void bfs() {
		Queue<Monkey> queue = new ArrayDeque<Monkey>();
		queue.offer(new Monkey(1, 1, 0, 0));
		visited[1][1][0] = true;
		while (!queue.isEmpty()) {
			Monkey cur = queue.poll();
			if (cur.y == H && cur.x == W) {
				System.out.println(cur.cnt);
				return;
			}
			if (cur.k < K) {
				for (int i = 0; i < 8; i++) {
					int ny = cur.y + HY[i];
					int nx = cur.x + HX[i];
					if (movable(ny, nx,cur.k+1)) {
						visited[ny][nx][cur.k+1] = true;
						queue.offer(new Monkey(ny, nx, cur.k + 1, cur.cnt + 1));
					}
				}
			}
			for (int i = 0; i < 4; i++) {
				int ny = cur.y + DY[i];
				int nx = cur.x + DX[i];
				if (movable(ny, nx,cur.k)) {
					visited[ny][nx][cur.k] = true;
					queue.offer(new Monkey(ny, nx, cur.k, cur.cnt + 1));
				}
			}
		}
		System.out.println(-1);
	}

	private static boolean movable(int y, int x, int k) {
		return 1 <= y && y <= H && 1 <= x && x <= W && map[y][x] == 0 && !visited[y][x][k];
	}
} // end of class
