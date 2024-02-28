import java.io.*;
import java.util.*;

public class leehyeonjin {
	static int V, E, P;
	static List<List<Node>> graph = new ArrayList<>();
	static final int INF = Integer.MAX_VALUE / 2;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// input
		st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= V; i++) graph.add(new ArrayList<>());
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Node(b, c));
			graph.get(b).add(new Node(a, c));
		}

		// solution
		StringBuilder sb = new StringBuilder();

		int total = DIJKSTRA(1, V);
		int fromStartToP = DIJKSTRA(1, P);
		int fromPToEnd = DIJKSTRA(P, V);

		if (fromStartToP + fromPToEnd != total) sb.append("GOOD BYE");
		else sb.append("SAVE HIM");

		// output
		System.out.println(sb);
	}

	static int DIJKSTRA(int start, int end) {
		PriorityQueue<Node> queue = new PriorityQueue<>((a, b) -> a.cost - b.cost);
		int[] dist = new int[V+1];
		Arrays.fill(dist, INF);
		queue.offer(new Node(start, 0));
		dist[start] = 0;

		while (!queue.isEmpty()) {
			Node now = queue.poll();
			if (now.cost > dist[now.idx]) {
				continue;
			}
			for (Node next : graph.get(now.idx)) {
				if (next.cost + dist[now.idx] < dist[next.idx]) {
					dist[next.idx] = next.cost + dist[now.idx];
					queue.offer(new Node(next.idx, dist[next.idx]));
				}
			}
		}

		return dist[end];
	}

	static class Node {
		int idx, cost;

		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}
}
