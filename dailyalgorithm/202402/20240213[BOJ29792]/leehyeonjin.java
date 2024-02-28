import java.util.*;
import java.io.*;

public class leehyeonjin {
	static class Boss {
		long p;
		int q;

		public Boss(long p, int q) {
			this.p = p;
			this.q = q;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// input
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		long[] damages = new long[N];
		for (int i = 0; i < N; i++) {
			damages[i] = Long.parseLong(br.readLine());
		}

		Boss[] bosses = new Boss[K];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			long p = Long.parseLong(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			bosses[i] = new Boss(p, q);
		}

		// solution
		int[] results = new int[N];

		for (int i = 0; i < N; i++) { // 캐릭터 종류
			long damage = damages[i];
			int cnt = 60 * 15 + 1;

			int[] dp;
			int[] dpNext = new int[cnt];
			Arrays.fill(dpNext, -1);
			dpNext[0] = 0;

			for (int j = 0; j < K; j++) { // 보스 종류
				Boss boss = bosses[j];
				long cost = boss.p % damage == 0 ? boss.p / damage : boss.p / damage + 1;
				dp = Arrays.copyOf(dpNext, cnt);

				for (int t = 1; t < cnt; t++) { // 시간(15분)
					if (cost > 900) continue;
					if (t-cost >= 0 && dp[(int)(t-cost)] != -1) {
						dpNext[t] = Math.max(dpNext[t], dp[(int)(t-cost)] + boss.q);
					}
				}
			}

			int max = 0;
			for (int j = 0; j < cnt; j++) {
				max = Math.max(max, dpNext[j]);
			}
			results[i] = max;
		}

		Arrays.sort(results);
		int res = 0;
		for (int i = N-1; i >= 0; i--) {
			if (M == 0) break;
			res += results[i];
			M--;
		}

		// output
		System.out.println(res);
	}
}
