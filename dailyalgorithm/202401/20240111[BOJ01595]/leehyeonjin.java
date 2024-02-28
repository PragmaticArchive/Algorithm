import java.util.*;
import java.io.*;

public class leehyeonjin {

	static class Node {
		int idx, cost;

		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}
	static List<List<Node>> graph = new ArrayList<>();
	static final int MAX = Integer.MAX_VALUE / 2;
	static int n = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i <= 10001; i++) graph.add(new ArrayList<>());

		String in;
		while ((in = br.readLine()) != null && !in.equals("")) {
			// input
			StringTokenizer st = new StringTokenizer(in, " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph.get(a).add(new Node(b, cost));
			graph.get(b).add(new Node(a, cost));

			n = Math.max(Math.max(n, a), b);
		}

		// solution
		if (n == 0) {
			System.out.println(0);
			return;
		}

		// 1. 무작위 노드에서 가장 먼 거리의 노드를 구한다
		int[] start = DIJKSTRA(1);
		int maxDist = Integer.MIN_VALUE;
		int maxIdx = 1;
		for (int i = 1; i <= n; i++) {
			if (start[i] >= MAX) continue;
			if (maxDist < start[i]) {
				maxIdx = i;
				maxDist = start[i];
			}
		}

		// 2. 노드에서 가장 먼 노드에서(maxIdx) 노드의 반대편의 가장 먼 노드까지의 거리를 구한다
		int[] results = DIJKSTRA(maxIdx);
		int res = Integer.MIN_VALUE;
		for (int i = 1; i <= n; i++) {
			if (results[i] >= MAX) continue;
			res = Math.max(res, results[i]);
		}

		// output
		System.out.println(res);
	}

	static int[] DIJKSTRA(int start) {
		PriorityQueue<Node> queue = new PriorityQueue<>((a, b) -> a.cost - b.cost);
		int[] dist = new int[10001];
		Arrays.fill(dist, MAX);
		queue.offer(new Node(start, 0));
		dist[start] = 0;

		while (!queue.isEmpty()) {
			Node now = queue.poll();
			if (now.cost < dist[now.idx]) {
				continue;
			}
			for (Node next : graph.get(now.idx)) {
				if (now.cost + next.cost < dist[next.idx]) {
					dist[next.idx] = now.cost + next.cost;
					queue.offer(new Node(next.idx, dist[next.idx]));
				}
			}
		}

		return dist;
	}
}
