import java.util.*;
import java.io.*;

public class leehyeonjin {

	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int N, M;
	static char[][] map;
	static List<Pos> destinations = new ArrayList<>();
	static Pos hanbyeol;
	static List<Pos> gifts = new ArrayList<>();
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static final int INF = Integer.MAX_VALUE / 2;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// input
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		for (int r = 0; r < N; r++) {
			String input = br.readLine();
			for (int c = 0; c < M; c++) {
				map[r][c] = input.charAt(c);
				if (map[r][c] == '#') {
					destinations.add(new Pos(r, c));
				}
				else if (map[r][c] == 'H') {
					hanbyeol = new Pos(r, c);
				}
				else if (map[r][c] == 'P') {
					gifts.add(new Pos(r, c));
				}
			}
		}

		// solution
		int res = 0;
		for (Pos destination : destinations) {
			int cnt = BFS(destination);
			res = Math.max(res, cnt);
		}

		// output
		System.out.println(res);
	}

	static int BFS(Pos start) {
		Queue<int[]> q = new LinkedList<>();
		int[][] distance = new int[N][M];
		for (int r = 0; r < N; r++) {
			Arrays.fill(distance[r], INF);
		}
		q.offer(new int[] {start.r, start.c, 0});
		distance[start.r][start.c] = 0;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int r = curr[0], c = curr[1], cost = curr[2];

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (!check(nr, nc)) continue;
				if (cost+1 < distance[nr][nc]) {
					q.offer(new int[] {nr, nc, cost+1});
					distance[nr][nc] = cost+1;
				}
			}
		}

		int cnt = 0;
		int hanbyeolCost = distance[hanbyeol.r][hanbyeol.c];
		for (Pos gift : gifts) {
			if (hanbyeolCost == INF) return 0;
			if (distance[gift.r][gift.c] <= hanbyeolCost) {
				cnt++;
			}
		}

		return cnt;
	}

	static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M && map[r][c] != 'X';
	}
}
