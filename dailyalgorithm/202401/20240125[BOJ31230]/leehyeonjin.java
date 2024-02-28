import java.util.*;
import java.io.*;

public class leehyeonjin {

	static class Pos {
		int idx;
		long cost;
		public Pos(int idx, long cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}

	static int N, M, A, B;
	static List<List<Pos>> graph = new ArrayList<>();
	static final long INF = Long.MAX_VALUE / 2;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// input
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Pos(b, c));
			graph.get(b).add(new Pos(a, c));
		}

		// solution
		long[] fromA = DIJKSTRA(A);
		long[] fromB = DIJKSTRA(B);

		long minDist = fromA[B];
		List<Integer> res = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			if (fromA[i] + fromB[i] == minDist) {
				res.add(i);
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(res.size()).append("\n");
		for (int i = 0; i < res.size(); i++) {
			sb.append(res.get(i)).append(" ");
		}

		// output
		System.out.println(sb);
	}

	static long[] DIJKSTRA(int s) {
		PriorityQueue<Pos> pq = new PriorityQueue<>((a, b) -> Long.compare(a.cost, b.cost));
		long[] dist = new long[N+1];
		Arrays.fill(dist, INF);
		pq.offer(new Pos(s, 0));
		dist[s] = 0;

		while (!pq.isEmpty()) {
			Pos curr = pq.poll();
			if (curr.cost > dist[curr.idx]) {
				continue;
			}
			for (Pos next : graph.get(curr.idx)) {
				if (dist[curr.idx] + next.cost < dist[next.idx]) {
					dist[next.idx] = dist[curr.idx] + next.cost;
					pq.offer(new Pos(next.idx, dist[next.idx]));
				}
			}
		}

		return dist;
	}
}
