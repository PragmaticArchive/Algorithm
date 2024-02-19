import java.util.*;
import java.io.*;

public class leehyeonjin {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// input
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] candies = new int[N];
		int max = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			candies[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, candies[i]);
		}

		// solution
		if (K > max * M) { // 얻을 수 있는 사탕의 최대 개수보다 동생이 많은 경우 -> 하나도 가져갈 수 없음
			System.out.println(0);
			return;
		}

		int[] dp = new int[max * M + 1];
		final int INF = Integer.MAX_VALUE / 2;
		Arrays.fill(dp, INF);
		dp[0] = 0;
		int res = 0;

		for (int i = 0; i < N; i++) {
			int candy = candies[i];
			for (int j = 1; j <= max * M; j++) {
				if (j - candy < 0) continue;
				if (dp[j - candy] >= M) continue;
				if (j - candy >= 0 && dp[j - candy] != INF) {
					dp[j] = Math.min(dp[j], dp[j - candy] + 1);
					if (j % K == 0) {
						res = Math.max(res, j);
					}
				}
			}
		}

		// output
		System.out.println(res);
	}
}
