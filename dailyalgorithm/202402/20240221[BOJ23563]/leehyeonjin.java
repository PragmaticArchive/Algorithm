import java.util.*;
import java.io.*;

public class leehyeonjin {
	static int H, W;
	static char[][] map;
	static int sr, sc, er, ec;
	static int[][] visited;
	static final int INF = Integer.MAX_VALUE / 2;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// input
		st = new StringTokenizer(br.readLine(), " ");
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		map = new char[H][W];
		for (int i = 0; i < H; i++) {
			String line = br.readLine();
			for (int j = 0; j < W; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == 'S') {
					sr = i;
					sc = j;
				}
				else if (map[i][j] == 'E') {
					er = i;
					ec = j;
				}
			}
		}

		// solution
		int res = solution();

		// output
		System.out.println(res);
	}

	static int solution() {
		Deque<int[]> q = new LinkedList<>(); // 가중치가 0이면 offerFirst, 1이면 offerLast
		visited = new int[H][W];
		for (int i = 0; i < H; i++) {
			Arrays.fill(visited[i], INF);
		}
		q.offer(new int[] {sr, sc, 0}); // r, c, cost
		visited[sr][sc] = 0;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0], c = cur[1], cost = cur[2];
			if (r == er && c == ec) {
				return cost;
			}
			boolean nb = isNearBy(r, c); // 현재위치가 벽과 인접한가
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (!check(nr, nc) || map[nr][nc] == '#') continue;
				if (isNearBy(nr, nc) && nb) { // 벽타기 이동
					if (visited[nr][nc] <= cost) continue;
					q.offerFirst(new int[] {nr, nc, cost});
					visited[nr][nc] = cost;
				}
				else { // 그냥 이동
					if (visited[nr][nc] <= cost + 1) continue;
					q.offerLast(new int[] {nr, nc, cost + 1});
					visited[nr][nc] = cost + 1;
				}
			}
		}
		return -1;
	}

	static boolean isNearBy(int r, int c) {
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (!check(nr, nc)) continue;
			if (map[nr][nc] == '#') return true;
		}
		return false;
	}

	static boolean check(int r, int c) {
		return r >= 0 && r < H && c >= 0 && c < W;
	}
}
