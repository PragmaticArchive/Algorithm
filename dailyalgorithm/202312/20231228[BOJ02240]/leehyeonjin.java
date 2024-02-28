import java.io.*;
import java.util.*;

public class leehyeonjin {
	static int T, W;
	static int[][] trees;
	static int[][][] memo;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		T = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		trees = new int[2][T];
		for (int i = 0; i < T; i++) {
			int tree = Integer.parseInt(br.readLine());
			trees[tree-1][i] = 1;
		}

		// solution
		memo = new int[T][W+1][2];
		int res = Math.max(DFS(0, W, 0), DFS(0, W-1, 1));

		// output
		System.out.println(res);
	}

	static int DFS(int depth, int moveCnt, int position) {
		if (depth == T-1) {
			return trees[position][depth];
		}

		if (memo[depth][moveCnt][position] != 0) {
			return memo[depth][moveCnt][position];
		}

		int max = DFS(depth+1, moveCnt, position);
		if (moveCnt > 0) {
			max = Math.max(max, DFS(depth+1, moveCnt-1, (position+1)%2));
		}

		return memo[depth][moveCnt][position] = max + trees[position][depth];
	}
}
