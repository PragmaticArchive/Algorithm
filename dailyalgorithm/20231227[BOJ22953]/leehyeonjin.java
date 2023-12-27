import java.io.*;
import java.util.*;

public class leehyeonjin {
	static int N, K, C;
	static int[] times;
	static long res = Long.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// input
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		times = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) times[i] = Integer.parseInt(st.nextToken());

		// solution
		COMBINATIONS(0, 0);


		// output
		System.out.println(res);
	}

	static void COMBINATIONS(int depth, int start) {
		long time = PARAMETRIC_SEARCH(0, 1000000L*1000000L);
		res = Math.min(res, time);

		if (depth == C) {
			return;
		}

		for (int i = start; i < N; i++) {
			if (times[i] <= 1) continue;
			times[i] -= 1;
			COMBINATIONS(depth+1, i);
			times[i] += 1;
		}
	}

	static long PARAMETRIC_SEARCH(long l, long r) {
		while (l+1 < r) {
			long mid = (l + r) / 2;
			if (check(mid)) r = mid;
			else l = mid;
		}
		return r;
	}

	static boolean check(long total) {
		long cnt = 0;
		for (int i = 0; i < N; i++) {
			cnt += total / times[i];
		}
		return cnt >= K;
	}
}
