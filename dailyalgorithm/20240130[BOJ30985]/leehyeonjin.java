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

	static int N, M, K;
	static List<List<Pos>> graph = new ArrayList<>();
	static long[] elCost;
	static final long INF = Long.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// input
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			graph.get(u).add(new Pos(v, c));
			graph.get(v).add(new Pos(u, c));
		}

		elCost = new long[N+1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			elCost[i] = Long.parseLong(st.nextToken());
		}

		// solution
		long[] oneToN = DIJKSTRA(1);
		long[] NToOne = DIJKSTRA(N);

		long res = INF;
		for (int i = 1; i <= N; i++) {
			if (oneToN[i] == INF) continue;
			if (NToOne[i] == INF) continue;
			if (elCost[i] == -1) continue;
			long cost = oneToN[i] + NToOne[i] + (K - 1) * elCost[i];
			res = Math.min(res, cost);
		}

		// output
		System.out.println(res == INF ? -1 : res);
	}

	static long[] DIJKSTRA(int s) {
		PriorityQueue<Pos> pq = new PriorityQueue<>((a, b) -> a.cost < b.cost ? -1 : 1);
		long[] dist = new long[N+1];
		Arrays.fill(dist, INF);
		pq.offer(new Pos(s, 0L));
		dist[s] = 0L;

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
