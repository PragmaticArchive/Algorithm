import java.util.*;
import java.io.*;

public class leehyeonjin {
	static int N, K;
	static int[] times, inBounds;
	static List<List<Integer>> graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// input
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			times = new int[N+1];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= N; i++) times[i] = Integer.parseInt(st.nextToken());

			graph = new ArrayList<>();
			inBounds = new int[N+1];
			for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				graph.get(x).add(y);
				inBounds[y]++;
			}

			int dest = Integer.parseInt(br.readLine());

			// solution
			int res = BFS(dest);

			// output
			sb.append(res).append("\n");
		}

		System.out.println(sb);
	}

	public static int BFS(int dest) {
		Queue<Integer> queue = new LinkedList<>();
		int[] dp = new int[N+1];

		for (int i = 1; i <= N; i++) {
			if (inBounds[i] == 0) {
				queue.offer(i);
				dp[i] = times[i];
			}
		}

		while (!queue.isEmpty()) {
			int now = queue.poll();
			if (now == dest) {
				return dp[dest];
			}
			for (int next : graph.get(now)) {
				inBounds[next]--;
				dp[next] = Math.max(dp[next], dp[now]+times[next]); // 시작시점 최댓값으로 갱신
				if (inBounds[next] == 0) {
					queue.offer(next);
				}
			}
		}

		return 0;
	}
}
