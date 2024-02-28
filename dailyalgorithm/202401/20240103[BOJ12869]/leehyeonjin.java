import java.io.*;
import java.util.*;

public class leehyeonjin {
	static int N;
	static int[] hp = new int[3];
	static int[][][] memo = new int[61][61][61];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) hp[i] = Integer.parseInt(st.nextToken());

		// solution
		int res = DFS(hp[0], hp[1], hp[2]);

		// output
		System.out.println(res);
	}

	static int DFS(int a, int b, int c) {
		if (a <= 0 && b <= 0 && c <= 0) {
			return 0;
		}

		if (a < 0) a = 0;
		if (b < 0) b = 0;
		if (c < 0) c = 0;

		if (memo[a][b][c] != 0) {
			return memo[a][b][c];
		}

		int min = Integer.MAX_VALUE;
		min = Math.min(min, DFS(a-1, b-3, c-9));
		min = Math.min(min, DFS(a-1, b-9, c-3));
		min = Math.min(min, DFS(a-3, b-1, c-9));
		min = Math.min(min, DFS(a-3, b-9, c-1));
		min = Math.min(min, DFS(a-9, b-1, c-3));
		min = Math.min(min, DFS(a-9, b-3, c-1));

		return memo[a][b][c] = min + 1;
	}
}
