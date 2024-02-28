import java.util.*;
import java.io.*;

public class leehyeonjin {
	static int N, X;
	static int[] cost;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// input
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		cost = new int[N+1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}

		// solution
		int res = PARAMETRIC_SEARCH(0, 100000);

		// output
		System.out.println(res);
	}

	static int PARAMETRIC_SEARCH(int l, int r) {
		while (l+1 != r) {
			int m = (l + r) / 2; // 공정라인개수
			if (check(m)) r = m;
			else l = m;
		}
		return r;
	}

	static boolean check(int m) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < m; i++) pq.offer(0);
		for (int i = 1; i <= N; i++) {
			Integer min = pq.poll();
			pq.offer(min + cost[i]);
		}

		int max = 0;
		while (!pq.isEmpty()) {
			max = pq.poll();
		}

		if (max <= X) return true;
		else return false;
	}
}
