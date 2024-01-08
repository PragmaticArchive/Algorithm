import java.io.*;
import java.util.*;

public class leehyeonjin {
	static int N, M, K;
	static int[][] order;
	static int[][][] memo;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// input
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		order = new int[N][2];
		for (int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			order[i][0] = x;
			order[i][1] = y;
		}

		// solution
		memo = new int[N+1][M+1][K+1];
		int res = DFS(0, M, K);

		// output
		System.out.println(res);
	}

	static int DFS(int depth, int cntBurger, int cntFries) {
		if (depth == N) {
			return 0;
		}

		if (memo[depth][cntBurger][cntFries] != 0) {
			return memo[depth][cntBurger][cntFries];
		}

		int cnt = DFS(depth+1, cntBurger, cntFries);
		if (check(cntBurger, cntFries, order[depth][0], order[depth][1])) {
			cnt = Math.max(cnt, DFS(depth+1, cntBurger-order[depth][0], cntFries-order[depth][1]) + 1);
		}

		return memo[depth][cntBurger][cntFries] = cnt;
	}

	static boolean check(int cntBurger, int cntFries, int burger, int fries) {
		return (cntBurger - burger >= 0) && (cntFries - fries >= 0);
	}
}
