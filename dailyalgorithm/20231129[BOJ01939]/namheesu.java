import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, factory1, factory2;
	private static ArrayList<Node>[] graph;
	private static boolean[] visited;
	private static Queue<Node> queue = new ArrayDeque<>();

	static class Node {
		int node;
		int weight;

		public Node(int node, int weight) {
			super();
			this.node = node;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N + 1];
		visited = new boolean[N + 1];

		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			graph[A].add(new Node(B, C));
			graph[B].add(new Node(A, C));
		}
		st = new StringTokenizer(br.readLine(), " ");
		factory1 = Integer.parseInt(st.nextToken());
		factory2 = Integer.parseInt(st.nextToken());

		findMaxWeight();
	} // end of main

	private static void findMaxWeight() {
		int left = 1;
		int right = 1000000000;
		int mid, ans = 0;
		while (left <= right) {
			mid = left + (right - left) / 2;
			Arrays.fill(visited, false);
			if (bfs(mid)) {
				ans = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		System.out.println(ans);
	}

	private static boolean bfs(int item) {
		visited[factory1] = true;
		queue.add(new Node(factory1, 0));
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			for (Node nxt : graph[cur.node]) {
				if (!visited[nxt.node] && item <= nxt.weight) {
					visited[nxt.node] = true;
					queue.add(nxt);
				}
			}
		}
		return visited[factory2];
	}
} // end of class
