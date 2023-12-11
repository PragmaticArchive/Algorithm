import java.io.*;
import java.util.*;

public class leehyeonjin {
	static final int DIV = 1000000009;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		int N = Integer.parseInt(br.readLine());

		// solution
		long[] dp = new long[N+1];
		dp[1] = 1;

		for(int i=2; i<=N; i++) {
			dp[i] = (2 * i -1) * dp[i-1] % DIV;
		}

		// output
		System.out.println(dp[N]);
	}
}
