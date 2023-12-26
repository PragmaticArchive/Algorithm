import java.io.*;
import java.util.*;

public class leehyeonjin {
	static int N;
	static char[][] map;
	static int[][] directions = { {3, 2, 1, 0}, {1, 0, 3, 2} }; // 0:오른쪽위, 1:왼쪽위 | ↑, ←, ↓, →
	static int[] dirR = {-1, 0, 1, 0}; // ↑, ←, ↓, →
	static int[] dirC = {0, -1, 0, 1}; // ↑, ←, ↓, →

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		int sr = 0, sc = 0;
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			String in = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = in.charAt(j);
				if (map[i][j] == '#') {
					sr = i;
					sc = j;
				}
			}
		}

		// solution
		int res = BFS(sr, sc);

		// output
		System.out.println(res);
	}

	static int BFS(int sr, int sc) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		for (int d = 0; d < 4; d++) {
			queue.offer(new int[] {sr, sc, d, 0}); // 현재행, 현재열, 현재방향, 느낌표_만난개수
		}
		visited[sr][sc] = true;

		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int r = now[0], c = now[1], d = now[2], cnt = now[3];

			int nr = r + dirR[d], nc = c + dirC[d];
			while (true) {
				if (!(nr >= 0 && nr < N && nc >= 0 && nc < N)) {
					break;
				}
				if (!(nr == sr && nc == sc) && map[nr][nc] == '#') {
					return cnt;
				}
				else if (map[nr][nc] == '*') {
					break;
				}
				else if (map[nr][nc] == '!') {
					queue.offer(new int[] {nr, nc, d, cnt});
					queue.offer(new int[] {nr, nc, directions[0][d], cnt+1});
					queue.offer(new int[] {nr, nc, directions[1][d], cnt+1});
				}
				nr += dirR[d];
				nc += dirC[d];
			}
		}

		return 0; // 더미
	}
}
