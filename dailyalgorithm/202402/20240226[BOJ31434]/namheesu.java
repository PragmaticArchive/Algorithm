import java.io.*;
import java.util.*;

public class Main {
	static class Button {
		int a, b;

		Button(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Button[] buttons = new Button[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			buttons[i] = new Button(a, b);
		}

		int[][] dp = new int[K + 1][100 * 50 + 1];
		for (int i = 0; i <= K; i++) {
			Arrays.fill(dp[i], -1);
		}
		dp[0][1] = 0;

		int ans = 0;
		for (int i = 0; i <= K; i++) {
			for (int j = 0; j <= 100 * 50; j++) {
				ans = Math.max(ans, dp[i][j]);
				if (dp[i][j] == -1 || i + 1 > K) {
					continue;
				}
				dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][j] + j);
				for (int k = 0; k < N; k++) {
					dp[i + 1][j + buttons[k].b] = Math.max(dp[i + 1][j + buttons[k].b], dp[i][j] - buttons[k].a);
				}
			}
		}
		System.out.println(ans);
	} // end of main
} // end of class
