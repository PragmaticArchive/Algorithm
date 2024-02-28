import java.io.*;
import java.util.*;

public class leehyeonjin {
	static int N, M, A, B;
	static List<List<int[]>> graph = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}

		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.get(a).add(new int[] {b, c});
			graph.get(b).add(new int[] {a, c});
		}

		st = new StringTokenizer(br.readLine(), " ");
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		// solution
		int res = parametricSearch(0, 1000000001);

		// output
		System.out.println(res);
	}

	static int parametricSearch(int l, int r) {
		while(l+1 < r) {
			int mid = (l+r)/2;
			if(bfs(mid)) {
				l = mid;
			} else {
				r = mid;
			}
		}

		return l;
	}

	static boolean bfs(int mid) {
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N+1];
		queue.offer(A);
		visited[A] = true;

		while(!queue.isEmpty()) {
			int now = queue.poll();
			if(now == B) return true;
			for(int[] next : graph.get(now)) {
				if(next[1] < mid || visited[next[0]]) continue;
				queue.offer(next[0]);
				visited[next[0]] = true;
			}
		}

		return false;
	}
}
