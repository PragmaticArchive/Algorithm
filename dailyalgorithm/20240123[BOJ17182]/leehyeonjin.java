import java.util.*;
import java.io.*;

public class leehyeonjin {

	static int[][] map;
	static int N, K;
	static final int INF = Integer.MAX_VALUE / 2;
	static int[][] dist;
	static boolean[] visited;
	static int res = INF;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// input
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// solution
		// 플로이드-워셜
		dist = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dist[i], INF);
			for (int j = 0; j < N; j++) {
				if (i == j) continue;
				dist[i][j] = map[i][j];
			}
		}

		for (int via = 0; via < N; via++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][via] + dist[via][j]);
				}
			}
		}

		// 백트래킹
		visited = new boolean[N];
		visited[K] = true;
		DFS(K, 0);

		// output
		System.out.println(res);
	}

	static void DFS(int curr, int cost) {
		if (check()) {
			res = Math.min(res, cost);
			return;
		}

		for (int next = 0; next < N; next++) {
			if (visited[next]) continue;
			visited[next] = true;
			DFS(next, cost + dist[curr][next]);
			visited[next] = false;
		}
	}

	static boolean check() {
		for (int i = 0; i < N; i++) {
			if (!visited[i]) return false;
		}
		return true;
	}
}
