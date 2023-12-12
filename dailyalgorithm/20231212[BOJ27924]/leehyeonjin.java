import java.io.*;
import java.util.*;

public class leehyeonjin {
	static int N, yuni, dalgu, phonix;
	static List<List<Integer>> graph = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		N = Integer.parseInt(br.readLine());
		for(int i=0; i<=N; i++) graph.add(new ArrayList<>());
		for(int i=0; i<N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		yuni = Integer.parseInt(st.nextToken());
		dalgu = Integer.parseInt(st.nextToken());
		phonix = Integer.parseInt(st.nextToken());

		// solution
		boolean res = BFS();

		// output
		System.out.println(res ? "YES" : "NO");
	}

	static boolean BFS() {
		Queue<int[]> queue = new ArrayDeque<>();
		int[] visited = new int[N+1]; // 1: 윤이 2: 경찰
		queue.offer(new int[] {yuni, 1});
		queue.offer(new int[] {dalgu, -1});
		queue.offer(new int[] {phonix, -1});
		visited[yuni] = 1;
		visited[dalgu] = -1;
		visited[phonix] = -1;

		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			// 윤이가 이동할 차례
			if(now[1] == 1) {
				yuni = now[0];
				if(graph.get(yuni).size() == 1) return true;
				if(visited[yuni] == -1) continue;
				for(int next : graph.get(yuni)) {
					if(visited[next] != 0) continue;
					queue.offer(new int[] {next, 1});
					visited[next] = 1;
				}
			}
			// 경찰들이 이동할 차례
			else {
				int police = now[0];
				for(int next : graph.get(police)) {
					if(visited[next] == -1) continue;
					queue.offer(new int[] {next, -1});
					visited[next] = -1;
				}
			}
		}

		return false;
	}
}
