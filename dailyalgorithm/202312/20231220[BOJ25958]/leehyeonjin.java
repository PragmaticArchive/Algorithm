import java.io.*;
import java.util.*;

public class leehyeonjin {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		// solution
		int[] dp = new int[M+1];
		dp[0] = 1;
		List<Integer> prettyNums = new ArrayList<>();
		for (int i = 1; i <= M; i++) {
			if (isPrettyNum(i)) {
				prettyNums.add(i);
			}
		}

		for (int i : prettyNums) {
			for (int j = 1; j <= M; j++) {
				// 인덱스 범위 검증
				if (j - i < 0) continue;
				dp[j] += dp[j-i];
				dp[j] %= K;
			}
		}

		// output
		System.out.println(dp[M]);
	}

	static boolean isPrettyNum(int num) {
		int sum = 0, temp = num;
		while (temp != 0) {
			sum += temp % 10;
			temp /= 10;
		}
		return num % sum == 0;
	}
}
