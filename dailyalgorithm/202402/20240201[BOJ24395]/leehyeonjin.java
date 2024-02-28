import java.util.*;
import java.io.*;

public class leehyeonjin {

	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// input
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[][] dp = new int[51][51];
		int[][] dpNext = new int[51][51];

		for (int j = 0; j <= 50; j++) {
			Arrays.fill(dpNext[j], -1);
		}
		dpNext[0][0] = 0;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int R = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());

			// solution
			// dp에 복사
			for (int k = 0; k <= 50; k++) {
				dp[k] = Arrays.copyOf(dpNext[k], 51);
			}

			// 갱신
			for (int r = 0; r <= 50; r++) {
				for (int b = 0; b <= 50; b++) {
					if (r-R < 0 || b-B < 0) continue;
					if (dp[r-R][b-B] == -1) continue;
					if (dpNext[r][b] < dp[r-R][b-B] + D) {
						dpNext[r][b] = dp[r-R][b-B] + D;
					}
				}
			}
		}

		PriorityQueue<int[]> results = new PriorityQueue<>((a, b) -> {
			if (a[1] == b[1]) return a[0] - b[0];
			else return a[1] - b[1];
		});
		for (int t = 1; t <= N; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			int R = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			results.offer(new int[] {t, dpNext[R][B] == -1 ? 0 : dpNext[R][B]});
		}

		// output
		while (!results.isEmpty()) {
			int[] result = results.poll();
			sb.append(result[0]).append(" ").append(result[1]).append("\n");
		}
		System.out.println(sb);
	}
}
