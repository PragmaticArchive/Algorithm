import java.util.*;
import java.io.*;

public class leehyeonjin {
	static int N;
	static int[] wei, sum;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		wei = new int[N];
		sum = new int[N+1];
		for (int i = 0; i < N; i++) {
			wei[i] = Integer.parseInt(st.nextToken());
			sum[i+1] = sum[i] + wei[i];
		}

		// solution
		int min = Integer.MAX_VALUE;
		int res = Integer.MIN_VALUE;

		for (int l = 1; l < N; l++) {
			for (int r = l+1; r <= N; r++) {
				int offset = sum[l-1];
				int idx = LOWER_BOUND(l, r, (sum[r]-offset)/2);
				int diff = Math.abs((sum[idx]-offset) - (sum[r]-sum[idx]));
				if (min > diff) {
					min = diff;
					res = sum[r] - offset;
				}
				else if (min == diff) {
					res = Math.max(res, sum[r] - offset);
				}
			}
		}


		// output
		System.out.println(res);
	}

	static int LOWER_BOUND(int l, int r, int target) {
		int offset = sum[l-1];
		while (l < r) {
			int m = (l + r) / 2;
			if (sum[m]-offset < target) l = m + 1;
			else r = m;
		}
		if (r != target) {
			int diffL = Math.abs(target - (sum[r-1]-offset));
			int diffR = Math.abs(target - (sum[r]-offset));
			return diffL < diffR ? r-1 : r;
		}
		return r;
	}
}
