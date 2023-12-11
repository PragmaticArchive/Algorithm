import java.io.*;
import java.util.*;

public class leehyeonjin {
	static int K, W, H;
	static int[][] map;
	static int[] dirR = {-1, 0, 1, 0, -2, -1, 1, 2, -2, -1, 1, 2}; //↑, ←, ↓, →, ↑←, ↑←, ↓←, ↓←, ↑←, ↑←, ↓←, ↓←
	static int[] dirC = {0, -1, 0, 1, -1, -2, -2, -1, 1, 2, 2, 1}; //↑, ←, ↓, →, ↑←, ↑←, ↓←, ↓←, ↑←, ↑←, ↓←, ↓←

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		for(int r=0; r<H; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c=0; c<W; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// solution
		int res = BFS();

		// output
		System.out.println(res);
	}

	static int BFS() {
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[][][] visited = new boolean[K + 1][H][W]; //점프횟수 행 열
		queue.offer(new int[] {0, 0, 0, 0}); //행 열 카운트 점프횟수
		visited[0][0][0] = true;

		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int r = now[0], c = now[1], move = now[2], jump = now[3];
			if (r == H - 1 && c == W - 1) {
				return move;
			}
			// 인접한 곳으로 이동
			for (int d = 0; d < 4; d++) {
				int nr = r + dirR[d];
				int nc = c + dirC[d];
				if (!(nr >= 0 && nr < H && nc >= 0 && nc < W) || visited[jump][nr][nc] || map[nr][nc] == 1)
					continue;
				queue.offer(new int[] {nr, nc, move + 1, jump});
				visited[jump][nr][nc] = true;
			}
			if (jump >= K) continue;
			// 점프
			for (int d = 4; d < 12; d++) {
				int nr = r + dirR[d];
				int nc = c + dirC[d];
				if (!(nr >= 0 && nr < H && nc >= 0 && nc < W) || visited[jump + 1][nr][nc] || map[nr][nc] == 1)
					continue;
				queue.offer(new int[] {nr, nc, move + 1, jump + 1});
				visited[jump + 1][nr][nc] = true;
			}

		}

		return -1;
	}
}
