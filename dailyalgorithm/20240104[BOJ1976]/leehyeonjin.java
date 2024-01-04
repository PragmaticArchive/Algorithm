import java.io.*;
import java.util.*;

public class leehyeonjin {
	static int N, M;
	static List<List<Integer>> graph = new ArrayList<>();
	static int[] plan;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// input
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				int c = Integer.parseInt(st.nextToken());
				if (c == 1) {
					graph.get(i).add(j);
					graph.get(j).add(i);
				}
			}
		}

		plan = new int[M];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) plan[i] = Integer.parseInt(st.nextToken());

		// solution
		String res = "YES";

		for (int i = 1; i < M; i++) {
			boolean possible = BFS(plan[i-1], plan[i]);
			if (!possible) {
				res = "NO";
				break;
			}
		}

		// output
		System.out.println(res);
	}

	static boolean BFS(int start, int end) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		queue.offer(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int now = queue.poll();
			if (now == end) {
				return true;
			}
			for (int next : graph.get(now)) {
				if (visited[next]) continue;
				queue.offer(next);
				visited[next] = true;
			}
		}

		return false;
	}
}
